package com.kannan.sample.bank.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kannan.sample.bank.util.Custom204Exception;
import com.kannan.sample.bank.util.CustomResponseEntity;
import com.kannan.sample.bank.vo.Account;
import com.kannan.sample.bank.vo.User;

@Service
public class BankServiceImpl implements BankService {

	@Value("${bank.user.uri}")
	public String USER_URI;
	@Value("${bank.account.uri}")
	public String ACCOUNT_URI;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User findUserByName(String userName) throws Custom204Exception {
		User user = new User();
		try {
			CustomResponseEntity response = restTemplate.getForObject(USER_URI + "/" + userName,
					CustomResponseEntity.class);

			if (response != null) {
				List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>) response.getData();
				for (LinkedHashMap<String, Object> linkedHashMap : usersMap) {
					user.setId((int) linkedHashMap.get("id"));
					user.setUserName((String) linkedHashMap.get("userName"));
					user.setStatus((String) linkedHashMap.get("status"));
					List<Account> accountDetails = findAccountByUserName(user.getUserName());
					if (!accountDetails.isEmpty())
						user.setAccountDetails(accountDetails);
				}
			} else {
				throw new Custom204Exception("No Data found for the User Name :" + userName);
			}
		} catch (RestClientException e) {
			throw new Custom204Exception("No Data found for the User Name :" + userName);
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() throws Custom204Exception {
		List<User> userList = new ArrayList<>();
		try {
			CustomResponseEntity response = restTemplate.getForObject(USER_URI, CustomResponseEntity.class);
			if (response != null) {
				List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>) response.getData();
				for (LinkedHashMap<String, Object> linkedHashMap : usersMap) {
					User user = new User();
					user.setId((int) linkedHashMap.get("id"));
					user.setUserName((String) linkedHashMap.get("userName"));
					user.setStatus((String) linkedHashMap.get("status"));
					List<Account> accountDetails = findAccountByUserName(user.getUserName());
					if (!accountDetails.isEmpty())
						user.setAccountDetails(accountDetails);
					userList.add(user);
				}
			} else {
				throw new Custom204Exception("No Data found");
			}
		} catch (RestClientException e) {
			throw new Custom204Exception("No Data found");
		}
		return userList;
	}

	@Override
	public List<Account> findAccountByUserName(String userName) {
		CustomResponseEntity response = restTemplate.getForObject(ACCOUNT_URI + "/" + userName,
				CustomResponseEntity.class);
		List<Account> l = (List<Account>) response.getData();
		return l;
	}

}
