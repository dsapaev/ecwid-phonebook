package com.ecwidtest.core.server;

import com.google.gwt.user.client.rpc.RemoteService;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author d.sapaev
 */
public class GwtHandlerAdapterWrapper implements HandlerAdapter {
  private static final String GWT_RPC_CONTENT_TYPE = "text/x-gwt-rpc";

  private DelegatingRemoteServiceHandler remoteServiceHandler;
  private HandlerAdapter adapter;
  private ServletContext servletContext;

  public GwtHandlerAdapterWrapper(HandlerAdapter adapter, DelegatingRemoteServiceHandler remoteServiceHandler,
                                  ServletContext servletContext) {
    this.adapter = adapter;
    this.remoteServiceHandler = remoteServiceHandler;
    this.servletContext = servletContext;
  }

  public long getLastModified(HttpServletRequest request, Object handler) {
    return -1;
  }

  public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (isGwtRpcRequest(request)) {
      remoteServiceHandler.doPost(request, response, handler, servletContext);
      return null;
    } else {
      return adapter.handle(request, response, handler);
    }
  }

  public boolean supports(Object handler) {
    return handler instanceof RemoteService;
  }

  protected boolean isGwtRpcRequest(HttpServletRequest request) {
    String contentType = request.getContentType();
    boolean gwtRpcContentType = false;

    if (contentType != null) {
      contentType = contentType.toLowerCase();
      /*
       * NOTE:We use startsWith because some servlet engines, i.e. Tomcat, do
       * not remove the charset component but others do.
       */
      if (contentType.startsWith(GWT_RPC_CONTENT_TYPE.toLowerCase())) {
        gwtRpcContentType = true;
      }
    }

    return gwtRpcContentType;
  }
}
