package com.rabbitmq.lab;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.Dao.AckDAO;
import com.rabbitmq.client.*;
import com.rabbitmq.utils.Logging;
import com.rabbitmq.utils.Utils;

public class PaymentAckPublisher {
	// handles the process for ack i.e retries
	// and sending to another queue of ack

	private final AckDAO ackObj;
	private final static transient Settings settings = Settings.getSelf();
	private final Logging logging = new Logging(PaymentAckPublisher.class);

	public PaymentAckPublisher(AckDAO ackObj) {
		this.ackObj = ackObj;
	}

	public Connection createAckConnection() {
		Connection connection = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(settings.getUri());
			factory.setConnectionTimeout(settings.getTimeout());
			connection = factory.newConnection();
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | URISyntaxException
				| TimeoutException e) {
			logging.error(Utils.prelogString(ackObj.toString(), Utils.getCodelineNumber(), (long) 0.0,
					"Multiple Exception => ", "IOException Exception => " + e.getLocalizedMessage()));
		}
		return connection;

	}

	public boolean publishToChannel() {
		try {
			Connection connection = createAckConnection();
			Channel channel = connection.createChannel();
			/**
			 * queue => name of the queue , durable => Maintain connection even if there are
			 * no messages , exclusive => Dont go away after publisher is done , autoDelete,
			 * arguments
			 */
			channel.queueDeclare(settings.getAck_queue(), true, false, false, null);
			channel.basicPublish(new String(), settings.getAck_queue(), null, ackObj.toString().getBytes());
			connection.close();
			return true;
		} catch (IOException e) {
			logging.error(Utils.prelogString(ackObj.toString(), Utils.getCodelineNumber(), (long) 0.0, "Exception => ",
					"IOException Exception => " + e.getLocalizedMessage()));
			return false;
		}

	}

}