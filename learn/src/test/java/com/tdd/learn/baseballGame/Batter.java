package com.tdd.learn.baseballGame;

public class Batter {
    private String guess;

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        if (guess == null || guess.length() != 3 || !guess.matches("\\d{3}")) {
            throw new IllegalArgumentException("타자의 추측도 3자리 숫자여야 합니다.");
        }
        this.guess = guess;
    }
}

