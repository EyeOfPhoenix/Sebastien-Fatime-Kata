package fr.sgcib.fatime.kata.account.domain;

import fr.sgcib.fatime.kata.account.exception.DepositException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DepositTest {

    @Test
    public void should_has_a_deposit() {
        Deposit deposit = Deposit.builder().deposit(1000L).build();

        assertThat(deposit.getDeposit()).isInstanceOf(Long.class);
    }

    @Test
    public void should_validate_a_deposit_is_positif() {
        Deposit deposit = Deposit.builder().deposit(1000L).build();

        assertThat(deposit.getDeposit()).isGreaterThan(-1);
    }

    @Test
    public void should_throw_exception_when_deposit_is_negative() throws DepositException {
        assertThatThrownBy(() -> Deposit.builder().deposit(-1000L).build())
                .isInstanceOf(DepositException.class)
                .hasMessage("A deposit should be positive.");
    }
}
