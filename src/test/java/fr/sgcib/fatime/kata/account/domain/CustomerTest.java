package fr.sgcib.fatime.kata.account.domain;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void should_has_a_name() {
        assertThat(Customer.builder().name("Fatime").build().getName(), instanceOf(String.class));
    }

    @Test
    public void should_has_a_firstname() {
        assertThat(Customer.builder().firstname("Sébastien").build().getFirstname(), instanceOf(String.class));
    }

    @Test
    public void should_has_an_account() {
        assertThat(Customer.builder().account(Account.builder().build()).build().getAccount(), instanceOf(Account.class));
    }

    @Test
    public void should_has_an_id() {
        assertThat(Customer.builder().account(Account.builder().build()).build().getAccount(), instanceOf(Account.class));
    }
}
