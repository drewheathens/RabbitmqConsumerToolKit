package com.rabbitmq.lab;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import java.io.IOException;
import java.net.URISyntaxException;
// import java.security.KeyManagementException;
// import java.security.NoSuchAlgorithmException;
// import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args)
            throws KeyManagementException, NoSuchAlgorithmException, IOException, TimeoutException, URISyntaxException {

        // for (int i = 0; i <= 10000; ++i) {
        // PubliserClass pub = new PubliserClass();
        // pub.createConnection("test" + i);

        // try {
        // Thread.sleep(5000);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }
        ConsumerClass consumer = new ConsumerClass();
        consumer.ConsumeService(new JobClass());
    }
    // public static void main(String[] args) throws IOException,
    // InterruptedException, TimeoutException,
    // KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
    // PubliserClass pub = new PubliserClass();
    // pub.createConnection("test");
    // // System.out.println("Hello World!");
    // }

    // public static void main(String[] args) {
    // Settings settings = SettingsLoader.loadSettings();
    // System.out.println(settings.toString());
    // }
}
