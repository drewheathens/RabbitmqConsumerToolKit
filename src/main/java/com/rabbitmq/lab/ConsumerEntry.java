package com.rabbitmq.lab;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.utils.Logging;
import com.rabbitmq.utils.Utils;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.Dao.JobInterface;
import com.rabbitmq.Dao.PaymentDAO;
import com.rabbitmq.Dao.AckDAO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public abstract class ConsumerEntry implements Daemon, Runnable {

	private QueueManager queueManager;
	private static final transient Settings settings = new Settings().getSelf();
	// private transient Logging logging;
	private ExecutorService messageExecutor;

	public transient Utils utilities;

	public transient Thread mainThread;

	private static final transient Logging logging = new Logging(ConsumerEntry.class);

	// private final JobInterface job;

	public ConsumerEntry() {
		this.queueManager = new QueueManager();

	}

	//
	public void consume(final ExecutorService exec, final JobInterface job, final Connection connection)
			throws IOException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException, TimeoutException {
		final Channel channel = queueManager.createChannel(connection);
		channel.basicConsume(settings.getQueue_name(), new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {

				String message = new String(body, "UTF-8");
				// Convert string to JSON
				JSONObject requestObj = Utils.stringToJson(message);
				// Request object mapping and Struct instanciate
				PaymentDAO dao = classConst(requestObj);

				logging.info(Utils.prelogString(dao, Utils.getCodelineNumber(), (long) 0.0, envelope.getRoutingKey(),
						message));
				String routingKey = envelope.getRoutingKey();
				String contentType = properties.getContentType();
				long deliveryTag = envelope.getDeliveryTag();

				job.setDAO(dao);
				Future<AckDAO> response = exec.submit(job);
				try {
					// Processing Ack
					PaymentAckPublisher payment = new PaymentAckPublisher(response.get(2000, TimeUnit.MICROSECONDS));
					// ;
					if (!payment.publishToChannel()) {
						logging.error(Utils.prelogString(dao, Utils.getCodelineNumber(), (long) 0.0,
								"Publishing Failed", response.get(2000, TimeUnit.MICROSECONDS).toString()));
					} else {
						logging.info(Utils.prelogString(dao, Utils.getCodelineNumber(), (long) 0.0,
								"Publishing Success", response.get(2000, TimeUnit.MICROSECONDS).toString()));
					}
				} catch (InterruptedException e) {
					logging.error(Utils.prelogString(dao, Utils.getCodelineNumber(), (long) 0.0, "Exception => ",
							"Interrupted Exception => " + e.getLocalizedMessage()));
				} catch (ExecutionException e) {
					logging.error(Utils.prelogString(dao, Utils.getCodelineNumber(), (long) 0.0, "Exception => ",
							"Execution Exception => " + e.getLocalizedMessage()));
				} catch (TimeoutException e) {
					logging.error(Utils.prelogString(dao, Utils.getCodelineNumber(), (long) 0.0, "Exception =>",
							"Timeout Exception => " + e.getLocalizedMessage()));
				}
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
		});

	}

	public PaymentDAO classConst(JSONObject request) {
		PaymentDAO dao = new PaymentDAO();
		dao.setAccountNumber(request.getString("accountNumber"));
		dao.setAmount(request.getDouble("amount"));
		dao.setExtradata(request.getJSONObject("paymentExtradata"));
		dao.setServiceCode(request.getString("serviceCode"));
		dao.setPayerClientCode(request.getString("clientCode"));
		dao.setPayerClient(request.getString("client"));
		dao.setMerchantPaymentID(request.getString("beepTransactionID"));
		dao.setCustomerMSISDN(request.getString("MSISDN"));
		return dao;
	}

	public void init(DaemonContext context) throws DaemonInitException, Exception {
		mainThread = new Thread(this);
		logging.info("Initializing " + settings.getApplicationname() + " service");
		// logging.info(Utils.prelogString(new PaymentDAO(), Utils.getCodelineNumber(),
		// (long) 0.00, "Not status",
		// "Initializing " + settings.getApplicationName() + " consumer..."));

	}

	public void start() throws Exception {
		mainThread.start();
		logging.info(utilities.prelogString(Utils.getCodelineNumber(), "Starting consumer..."));

	}

	public void stop() throws Exception {
		logging.info(utilities.prelogString(Utils.getCodelineNumber(), "Stoping consumer..."));
		messageExecutor.shutdown();
		String message;
		try {
			do {
				messageExecutor.awaitTermination(60, TimeUnit.SECONDS);
				message = "Waiting for the transaction to finish. pool: " + messageExecutor.hashCode();
				logging.info(utilities.prelogString(Utils.getCodelineNumber(), message));
			} while (!messageExecutor.isTerminated());
			messageExecutor.shutdownNow();
			message = "Executor executorService terminated successfully. pool:" + messageExecutor.hashCode();
			logging.info(utilities.prelogString(Utils.getCodelineNumber(), message));
		} catch (InterruptedException exception) {
			message = "Executor executorService shutdown error:" + exception.getMessage();
			logging.error(utilities.prelogString(Utils.getCodelineNumber(), message));
			messageExecutor.shutdownNow();
			Thread.currentThread().interrupt();
		}
		logging.info(
				utilities.prelogString(Utils.getCodelineNumber(), settings.getApplicationname() + " consumer stopped"));

	}

	public void destroy() {
		logging.info(utilities.prelogString(Utils.getCodelineNumber(),
				"Destroying " + settings.getApplicationname() + " consumer..."));

	}

	public void run() {
		// JobInterface job = loadJob();
		//
		try {
			messageExecutor = Executors.newFixedThreadPool(settings.getPrefetchSize());
			int channels = settings.getChannels();
			// logging.info(utilities.prelogString(Utils.getCodelineNumber(), "Channels
			// setup starting..."));
			logging.info("Channels setup starting...");
			// Create connection outside loop to have all the chanels in one connection
			// Else create connection inside loop to have each connection with a channel
			final Connection connection = queueManager.createConnection();
			for (int i = 1; i <= channels; i++) {
				JobInterface job = loadJob();
				this.consume(messageExecutor, job, connection);
				// logging.info(utilities.prelogString(Utils.getCodelineNumber(),
				// "Channel number : " + i + "=> Accepting connections"));
				logging.info("Channel number : " + i + "=> Accepting connections");
			}

		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

	public abstract JobInterface loadJob();

}