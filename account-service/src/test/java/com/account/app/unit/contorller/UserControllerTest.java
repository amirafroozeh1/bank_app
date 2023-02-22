package com.account.app.unit.contorller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.account.app.api.UserController;
import com.account.app.domain.User;
import com.account.app.domain.jsondata.CustomerJsonData;
import com.account.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    CustomerJsonData customerJsonData = new CustomerJsonData("amir", "afroozeh", "holland", "12345678");
    User user = new User("customerId", "amir", "afroozeh", "holland", "12345678", null);
    String userId = "customerId";

    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    @Test
    public void addUserTest() {
        ResponseEntity<String> result = userController.addUser(customerJsonData);

        verify(userService).addUser(any());
        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void getUserInfoWhenUserNotExistTest() {
        when(userService.findUser(userId)).thenReturn(Optional.empty());

        ResponseEntity<?> result = userController.getUserInfo(userId);

        verify(userService).findUser(userId);
        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    public void getUserInfoWhenUserExistTest() {
        when(userService.findUser(userId)).thenReturn(Optional.of(user));

        ResponseEntity<?> result = userController.getUserInfo(userId);

        verify(userService).findUser(any());
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(user, result.getBody());
    }
}
