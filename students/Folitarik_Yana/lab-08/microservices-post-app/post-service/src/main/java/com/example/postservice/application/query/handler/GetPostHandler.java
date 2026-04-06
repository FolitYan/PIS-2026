package com.example.postservice.application.query.handler;

import com.example.postservice.application.query.GetPostQuery;
import com.example.postservice.application.query.dto.PostResponse;
import com.example.postservice.infrastructure.adapter.out.persistence.PostDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostHandler {

  private final PostRepository repository;
  public GetPostHandler(PostRepository repository) { this.repository = repository; }

  public PostResponse handle(GetPostQuery q) {
    return repository.findById(q.postId())
      .map(p -> new PostResponse(p.getId().toString(), p.getContent().value(), p.getStatus().name(), p.getLikesCount()))
      .orElseThrow();
  }
}
