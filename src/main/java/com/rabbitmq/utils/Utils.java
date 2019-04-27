package com.rabbitmq.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.rabbitmq.Dao.PaymentDAO;
import com.rabbitmq.lab.Settings;
import org.apache.commons.lang3.StringUtils;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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

	public static String prelogString(String dataStruct, int lineNumber, long tat, String status, String logMessage) {
		String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		String file = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		return " | COUNTRYCODE:" + properties.getCountryCode() + " | MESSAGETYPE:" + properties.getApplicationName()
				+ " | FILE:" + file + " | FUNCTION:" + method + " | LINE:" + lineNumber + " | TAT:" + tat + " | STATUS:"
				+ status + " | PAYLOAD : " + dataStruct + "| LOGMESSAGE : " + logMessage;
	}

	/**
	 * getCodelineNumber
	 *
	 * @return lineNumber
	 */
	public static int getCodelineNumber() {
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

	public static String payloadForm(PaymentDAO pmntdao, Settings setup) throws IOException {
		String template = readFile(setup.getRequest_template_path(), StandardCharsets.UTF_8);
		HashMap<String, String> holder = pmntdao.mapedMap();
		for (String val_map : setup.getMap_keys()) { // originates from settings
			String[] map_isolated = val_map.trim().split("\\s*:\\s*");
			template = template.replace(map_isolated[0], holder.get(map_isolated[1]));
		}
		return template;
	}

	/**
	 * Function processes post requests The returned variable is a map of the status
	 * code and the response String
	 * 
	 * @param url
	 * @param payload
	 * @param mimeType
	 * @param headers
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String, String> updatedPostMethod(String url, String payload,
			HashMap<String, String> headers) {

		HashMap<String, String> response = new HashMap<String, String>();
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		CloseableHttpResponse responseBody = null;
		int status = 0;

		// Replace below with log
		// System.out.println("Requesting : " + httppost.getURI());

		try {
			StringEntity entity = new StringEntity(payload, StandardCharsets.UTF_8);

			for (String header : headers.keySet()) {
				httppost.addHeader(header, headers.get(header));
			}

			httppost.setEntity(entity);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = httpclient.execute(httppost);

			// Compare the 2 calls for body and response below
			String body = responseHandler.handleResponse(responseBody);
			// String body = EntityUtils.toString(responseBody.getEntity());
			status = responseBody.getStatusLine().getStatusCode();
			response.put("Status", "" + status);
			response.put("Body", body);

			// Log response below
			// System.out.println("responseBody : " + responseBody);

		} catch (UnsupportedEncodingException e) {
			response.put("Status", "" + status);
			response.put("Body", e.getStackTrace().toString());
		} catch (ClientProtocolException e) {
			response.put("Status", "" + status);
			response.put("Body", e.getStackTrace().toString());
		} catch (IOException e) {
			response.put("Status", "" + status);
			response.put("Body", e.getStackTrace().toString());
		} finally {
			try {
				responseBody.close();
			} catch (IOException e) {
				response.put("Status", "" + status);
				response.put("Body", e.getStackTrace().toString());
			}
		}
		return response;

	}
	public static String strBetwee(String full, String before, String after) {
		return StringUtils.substringBetween(full, before, after);
	}

}