package com.rabbitmq.lab;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.Dao.AckDAO;
import com.rabbitmq.client.*;
import com.rabbitmq.utils.Logging;
import com.rabbitmq.utils.Utils;

public class PaymentAckPublisher {
	// handles the process for ack i.e retries
	// and sending to another queue of ack

	private final AckDAO ackObj;
	private final Settings settings = new Settings().getSelf();
	private final Logging logging = new Logging(this.getClass());

	public PaymentAckPublisher(AckDAO ackObj) {
		this.ackObj = ackObj;
	}

	public boolean publishPaymentAck() {
		Connection connection;
		try {
			connection = this.createAckConnection();
			this.publishToChannel(ackObj.toString(), connection);
			return true;
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | InterruptedException
				| TimeoutException | URISyntaxException e) {
			logging.error(Utils.prelogString(ackObj.toString(), Utils.getCodelineNumber(), (long) 0.0,
					"Multiple Exception => ", "IOException Exception => " + e.getLocalizedMessage()));
			e.printStackTrace();
			return false;
		}

	}

	public Connection createAckConnection() throws IOException, InterruptedException, TimeoutException,
			KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(settings.getUri());
		factory.setConnectionTimeout(settings.getConnectionTimeout());
		Connection connection = factory.newConnection();
		return connection;

	}

	public boolean publishToChannel(String message, Connection connection) {
		try {
			Channel channel = connection.createChannel();

			/**
			 * queue => name of the queue , durable => Maintain connection even if there are
			 * no messages , exclusive => Dont go away after publisher is done , autoDelete,
			 * arguments
			 */
			channel.queueDeclare("ack_queue", true, false, false, null);
			channel.basicPublish("", "ack_queue", null, message.getBytes());
			connection.close();
			return true;
		} catch (IOException e) {
			logging.error(Utils.prelogString(message, Utils.getCodelineNumber(), (long) 0.0, "Exception => ",
					"IOException Exception => " + e.getLocalizedMessage()));
			return false;
		}

	}

}