package com.szh;

import com.szh.dao.UserDao;
import com.szh.lifeCycle.PersonBean;
import com.szh.spring.BeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void Test() {
        //1.创建bean⼯⼚(同时完成了加载资源、创建注册单例bean注册器的操作)
        BeanFactory beanFactory = new BeanFactory();

        //2.第⼀次获取bean（通过反射创建bean，缓存bean）
        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        userDao.queryUserInfo();

        //3.第⼆次获取bean（从缓存中获取bean）
        UserDao userDao1 = (UserDao) beanFactory.getBean("userDao");
        userDao1.queryUserInfo();
    }

    /**
     * 测试Bean生命周期
     *
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        PersonBean personBean = (PersonBean) applicationContext.getBean("personBean");
        personBean.work();
        applicationContext.destroy();
    }

}
