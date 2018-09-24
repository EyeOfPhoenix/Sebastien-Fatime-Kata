package fr.sgcib.fatime.kata.account.domain;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void should_has_a_name() {
        assertThat(Customer.builder().name("Fatime").build().getName(), IsEqual.equalTo("Fatime"));
    }

    @Test
    public void should_has_a_firstname() {
        assertThat(Customer.builder().firstname("Sébastien").build().getFirstname(), IsEqual.equalTo("Sébastien"));
    }
}
