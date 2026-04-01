package domain.model;

import domain.event.DomainEvent;
import domain.event.PostCreatedEvent;
import domain.exception.DomainException;
import domain.valueobject.AuthorId;
import domain.valueobject.PostContent;
import domain.valueobject.PostStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
    if (this.status != PostStatus.DRAFT) {
      throw new DomainException("Only drafts can be published");
    }
    this.status = PostStatus.PUBLISHED;
  }

  public void addLike() {
    if (this.status != PostStatus.PUBLISHED) {
      throw new DomainException("Cannot like a post that is not published");
    }
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
