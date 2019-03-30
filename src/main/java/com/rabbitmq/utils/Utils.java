package com.rabbitmq.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.rabbitmq.Dao.PaymentDAO;
import com.rabbitmq.lab.Settings;

import org.json.JSONObject;
import org.json.XML;

public class Utils {

	private static transient Settings properties;

	public Utils(Settings sett) {
		properties = sett;
	}

	/**
	 * Prepended text info & debug added to each log message.
	 *
	 * @param lineNumber
	 * @param logMessage
	 * @return
	 */
	public String prelogString(int lineNumber, String logMessage) {
		String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		String file = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		return " | COUNTRYCODE:" + properties.getCountryCode() + " | MESSAGETYPE:" + properties.getCountryCode()
				+ " | FILE:" + file + " | FUNCTION:" + method + " | LINE:" + lineNumber + " | " + logMessage;
	}

	/**
	 * Prepended text info & debug added to each log message.
	 *
	 * @param dataStruct
	 * @param tat
	 * @param status
	 * @param lineNumber
	 * @param logMessage
	 * @return
	 */
	public static String prelogString(PaymentDAO dataStruct, int lineNumber, long tat, String status,
			String logMessage) {
		String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		String file = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		return " | COUNTRYCODE:" + properties.getCountryCode() + " | MESSAGETYPE:" + properties.getApplicationName()
				+ " | PAYMENTID:" + dataStruct.getMerchantPaymentID() + " | SERVICECODE:" + dataStruct.getServiceCode()
				+ " | CLIENTCODE:" + dataStruct.getPayerClientCode() + " | MSISDN:" + dataStruct.getCustomerMSISDN()
				+ " | FILE:" + file + " | FUNCTION:" + method + " | LINE:" + lineNumber + " | TAT:" + tat + " | STATUS:"
				+ status + logMessage;
	}

	/**
	 * getCodelineNumber
	 *
	 * @return lineNumber
	 */
	public int getCodelineNumber() {
		int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		return lineNumber;
	}

	/**
	 * Convert json string to json Object
	 * 
	 * @param jsonstring
	 * @return
	 */
	public static JSONObject stringToJson(String jsonstring) {
		return new JSONObject(jsonstring);
	}

	/**
	 * Replace all newline and tab spaces in a string
	 * 
	 * @param input
	 * @return
	 */
	public static String rmNewlineTab(String input) {
		return input.replace("\n", "").replace("\r", "").replace("\t", "");
	}

	/**
	 * Convert xml string to JsonObject
	 * 
	 * @param xmlString
	 * @return
	 */
	public static JSONObject xmlToJson(String xmlString) {
		JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
		return xmlJSONObj;
	}

	/**
	 * Read file
	 * 
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		String tmplt = new String(encoded, encoding);
		return tmplt;
	}

	/**
	 * Convert current datetime to date and time String array
	 * 
	 * @return
	 */
	public String[] dateToString() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate.split("\\s+");
	}
	// TODO:
	// import org.apache.commons.lang3.StringUtils;
	// public static String strBetwee(String full, String before, String after) {
	// return StringUtils.substringBetween(full, before, after);
	// }

	/**
	 *
	 * @param jsonElement
	 * @return
	 */
	// public static String getNullAsEmptyString(JsonElement jsonElement) {
	// return jsonElement.isJsonNull() ? "" : jsonElement.getAsString().trim();
	// }

}