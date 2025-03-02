package com.tdd.learn.baseballGame.numberBaseball;

public enum BaseballStatus {
    STRIKE("S"),
    BALL("B"),
    OUT("O");

    private final String value;

    BaseballStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
