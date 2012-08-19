package com.ecwidtest.core.server;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author d.sapaev
 */
public class DelegatingRemoteServiceHandler extends RemoteServiceServlet {
  private transient ThreadLocal<Object> perThreadDelegate;
  private transient ThreadLocal<ServletContext> perThreadServletContext;

  private Object getDelegate() {
    synchronized (this) {
      validateThreadLocalData();
      return perThreadDelegate.get();
    }
  }

  @Override
  public ServletContext getServletContext() {
    synchronized (this) {
      validateThreadLocalData();
      return perThreadServletContext.get();
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response, Object delegate, ServletContext servletContext) {
    try {
      // Store delegate in thread-local storage.
      synchronized (this) {
        validateThreadLocalData();
        perThreadDelegate.set(delegate);
        perThreadServletContext.set(servletContext);
      }

      doPost(request, response);
    } finally {
      // null the thread-locals to avoid holding delegate
      perThreadDelegate.set(null);
    }
  }

  private void validateThreadLocalData() {
    if (perThreadDelegate == null) {
      perThreadDelegate = new ThreadLocal<Object>();
    }
    if (perThreadServletContext == null) {
      perThreadServletContext = new ThreadLocal<ServletContext>();
    }
  }

  @Override
  public String processCall(String payload) throws SerializationException {
    // First, check for possible XSRF situation
    checkPermutationStrongName();

    try {
      Object delegate = getDelegate();
      if (delegate == null) {
        throw new IncompatibleRemoteServiceException("Delegate was not specified");
      }

      RPCRequest rpcRequest = RPC.decodeRequest(payload, delegate.getClass(), this);
      onAfterRequestDeserialized(rpcRequest);
      return RPC.invokeAndEncodeResponse(delegate, rpcRequest.getMethod(),
          rpcRequest.getParameters(), rpcRequest.getSerializationPolicy(),
          rpcRequest.getFlags());
    } catch (IncompatibleRemoteServiceException ex) {
      log("An IncompatibleRemoteServiceException was thrown while processing this call.", ex);
      return RPC.encodeResponseForFailure(null, ex);
    } catch (RpcTokenException tokenException) {
      log("An RpcTokenException was thrown while processing this call.", tokenException);
      return RPC.encodeResponseForFailure(null, tokenException);
    }
  }
}
