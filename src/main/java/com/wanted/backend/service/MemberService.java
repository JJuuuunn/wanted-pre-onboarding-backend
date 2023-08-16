package com.wanted.backend.service;

import com.wanted.backend.dto.MemberJoinRequest;
import com.wanted.backend.dto.MemberLoginRequest;
import com.wanted.backend.dto.MemberJoinResponse;

public interface MemberService {
    MemberJoinResponse Join(MemberJoinRequest request);

    String Login(MemberLoginRequest request);
}
