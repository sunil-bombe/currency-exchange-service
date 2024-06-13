package com.webwork.currencyexchangeservice.beans;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
public class ServerPortListener implements ApplicationListener<WebServerInitializedEvent>
{
	
	private int port;
	
	private Environment environment;

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		this.port = event.getWebServer().getPort();
        System.out.println("Application is running on port: " + port);
		
	}
	
	 public int getPort() {
	        return port;
	    }

	    public String getPortFromEnvironment() {
	        return environment.getProperty("local.server.port");
	    }

}
