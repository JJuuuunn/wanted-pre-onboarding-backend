package com.wanted.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "멤버를 찾을 수 없습니다."),
    SAME_EMAIL_EXISTS(HttpStatus.CONFLICT, "동일한 이메일이 존재합니다."),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시물입니다."),
    DIFFERENT_MEMBER(HttpStatus.UNAUTHORIZED, "멤버가 다릅니다.");

    private HttpStatus status;
    private String description;
}
