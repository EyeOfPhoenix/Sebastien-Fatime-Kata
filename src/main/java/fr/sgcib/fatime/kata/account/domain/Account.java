package fr.sgcib.fatime.kata.account.domain;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@Builder
@ToString
public class Account {

    @Id
    private final Long id;

    @NotNull
    private final String number;

    private final Long solde;

    @OneToOne(cascade = ALL, fetch = LAZY)
    @MapsId
    private Customer customer;
}
