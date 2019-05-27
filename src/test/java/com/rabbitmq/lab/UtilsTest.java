package com.rabbitmq.lab;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

import com.rabbitmq.utils.Utils;

/**
 * UtilsTest
 */
public class UtilsTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testPrelogString() {
		fail("Un implimented");
	}

	@Test
	public void testGetCodelineNumber() {
		int expected = Utils.getCodelineNumber() + 1;
		assertEquals(expected, Utils.getCodelineNumber());
	}

	@Test
	public void testStringToJson() {
		String expected = "{key:value}";
		assertTrue(Utils.stringToJson(expected) instanceof JSONObject);
	}

	@Test
	public void testRmNewlineTab() {
		String expected = "Replace newline and tab with spaces";
		String input = "Replace\n newline\t\n and\t tab\t with\n spaces";
		assertEquals(expected, Utils.rmNewlineTab(input));
	}

	@Test
	public void testXmlToJson() {
		String xmlInput = "<header><title>title</title><body>request</body></header>";
		JSONObject expected = new JSONObject("{header:{title:title,body:request}}");
		assertEquals(expected.toString(), Utils.xmlToJson(xmlInput).toString());
	}

	@Test
	public void testReadFile() {
		assertTrue(Utils.readFile("templates/IdealPrepaid-Request.xml", StandardCharsets.UTF_8) instanceof String);
		exception.expect(IllegalArgumentException.class);
		assertTrue(Utils.readFile("path", StandardCharsets.UTF_8) instanceof String);
	}

	@Test
	public void testDateToString() {
		int expected = 2;
		assertEquals(expected, Utils.dateToString().length);
	}

	@Test
	public void testPayloadForm() {
		fail("Un implimented");
	}

	@Test
	public void testUpdatedPostMethod() {
		fail("Un implimented");
	}

	@Test
	public void testResponseProcess() {
		fail("Un implimented");
	}

	@Test
	public void testCreateSubstrings() {
		fail("Un implimented");
	}
}