package com.example.postservice.infrastructure.persistence;

import com.example.postservice.domain.model.Post;
import com.example.postservice.domain.valueobject.*;
import com.example.postservice.infrastructure.adapter.out.persistence.PostRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class PostRepositoryAdapterIT {

  @Container
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
    .withDatabaseName("post_db")
    .withUsername("postgres")
    .withPassword("password");

  @Autowired
  private PostRepositoryAdapter adapter;

  @Test
  void shouldSaveAndRetrievePost() {
    Post post = Post.create(new AuthorId("auth-1"), new PostContent("Integration content"));
    adapter.save(post);

    Optional<Post> found = adapter.findById(post.getId());
    assertTrue(found.isPresent());
    assertEquals("Integration content", found.get().getContent().value());
  }
}
