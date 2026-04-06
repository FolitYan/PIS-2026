package com.example.postservice.infrastructure.adapter.in.rest;

import com.example.postservice.application.command.CreatePostCommand;
import com.example.postservice.application.command.handler.CreatePostHandler;
import com.example.postservice.application.query.GetPostQuery;
import com.example.postservice.application.query.dto.PostResponse;
import com.example.postservice.application.query.handler.GetPostHandler;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  private final CreatePostHandler c;
  private final GetPostHandler g;
  public PostController(CreatePostHandler c, GetPostHandler g) { this.c = c; this.g = g; }

  @PostMapping
  public UUID create(@RequestBody CreatePostCommand cmd) { return c.handle(cmd); }

  @GetMapping("/{id}")
  public PostResponse get(@PathVariable UUID id) { return g.handle(new GetPostQuery(id)); }
}
