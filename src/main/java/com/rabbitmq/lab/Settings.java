package com.rabbitmq.lab;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

import java.io.File;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@XmlRootElement
final public class Settings {

	private String host;

	private String port;

	private String uri;

	private String username;

	private String password;

	private int ConnectionTimeout;

	private String queue_name;

	private String error_log;

	private String info_log;

	private int channels;

	private int prefetchSize;

	private String CountryCode;

	private String ApplicationName;

	public Settings() {
	}

	/**
	 * ************ Property getters ********************************
	 */
	/**
	 * @param connectionTimeout the connectionTimeout to set
	 */
	@XmlElement
	public void setConnectionTimeout(int connectionTimeout) {
		ConnectionTimeout = connectionTimeout;
	}

	/**
	 * @param error_log the error_log to set
	 */
	@XmlElement
	public void setError_log(String error_log) {
		this.error_log = error_log;
	}

	/**
	 * @param info_log the info_log to set
	 */

	@XmlElement
	public void setInfo_log(String info_log) {
		this.info_log = info_log;
	}

	/**
	 * @param channels the channels to set
	 */
	@XmlElement
	public void setChannels(int channels) {
		this.channels = channels;
	}

	/**
	 * @param prefetchSize the prefetchSize to set
	 */
	@XmlElement
	public void setPrefetchSize(int prefetchSize) {
		this.prefetchSize = prefetchSize;
	}

	/**
	 * @param applicationName the applicationName to set
	 */
	@XmlElement
	public void setApplicationName(String applicationName) {
		ApplicationName = applicationName;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	@XmlElement
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	/**
	 * @return the error_log
	 */
	public String getError_log() {
		return error_log;
	}

	/**
	 * @return the info_log
	 */
	public String getInfo_log() {
		return info_log;
	}

	/**
	 * @return the channels
	 */
	public int getChannels() {
		return channels;
	}

	/**
	 * @param host the host to set
	 */
	@XmlElement
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @param password the password to set
	 */
	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param port the port to set
	 */
	@XmlElement
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @param queue_name the queue_name to set
	 */
	@XmlElement
	public void setQueue_name(String queue_name) {
		this.queue_name = queue_name;
	}

	/**
	 * @param uri the uri to set
	 */
	@XmlElement
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @param username the username to set
	 */
	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the prefetchSize
	 */
	public int getPrefetchSize() {
		return prefetchSize;
	}

	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return ApplicationName;
	}

	/**
	 * @return the host
	 */

	public String getHost() {
		return host;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the connectionTimeout
	 */
	public int getConnectionTimeout() {
		return ConnectionTimeout;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @return the queue_name
	 */
	public String getQueue_name() {
		return queue_name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return CountryCode;
	}

	public Settings getSelf() {
		return SettingsLoader.loadSettings();
	}
}

/**
 * Load the xml class to
 */
class SettingsLoader {
	public static Settings loadSettings(String filename) {

		File settings = new File(filename);
		JAXBContext jaxbContext;
		Unmarshaller jaxbUnmarshaller;
		Settings setup;

		try {
			jaxbContext = JAXBContext.newInstance(Settings.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			setup = (Settings) jaxbUnmarshaller.unmarshal(settings);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
		return setup;

	}

	public static Settings loadSettings() {

		File settings = new File("config/settings.xml");
		JAXBContext jaxbContext;
		Unmarshaller jaxbUnmarshaller;
		Settings setup;

		try {
			jaxbContext = JAXBContext.newInstance(Settings.class);
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			setup = (Settings) jaxbUnmarshaller.unmarshal(settings);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
		return setup;

	}
}