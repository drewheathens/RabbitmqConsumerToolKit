package com.rabbitmq.lab;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Utils {
	static Logger logger = Logger.getLogger(Utils.class);

	private static void logProps() {
		PropertyConfigurator.configure("log4j.properties");
	}

	

}