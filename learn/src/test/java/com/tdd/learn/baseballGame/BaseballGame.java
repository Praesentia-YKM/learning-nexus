package com.tdd.learn.baseballGame;

public class BaseballGame {

    private Batter batter;
    private Pitcher pitcher;
    private int strikes;
    private int balls;
    private int outs;

    public BaseballGame(Batter batter, Pitcher pitcher) {
        if(batter == null || pitcher == null) throw new NullPointerException();

        this.batter = batter;
        this.pitcher = pitcher;
        this.strikes = 0;
        this.balls = 0;
        this.outs = 0;
    }

    public String play() {
        if (outs == 3) {
            return "Game Over";
        }

        String answer = pitcher.getAnswer(); // 투수의 답
        if (answer.length() != 3) {
            throw new IllegalArgumentException("던질 숫자는 3자리수 숫자여야 합니다.");
        }

        // 타자가 추측한 숫자 (예: "123")
        String batterGuess = batter.getGuess(); // 타자가 입력한 숫자

        if (batterGuess.length() != 3) {
            throw new IllegalArgumentException("타자의 추측도 3자리수여야 합니다.");
        }

        // 초기화
        strikes = 0;
        balls = 0;
        outs = 0;

        // 스트라이크와 볼 계산
        for (int i = 0; i < 3; i++) {
            if (batterGuess.charAt(i) == answer.charAt(i)) {
                strikes++; // 동일한 위치에 있는 숫자는 스트라이크
            } else if (answer.contains(String.valueOf(batterGuess.charAt(i)))) {
                balls++; // 값은 일치하지만 위치가 다른 경우 볼
            } else {
                outs++;
            }
        }

        // 게임 상태 반환
        return String.format("%dS/%dB/%dO", strikes, balls, outs);
    }


    public Batter getBatter() {
        return batter;
    }

    public void setBatter(Batter batter) {
        this.batter = batter;
    }

    public Pitcher getPitcher() {
        return pitcher;
    }

    public void setPitcher(Pitcher pitcher) {
        this.pitcher = pitcher;
    }
}