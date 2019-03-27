package com.rabbitmq.lab;

public interface JobInterface {
	AckDAO process(PaymentDAO dao);
}