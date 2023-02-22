package com.account.app.unit.service;

import com.account.app.domain.Account;
import com.account.app.domain.User;
import com.account.app.repository.UserRepository;
import com.account.app.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    User user = new User("amir", "afroozeh", "holland", "12345678");
    User savedUser = new User("customerId", "amir", "afroozeh", "holland", "12345678", new ArrayList<>());
    Account account = new Account("newAccountId", savedUser, BigDecimal.TEN);

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    public void addUserTest() {
        when(userRepository.save(user)).thenReturn(savedUser);

        String savedUserId = userServiceImpl.addUser(user);

        assertEquals("customerId", savedUserId);
        verify(userRepository).save(user);
    }

    @Test
    public void findUserTest() {
        when(userRepository.findById("customerId")).thenReturn(Optional.of(savedUser));

        Optional<User> foundUser = userServiceImpl.findUser("customerId");

        assertEquals(savedUser, foundUser.get());
        verify(userRepository).findById("customerId");
    }

    @Test
    public void addNewAccountTest() {
        userServiceImpl.addNewAccount(account);

        verify(userRepository).save(any());
    }
}
