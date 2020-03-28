package org.songdan.spring.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class SpelDemoServiceTest {

    @Autowired
    private SpelDemoService spelDemoService;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        SpelDemoService spelDemoService = context.getBean(SpelDemoService.class);
        Human human = new Human();
        human.setAge(1);
        spelDemoService.execute(100,human);
    }

    @Test
    public void execute() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-context.xml");

        Human human = new Human();
        human.setAge(1);
        spelDemoService.execute(100,human);
    }
}