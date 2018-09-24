package fr.sgcib.fatime.kata.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@ToString(exclude = {"account"})
public class Customer {
    @Id
    @GeneratedValue
    Long id;

    String name;
    String firstname;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    Account account;
}
