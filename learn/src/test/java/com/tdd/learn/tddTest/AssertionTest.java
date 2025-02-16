package com.tdd.learn.tddTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class AssertionTest {

    @Test
    public void assertEquals는_실제값과_기대하는값이_같음을_비교한다() {
        LocalDate dateTime1 = LocalDate.now();
        LocalDate dateTime2 = LocalDate.now();
        assertEquals(dateTime1, dateTime2);
    }

    @Test
    public void assertNotEquals는_실제값과_기대하는값이_같지않음을_비교한다() {
        LocalDate dateTime1 = LocalDate.of(2025, 02, 16);
        LocalDate dateTime2 = LocalDate.of(2024, 02, 16);
        assertNotEquals(dateTime1, dateTime2);
    }

    @Test
    public void assertTrue_assertFalse는_각각_조건이_참또는거짓임을_검증한다() {
        assertTrue(1==1);
        assertFalse(1!=1);
    }

    @Test
    public void assertNull_assertNotNull는_각각_조건이_Null인지아닌지_검증한다() {
        assertNull(null);
        assertNotNull("");
    }

    @Test
    public void assertArrayEquals는_기대하는배열과_실제배열의값이_서로동일한지비교한다_동등성비교() {
        // ✅ 테스트 통과 (동등성 비교)
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);

        // ❌ 테스트 실패 (3 != 4)
        int[] expected2 = {1, 2, 3};
        int[] actual2 = {1, 2, 4};
        assertArrayEquals(expected2, actual2);
    }

    @Test
    public void assertSame는_기대하는배열과_실제배열이_서로동일한지비교한다_동일성비교() {

        // ✅ 동일성 비교 (메모리 주소 비교)
        int[] expected = {1, 2, 3};
        int[] actual = expected;
        assertSame(expected, actual);

        // ✅ 메모리 주소가 다름 -> assertSame으로 하면 FAIL
        int[] expected2 = {1, 2, 3};
        int[] actual2 = {1, 2, 3};
        assertNotSame(expected2, actual2);
    }

    @Test
    public void assertIterableEquals는_Iterable한자료구조에대하여_서로동일한지비교한다() {
        // ✅ List 비교 (순서 중요)
        List<String> expectedList = List.of("A", "B", "C");
        List<String> actualList = Arrays.asList("A", "B", "C");
        assertIterableEquals(expectedList, actualList, "List 비교 실패!");

        // ✅ LinkedList 비교 (순서 중요)
        LinkedList<String> actualLinkedList = new LinkedList<>(List.of("A", "B", "C"));
        assertIterableEquals(expectedList, actualLinkedList, "LinkedList 비교 실패!");

        // ✅ Queue 비교 (순서 중요)
        Queue<String> actualQueue = new ArrayDeque<>(List.of("A", "B", "C"));
        assertIterableEquals(expectedList, actualQueue, "Queue 비교 실패!");

        // ✅ Stack 비교 (순서 중요)
        Stack<String> actualStack = new Stack<>();
        actualStack.addAll(List.of("A", "B", "C"));
        assertIterableEquals(expectedList, actualStack, "Stack 비교 실패!");

        // ❌ Set 비교 (순서 다르면 실패 가능)
        Set<String> expectedSet = new LinkedHashSet<>(List.of("A", "B", "C")); // 순서 유지됨
        Set<String> actualSet = new LinkedHashSet<>(Set.of("A", "B", "C")); // 동일한 순서라면 통과
        assertIterableEquals(expectedSet, actualSet, "LinkedHashSet 비교 실패!");

        // ❌ HashSet은 순서를 보장하지 않으므로 테스트가 실패할 수 있음
        Set<String> actualHashSet = new HashSet<>(Set.of("A", "B", "C"));
        assertIterableEquals(expectedSet, actualHashSet, "HashSet 비교 실패!"); // ❌ 실패 가능 (순서 다름)

        // Iterable 하지 않은 자료구조 (배열, Iterable클래스를 상속받지 않은 커스텀객체, Map)은 비교불가
        // ❌ 실행하면 java.lang.ClassCastException 발생!
        Integer[] expected = {1, 2, 3};
        Integer[] actual = {1, 2, 3};
        //assertIterableEquals(expected, actual);
    }

    @Test
    public void assertThrows는_예외를정해두고_지정된예외가발생하는지코드를실행하여검증한다() {
        // ✅ IllegalArgumentException 발생 확인
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("잘못된 인자!");
        }, "IllegalArgumentException이 발생해야 합니다.");

        // ✅ ArithmeticException 발생 확인 (0으로 나누기)
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        }, "0으로 나누면 ArithmeticException이 발생해야 합니다.");

        // ✅ NullPointerException 발생 확인
        assertThrows(NullPointerException.class, () -> {
            String str = null;
            str.length(); // NPE 발생
        }, "null 접근 시 NullPointerException이 발생해야 합니다.");

        // ✅ ArrayIndexOutOfBoundsException 발생 확인
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            int[] arr = new int[3];
            int value = arr[5]; // 잘못된 인덱스 접근
        }, "배열의 범위를 넘어가면 ArrayIndexOutOfBoundsException이 발생해야 합니다.");
    }

    @Test
    void assertThrows는_예외객체를_리턴한다_이를또사용할수있다() {
        // ✅ 예외 발생 테스트 및 예외 객체 저장
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
            () -> {
                throw new IllegalArgumentException("잘못된 입력값입니다.");
            });

        // ✅ 발생한 예외의 메시지 검증
        assertEquals("잘못된 입력값입니다.", thrown.getMessage());
    }


    @Test
    void assertDoesNotThrow는_executable한실행문이_예외를발생하지않아야_성공한다() {
        // ✅
        assertDoesNotThrow(() -> {
            int result = 10 / 2;
        }, "정상실행");

        // ✅
        assertDoesNotThrow(() -> {
            new IllegalArgumentException("예외발생");
        }, "예외를 생성만하지 던지지는 않았으니까 성공");

        // ❌
        assertDoesNotThrow(() -> {
            throw new IllegalArgumentException("예외를 던졌으니 테스트가 실패한다.");
        }, "에러발생");

    }

    @Test
    public void assert문은_검증실패시_이후코드를_실행하지않는다() {
        assertEquals(3, 5/2); // 3이 아니고 2라서 실패
        assertEquals(4, 2*2); // 성공케이스지만 여기까지 검증하지 않고 전체 실패로 반환
    }

    @Test
    public void assertAll문은_검증실패시에도_모든검증을실행하고_실패목록들을_알려준다() {
        assertAll(
            () -> assertEquals(3,5/2), // 2
            () -> assertEquals(4,2*2), // 4
            () -> assertEquals(6,11/2) // 5
        );
    }

    @BeforeAll
    static void setup() {
        System.out.println("테스트 시작 전 한 번만 실행"); // ❌ 실행되지 않음
    }

    @Test
    @DisplayName("사용자 로그인 테스트~")
    void 로그인_테스트가_성공하는지_테스트한다() {
        System.out.println("로그인 테스트 실행");
    }

    @Test
    @Disabled("버그 수정 후 활성화 예정")
    void skippedTest() {
        System.out.println("이 테스트는 실행되지 않습니다.");
    }
}
