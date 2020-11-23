package com.trms.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogThis {
	
	static Logger logger = LogManager.getLogger();
	
	public static void LogIt(String level, String message) { 
		switch(level) {
		case "debug":
			logger.debug(message);
			break;
		case "warn":
			logger.warn(message);
			break;
		case "error":
			logger.error(message);
			break;
		case "fatal":
			logger.fatal(message);
			break;
		case "info":
			logger.info(message);
			break;
		case "trace":
			logger.trace(message);
			break;
		default:
			System.err.println("logger dumb");
		}
	}

}