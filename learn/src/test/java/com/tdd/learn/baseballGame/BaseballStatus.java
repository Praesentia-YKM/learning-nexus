package com.tdd.learn.baseballGame;

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

    @Override
    public String toString() {
        return this.value;
    }
}
