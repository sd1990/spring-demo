package org.songdan.spring.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 项目配置
 * @author Songdan
 * @date 2017/8/1 10:58
 */
@SpringBootApplication
public class Application {

    final static String queueName = "spring_boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring_boot_exchange");
    }

    @Bean
    Binding binding(Queue queue,TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("172.16.6.101", 5672);
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        cachingConnectionFactory.setUsername("admin");
        cachingConnectionFactory.setPassword("admin");
        return cachingConnectionFactory;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
