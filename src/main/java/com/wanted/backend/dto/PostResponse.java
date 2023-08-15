package com.wanted.backend.dto;


import com.wanted.backend.entity.Post;
import lombok.Builder;

import java.util.Optional;

public class PostResponse {
    private String title;
    private String contents;

    @Builder
    public PostResponse(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .build();
    }
}
