package com.example.postservice.infrastructure.messaging;

import com.example.postservice.application.port.out.EventPublisher;
import com.example.postservice.domain.event.DomainEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender implements EventPublisher {
  private final RabbitTemplate rabbitTemplate;

  public RabbitMQSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void publish(DomainEvent event) {
    rabbitTemplate.convertAndSend("post_exchange", "post.created", event.toString());
  }
}
