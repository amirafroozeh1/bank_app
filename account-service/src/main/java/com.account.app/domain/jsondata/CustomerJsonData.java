package com.account.app.domain.jsondata;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerJsonData {
    String firstName;
    String lastName;
    String address;
    String phoneNumber;
}
