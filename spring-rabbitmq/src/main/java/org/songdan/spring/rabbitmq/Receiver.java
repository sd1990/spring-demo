package org.songdan.spring.rabbitmq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author Songdan
 * @date 2017/8/1 11:09
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

        latch.countDown();

    }

}
