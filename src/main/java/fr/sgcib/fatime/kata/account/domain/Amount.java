package fr.sgcib.fatime.kata.account.domain;

import fr.sgcib.fatime.kata.account.exception.AmountException;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Amount {
    Long amount;

    public Amount(Long amount) {
        this.amount = validate(amount);
    }

    private Long validate(Long deposit) {
        if (deposit > -1) {
            return deposit;
        }

        throw new AmountException("A amount should be positive.");
    }
}
