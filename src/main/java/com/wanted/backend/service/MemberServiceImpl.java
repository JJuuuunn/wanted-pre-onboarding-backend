package com.wanted.backend.service;

import com.wanted.backend.dto.MemberJoinRequest;
import com.wanted.backend.dto.MemberJoinResponse;
import com.wanted.backend.dto.MemberLoginRequest;
import com.wanted.backend.entity.Member;
import com.wanted.backend.exception.ErrorCode;
import com.wanted.backend.exception.WantedException;
import com.wanted.backend.repository.MemberRepository;
import com.wanted.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public MemberJoinResponse Join(MemberJoinRequest request) {
        Optional<Member> optionalMember = memberRepository.findMemberByEmail(request.getEmail());
        if (optionalMember.isPresent()) {
            throw new WantedException(ErrorCode.SAME_EMAIL_EXISTS);
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();

        memberRepository.save(member);

        return MemberJoinResponse.from(member);
    }

    @Override
    @Transactional
    public String Login(MemberLoginRequest request) {
        Optional<Member> optionalMember = memberRepository.findMemberByEmail(request.getEmail());
        if (optionalMember.isEmpty()) {
            throw new WantedException(ErrorCode.MEMBER_NOT_FOUND);
        }
        Member member = optionalMember.get();

        if (!bCryptPasswordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new WantedException(ErrorCode.WRONG_PASSWORD);
        }

        String jwtToken = jwtUtil.createJwtToken(member.getEmail());
        
        return jwtToken;
    }
}
