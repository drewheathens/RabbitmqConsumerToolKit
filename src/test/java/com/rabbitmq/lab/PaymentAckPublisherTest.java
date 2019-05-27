package com.rabbitmq.lab;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rabbitmq.Dao.AckDAO;
import com.rabbitmq.client.Connection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.rules.ExpectedException;

/**
 * PaymentAckPublisherTest
 */
public class PaymentAckPublisherTest {
	private PaymentAckPublisher payAckPublisher;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testPublishPaymentAck() {
		// AckDAO ack = new AckDAO();
		// ack.setDescription("description");
		// ack.setPassword("password");
		// ack.setReceiverNarration("receiverNarration");
		// ack.setUsername("username");
		// ack.setStatusCode("statusCode");
		// PaymentAckPublisher payAckPublisher = new PaymentAckPublisher(ack);

		// // exception.expect(NullPointerException.class);
		// assertTrue(payAckPublisher.createAckConnection() instanceof Connection);
		fail("Un Implemented");
	}

	@Test
	public void testCreateAckConnection() {
		// exception.expect(NullPointerException.class);
		// assertTrue(payAckPublisher.createAckConnection() instanceof Connection);
		fail("Un Implemented");
	}

	@Test
	public void testPublishToChannel() {
		// assertTrue(payAckPublisher.publishToChannel());
		fail("Un Implemented");
	}
}