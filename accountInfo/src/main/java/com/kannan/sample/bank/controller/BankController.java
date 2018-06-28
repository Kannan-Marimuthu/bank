package com.kannan.sample.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kannan.sample.bank.service.BankService;
import com.kannan.sample.bank.util.Custom204Exception;
import com.kannan.sample.bank.util.CustomResponseEntity;
import com.kannan.sample.bank.vo.User;

@RestController
@RequestMapping("/api")
public class BankController {

	@Autowired
	private BankService bankService;

	@RequestMapping(value = "/accountinfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> accountsInfo() throws Custom204Exception {
		List<User> userList = bankService.findAllUsers();
		if (userList.isEmpty()) {
			throw new Custom204Exception("User Detetails not found");
		}
		return buildResponse(userList);
	}

	@RequestMapping(value = "/accountinfo/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> accountsInfo(@PathVariable("name") String name) throws Custom204Exception {
		User user = bankService.findUserByName(name);
		if (user == null) {
			throw new Custom204Exception("User details not found for user name :" + name);
		}
		return buildResponse(user);
	}

	private ResponseEntity<?> buildResponse(Object userInfo) {
		CustomResponseEntity response = new CustomResponseEntity(HttpStatus.OK);
		response.setMessage("Success");
		response.setData(userInfo);
		return new ResponseEntity<>(response, response.getStatus());
	}
}
