package fr.sgcib.fatime.kata.account.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Customer {
    String name;
}
