package com.account.app.domain.jsondata;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class TransactionJsonData {
    String customerId;
    String creditAccountId;
    String debitAccountId;
    BigDecimal amount;
    String description;
}
