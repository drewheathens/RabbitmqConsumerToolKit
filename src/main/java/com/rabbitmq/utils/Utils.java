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

	private final static transient Settings properties = Settings.getSelf();

	private final static transient Logging log = new Logging(Utils.class);

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
		return " | COUNTRYCODE:" + properties.getCountrycode() + " | MESSAGETYPE:" + properties.getCountrycode()
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
		return " | COUNTRYCODE:" + properties.getCountrycode() + " | MESSAGETYPE:" + properties.getApplicationname()
				+ " | PAYMENTID:" + dataStruct.getMerchantPaymentID() + " | SERVICECODE:" + dataStruct.getServiceCode()
				+ " | CLIENTCODE:" + dataStruct.getPayerClientCode() + " | MSISDN:" + dataStruct.getCustomerMSISDN()
				+ " | FILE:" + file + " | FUNCTION:" + method + " | LINE:" + lineNumber + " | TAT:" + tat + " | STATUS:"
				+ status + logMessage;
	}

	public static String prelogString(String dataStruct, int lineNumber, long tat, String status, String logMessage) {
		String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		String file = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		return " | COUNTRYCODE:" + properties.getCountrycode() + " | MESSAGETYPE:" + properties.getApplicationname()
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
	public static String readFile(String path, Charset encoding) {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			throw new IllegalArgumentException("Path not found : " + e.getMessage());
		}
		String tmplt = new String(encoded, encoding);
		return tmplt;
	}

	/**
	 * Convert current datetime to date and time String array
	 * 
	 * @return
	 */
	public static String[] dateToString() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate.split("\\s+");
	}

	public static String payloadForm(PaymentDAO pmntdao, Settings setup) {
		log.info("Creating payload for payment => " + pmntdao.mapedMap());
		String template = "";
		try {
			template = readFile(setup.getRequest_template_path(), StandardCharsets.UTF_8);
		} catch (IllegalArgumentException e) {
			log.error("IOException" + e.getStackTrace());
			return template;
		}
		HashMap<String, String> holder = pmntdao.mapedMap();
		for (String val_map : setup.getMap_keys()) { // originates from settings
			String[] map_isolated = val_map.trim().split("\\s*:\\s*");
			template = template.replace(map_isolated[0], holder.get(map_isolated[1]));
		}
		log.info("Created payment paylaod =>" + template);
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

		log.info("Sending Http Post Request to => " + url);

		HashMap<String, String> response = new HashMap<String, String>();
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		CloseableHttpResponse responseBody = null;
		int status = 0;

		// Replace below with log

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

		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException @ " + e.getMessage());
			response.put("Status", "" + status);
			response.put("Body", e.getMessage());
		} catch (ClientProtocolException e) {
			log.error("ClientProtocolException @ " + e.getMessage());
			response.put("Status", "" + status);
			response.put("Body", e.getMessage());
		} catch (IOException e) {
			log.error("IOException @ " + e.getMessage());
			response.put("Status", "" + status);
			response.put("Body", e.getMessage());
		} finally {
			try {
				responseBody.close();
			} catch (IOException e) {
				log.error("IOException @ " + e.getMessage());
				response.put("Status", "" + status);
				response.put("Body", e.getMessage());
			}
		}
		log.info("Recieved payload processing => " + response);
		return response;

	}

	/**
	 * Extract response values from the response string to key values map
	 * 
	 * @param mapping
	 * @param response
	 * @param isXml    extraction of xml string is unique
	 * @return
	 */
	public static HashMap<String, String> responseProcess(String mapping, String response, Boolean isXml) {
		log.info("Response recieved for processing => " + response);

		HashMap<String, String> extracted = new HashMap<String, String>();

		for (String variables : mapping.trim().split("\\s*,\\s*")) {
			String[] mapped = variables.trim().split("\\s*~\\s*");
			String[] rep = mapped[0].trim().split("\\s*:\\s*");
			String value = "";
			if (isXml) {
				log.info("Processing for xml response");
				String before = createSubstrings(rep[0]);
				String after = createSubstrings(rep[1]);
				if (before.isEmpty() || after.isEmpty()) {
					log.error("xml tag representation tag not specified => " + rep[0] + "^^" + rep[1]);
					return new HashMap<String, String>();
				}
				value = StringUtils.substringBetween(response, before, after);
			} else {
				log.info("Processing for other string responses");
				value = StringUtils.substringBetween(response, rep[0], rep[1]);
			}
			// if value is null throw error invalid mapping

			extracted.put(mapped[1], value);
		}
		log.info("Fully processed response map => " + extracted);
		return extracted;
	}

	/**
	 * Method recreates the search substrings if the response is an xml Given that
	 * fitting an xml string with no closing tag in the xml setting file Breaks the
	 * system Hence st anotates the start of a tag and en signifies
	 * 
	 * Function restricted to xml strings
	 * 
	 * @param str
	 * @return gives the reconstructed xml substring
	 */
	public static String createSubstrings(String str) {
		log.info("Recreating response templates to match substrings => " + str);
		String toreturn = "";
		for (String tag : str.trim().split("\\s*-\\s*")) {
			String[] val = tag.trim().split("\\s*_\\s*");
			if (val[0].equals(properties.getStarttag())) {
				toreturn += "<" + val[1] + ">";
			} else if (val[0].equals(properties.getEndtag())) {
				toreturn += "</" + val[1] + ">";
			} else {
				log.error("Tag recreation failed  for placeholder => " + val[0]);
				return "";
			}
		}
		log.info("Completed xml substring recreation => " + toreturn);
		return toreturn;
	}

}