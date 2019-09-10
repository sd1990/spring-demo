package org.songdan.spring.simple;

import org.springframework.stereotype.Component;

/**
 * @author: Songdan
 * @create: 2019-09-10 14:23
 **/
@Component
public class SpelDemoService {

    @Spel(express = "#num>10&&#human!=null&&#human.age<10")
    public void execute(long num,Human human) {
        System.out.println("working ....");
    }

}
