package com.transaction.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

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

    public Transaction(String customerId, String creditAccountId,
                       String debitAccountId,
                       BigDecimal amount,
                       String description) {
        this.transactionId = UUID.randomUUID().toString();
        this.customerId = customerId;
                this.creditAccountId = creditAccountId.toString();
        this.debitAccountId = debitAccountId.toString();
        this.amount = amount;
        this.description = description;
    }
}
