package com.tdd.learn.baseballGame;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BaseballGameTest {

    private BaseballGame baseballGame;
    private Batter batter;
    private Pitcher pitcher;

    @BeforeEach
    public void setUp() {
        // 테스트가 실행될 때마다 새로운 Batter, Pitcher, BaseballGame 객체 생성
        batter = new Batter();
        pitcher = new Pitcher("123");  // 기본적인 답안으로 설정
        baseballGame = new BaseballGame(batter, pitcher);
    }

    @AfterEach
    public void tearDown() {
        // 테스트가 끝날 때마다 자원 정리
        baseballGame = null;
        batter = null;
        pitcher = null;
    }

    // 기본 게임 진행 관련 테스트
    @Test
    @DisplayName("타자가 3개의 스트라이크를 기록한 경우.")
    public void testThreeStrikes() {
        batter.setGuess("123"); // 타자의 추측
        // When
        String result = baseballGame.play();
        // Then
        assertEquals("3S/0B/0O", result, "타자가 3개의 스트라이크를 기록한 경우");
    }

    @Test
    @DisplayName("타자가 3개의 볼을 기록한 경우.")
    public void testThreeBalls() {
        pitcher.setAnswer("456");
        batter.setGuess("564");
        // When
        String result = baseballGame.play();
        // Then
        assertEquals("0S/3B/0O", result, "타자가 3개의 볼을 기록한 경우");
    }

    @Test
    @DisplayName("타자가 전부 아웃이 된 경우.")
    public void testAllOuts() {
        pitcher.setAnswer("789");
        batter.setGuess("123");
        // When
        String result = baseballGame.play();
        // Then
        assertEquals("0S/0B/3O", result, "타자가 전부 아웃이 된 경우");
    }

    @Test
    @DisplayName("타자가 스트라이크와 볼을 섞어서 기록한 경우.")
    public void testMixOfStrikesAndBalls() {
        pitcher.setAnswer("135");
        batter.setGuess("125");
        // When
        String result = baseballGame.play();
        // Then
        assertEquals("2S/0B/1O", result, "타자가 스트라이크와 볼을 섞어서 기록한 경우");
    }

    @Test
    @DisplayName("투수의 답안이 길이가 3이 아닌 경우 예외 터짐.")
    public void testPitcherAnswerWithInvalidLength() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Pitcher("12"), "던질 숫자는 3자리수 숫자여야 합니다.");
    }

    @Test
    @DisplayName("투수의 답안이 숫자가 아닌 문자를 포함한 경우 예외 터짐.")
    public void testPitcherAnswerWithInvalidCharacters() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Pitcher("abc"), "던질 숫자는 3자리수 숫자여야 합니다.");
    }

    @Test
    @DisplayName("투수의 답안이 빈 문자열일 경우 예외 터짐.")
    public void testPitcherAnswerWithEmptyString() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Pitcher(""), "던질 숫자는 3자리수 숫자여야 합니다.");
    }

    @Test
    @DisplayName("게임 시작 전에 Pitcher가 없는 경우 예외 터짐.")
    public void testGameWithNoPitcher() {
        assertThrows(
            NullPointerException.class,
            () -> new BaseballGame(null, new Pitcher("123")));
    }

    @Test
    @DisplayName("게임 시작 전에 Batter가 없는 경우 예외 터짐.")
    public void testGameWithNoBatter() {
        assertThrows(
            NullPointerException.class,
            () -> new BaseballGame(new Batter(), null));
    }

    @Test
    @DisplayName("게임 상태 초기화 상태 확인.")
    public void testGameInitialState() {
        batter.setGuess("123"); // 타자의 추측
        // When
        String result = baseballGame.play();
        // Then
        assertEquals("3S/0B/0O", result, "게임 상태 초기화 상태 확인");
    }

    @Test
    @DisplayName("게임이 종료된 후 상태 확인.")
    public void testGameStateAfterCompletion() {
        pitcher.setAnswer("567");  // 모든 타석에서 아웃이 발생하는 경우
        batter.setGuess("123");
        baseballGame.play();  // 게임 한 번 진행
        // When
        String result = baseballGame.play();  // 다시 게임을 진행
        // Then
        assertEquals("Game Over", result, "게임 종료 후 상태 확인");
    }

    @Test
    @DisplayName("게임에서 유효한 최대 답안 길이 체크.")
    public void testGameWithMaximumValidAnswerLength() {
        pitcher.setAnswer("987");
        batter.setGuess("987");
        // When
        String result = baseballGame.play();
        // Then
        assertEquals("3S/0B/0O", result, "게임에서 유효한 최대 답안 길이 체크");
    }

    @Test
    @DisplayName("게임에서 유효한 최대 값 체크.")
    public void testGameWithMaximumValidAnswerValue() {
        pitcher.setAnswer("987");
        batter.setGuess("987");
        // When
        String result = baseballGame.play();
        // Then
        assertEquals("3S/0B/0O", result, "게임에서 유효한 최대 값 체크");
    }

    @Test
    @DisplayName("게임에서 타자가 3개의 볼을 기록한 후 상태 확인.")
    public void testGameWithThreeBalls() {
        pitcher.setAnswer("123");
        batter.setGuess("231");

        // When
        String result = baseballGame.play();
        // Then
        assertEquals("0S/3B/0O", result, "게임에서 타자가 3개의 볼을 기록한 후 상태 확인");
    }
}
