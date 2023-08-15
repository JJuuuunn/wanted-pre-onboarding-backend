package com.wanted.backend.service;

import com.wanted.backend.dto.MemberJoinRequest;
import com.wanted.backend.dto.MemberLoginRequest;

public interface MemberService {
    void Join(MemberJoinRequest request);

    String Login(MemberLoginRequest request);
}
