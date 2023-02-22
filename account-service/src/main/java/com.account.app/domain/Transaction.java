package com.account.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    String transactionId;
    String customerId;
    String creditAccountId;
    String debitAccountId;
    BigDecimal amount;
    String description;
}
