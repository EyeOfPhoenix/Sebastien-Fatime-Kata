package fr.sgcib.fatime.kata.account.domain;


import com.sun.istack.internal.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@Builder
public class Account {

    @Id
    @GeneratedValue
    private final Long id;

    @NotNull
    private final String number;

    private final Long solde;

    @OneToOne(fetch = LAZY)
    private final Customer customer;
}
