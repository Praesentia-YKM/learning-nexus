package com.tdd.learn.baseballGame;

public class Pitcher {

    private String answer;

    public Pitcher(String answer) {
        if (answer == null || answer.length() != 3 || !answer.matches("\\d{3}")) {
            throw new IllegalArgumentException("던질 숫자는 3자리수 숫자여야 합니다.");
        }
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        if (answer == null || answer.length() != 3 || !answer.matches("\\d{3}")) {
            throw new IllegalArgumentException("던질 숫자는 3자리수 숫자여야 합니다.");
        }
        this.answer = answer;
    }
}
