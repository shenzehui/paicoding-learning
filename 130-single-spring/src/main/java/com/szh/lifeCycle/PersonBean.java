package com.szh.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Bean 生命周期
 */
public class PersonBean implements InitializingBean, BeanFactoryAware, BeanNameAware, DisposableBean {

    /**
     * 身份证号
     */
    private Integer no;

    /**
     * 姓名
     */
    private String name;

    public PersonBean() {
        System.out.println("1.调⽤构造⽅法：我出⽣了！");
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("2.设置属性：我的名字叫" + name);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3.调⽤BeanNameAware#setBeanName⽅法：我要上学了，起了个学名");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4.调⽤BeanFactoryAware#setBeanFactory⽅法：选好学校了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6.InitializingBean#afterPropertiesSet⽅法：⼊学登记");
    }

    public void init() {
        System.out.println("7.⾃定义init⽅法：努⼒上学ing");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9.DisposableBean#destroy⽅法：平淡的⼀⽣落幕了");
    }

    public void destroyMethod() {
        System.out.println("10.⾃定义destroy方法：睡了，别想叫醒我");
    }

    public void work() {
        System.out.println("Bean使⽤中：⼯作，只有对社会没有⽤的⼈才放假。。");
    }

}
