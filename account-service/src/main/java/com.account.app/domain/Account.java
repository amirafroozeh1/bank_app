package com.account.app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    String accountId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    User user;
    BigDecimal balance;

    public Account(User user) {
        this.accountId = UUID.randomUUID().toString();
        this.balance = BigDecimal.ZERO;
        this.user = user;
    }
}
