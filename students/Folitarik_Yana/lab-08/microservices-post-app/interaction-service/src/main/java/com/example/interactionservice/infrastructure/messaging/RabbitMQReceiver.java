package com.example.interactionservice.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {
  @RabbitListener(queues = "post_queue")
  public void receive(String message) {
    System.out.println("Received event from Post Service: " + message);
  }
}
