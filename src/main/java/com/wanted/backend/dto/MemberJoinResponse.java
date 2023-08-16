package com.wanted.backend.dto;

import com.wanted.backend.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberJoinResponse {

    private Long id;
    private String email;

    @Builder
    public MemberJoinResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static MemberJoinResponse from(Member member) {
        return MemberJoinResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .build();
    }
}
