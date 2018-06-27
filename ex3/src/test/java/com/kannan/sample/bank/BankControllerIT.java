package com.kannan.sample.bank;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankControllerIT {

	private static final String HTTP_LOCALHOST = "http://localhost:";
	private static String successResponse = "{\"status\":\"OK\",\"message\":\"Success\",\"data\":{\"id\":1,\"userName\":\"Kannan\",\"status\":\"Active\",\"accountDetails\":[{\"id\":1,\"accountNumber\":1001,\"userName\":\"Kannan\",\"type\":\"S\",\"balance\":15000},{\"id\":4,\"accountNumber\":1002,\"userName\":\"Kannan\",\"type\":\"C\",\"balance\":1200}]}}";
	private static String failureResponse = "{\"status\":\"NO_CONTENT\",\"message\":\"Service Error\",\"data\":null}";

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveAccountInfo() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/accountinfo/Kannan"),
				HttpMethod.GET, entity, String.class);
		JSONAssert.assertEquals(successResponse, response.getBody(), false);
	}

	@Test
	public void testRetrieveInvalidAccountInfo() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/accountinfo/Kannan1"),
				HttpMethod.GET, entity, String.class);
		JSONAssert.assertEquals(failureResponse, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return HTTP_LOCALHOST + port + uri;
	}
}
