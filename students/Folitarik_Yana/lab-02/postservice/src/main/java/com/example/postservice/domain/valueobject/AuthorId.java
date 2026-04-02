package com.example.postservice.domain.valueobject;

import com.example.postservice.domain.exception.DomainException;

public record AuthorId(String value) {
  public AuthorId {
    if (value == null || value.isBlank()) throw new DomainException("Author ID empty");
  }
}
