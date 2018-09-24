package fr.sgcib.fatime.kata.account.domain;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class AccountTest {

    @Test
    public void should_has_an_id() {
        assertThat(Account.builder().id(10000L).build().getId(), instanceOf(Long.class));
    }

    @Test
    public void should_has_an_number() {
        assertThat(Account.builder().number("A123456B").build().getNumber(), instanceOf(String.class));
    }

    @Test
    public void should_has_a_balance() {
        assertThat(Account.builder().solde(2000L).build().getSolde(), instanceOf(Long.class));
    }

    @Test
    public void should_has_a_customer() {
        assertThat(Account.builder().customer(Customer.builder().build()).build().getCustomer(), instanceOf(Customer.class));
    }
}