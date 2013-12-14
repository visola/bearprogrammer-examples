package com.bearprogrammer.blog.buildtool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {

	private static Application instance;
	
	ConfigurableApplicationContext context;
	Logger logger = LoggerFactory.getLogger(Application.class);

	public static Application getInstance() {
		return instance;
	}

	public static void main (String [] args) {
		instance = new Application();
		instance.initialize();
		
		Runtime.getRuntime().addShutdownHook(new Thread("Shutdown Hook") {
			public void run() {
				instance.destroy();
			}
		});
	}
	
	public Application () {}
	
	private void initialize() {
		logger.debug("Initializing application...");
		
		logger.trace("Starting Spring application context...");
		context = new GenericXmlApplicationContext("classpath:/META-INF/spring/app.xml");
		logger.trace("Spring application context ready.");
		
		logger.debug("Application is initialized.");
	}
	
	private void destroy() {
		logger.debug("Destroying application...");
		
		logger.trace("Starting Spring application context...");
		context.close();
		logger.trace("Spring application context destroyed.");
		
		logger.info("Good bye!");
	}
	
}