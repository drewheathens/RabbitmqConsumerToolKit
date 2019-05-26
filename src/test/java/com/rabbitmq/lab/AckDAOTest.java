package com.rabbitmq.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rabbitmq.Dao.AckDAO;

import org.junit.Test;

/**
 * AckDAOTest
 */
public class AckDAOTest {

	private AckDAO ackObj = new AckDAO();

	@Test
	public void testDescription() {
		String expected = "Transaction was successfull";
		ackObj.setDescription(expected);
		assertEquals(expected, ackObj.getDescription());
	}

	@Test
	public void testRecieptNumber() {
		String expected = "MVN678TST";
		ackObj.setRecieptNumber(expected);
		assertEquals(expected, ackObj.getRecieptNumber());
	}

	@Test
	public void testStatusCode() {
		String expected = "200";
		ackObj.setStatusCode(expected);
		assertEquals(expected, ackObj.getStatusCode());
	}

	@Test
	public void testPassword() {
		String expected = "password";
		ackObj.setPassword(expected);
		assertEquals(expected, ackObj.getPassword());
	}

	@Test
	public void testReceiverNarration() {
		String expected = "Payment was a success";
		ackObj.setReceiverNarration(expected);
		assertEquals(expected, ackObj.getReceiverNarration());
	}

	@Test
	public void testUsername() {
		String expected = "administrator";
		ackObj.setUsername(expected);
		assertEquals(expected, ackObj.getUsername());
	}

	@Test
	public void testToString() {
		assertTrue(ackObj.toString() instanceof String);
	}
}