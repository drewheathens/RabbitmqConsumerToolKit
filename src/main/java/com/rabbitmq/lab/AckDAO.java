package com.rabbitmq.lab;

public class AckDAO {
	private String descriptionString;

	private String statusCode;

	private String recieptNumber;

	/**
	 * @param descriptionString the descriptionString to set
	 */
	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
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
	 * @return the descriptionString
	 */
	String getDescriptionString() {
		return descriptionString;
	}

	/**
	 * @return the recieptNumber
	 */
	String getRecieptNumber() {
		return recieptNumber;
	}

	/**
	 * @return the statusCode
	 */
	String getStatusCode() {
		return statusCode;
	}

}