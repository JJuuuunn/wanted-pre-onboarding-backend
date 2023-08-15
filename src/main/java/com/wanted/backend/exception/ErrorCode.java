package com.wanted.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND("멤버를 찾을 수 없습니다."),
    SAME_EMAIL_EXISTS("동일한 이메일이 존재합니다."),
    WRONG_PASSWORD("잘못된 비밀번호입니다."),
    POST_NOT_FOUND("존재하지 않는 게시물입니다."),
    DIFFERENT_MEMBER("멤버가 다릅니다.");

    private String description;
}
