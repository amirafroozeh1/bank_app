package com.account.app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "accounts"})
@ToString(exclude = { "accounts"})
public class User {
    @Id
    String customerId;
    String firstName;
    String lastName;
    String address;
    String phoneNumber;
    @OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Account> accounts;

    public User(String firstName, String lastName, String address, String phoneNumber) {
        this.customerId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
