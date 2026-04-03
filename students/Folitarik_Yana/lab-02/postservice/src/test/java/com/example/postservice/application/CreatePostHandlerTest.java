package com.example.postservice.application;

import com.example.postservice.application.command.CreatePostCommand;
import com.example.postservice.application.command.handler.CreatePostHandler;
import com.example.postservice.application.port.out.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreatePostHandlerTest {

  @Test
  void shouldSavePostAndPublishEvent() {
    PostRepository repository = mock(PostRepository.class);
    EventPublisher publisher = mock(EventPublisher.class);
    CreatePostHandler handler = new CreatePostHandler(repository, publisher);
    CreatePostCommand cmd = new CreatePostCommand("author-1", "Valid content");

    UUID postId = handler.handle(cmd);

    verify(repository, times(1)).save(any());
    verify(publisher, atLeastOnce()).publish(any());
  }
}
