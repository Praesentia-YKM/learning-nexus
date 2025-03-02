package com.tdd.learn.assertionTest;


import static com.tdd.learn.assertionTest.PasswordStrength.*;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return INVALID;

        // TODO 길이 만족하면서 나머지 조건은 다 만족하지 않으면 WEAK 리턴
        if(s.length() < 8) {
            return NORMAL;
        }

        boolean containsNum = meetsContainingNumber(s);

        if(!containsNum) return NORMAL;
        return STRONG;
    }

    private static boolean meetsContainingNumber(String s) {
        for (char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
