package com.example.postservice.domain.valueobject;

import com.example.postservice.domain.exception.DomainException;

public record PostContent(String value) {
  public PostContent {
    if (value == null || value.isBlank() || value.length() > 280) throw new DomainException("Invalid content");
  }
}
