package com.account.app.api;

import com.account.app.domain.User;
import com.account.app.domain.jsondata.CustomerJsonData;
import com.account.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody CustomerJsonData customerJsonData) {
        log.info("Adding a new User");
        User user = new User(customerJsonData.getFirstName(), customerJsonData.getLastName(), customerJsonData.getAddress(), customerJsonData.getPhoneNumber());
        String customerId = userService.addUser(user);
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(@PathVariable("id") String id) {
        log.info("Getting user info with id {}", id);
        Optional<User> user = userService.findUser(id);

        if (user.isEmpty()) {
            log.error("User with id {} not found", id);
            return new ResponseEntity<>("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
