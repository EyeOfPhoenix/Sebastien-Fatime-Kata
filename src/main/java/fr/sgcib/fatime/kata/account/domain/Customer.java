package fr.sgcib.fatime.kata.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Customer {
    String name;
    String firstname;
    Account account;
}
