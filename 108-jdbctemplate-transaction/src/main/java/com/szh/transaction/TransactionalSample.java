package com.szh.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.channels.Selector;

/**
 * Created by szh on 2023-06-11
 *
 * @author szh
 */

@Component
public class TransactionalSample {
    @Autowired
    private SimpleDemo simpleService;

    public void testSimpleCase() {
        System.out.println("=========== 事务正常工作 start ==========");
        simpleService.query("transaction before", 130);
        try {
            simpleService.testRuntimeExceptionTrans(130);
        } catch (Exception e) {
        }
        simpleService.query("transaction end", 130);
        System.out.println("============ 事务正常工作 end ========== \n");

        /*==================================================================*/

        System.out.println("============ 事务不生效 start ========== ");
        simpleService.query("transaction before", 140);
        try {
            // 因为抛出的是非运行异常，不会回滚
            simpleService.testNormalException(140);
        } catch (Exception e) {
        }
        simpleService.query("transaction end", 140);
        System.out.println("============ 事务不生效 end ========== \n");

        /*==================================================================*/

        System.out.println("============ 事务生效 start ========== ");
        simpleService.query("transaction before", 150);
        try {
            // 注解中，指定所有异常都回滚
            simpleService.testSpecialException(150);
        } catch (Exception e) {
        }
        simpleService.query("transaction end", 150);
        System.out.println("============ 事务生效 end ========== \n");
    }
}
