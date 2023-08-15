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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void write(PostWriteRequest request, String email) {
        Optional<Member> optionalMember = memberRepository.findMemberByEmail(email);
        if (optionalMember.isEmpty()) {
            throw new WantedException(ErrorCode.MEMBER_NOT_FOUND);
        }
        Member member = optionalMember.get();

        postRepository.save(Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .member(member)
                .build()
        );
    }

    @Override
    public Page<Post> list(Pageable pageable) {
        return postRepository.findAll(pageable);
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

        post.modify(request.getContent());

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
