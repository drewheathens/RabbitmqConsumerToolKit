package com.rabbitmq.lab;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

public class ConsumerClass {

	private final Settings settings = SettingsLoader.loadSettings();

	public void ConsumeService(final JobInterface job)
			throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(settings.getUri());
		factory.setConnectionTimeout(settings.getConnectionTimeout());
		Connection connection = factory.newConnection();

		// has to be final for the basic ack
		final Channel channel = connection.createChannel();

		/**
		 * queue => name of the queue , durable => Maintain connection even if there are
		 * no messages , exclusive => Dont go away after publisher is done , autoDelete,
		 * arguments
		 */
		channel.queueDeclare(settings.getQueue_name(), true, false, false, null);
		channel.basicConsume(settings.getQueue_name(), new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {

				String message = new String(body, "UTF-8");
				System.out.println("Consuming : " + message);
				String routingKey = envelope.getRoutingKey();
				String contentType = properties.getContentType();
				long deliveryTag = envelope.getDeliveryTag();

				/**
				 * 
				 */
				PaymentDAO dao = new PaymentDAO();
				job.process(dao);
				// this.processPayment();
				// (process the message components here ...)
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		});

	}

}