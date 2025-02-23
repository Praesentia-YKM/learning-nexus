package com.tdd.learn.testDouble.autoDebit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tdd.learn.testDouble.autoDebit.CardValidity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Stub_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    void invalidCard() {
        // 유효한 카드임을 알고싶으면 StubCardNumberValidator에 invalidNo추가
        stubValidator.setInvalidNo("111122223333");

        AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
        RegisterResult result = this.register.register(req);

        assertEquals(INVALID, result.getValidity());
    }

    @Test
    void theftCard() {
        // 도난당한 카드 테스트하고 싶으면 StubCardNumberValidator에 theftNo 추가
        stubValidator.setTheftNo("1234567890123456");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);

        assertEquals(CardValidity.THEFT, result.getValidity());
    }

    @Test
    void validCard() {
        // 대역을 사요하지 않고 비지니스 코드를 그대로 사용하면 해당 카드번호를 업체에게 공급받아야 테스트가 일시적으로 가능하다.
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }
}
