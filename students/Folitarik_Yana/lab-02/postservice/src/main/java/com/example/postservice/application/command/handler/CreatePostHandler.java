package com.example.postservice.application.command.handler;

import com.example.postservice.application.command.CreatePostCommand;
import com.example.postservice.application.port.out.*;
import com.example.postservice.domain.model.Post;
import com.example.postservice.domain.valueobject.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class CreatePostHandler {
  private final PostRepository repository;
  private final EventPublisher publisher;

  public CreatePostHandler(PostRepository repository, EventPublisher publisher) {
    this.repository = repository;
    this.publisher = publisher;
  }

  @Transactional
  public UUID handle(CreatePostCommand cmd) {
    Post post = Post.create(new AuthorId(cmd.authorId()), new PostContent(cmd.text()));
    repository.save(post);
    post.getEvents().forEach(publisher::publish);
    return post.getId();
  }
}
