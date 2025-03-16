package com.tdd.learn.externalApiTest.user;

import com.tdd.learn.externalApi.user.DupIdException;
import com.tdd.learn.externalApi.user.UserRegister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // @SpringBootApplication이 있는 패키지를 루트 패키지로 간주하고 그 하위 패키지를 자동 의존성 스캔
@Sql("classpath:init-data.sql")
public class UserRegisterIntTestUsingSql {
    @Autowired
    private UserRegister register;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void 동일ID가_이미_존재하면_익셉션() {
        // 실행, 결과 확인
        assertThrows(DupIdException.class,
                () -> register.register("cbk", "strongpw", "email@email.com")
        );
    }

    @Test
    void 존재하지_않으면_저장함() {
        // 실행
        register.register("cbk2", "strongpw", "email@email.com");
        // 결과 확인
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from user where id = ?", "cbk2");
        rs.next();
        assertEquals("email@email.com", rs.getString("email"));
    }
}
