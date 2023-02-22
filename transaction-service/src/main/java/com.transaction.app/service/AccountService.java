package com.transaction.app.service;

import java.math.BigDecimal;

public interface AccountService {
    void updateAccountBalance(String accountId, BigDecimal amount);
}
