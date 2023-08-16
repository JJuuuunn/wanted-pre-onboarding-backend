package com.wanted.backend.service;

import com.wanted.backend.dto.PostModifyRequest;
import com.wanted.backend.dto.PostResponse;
import com.wanted.backend.dto.PostWriteRequest;
import com.wanted.backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    PostResponse write(PostWriteRequest request, String email);

    Page<PostResponse> list(Pageable pageable);

    PostResponse get(Long id);

    PostResponse modify(Long id, PostModifyRequest request, String email);

    void delete(Long id, String email);
}
