package org.songdan.spring.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Songdan
 * @date 2017/8/1 11:27
 */
@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    private final Receiver receiver;

    private final ConfigurableApplicationContext context;

    public Runner(ConfigurableApplicationContext context, RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.context = context;
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("sending message...");
        rabbitTemplate.convertAndSend(Application.queueName, "Hello from RabbitMQ");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();
    }
}
