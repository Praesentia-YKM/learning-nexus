package com.tdd.learn.baseballGame;

import java.util.HashSet;
import java.util.Set;

public class GameUtil {

    public static boolean inputValid(int[] userGuess) {
        // 숫자 길이가 3인지 확인
        if (userGuess.length != 3) return false;

        // 0~9 사이의 숫자만 있고, 중복이 없어야 함
        Set<Integer> seen = new HashSet<>();
        for (int num : userGuess) {
            // 숫자가 0~9 사이인지 확인
            if (num < 0 || num > 9) return false;

            // 중복된 숫자가 있는지 확인
            if (!seen.add(num)) return false;
        }

        return true;
    }

    public static boolean isContainsNumber(int[] array, int value) {
        for (int num : array) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }
}
