package domain.valueobject;

import domain.exception.DomainException;

public record AuthorId(String value) {
  public AuthorId {
    if (value == null || value.isBlank()) {
      throw new DomainException("Author ID cannot be empty");
    }
  }
}
