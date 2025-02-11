package com.tdd.learn.spring.numberBaseball;

import java.util.Scanner;

import static com.tdd.learn.spring.numberBaseball.GameUtil.inputValid;

/**
 * 타자는 숫자를 맞추기만 한다.
 */
public class Batter {

    private int[] userGuess;

    public Batter() {
    }

    public int[] guess() {
        userGuess = null;

        Scanner scanner = new Scanner(System.in);
        userGuess = new int[3];

        System.out.println("세 개의 숫자를 공백으로 구분하여 입력하세요 (0 ~ 9까지의 서로 다른 숫자): ");

        while (true) {
            try {
                String input = scanner.nextLine();

                String[] parts = input.split(" ");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("3개의 숫자를 입력해야 합니다.");
                }

                for (int i = 0; i < 3; i++) {
                    userGuess[i] = Integer.parseInt(parts[i]);
                }

                if (inputValid(userGuess)) {
                    break;
                } else {
                    System.out.println("잘못된 입력입니다. 0~9 사이의 서로 다른 숫자 3개를 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해야 합니다. 다시 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
        return userGuess;
    }

    public int[] getUserGuess() {
        return userGuess;
    }
}
