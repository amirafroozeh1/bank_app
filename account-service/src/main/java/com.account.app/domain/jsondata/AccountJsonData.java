package com.account.app.domain.jsondata;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountJsonData {
    String customerId;
    BigDecimal initialCredit;
}
