/**
 * 
 * @author Jeremiah Mbaria <jeremiah.mbaria@cellulant.com>
 * 
 */
package com.rabbitmq.utils;

import org.apache.log4j.PropertyConfigurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Log class.
 *
 * @author Charles Otieno
 */
// @SuppressWarnings({ "FinalClass", "ClassWithoutLogger" })
public final class Logging {
	/**
	 * Logger object.
	 */
	// @SuppressWarnings("FieldMayBeFinal")	
	private Logger logger;

	/**
	 * Constructor.
	 *
	 * @param clazz the class doing the logging
	 */
	public Logging(final Class<?> clazz) {
		PropertyConfigurator.configure("config/log.properties");
		logger = LoggerFactory.getLogger(clazz);
	}

	public Logging() {
		PropertyConfigurator.configure("config/log.properties");
		// throw new UnsupportedOperationException("Not supported yet."); //To change
		// body of generated methods, choose Tools | Templates.
	}

	/**
	 * Get the logger.
	 *
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * Log a debug message according to the specified format and argument.
	 *
	 * @param arg0 the format
	 * @param arg1 the argument
	 */
	public void debug(final String arg0, final Object arg1) {
		logger.debug(arg0, arg1);
	}

	/**
	 * Log a debug message.
	 *
	 * @param arg0 the message
	 */
	public void debug(final String arg0) {
		logger.debug(arg0);
	}

	/**
	 * Log a error message according to the specified format and argument.
	 *
	 * @param arg0 the format
	 * @param arg1 the argument
	 */
	public void error(final String arg0, final Object arg1) {
		this.logger.error(arg0, arg1);
	}

	/**
	 * Log an error message.
	 *
	 * @param arg0 the format
	 * @param arg1 the throwable exception
	 */
	public void error(final String arg0, final Throwable arg1) {
		this.logger.error(arg0, arg1);
	}

	/**
	 * Log an error message.
	 *
	 * @param arg0 the format
	 */
	public void error(final String arg0) {
		this.logger.error(arg0);
	}

	/**
	 * Log an info message according to the specified format and argument.
	 *
	 * @param arg0 the format
	 * @param arg1 the argument
	 */
	public void info(final String arg0, final Object arg1) {
		this.logger.info(arg0, arg1);
	}

	/**
	 * Log an info message.
	 *
	 * @param arg0 the format
	 */
	public void info(final String arg0) {
		this.logger.info(arg0);
	}

	/**
	 * Log a warning message according to the specified format and argument.
	 *
	 * @param arg0 the format
	 * @param arg1 the argument
	 */
	public void warn(final String arg0, final Object arg1) {
		this.logger.warn(arg0, arg1);
	}

	/**
	 * Log a warning message.
	 *
	 * @param arg0 the format
	 * @param arg1 the throwable exception
	 */
	public void warn(final String arg0, final Throwable arg1) {
		this.logger.warn(arg0, arg1);
	}

	/**
	 * Log a warning message.
	 *
	 * @param arg0 the format
	 */
	public void warn(final String arg0) {
		this.logger.warn(arg0);
	}
}
