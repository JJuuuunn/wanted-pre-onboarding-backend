package com.wanted.backend.controller;

import com.wanted.backend.dto.MemberJoinRequest;
import com.wanted.backend.dto.MemberJoinResponse;
import com.wanted.backend.dto.MemberLoginRequest;
import com.wanted.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberJoinResponse> join(@RequestBody @Valid MemberJoinRequest request) {

        return ResponseEntity.ok(memberService.Join(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberLoginRequest request) {

        String token = memberService.Login(request);

        return ResponseEntity.ok(token);
    }
}
