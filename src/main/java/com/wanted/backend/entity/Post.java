package com.wanted.backend.entity;

import javax.persistence.*;

import com.wanted.backend.dto.PostModifyRequest;
import lombok.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity(name="post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    private String title;
    private String contents;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Post(Long id, String title, String contents, Member member) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.member = member;
    }

    public void modify(PostModifyRequest request) {
        this.title = (request.getTitle().isEmpty() || request.getTitle() == null) ? title : request.getTitle();
        this.contents = (request.getContents().isEmpty() || request.getContents() == null) ? title : request.getContents();
    }
}