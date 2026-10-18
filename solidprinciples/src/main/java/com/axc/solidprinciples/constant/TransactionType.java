package com.axc.solidprinciples.constant;

public enum TransactionType {

    DEPOSIT("Money deposited"),
    WITHDRAWAL("Money withdrawn"),
    TRANSFER("Money transferred");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {

        return description;
    }

}
