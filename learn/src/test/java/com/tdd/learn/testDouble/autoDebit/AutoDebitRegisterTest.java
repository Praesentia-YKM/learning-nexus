package com.tdd.learn.testDouble.autoDebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tdd.learn.testDouble.autoDebit.CardValidity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() {
        // 업체에서 받은 테스트용 유효한 카드 번호 사용
        // -- 대역 활용을 안 했으니까 외부의 영향을 받아 테스트를 할 떄마다 결과가 달라짐
//        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
//        RegisterResult result = this.register.register(req);
//        assertEquals(VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        // 업체에서 받은 도난 테스트용 카드 번호 사용
        // -- 대역 활용을 안 했으니까 외부의 영향을 받아 테스트를 할 떄마다 결과가 달라짐
//        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
//        RegisterResult result = this.register.register(req);
//        assertEquals(THEFT, result.getValidity());
    }
}
