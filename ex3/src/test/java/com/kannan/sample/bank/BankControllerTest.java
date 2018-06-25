package com.kannan.sample.bank;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerTest {

	private static String successResponse = "{\"status\":\"OK\",\"message\":\"Success\",\"data\":{\"id\":1,\"userName\":\"Kannan\",\"status\":\"Active\",\"accountDetails\":[{\"id\":1,\"accountNumber\":1001,\"userName\":\"Kannan\",\"type\":\"S\",\"balance\":15000},{\"id\":4,\"accountNumber\":1002,\"userName\":\"Kannan\",\"type\":\"C\",\"balance\":1200}]}}";
	private static String failureResponse = "{\"status\":\"NO_CONTENT\",\"message\":\"No Data found for the User Name :Kannan1\",\"data\":null}";
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnSuccessMessage() throws Exception {
		this.mockMvc.perform(get("/api/accountinfo")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Success")));
	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/api/accountinfo/Kannan")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Kannan")));
	}

	@Test
	public void shouldReturnSuccess() throws Exception {
		this.mockMvc.perform(get("/api/accountinfo/Kannan")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Success"))).andExpect(content().string(successResponse));
	}

	@Test
	public void shouldReturnFailure() throws Exception {
		this.mockMvc.perform(get("/api/accountinfo/Kannan1")).andDo(print()).andExpect(status().is4xxClientError())
				.andExpect(content().string(failureResponse));
	}
}
