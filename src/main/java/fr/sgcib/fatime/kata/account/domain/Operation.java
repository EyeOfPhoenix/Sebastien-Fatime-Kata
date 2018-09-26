package fr.sgcib.fatime.kata.account.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

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
}
