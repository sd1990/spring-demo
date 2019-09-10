package org.songdan.spring.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class SpelDemoServiceTest {

    @Autowired
    private SpelDemoService spelDemoService;

    @Test
    public void execute() {
        Human human = new Human();
        human.setAge(1);
        spelDemoService.execute(100,human);
    }
}