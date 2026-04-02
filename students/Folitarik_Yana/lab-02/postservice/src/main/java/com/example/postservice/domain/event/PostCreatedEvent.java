package com.example.postservice.domain.event;

import java.time.Instant;
import java.util.UUID;

public record PostCreatedEvent(UUID postId, String authorId, Instant occurredOn) implements DomainEvent {
  public PostCreatedEvent(UUID postId, String authorId) {
    this(postId, authorId, Instant.now());
  }
}
