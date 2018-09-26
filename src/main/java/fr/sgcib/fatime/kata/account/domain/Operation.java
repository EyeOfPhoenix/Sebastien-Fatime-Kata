package fr.sgcib.fatime.kata.account.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@Builder
public class Operation {
    @Id
    @GeneratedValue
    private final Long id;

    private final OperationType operationType;
    private final Date date;
    private final Long amount;

    @ManyToOne(fetch = LAZY)
    private final Account account;
}
