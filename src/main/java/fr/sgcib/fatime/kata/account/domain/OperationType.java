package fr.sgcib.fatime.kata.account.domain;

public enum OperationType {
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL");

    private String value;

    OperationType(String value) {
        this.value = value;
    }
}
