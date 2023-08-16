package com.wanted.backend.service;

import com.wanted.backend.dto.PostModifyRequest;
import com.wanted.backend.dto.PostResponse;
import com.wanted.backend.dto.PostWriteRequest;
import com.wanted.backend.entity.Member;
import com.wanted.backend.entity.Post;
import com.wanted.backend.exception.ErrorCode;
import com.wanted.backend.exception.WantedException;
import com.wanted.backend.repository.MemberRepository;
import com.wanted.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public PostResponse write(PostWriteRequest request, String email) {
        Optional<Member> optionalMember = memberRepository.findMemberByEmail(email);
        if (optionalMember.isEmpty()) {
            throw new WantedException(ErrorCode.MEMBER_NOT_FOUND);
        }
        Member member = optionalMember.get();

        Post post = Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .member(member)
                .build();

        postRepository.save(post);

        return PostResponse.from(post);
    }

    @Override
    public Page<PostResponse> list(Pageable pageable) {

        Page<Post> posts = postRepository.findAll(pageable);

        return posts.map(PostResponse::from);
    }

    @Override
    public PostResponse get(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new WantedException(ErrorCode.POST_NOT_FOUND);
        }
        Post post = optionalPost.get();

        return PostResponse.from(post);
    }

    @Override
    @Transactional
    public PostResponse modify(Long id, PostModifyRequest request, String email) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new WantedException(ErrorCode.POST_NOT_FOUND);
        }
        Post post = optionalPost.get();

        if (!post.getMember().getEmail().equals(email)) {
            throw new WantedException(ErrorCode.DIFFERENT_MEMBER);
        }

        log.info(request.getTitle());
        log.info(request.getContents());

        post.modify(request);

        return PostResponse.from(post);
    }

    @Override
    @Transactional
    public void delete(Long id, String email) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new WantedException(ErrorCode.POST_NOT_FOUND);
        }
        Post post = optionalPost.get();

        if (!post.getMember().getEmail().equals(email)) {
            throw new WantedException(ErrorCode.DIFFERENT_MEMBER);
        }

        postRepository.deleteById(id);
    }
}
