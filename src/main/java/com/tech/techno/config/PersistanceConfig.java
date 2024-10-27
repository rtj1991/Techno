package com.tech.techno.config;

import com.tech.techno.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistanceConfig {
    @Bean
    AuditorAware<User> auditorProvider(){
        return new CustomAuditorAware();
    }
}
