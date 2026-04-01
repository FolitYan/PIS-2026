package domain.valueobject;


import domain.exception.DomainException;

public record PostContent(String value) {
  public PostContent {
    if (value == null || value.isBlank()) {
      throw new DomainException("Content cannot be empty");
    }
    if (value.length() > 280) {
      throw new DomainException("Content length exceeds 280 characters");
    }
  }
}
