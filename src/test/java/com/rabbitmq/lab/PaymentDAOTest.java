package com.rabbitmq.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import com.rabbitmq.Dao.PaymentDAO;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * PaymentDAOTest
 */
public class PaymentDAOTest {
	private PaymentDAO paymentObj = new PaymentDAO();

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testCustomerMSISDN() {
		String expected = "XXX XXX XXX XXX";
		paymentObj.setCustomerMSISDN(expected);
		assertEquals(expected, paymentObj.getCustomerMSISDN());
		// TODO: add setting and validation of setCustomerMSISDN size
		assertEquals(expected.length(), paymentObj.getCustomerMSISDN().length());
	}

	@Test
	public void testMerchantPaymentID() {
		String expected = "EXT90098REF";
		paymentObj.setMerchantPaymentID(expected);
		assertEquals(expected, paymentObj.getMerchantPaymentID());
	}

	@Test
	public void testPayerClientCode() {
		String expected = "XXXX";
		paymentObj.setPayerClientCode(expected);
		assertEquals(expected, paymentObj.getPayerClientCode());
	}

	@Test
	public void testServiceCode() {
		String expected = "XXXX";
		paymentObj.setServiceCode(expected);
		assertEquals(expected, paymentObj.getServiceCode());
	}

	@Test
	public void testAccountNumber() {
		String expected = "XXX XXX XX";
		paymentObj.setAccountNumber(expected);
		assertEquals(expected, paymentObj.getAccountNumber());
	}

	@Test
	public void testAmount() {
		double expected = 1000.00;
		paymentObj.setAmount(expected);
		assertEquals(expected, paymentObj.getAmount());
	}

	@Test
	public void testExtradata() {
		String expected = "{KEY:XXX,KEY2:XX}";
		JSONObject expects = new JSONObject(expected);
		paymentObj.setExtradata(expects);
		assertEquals(expects, paymentObj.getExtradata());
		assertNotEquals(expected, paymentObj.getExtradata().toString());
	}

	@Test
	public void testPayerClient() {
		String expected = "XXXX";
		paymentObj.setPayerClient(expected);
		assertEquals(expected, paymentObj.getPayerClient());
	}

	@Test
	public void testMappedWithoutVars() {
		exception.expect(IllegalArgumentException.class);
		assertTrue(paymentObj.mapedMap() instanceof HashMap);
	}

	@Test
	public void testMappedWithVars() {
		String expected = "{KEY:XXX,KEY2:XX}";
		JSONObject expects = new JSONObject(expected);
		paymentObj.setExtradata(expects);
		paymentObj.setPayerClient(expected);
		paymentObj.setExtradata(expects);
		paymentObj.setAccountNumber(expected);
		paymentObj.setMerchantPaymentID(expected);
		paymentObj.setCustomerMSISDN(expected);
		paymentObj.setServiceCode(expected);
		paymentObj.setPayerClientCode(expected);

		double expectedD = 1000.00;
		paymentObj.setAmount(expectedD);

		assertTrue(paymentObj.mapedMap() instanceof HashMap);
	}
}