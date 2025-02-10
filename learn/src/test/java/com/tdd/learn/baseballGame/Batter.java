package com.tdd.learn.baseballGame;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.tdd.learn.baseballGame.GameUtil.inputValid;

/**
 * 타자는 숫자를 맞추기만 한다.
 */
public class Batter {

    public Batter() {
    }

    public int[] guess() {
        Scanner scanner = new Scanner(System.in);
        int[] userGuess = new int[3];

        System.out.println("세 개의 숫자를 입력하세요 (0 ~ 9까지의 숫자): ");

        while (true) {
            for (int i = 0; i < 3; i++) {
                userGuess[i] = scanner.nextInt();
            }

            if (inputValid(userGuess)) {
                break;
            } else {
                System.out.println("잘못된 입력입니다. 0~9 사이의 서로 다른 양수 3개를 입력해주세요.");
            }
        }

        return userGuess;
    }
}
