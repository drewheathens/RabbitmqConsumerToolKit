package com.rabbitmq.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.Test;

/**
 * SettingsTest
 */
public class SettingsTest {
	private Settings setup = SettingsLoader.loadSettings("TestConfs/settings.xml");

	// @BeforeAll
	// public void setUp() {
	// setup =
	// }

	@Test
	public void testgetExternalsuccesscode() {
		String expectedStatuscode = "210"; // status code
		String expectedStatusLoc = "st_statusCode:en_statusCode";
		assertEquals(expectedStatuscode, setup.getExternalsuccesscode()[1]);
		assertEquals(expectedStatusLoc, setup.getExternalsuccesscode()[0]);
	}

	@Test
	public void testgetHTTPHeaders() {
		HashMap<String, String> expected = new HashMap<>();
		expected.put("Content-type", "application/json");
		assertEquals(expected, setup.getHTTPHeaders());
	}

	@Test
	public void testgetHttpsuccess() {
		String expected = "200";
		assertEquals(expected, setup.getHttpsuccess());
	}

	@Test
	public void testgetFailedAckCode() {
		String expected = "141";
		assertEquals(expected, setup.getFailedAckCode());
	}

	@Test
	public void testgetSuccessAckCode() {
		String expected = "140";
		assertEquals(expected, setup.getSuccessAckCode());
	}

	@Test
	public void testgetChannels() {
		int expected = 5;
		assertEquals(expected, setup.getChannels());
	}

	@Test
	public void testgetEndtag() {
		String expected = "en";
		assertEquals(expected, setup.getEndtag());
	}

	@Test
	public void testgetApi_password() {
		String expected = "password";
		assertEquals(expected, setup.getApi_password());
	}

	@Test
	public void testgetApi_username() {
		String expected = "username";
		assertEquals(expected, setup.getApi_username());
	}

	@Test
	public void testgetResponse_template() {
		assertTrue(setup.getResponse_template() instanceof String);
	}

	@Test
	public void testgetStarttag() {
		String expected = "st";
		assertEquals(expected, setup.getStarttag());
	}

	@Test
	public void testgetIsxml() {
		assertTrue(setup.getIsxml());
	}

	@Test
	public void testgetAck_queue() {
		String expected = "ackQueue";
		assertEquals(expected, setup.getAck_queue());
	}

	@Test
	public void testgetPrefetchSize() {
		int expected = 1;
		assertEquals(expected, setup.getPrefetchSize());
	}

	@Test
	public void testgetHost() {
		String expected = "192.168.56.102";
		assertEquals(expected, setup.getHost());
	}

	@Test
	public void testgetPassword() {
		String expected = "administrator";
		assertEquals(expected, setup.getPassword());
	}

	@Test
	public void testgetTimeout() {
		int expected = 5000;
		assertEquals(expected, setup.getTimeout());
	}

	@Test
	public void testgetMap_keys() {
		String[] expected = { "PC:payerClient", "AC:accountNumber", "AMNT:amount", "PCC:payerClientCode" };
		assertEquals(expected.length, setup.getMap_keys().length);
	}

	@Test
	public void testgetPost_url() {
		String expected = "http://localhost/simulator/index.php";
		assertEquals(expected, setup.getPost_url());
	}

	@Test
	public void testgetRequest_template_path() {
		String expected = "templates/IdealPrepaid-Request.xml";
		assertEquals(expected, setup.getRequest_template_path());
	}

	@Test
	public void testgetPort() {
		String expected = "15672";
		assertEquals(expected, setup.getPort());
		assertTrue(setup.getPort() instanceof String);
	}

	@Test
	public void testgetUri() {
		String expected = "amqp://admin:administrator@192.168.56.102";
		assertEquals(expected, setup.getUri());
	}

	@Test
	public void testgetQueue_name() {
		String expected = "test-Queue";
		assertEquals(expected, setup.getQueue_name());
	}

	@Test
	public void testgetUsername() {
		String expected = "admin";
		assertEquals(expected, setup.getUsername());
	}

	@Test
	public void testgetSelf() {
		assertTrue(Settings.getSelf() instanceof Settings);
	}

	@Test
	public void testgetApplicationname() {
		String expected = "ConsumerLab";
		assertEquals(expected, setup.getApplicationname());
	}

	@Test
	public void testgetCountrycode() {
		String expected = "MZ";
		assertEquals(expected, setup.getCountrycode());
	}

	@Test
	public void testloadSettings() {
		assertTrue(SettingsLoader.loadSettings() instanceof Settings);
		assertTrue(SettingsLoader.loadSettings("TestConfs/settings.xml") instanceof Settings);
	}
}