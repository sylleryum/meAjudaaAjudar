package com.sylleryum.meajudaaajudar;

import com.sylleryum.meajudaaajudar.security.JWTKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.util.Date;

@SpringBootTest
class MeajudaaajudarApplicationTests {

    @Autowired
    JWTKey jwtConfig;

    @Test
    void test() {
        String secretKey = jwtConfig.getSecretKey();
        System.out.println();
        long now = System.currentTimeMillis();
        int mins30 = 1800000;
        long year1 = 31560000000l;
        Date date = new Date(now);
        String dataformatted = DateFormat.getDateInstance().format(date);
        System.out.println();

    }

}
