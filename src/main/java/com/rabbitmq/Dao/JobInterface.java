package com.rabbitmq.Dao;

import java.util.concurrent.Callable;

import com.rabbitmq.lab.Settings;
import com.rabbitmq.utils.Logging;
import com.rabbitmq.utils.Utils;

/**
 * Abstaraction of Job
 */
public abstract class JobInterface implements Callable<AckDAO> {

	public abstract AckDAO process();

	// void setDAO
	public abstract void setDAO(PaymentDAO dao);
}