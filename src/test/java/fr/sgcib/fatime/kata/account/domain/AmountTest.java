package fr.sgcib.fatime.kata.account.domain;

import fr.sgcib.fatime.kata.account.exception.AmountException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AmountTest {

    @Test
    public void should_has_a_deposit() {
        Amount amount = Amount.builder().amount(1000L).build();

        assertThat(amount.getAmount()).isInstanceOf(Long.class);
    }

    @Test
    public void should_validate_a_deposit_is_positif() {
        Amount amount = Amount.builder().amount(1000L).build();

        assertThat(amount.getAmount()).isGreaterThan(-1);
    }

    @Test
    public void should_throw_exception_when_deposit_is_negative() throws AmountException {
        assertThatThrownBy(() -> Amount.builder().amount(-1000L).build())
                .isInstanceOf(AmountException.class)
                .hasMessage("A deposit should be positive.");
    }
}
