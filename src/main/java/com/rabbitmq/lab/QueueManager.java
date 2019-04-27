package com.rabbitmq.lab;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.utils.Logging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class QueueManager {

	private final static transient Settings settings = SettingsLoader.loadSettings();
	private final static transient Logging log = new Logging(QueueManager.class);

	public Connection createConnection()
			throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException, TimeoutException {
		log.info("Creating connection");
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(settings.getUri());
		factory.setConnectionTimeout(settings.getConnectionTimeout());
		Connection connection = factory.newConnection();
		return connection;
	}

	public Channel createChannel(Connection connection) throws IOException {
		final Channel channel = connection.createChannel();

		/**
		 * queue => name of the queue , durable => Maintain connection even if there are
		 * no messages , exclusive => Dont go away after publisher is done , autoDelete,
		 * arguments
		 */
		channel.queueDeclare(settings.getQueue_name(), true, false, false, null);
		return channel;

	}

	/**
	 * passing in the queue name
	 * 
	 * @param connection
	 * @param queue_name
	 * @return
	 * @throws IOException
	 */
	public Channel createChannel(Connection connection, String queue_name) throws IOException {
		final Channel channel = connection.createChannel();

		/**
		 * queue => name of the queue , durable => Maintain connection even if there are
		 * no messages , exclusive => Dont go away after publisher is done , autoDelete,
		 * arguments
		 */
		channel.queueDeclare(queue_name, true, false, false, null);
		return channel;

	}
}