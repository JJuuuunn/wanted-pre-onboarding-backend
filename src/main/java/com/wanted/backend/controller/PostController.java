package com.wanted.backend.controller;

import com.wanted.backend.dto.PostModifyRequest;
import com.wanted.backend.dto.PostResponse;
import com.wanted.backend.dto.PostWriteRequest;
import com.wanted.backend.entity.Post;
import com.wanted.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public ResponseEntity<String> join(@RequestBody PostWriteRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().build();
        }

        postService.write(request, authentication.getName());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Post>> list(Pageable pageable) {
        return ResponseEntity.ok().body(postService.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.get(id));
    }

    @PutMapping("/{id}/modify")
    public ResponseEntity<PostResponse> modify(@PathVariable Long id, @RequestBody PostModifyRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().build();
        }
        String email = authentication.getName();

        return ResponseEntity.ok().body(postService.modify(id, request, email));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().build();
        }
        String email = authentication.getName();

        postService.delete(id, email);

        return ResponseEntity.ok().build();
    }
}
