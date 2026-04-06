package com.example.postservice.domain;

import com.example.postservice.domain.exception.DomainException;
import com.example.postservice.domain.model.Post;
import com.example.postservice.domain.valueobject.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {

  @Test
  void shouldCreatePostInDraftStatus() {
    Post post = Post.create(new AuthorId("user-1"), new PostContent("Hello"));
    assertEquals(PostStatus.DRAFT, post.getStatus());
    assertEquals(0, post.getLikesCount());
  }

  @Test
  void shouldThrowExceptionWhenContentIsTooLong() {
    String longText = "a".repeat(281);
    assertThrows(DomainException.class, () -> new PostContent(longText));
  }

  @Test
  void shouldNotAllowLikeForDraftPost() {
    Post post = Post.create(new AuthorId("user-1"), new PostContent("Hello"));
    assertThrows(DomainException.class, post::addLike);
  }

  @Test
  void shouldAllowLikeAfterPublishing() {
    Post post = Post.create(new AuthorId("user-1"), new PostContent("Hello"));
    post.publish();
    post.addLike();
    assertEquals(1, post.getLikesCount());
  }
}
