package com.transaction.app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    String customerId;
    String firstName;
    String lastName;
    String address;
    String phoneNumber;
    @OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Account> accounts;
}
