package com.tdd.learn.baseballGame;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BaseballGameTest {

    @Test
    public void testBaseballGame() {
        assertTrue(true, "야구게임 테스트");
    }

    @Test
    public void userGuessEveryNumber() {
        // 3 Strike test
        Pitcher pitcher = new Pitcher(new int[]{1, 2, 3});
        Batter batter = new Batter();
        BaseballGame game = new BaseballGame(pitcher, batter);

        String simulatedInput = "1\n2\n3\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        int[] guess = batter.guess();
        String result = game.play(guess);

        assertEquals("3S/0B/0O", result);
        System.setIn(System.in);
    }

    @Test
    public void userInputValidation() {
        // 올바른 입력 (중복 숫자: 1, 2, 3)
        String validInput = "1\n2\n3\n";
        InputStream in = new ByteArrayInputStream(validInput.getBytes());
        System.setIn(in);

        Batter batter = new Batter();
        int[] guess = batter.guess();
        assertTrue(inputValid(guess),"입력값이 유효합니다.");

        // 잘못된 입력 (중복 숫자: 1, 1, 3)
        String duplicateInput = "1\n1\n3\n";
        in = new ByteArrayInputStream(duplicateInput.getBytes());
        System.setIn(in);

        guess = batter.guess();
        assertFalse(inputValid(guess),"입력값이 유효하지 않습니다.");

        // 잘못된 입력 (음수 숫자: -1, -2, -3)
        String unavailableInput = "-1\n-2\n-3\n";
        in = new ByteArrayInputStream(unavailableInput.getBytes());
        System.setIn(in);

        guess = batter.guess();
        assertFalse(inputValid(guess),"입력값이 유효하지 않습니다.");

    }

    @Test
    public void testBallAndStrike() {
        // 타자가 볼, 스트라이크, 아웃을 제대로 구분하는지 테스트

        // 정답: 1, 2, 3
        Pitcher pitcher = new Pitcher(new int[]{1, 2, 3});
        Batter batter = new Batter();
        BaseballGame game = new BaseballGame(pitcher, batter);

        // 테스트 1: 0개 숫자 맞고 3개는 순서 틀림 -> 0S/3B/0O
        String input = "3\n1\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int[] guess = batter.guess();
        String result = game.play(guess);
        assertEquals("0S/3B/0O", result);

        // 테스트 2: 1개 숫자 맞고 나머지는 틀림 -> 1S/0B/2O
        input = "1\n4\n5\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        guess = batter.guess();
        result = game.play(guess);
        assertEquals("1S/0B/2O", result);

        // 테스트 3: 1개 숫자 맞고 1개는 숫자만 맞고 나머지는 틀림 -> 1S/1B/1O
        input = "1\n3\n4\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        guess = batter.guess();
        result = game.play(guess);
        assertEquals("1S/1B/1O", result);

        // 테스트 4: 모두틀림 -> 0B/0S/3O
        input = "6\n7\n8\n";  // 모두 아웃
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        guess = batter.guess();
        result = game.play(guess);
        assertEquals("0S/0B/3O", result);

        System.setIn(System.in);
    }

    @Test
    public void gameOverAfterMaxAttempts() {
        // 9회 시도 후 게임 종료 테스트
        Pitcher pitcher = new Pitcher(new int[]{1, 2, 3});
        Batter batter = new Batter();
        BaseballGame game = new BaseballGame(pitcher, batter);

        // 9회의 시도: 모두 틀린 입력 (1S/0B/2O) 방식으로 시뮬레이션
        String[] inputs = {
            "4\n5\n6\n", // 1st try
            "7\n8\n9\n", // 2nd try
            "1\n4\n5\n", // 3rd try
            "2\n4\n6\n", // 4th try
            "3\n5\n7\n", // 5th try
            "4\n5\n8\n", // 6th try
            "2\n1\n6\n", // 7th try
            "5\n8\n9\n", // 8th try
            "6\n7\n8\n"  // 9th try
        };

        for (String input : inputs) {
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            int[] guess = batter.guess();
            game.play(guess);
        }

        String result = game.play(new int[]{1, 2, 3});  // 마지막 시도 -> 맞춰도 종료
        assertEquals("게임 종료! 최대 시도 횟수를 초과했습니다.", result);
        System.setIn(System.in);
    }

    private boolean inputValid(int[] userGuess) {
        if (userGuess.length != 3) return false;

        Set<Integer> seen = new HashSet<>();
        for (int num : userGuess) {
            if (num < 0 || num > 9) return false;
            if (!seen.add(num)) return false;
        }

        return true;
    }
}
