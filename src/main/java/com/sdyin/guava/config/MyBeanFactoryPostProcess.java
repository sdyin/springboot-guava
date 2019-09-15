package com.sdyin.guava.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: BeanFactory开始创建 Bean 实例前前置处理
 * @Author: liuye
 * @time: 2019/9/15$ 下午8:27$
 */
@Component
public class MyBeanFactoryPostProcess implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int hashCode = beanFactory.hashCode();
        System.out.println("----------------------BeanFactory 开始工作:" + hashCode);
    }
}
