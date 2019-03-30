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

	private final Settings settings = SettingsLoader.loadSettings();
	private final transient Logging log;

	public QueueManager(Logging logging) {
		this.log = logging;
	}

	public Connection createConnection()
			throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException, TimeoutException {
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
}