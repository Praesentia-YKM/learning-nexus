package com.tdd.learn.baseballGame;

import java.util.Scanner;

/**
 * 투수는 숫자를 던지는 역할을 한다.
 */
public class Pitcher {

    private final int[] answer;

    public Pitcher(int[] answer) {
        this.answer = answer;
    }

    public int[] getAnswer() {
        return answer;
    }
}
