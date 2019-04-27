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

	private int connectionTimeout;

	private String queue_name;

	private int channels;

	private int prefetchSize;

	private String countryCode;

	private String applicationName;

	private String ack_queue;

	private String post_url;

	private String request_template_path;

	private String map_key;

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
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * @param ack_queue the ack_queue to set
	 */
	@XmlElement
	public void setAck_queue(String ack_queue) {
		this.ack_queue = ack_queue;
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
		this.applicationName = applicationName;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	@XmlElement
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the channels
	 */
	public int getChannels() {
		return channels;
	}

	/**
	 * @return the ack_queue
	 */
	public String getAck_queue() {
		return ack_queue;
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
	 * @param map_key the map_key to set
	 */
	@XmlElement
	public void setMap_key(String map_key) {
		this.map_key = map_key;
	}

	/**
	 * @param post_url the post_url to set
	 */
	@XmlElement
	public void setPost_url(String post_url) {
		this.post_url = post_url;
	}

	/**
	 * @param request_template_path the request_template_path to set
	 */
	@XmlElement
	public void setRequest_template_path(String request_template_path) {
		this.request_template_path = request_template_path;
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
		return applicationName;
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
		return connectionTimeout;
	}

	/**
	 * @return the map_key
	 */
	public String[] getMap_keys() {
		return map_key.trim().split("\\s*,\\s*");
	}

	/**
	 * @return the post_url
	 */
	public String getPost_url() {
		return post_url;
	}

	/**
	 * @return the request_template_path
	 */
	public String getRequest_template_path() {
		return request_template_path;
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
		return countryCode;
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