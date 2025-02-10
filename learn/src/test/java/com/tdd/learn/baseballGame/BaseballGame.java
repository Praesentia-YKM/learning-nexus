package com.tdd.learn.baseballGame;

import static com.tdd.learn.baseballGame.GameUtil.isContainsNumber;

public class BaseballGame {

    private final Pitcher pitcher;
    private final Batter batter;
    private int attempts;

    public BaseballGame(Pitcher pitcher, Batter batter) {
        this.pitcher = pitcher;
        this.batter = batter;
        this.attempts = 0;
    }

    public String play(int[] userGuess) {
        if (attempts >= 9) {
            return "게임 종료! 최대 시도 횟수를 초과했습니다.";
        }

        int[] answer = pitcher.getAnswer();
        int strike = 0, ball = 0, out = 0;

        // 타자가 던진 숫자와 투수의 숫자를 비교
        for (int i = 0; i < userGuess.length; i++) {
            if (userGuess[i] == answer[i]) {
                strike++;
            } else if (isContainsNumber(answer, userGuess[i])) {
                ball++;
            } else {
                out++;
            }
        }

        attempts++;

        // 3Strike가 맞으면 게임 종료
        if (strike == 3) {
            return "게임 종료! 정답을 맞추었습니다!";
        }

        return strike + "S/" + ball + "B/" + out + "O";
    }

    public int getAttempts() {
        return attempts;
    }
}
