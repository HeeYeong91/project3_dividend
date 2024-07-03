package com.zerobase.dividend.model;

import lombok.Data;

import java.util.List;

/**
 * 로그인 검증을 위한 클래스
 * @author 이희영
 */
public class Auth {

    @Data
    public static class SignIn {

        private String username;

        private String password;
    }

    @Data
    public static class SignUp {

        private String username;

        private String password;

        private List<String> roles;

        public MemberEntity toEntity() {
            return MemberEntity.builder()
                    .username(this.username)
                    .password(this.password)
                    .roles(this.roles)
                    .build();
        }
    }
}
