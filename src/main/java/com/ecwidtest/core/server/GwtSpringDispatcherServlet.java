package com.ecwidtest.core.server;

import com.google.gwt.user.client.rpc.RemoteService;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;

import javax.servlet.ServletException;

/**
 * @author d.sapaev
 */
public class GwtSpringDispatcherServlet extends DispatcherServlet {
  protected DelegatingRemoteServiceHandler remoteServiceHandler = new DelegatingRemoteServiceHandler();

  @Override
  protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
    HandlerAdapter adapter = super.getHandlerAdapter(handler);
    return handler instanceof RemoteService ?
        new GwtHandlerAdapterWrapper(adapter, remoteServiceHandler, getServletContext()) : adapter;
  }
}
