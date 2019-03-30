package com.rabbitmq.Dao;

import org.json.JSONObject;

public class AckDAO {
	private String description;

	private String statusCode;

	private String recieptNumber;

	private String ReceiverNarration;

	private String username;

	private String password;

	/**
	 * @param descriptionString the descriptionString to set
	 */
	public void setDescriptionString(String descriptionString) {
		this.description = descriptionString;
	}

	/**
	 * @param recieptNumber the recieptNumber to set
	 */
	public void setRecieptNumber(String recieptNumber) {
		this.recieptNumber = recieptNumber;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param receiverNarration the receiverNarration to set
	 */
	public void setReceiverNarration(String receiverNarration) {
		ReceiverNarration = receiverNarration;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the receiverNarration
	 */
	public String getReceiverNarration() {
		return ReceiverNarration;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the descriptionString
	 */
	public String getDescriptionString() {
		return description;
	}

	/**
	 * @return the recieptNumber
	 */
	public String getRecieptNumber() {
		return recieptNumber;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	@Override
	public String toString() {
		JSONObject str = new JSONObject();
		str.put("narration", this.ReceiverNarration);
		str.put("description", this.description);
		str.put("password", this.password);
		str.put("username", this.username);
		str.put("Receipt", this.recieptNumber);
		str.put("StatusCode", this.statusCode);
		return str.toString();
	}

}