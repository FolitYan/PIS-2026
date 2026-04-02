package com.example.postservice.domain.model;

import com.example.postservice.domain.event.*;
import com.example.postservice.domain.exception.DomainException;
import com.example.postservice.domain.valueobject.*;
import java.util.*;

public class Post {
  private final UUID id;
  private final AuthorId authorId;
  private PostContent content;
  private PostStatus status;
  private int likesCount;
  private final List<DomainEvent> events = new ArrayList<>();

  private Post(UUID id, AuthorId authorId, PostContent content) {
    this.id = id;
    this.authorId = authorId;
    this.content = content;
    this.status = PostStatus.DRAFT;
    this.likesCount = 0;
    this.events.add(new PostCreatedEvent(id, authorId.value()));
  }

  public static Post create(AuthorId authorId, PostContent content) {
    return new Post(UUID.randomUUID(), authorId, content);
  }

  public void publish() {
    if (this.status == PostStatus.PUBLISHED) throw new DomainException("Already published");
    this.status = PostStatus.PUBLISHED;
  }

  public void addLike() {
    if (this.status != PostStatus.PUBLISHED) throw new DomainException("Not published");
    this.likesCount++;
  }

  public UUID getId() { return id; }
  public AuthorId getAuthorId() { return authorId; }
  public PostContent getContent() { return content; }
  public PostStatus getStatus() { return status; }
  public int getLikesCount() { return likesCount; }
  public List<DomainEvent> getEvents() { return Collections.unmodifiableList(events); }
  public void clearEvents() { events.clear(); }
}
