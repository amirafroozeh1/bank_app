package com.account.app.api;

import com.account.app.domain.Account;
import com.account.app.domain.User;
import com.account.app.domain.jsondata.AccountJsonData;
import com.account.app.domain.jsondata.TransactionJsonData;
import com.account.app.service.TransactionService;
import com.account.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final TransactionService transactionService;

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity<String> openNewAccount(@RequestBody AccountJsonData accountJsonData) {
        String customerId = accountJsonData.getCustomerId();
        log.info("Opening new account for customerId {}", customerId);

        Optional<User> foundUser = userService.findUser(customerId);
        if (foundUser.isEmpty()) {
            log.error("User with id {} not found", customerId);
            return new ResponseEntity<>("User with id " + customerId + " not found", HttpStatus.NOT_FOUND);
        }

        log.info("User with id {} found", customerId);
        Account newAccount = new Account(foundUser.get());

        log.info("Add a new account to user with customer id {}", customerId);
        userService.addNewAccount(newAccount);

        if (accountJsonData.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            log.info("Sent transaction message");

            TransactionJsonData transactionJsonData = new TransactionJsonData(customerId, newAccount.getAccountId(), newAccount.getAccountId(), accountJsonData.getInitialCredit(), "Opening a new account");
            transactionService.sendTransactionMessage(transactionJsonData);
        }

        return new ResponseEntity<>(newAccount.getAccountId(), HttpStatus.CREATED);
    }
}
