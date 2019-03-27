package com.rabbitmq.lab;

import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class PubliserClass {

	private final Settings settings = SettingsLoader.loadSettings();

	public Channel Connect() throws IOException, InterruptedException, TimeoutException, KeyManagementException,
			NoSuchAlgorithmException, URISyntaxException {
		Channel channel;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(settings.getUri());
		factory.setConnectionTimeout(settings.getConnectionTimeout());
		Connection connection = factory.newConnection();
		channel = connection.createChannel();

		/**
		 * queue => name of the queue , durable => Maintain connection even if there are
		 * no messages , exclusive => Dont go away after publisher is done , autoDelete,
		 * arguments
		 */
		channel.queueDeclare(settings.getQueue_name(), true, false, false, null);
		return channel;

	}

	public void createConnection(String message) {
		try {
			this.Connect().basicPublish("", settings.getQueue_name(), null, message.getBytes());
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}