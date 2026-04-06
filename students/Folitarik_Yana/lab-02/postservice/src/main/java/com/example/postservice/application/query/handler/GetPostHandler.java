package com.example.postservice.application.query.handler;

import com.example.postservice.application.query.GetPostQuery;
import com.example.postservice.application.query.dto.PostResponse;
import com.example.postservice.infrastructure.adapter.out.persistence.PostDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPostHandler {
  private final PostDetailsRepository readRepository;

  public GetPostHandler(PostDetailsRepository readRepository) {
    this.readRepository = readRepository;
  }

  public PostResponse handle(GetPostQuery query) {
    return readRepository.findById(query.postId())
      .map(view -> new PostResponse(
        view.getId().toString(),
        view.getContent(),
        view.getStatus(),
        view.getLikesCount()
      )).orElseThrow();
  }
}
