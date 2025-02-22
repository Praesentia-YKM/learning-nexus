package com.tdd.learn.testDouble.userRegist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker,
                fakeRepository,
                mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        BDDMockito
            // "pw" 인자를 사용해서 모의 객체의 checkPasswordWeak 메서드를 호출하면
            .given(mockPasswordChecker.checkPasswordWeak("pw"))
            // 결과를 true로 리턴해라.
            // StubWeakPasswordChecker 은 stub을 활용해서 weak함을 리턴하게 해두었으므로
            // (checkPasswordWeak의 리턴이 true면 "pw"는 약한 비밀번호임을 의미
            .willReturn(true);

        // 비밀번호가 약하면 익셉션 던짐
        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }


    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        BDDMockito
            // 인자로 전달한 mockPasswordChecker 모의 객체에 대하여 검증할꺼야
            .then(mockPasswordChecker)
            // 어떤 메서드가 호출됐는지 검증하려고 하는데
            .should()
            // 임의의 String 타입인자를 이용해서 checkPasswordWeak 메서드 호추 ㄹ여부를 확인하려고 해
            .checkPasswordWeak(Mockito.matches("pw"));
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupId() {
        // 이미 같은 ID 존재하는 상황 만들기
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class); // Mock의 인자 캡쳐할 변수 생성
        BDDMockito
            .then(mockEmailNotifier)
            .should()
            .sendRegisterEmail(captor.capture()); // should를 통해 행위를 할 때, 메서드의 인자로 캡쳐변수.capture()를 던진다.

        String realEmail = captor.getValue(); // 캡쳐변수의 값을 getValue를 통해 꺼내온다.
        assertEquals("email@email.com", realEmail);
    }
}
