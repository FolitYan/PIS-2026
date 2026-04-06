package com.example.postservice.application.projection;

import com.example.postservice.domain.event.PostCreatedEvent;
import com.example.postservice.infrastructure.adapter.out.persistence.PostDetailsJpaEntity;
import com.example.postservice.infrastructure.adapter.out.persistence.PostDetailsRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Component
public class PostProjection {
  private final PostDetailsRepository readRepository;

  public PostProjection(PostDetailsRepository readRepository) {
    this.readRepository = readRepository;
  }

  @EventListener
  @Transactional
  public void on(PostCreatedEvent event) {
    PostDetailsJpaEntity view = new PostDetailsJpaEntity();
    view.setId(event.postId());
    view.setContent("Загрузка контента...");
    view.setAuthorName("Автор: " + event.authorId());
    view.setStatus("DRAFT");
    view.setLikesCount(0);
    view.setLastUpdatedAt(LocalDateTime.now());

    readRepository.save(view);
  }
}
