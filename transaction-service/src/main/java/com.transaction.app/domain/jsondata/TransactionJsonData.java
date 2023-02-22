package com.transaction.app.domain.jsondata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TransactionJsonData {
    String customerId;
    String creditAccountId;
    String debitAccountId;
    BigDecimal amount;
    String description;
}
