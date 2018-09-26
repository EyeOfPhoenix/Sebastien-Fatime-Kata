package fr.sgcib.fatime.kata.account.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationTest {

    @Test
    public void should_has_an_id() {
        assertThat(Operation.builder().id(1L).build().getId()).isInstanceOf(Long.class);
    }
}
