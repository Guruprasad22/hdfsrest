package com.playground.httpfs.utility;

/**
 * Hello world!
 *
 */
import java.net.ServerSocket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
 
public class JettyRunner {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        int port = -1;
        try {
        	ServerSocket socket= new ServerSocket(0);
            port = socket.getLocalPort();
            socket.close(); 
        } catch (Exception e) {
        	throw new Exception("no free ports available to start jetty container!!");
        }
        System.out.println("Starting server on port number : " + port);
        Server jettyServer = new Server(port);
        jettyServer.setHandler(context);
 
        ServletHolder jerseyServlet = context.addServlet(
             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
 
        jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "com.playground.httpfs");
 
        try {
            jettyServer.start();
            jettyServer.join();
        } catch(Exception e) {
        	System.out.println("Exception caught while launching jersey");
        	System.out.println(e);
        }finally {
        	jettyServer.destroy();
        }            
    }
}
