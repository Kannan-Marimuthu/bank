package com.kannan.sample.bank.service;

import java.util.List;

import com.kannan.sample.bank.vo.Account;
import com.kannan.sample.bank.vo.User;
import com.kannan.sample.util.Custom204Exception;

public interface BankService {

	User findUserByName(String userName) throws Custom204Exception;

	List<User> findAllUsers() throws Custom204Exception;

	List<Account> findAccountByUserName(String userName);

}
