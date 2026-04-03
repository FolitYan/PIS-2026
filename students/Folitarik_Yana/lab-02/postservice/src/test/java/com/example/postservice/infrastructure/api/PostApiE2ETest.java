package com.example.postservice.infrastructure.api;

import com.example.postservice.application.command.CreatePostCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostApiE2ETest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void shouldCreateAndThenGetPost() {
    // 1. Create Post
    CreatePostCommand cmd = new CreatePostCommand("user-123", "E2E testing is great");
    ResponseEntity<UUID> createResponse = restTemplate.postForEntity("/api/posts", cmd, UUID.class);

    assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
    UUID id = createResponse.getBody();
    assertNotNull(id);

    // 2. Get Post
    ResponseEntity<Object> getResponse = restTemplate.getForEntity("/api/posts/" + id, Object.class);
    assertEquals(HttpStatus.OK, getResponse.getStatusCode());
  }
}
