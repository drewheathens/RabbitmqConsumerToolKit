package com.rabbitmq.Dao;

import java.util.concurrent.Callable;

/**
 * Abstaraction of Job
 */
public abstract class JobInterface implements Callable<AckDAO> {

	public abstract AckDAO process();

	// void setDAO
	public abstract void setDAO(PaymentDAO dao);
}