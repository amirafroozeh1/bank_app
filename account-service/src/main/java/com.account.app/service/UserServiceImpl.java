package com.account.app.service;

import com.account.app.domain.Account;
import com.account.app.domain.User;
import com.account.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public String addUser(User user) {
        return userRepository.save(user).getCustomerId();
    }

    public Optional<User> findUser(String customerId) {
        return userRepository.findById(customerId);
    }

    public void addNewAccount(Account account) {
        User user = account.getUser();
        user.getAccounts().add(account);

        userRepository.save(user);
    }
}
