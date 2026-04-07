package com.example.postservice.infrastructure.grpc;

import com.example.postservice.grpc.*;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class PostGrpcServiceImpl extends PostGrpcServiceGrpc.PostServiceImplBase {

  @Override
  public void getPost(GetPostRequest request, StreamObserver<PostProto> responseObserver) {
    PostProto response = PostProto.newBuilder()
      .setId(request.getPostId())
      .setAuthorId("user-123")
      .setContent("Hello gRPC World")
      .setStatus("PUBLISHED")
      .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void streamAuthorPosts(AuthorStreamRequest request, StreamObserver<PostProto> responseObserver) {
    for (int i = 1; i <= 5; i++) {
      PostProto post = PostProto.newBuilder()
        .setId("post-" + i)
        .setAuthorId(request.getAuthorId())
        .setContent("Streaming message #" + i)
        .build();

      responseObserver.onNext(post);
      try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }
    responseObserver.onCompleted();
  }
}
