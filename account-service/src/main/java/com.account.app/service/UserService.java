package com.account.app.service;

import com.account.app.domain.Account;
import com.account.app.domain.User;

import java.util.Optional;

public interface UserService {
    String addUser(User user);
    Optional<User> findUser(String customerId);
    void addNewAccount(Account account);
}
