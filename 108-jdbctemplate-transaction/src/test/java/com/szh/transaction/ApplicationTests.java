package com.szh.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private TransactionalSample transactionalSample;

    @Test
    void contextLoads() {
        transactionalSample.testSimpleCase();
    }

}
