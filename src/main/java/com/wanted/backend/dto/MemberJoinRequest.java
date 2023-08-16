package com.wanted.backend.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter
public class MemberJoinRequest {

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "이메일 형식을 맞춰 주십시오")
    private String email;

    @Length(min = 8, message = "최소 8자 이상 필요합니다.")
    private String password;
}
