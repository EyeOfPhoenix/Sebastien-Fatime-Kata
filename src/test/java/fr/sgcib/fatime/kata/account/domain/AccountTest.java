package fr.sgcib.fatime.kata.account.domain;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class AccountTest {

    @Test
    public void should_has_an_id() {
        assertThat(Account.builder().id(10000L).build().getId(), instanceOf(Long.class));
    }
}