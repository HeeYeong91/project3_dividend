package com.zerobase.dividend.config;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 앱 설정
 * @author 이희영
 */
@Configuration
public class AppConfig {

    @Bean
    public Trie<String, String> trie() {
        return new PatriciaTrie<>();
    }
}
