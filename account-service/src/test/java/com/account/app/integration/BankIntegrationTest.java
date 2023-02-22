package com.account.app.integration;

import com.account.app.domain.Transaction;
import com.account.app.domain.User;
import com.account.app.domain.jsondata.AccountJsonData;
import com.account.app.domain.jsondata.CustomerJsonData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankIntegrationTest {

    public static final String BASE_URL = "http://localhost";

    @Value("${local.server.port}")
    private int serverPort;
    private String userUrl;
    private String accountUrl;
    private String transactionUrl;
    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        userUrl = BASE_URL.concat(":").concat(serverPort + "").concat("/api/user/");
        accountUrl = BASE_URL.concat(":").concat(serverPort + "").concat("/api/account/");
        transactionUrl = BASE_URL.concat(":").concat(8082 + "").concat("/api/transaction/");
    }

    @Test
    public void openNewAccountAndGetUserAndTransactionInfo() throws InterruptedException {
        CustomerJsonData customerJsonData_one = new CustomerJsonData("amir", "afroozeh", "nl", "123");
        CustomerJsonData customerJsonData_two = new CustomerJsonData("rob", "visser", "nl", "456");
        CustomerJsonData customerJsonData_three = new CustomerJsonData("xie", "woo", "china", "789");

        String customerId_one = restTemplate.postForObject(userUrl, customerJsonData_one, String.class);
        String customerId_two = restTemplate.postForObject(userUrl, customerJsonData_two, String.class);
        String customerId_three = restTemplate.postForObject(userUrl, customerJsonData_three, String.class);

        AccountJsonData account_one = new AccountJsonData(customerId_one, BigDecimal.ZERO);
        AccountJsonData account_two = new AccountJsonData(customerId_two, BigDecimal.ONE);
        AccountJsonData account_three = new AccountJsonData(customerId_three, BigDecimal.TEN);

        String accountId_one = restTemplate.postForObject(accountUrl, account_one, String.class);
        String accountId_two = restTemplate.postForObject(accountUrl, account_two, String.class);
        String accountId_three = restTemplate.postForObject(accountUrl, account_three, String.class);

        Thread.sleep(20000);

        User user_one = restTemplate.getForObject(userUrl + customerId_one, User.class);

        assertEquals(customerId_one, user_one.getCustomerId());
        assertEquals("amir", user_one.getFirstName());
        assertEquals("afroozeh", user_one.getLastName());
        assertEquals("nl", user_one.getAddress());
        assertEquals("123", user_one.getPhoneNumber());
        assertEquals(accountId_one, user_one.getAccounts().get(0).getAccountId());
        assertEquals(account_one.getInitialCredit().intValue(), user_one.getAccounts().get(0).getBalance().intValue());

        User user_two = restTemplate.getForObject(userUrl + customerId_two, User.class);

        assertEquals(customerId_two, user_two.getCustomerId());
        assertEquals("rob", user_two.getFirstName());
        assertEquals("visser", user_two.getLastName());
        assertEquals("nl", user_two.getAddress());
        assertEquals("456", user_two.getPhoneNumber());
        assertEquals(accountId_two, user_two.getAccounts().get(0).getAccountId());
        assertEquals(account_two.getInitialCredit().intValue(), user_two.getAccounts().get(0).getBalance().intValue());

        User user_three = restTemplate.getForObject(userUrl + customerId_three, User.class);

        assertEquals(customerId_three, user_three.getCustomerId());
        assertEquals("xie", user_three.getFirstName());
        assertEquals("woo", user_three.getLastName());
        assertEquals("china", user_three.getAddress());
        assertEquals("789", user_three.getPhoneNumber());
        assertEquals(accountId_three, user_three.getAccounts().get(0).getAccountId());
        assertEquals(account_three.getInitialCredit().intValue(), user_three.getAccounts().get(0).getBalance().intValue());

        Transaction[] transaction_one =
                restTemplate.getForObject(
                        transactionUrl + customerId_one,
                        Transaction[].class);

        // No transaction when initial credit is zero
        assertEquals(transaction_one.length, 0);

        Transaction[] transaction_two =
                restTemplate.getForObject(
                        transactionUrl + customerId_two,
                        Transaction[].class);

        assertEquals(customerId_two, transaction_two[0].getCustomerId());
        assertEquals(accountId_two, transaction_two[0].getCreditAccountId());
        assertEquals(accountId_two, transaction_two[0].getDebitAccountId());
        assertEquals(1, transaction_two[0].getAmount().intValue());
        assertEquals("Opening a new account", transaction_two[0].getDescription());

        Transaction[] transaction_three =
                restTemplate.getForObject(
                        transactionUrl + customerId_three,
                        Transaction[].class);

        assertEquals(customerId_three, transaction_three[0].getCustomerId());
        assertEquals(accountId_three, transaction_three[0].getCreditAccountId());
        assertEquals(accountId_three, transaction_three[0].getDebitAccountId());
        assertEquals(10, transaction_three[0].getAmount().intValue());
        assertEquals("Opening a new account", transaction_three[0].getDescription());
    }
}
