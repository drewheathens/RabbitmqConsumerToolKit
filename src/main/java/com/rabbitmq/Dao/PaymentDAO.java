package com.rabbitmq.Dao;

import java.util.HashMap;

import org.json.JSONObject;

public class PaymentDAO {
	private String merchantPaymentID;

	private String serviceCode;

	private String payerClientCode;

	private String customerMSISDN;

	private String payerClient;

	private String accountNumber;

	private double amount;

	private JSONObject extradata;

	/**
	 * @param customerMSISDN the customerMSISDN to set
	 */
	public void setCustomerMSISDN(String customerMSISDN) {
		this.customerMSISDN = customerMSISDN;
	}

	/**
	 * @param merchantPaymentID the merchantPaymentID to set
	 */
	public void setMerchantPaymentID(String merchantPaymentID) {
		this.merchantPaymentID = merchantPaymentID;
	}

	/**
	 * @param payerClientCode the payerClientCode to set
	 */
	public void setPayerClientCode(String payerClientCode) {
		this.payerClientCode = payerClientCode;
	}

	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param extradata the extradata to set
	 */
	public void setExtradata(JSONObject extradata) {
		this.extradata = extradata;
	}

	/**
	 * @param payerClient the payerClient to set
	 */
	public void setPayerClient(String payerClient) {
		this.payerClient = payerClient;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the extradata
	 */
	public JSONObject getExtradata() {
		return extradata;
	}

	/**
	 * @return the payerClient
	 */
	public String getPayerClient() {
		return payerClient;
	}

	/**
	 * @return the customerMSISDN
	 */
	public String getCustomerMSISDN() {
		return customerMSISDN;
	}

	/**
	 * @return the merchantPaymentID
	 */
	public String getMerchantPaymentID() {
		return merchantPaymentID;
	}

	/**
	 * @return the payerClientCode
	 */
	public String getPayerClientCode() {
		return payerClientCode;
	}

	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	public HashMap<String, String> mapedMap() {
		HashMap<String, String> maped = new HashMap<String, String>();
		maped.put("merchantPaymentID", merchantPaymentID);
		maped.put("serviceCode", serviceCode);
		maped.put("payerClient", payerClient);
		maped.put("payerClientCode", payerClientCode);
		maped.put("customerMSISDN", customerMSISDN);
		maped.put("extradata", extradata.toString());
		maped.put("amount", amount + "");
		maped.put("accountNumber", accountNumber);
		return maped;
	}

}
