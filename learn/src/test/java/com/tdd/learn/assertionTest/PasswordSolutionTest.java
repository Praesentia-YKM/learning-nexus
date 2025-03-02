package com.tdd.learn.assertionTest;

import org.junit.jupiter.api.Test;

import static com.tdd.learn.assertionTest.PasswordStrength.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordSolutionTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
    @Test
    void meetsOnlyUpperCreteria_Then_Weak() {
        assertStrength("absDef", WEAK);
    }

    @Test
    void meetsAllCreteria_Then_Strong() {
        // 대문자, 자릿수, 숫자까지 만족
        assertStrength("ab12!@AB", STRONG);
        assertStrength("abc1!Add", STRONG);
    }

    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", NORMAL);
    }

    @Test
    void meetsOtherCriteria_except_for_Number_Then_Normal() {
        // 숫자를 포함하지 않고 나머지 조건을 충족하는 경우 Normal
        assertStrength("ab!@ABqwer", NORMAL);
    }

    @Test
    void emptyInput_Then_Invalid() {
        assertStrength("", INVALID);
        assertStrength(null, INVALID);
    }

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }
}
