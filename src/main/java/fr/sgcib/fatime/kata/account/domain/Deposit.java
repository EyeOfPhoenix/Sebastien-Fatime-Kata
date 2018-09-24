package fr.sgcib.fatime.kata.account.domain;

import fr.sgcib.fatime.kata.account.exception.DepositException;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Deposit {
    Long deposit;

    public Deposit(Long deposit) {
        this.deposit = validate(deposit);
    }

    private Long validate(Long deposit) {
        if (deposit > -1) {
            return deposit;
        }

        throw new DepositException("A deposit should be positive.");
    }
}
