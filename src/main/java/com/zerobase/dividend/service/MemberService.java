package com.zerobase.dividend.service;

import com.zerobase.dividend.model.Auth;
import com.zerobase.dividend.model.MemberEntity;
import com.zerobase.dividend.persist.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원 서비스
 * @author 이희영
 */
@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + username));
    }

    /**
     * 회원가입
     *
     * @param member 회원가입 회원
     * @return 회원 엔티티
     */
    public MemberEntity register(Auth.SignUp member) {
        boolean exist = this.memberRepository.existsByUsername(member.getUsername());
        if (exist) {
            throw new RuntimeException("이미 사용 중인 아이디 입니다");
        }

        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        return this.memberRepository.save(member.toEntity());
    }

    /**
     * 로그인 검증
     *
     * @param member 로그인 회원
     * @return 회원 엔티티
     */
    public MemberEntity authenticate(Auth.SignIn member) {
        return null;
    }
}
