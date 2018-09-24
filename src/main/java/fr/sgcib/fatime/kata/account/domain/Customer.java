package fr.sgcib.fatime.kata.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Value
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    Long id;

    String name;
    String firstname;

    @OneToOne(fetch = LAZY)
    Account account;
}
