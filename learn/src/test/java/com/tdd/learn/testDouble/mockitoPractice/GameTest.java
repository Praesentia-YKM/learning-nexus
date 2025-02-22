package com.tdd.learn.testDouble.mockitoPractice;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;

public class GameTest {

    @Test
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY); // game.init 함수 안에서 generate 호출함

        // Mock이 GameLevel.EASY를 인자로 하는 generate함수를 단 한번만 호출 하는지 검증
        then(genMock).should(only()).generate(GameLevel.EASY);
    }
}
