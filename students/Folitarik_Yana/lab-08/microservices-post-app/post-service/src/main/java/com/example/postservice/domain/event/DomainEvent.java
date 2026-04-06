package com.example.postservice.domain.event;

import java.time.Instant;

public interface DomainEvent {
  Instant occurredOn();
}
