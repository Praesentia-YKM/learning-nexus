package com.tdd.learn.spring.numberBaseball;

import static com.tdd.learn.spring.numberBaseball.GameUtil.isContainsNumber;

public class BaseballGame {

    private final Pitcher pitcher;
    private final Batter batter;
    private int attempts;

    public BaseballGame(Pitcher pitcher, Batter batter) {
        this.pitcher = pitcher;
        this.batter = batter;
        this.attempts = 0;
    }

    public void play() {

        int loop = 0;

        while(loop > 9) {
            batter.guess();

            if (attempts >= 9) {
                return;
            }

            int[] answer = pitcher.getAnswer();
            int strike = 0, ball = 0, out = 0;

            // 타자가 던진 숫자와 투수의 숫자를 비교
            for (int i = 0; i < batter.getUserGuess().length; i++) {
                if (batter.getUserGuess()[i] == answer[i]) {
                    strike++;
                } else if (isContainsNumber(answer, batter.getUserGuess()[i])) {
                    ball++;
                } else {
                    out++;
                }
            }

            attempts++;

            // 3Strike가 맞으면 게임 종료
            if (strike == 3) {
                System.out.println("전부다 스트라이크! 게임 종료");
                break;
            }

            loop++;

            System.out.println(strike + "S/" + ball + "B/" + out + "O");
        }
    }

    public int getAttempts() {
        return attempts;
    }
}
