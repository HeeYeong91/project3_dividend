package com.zerobase.dividend.web;

import com.zerobase.dividend.model.Auth;
import com.zerobase.dividend.security.TokenProvider;
import com.zerobase.dividend.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenProvider tokenProvider;

    @Test
    @DisplayName("회원가입 성공")
    void successSignup() {
        //given
        Auth.SignUp request = new Auth.SignUp();
        request.setUsername("tester");
        request.setPassword("123456");
        request.setRoles(List.of("ROLE_WRITE"));

        //when
        var result = memberService.register(request);

        //then
        assertEquals("tester", result.getUsername());
    }

    @Test
    @DisplayName("로그인 성공")
    void successSignin() {
        //given
        Auth.SignUp requestSignup = new Auth.SignUp();
        requestSignup.setUsername("tester");
        requestSignup.setPassword("123456");
        requestSignup.setRoles(List.of("ROLE_WRITE"));
        memberService.register(requestSignup);

        Auth.SignIn requestSignin = new Auth.SignIn();
        requestSignin.setUsername("tester");
        requestSignin.setPassword("123456");

        //when
        var member = memberService.authenticate(requestSignin);
        var token = tokenProvider.generateToken(member.getUsername(), member.getRoles());

        //then
        assertNotNull(token);
    }
}