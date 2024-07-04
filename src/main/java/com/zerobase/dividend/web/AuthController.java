package com.zerobase.dividend.web;

import com.zerobase.dividend.model.Auth;
import com.zerobase.dividend.security.TokenProvider;
import com.zerobase.dividend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 컨트롤러
 * @author 이희영
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    private final TokenProvider tokenProvider;

    /**
     * 회원 가입
     * 
     * @param request 요청
     * @return 응답
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody
            Auth.SignUp request
    ) {
        var result = this.memberService.register(request);
        log.info("user signup -> " + request.getUsername());
        return ResponseEntity.ok(result);
    }

    /**
     * 로그인
     * 
     * @param request 요청
     * @return 응답
     */
    @PostMapping("/signin")
    public ResponseEntity<?> signin(
            @RequestBody
            Auth.SignIn request
    ) {
        var member = this.memberService.authenticate(request);
        var token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());
        log.info("user login -> " + request.getUsername());
        return ResponseEntity.ok(token);
    }
}
