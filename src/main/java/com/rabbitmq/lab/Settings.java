package com.rabbitmq.lab;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

import java.io.File;
import java.util.HashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@XmlRootElement
final public class Settings {

	private String host;

	private String port;

	private Boolean isxml;

	private String api_password;

	private String api_username;

	private String httpsuccess;

	private String thirdpartysuccesscode;

	private String uri;

	private String username;

	private String password;

	private int connectionTimeout;

	private String queue_name;

	private String response_template;

	private String successAckCode;

	private String failedAckCode;

	private int channels;

	private int prefetchSize;

	private String countrycode;

	private String applicationname;

	private String ack_queue;

	private String post_url;

	private String request_template_path;

	private String map_key;

	private String starttag;

	private String endtag;

	private String httpHeaders;

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
	 * @param isxml the isxml to set
	 */
	@XmlElement
	public void setIsxml(Boolean isxml) {
		this.isxml = isxml;
	}

	/**
	 * @param channels the channels to set
	 */
	@XmlElement
	public void setChannels(int channels) {
		this.channels = channels;
	}

	/**
	 * @param thirdpartysuccesscode the thirdpartysuccesscode to set
	 */
	@XmlElement
	public void setThirdpartysuccesscode(String thirdpartysuccesscode) {
		this.thirdpartysuccesscode = thirdpartysuccesscode;
	}

	/**
	 * @return the thirdpartysuccesscode
	 */
	public String[] getExternalsuccesscode() {
		return thirdpartysuccesscode.trim().split("\\s*~\\s*");
	}

	/**
	 * @param httpHeaders the httpHeaders to set
	 */
	@XmlElement
	public void setHttpHeaders(String httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public HashMap<String, String> getHTTPHeaders() {
		HashMap<String, String> formatted = new HashMap<String, String>();
		String[] headers = httpHeaders.trim().split("\\s*,\\s*");
		for (String header : headers) {
			String[] headerKeyValue = header.trim().split("\\s*:\\s*");
			formatted.put(headerKeyValue[0], headerKeyValue[1]);
		}
		return formatted;

	}

	/**
	 * @param prefetchSize the prefetchSize to set
	 */
	@XmlElement
	public void setPrefetchSize(int prefetchSize) {
		this.prefetchSize = prefetchSize;
	}

	/**
	 * @param httpsuccess the httpsuccess to set
	 */
	@XmlElement
	public void setHttpsuccess(String httpsuccess) {
		this.httpsuccess = httpsuccess;
	}

	/**
	 * @return the httpsuccess
	 */
	public String getHttpsuccess() {
		return httpsuccess;
	}

	/**
	 * @param endtag the endtag to set
	 */
	@XmlElement
	public void setEndtag(String endtag) {
		this.endtag = endtag;
	}

	/**
	 * @param failedAckCode the failedAckCode to set
	 */
	@XmlElement
	public void setFailedAckCode(String failedAckCode) {
		this.failedAckCode = failedAckCode;
	}

	/**
	 * @param successAckCode the successAckCode to set
	 */
	@XmlElement
	public void setSuccessAckCode(String successAckCode) {
		this.successAckCode = successAckCode;
	}

	/**
	 * @return the failedAckCode
	 */
	public String getFailedAckCode() {
		return failedAckCode;
	}

	/**
	 * @return the successAckCode
	 */
	public String getSuccessAckCode() {
		return successAckCode;
	}

	/**
	 * @param response_template the response_template to set
	 */
	@XmlElement
	public void setResponse_template(String response_template) {
		this.response_template = response_template;
	}

	/**
	 * @param api_password the api_password to set
	 */
	@XmlElement
	public void setApi_password(String api_password) {
		this.api_password = api_password;
	}

	/**
	 * @param api_username the api_username to set
	 */
	@XmlElement
	public void setApi_username(String api_username) {
		this.api_username = api_username;
	}

	/**
	 * @param starttag the starttag to set
	 */
	@XmlElement
	public void setStarttag(String starttag) {
		this.starttag = starttag;
	}

	/**
	 * @return the channels
	 */
	public int getChannels() {
		return channels;
	}

	/**
	 * @return the endtag
	 */
	public String getEndtag() {
		return endtag;
	}

	/**
	 * @return the api_password
	 */
	public String getApi_password() {
		return api_password;
	}

	/**
	 * @return the api_username
	 */
	public String getApi_username() {
		return api_username;
	}

	/**
	 * @return the response_template
	 */
	public String getResponse_template() {
		return response_template;
	}

	/**
	 * @return the starttag
	 */
	public String getStarttag() {
		return starttag;
	}

	/**
	 * @return the isxml
	 */
	public Boolean getIsxml() {
		return isxml;
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
	 * @param applicationname the applicationname to set
	 */
	@XmlElement
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}

	/**
	 * @param countrycode the countrycode to set
	 */
	@XmlElement
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public Settings getSelf() {
		return SettingsLoader.loadSettings();
	}

	/**
	 * @return the applicationname
	 */
	public String getApplicationname() {
		return applicationname;
	}

	/**
	 * @return the countrycode
	 */
	public String getCountrycode() {
		return countrycode;
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