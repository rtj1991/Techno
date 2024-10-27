package com.tech.techno.config;

import com.tech.techno.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;


import javax.servlet.http.HttpSession;

public class CustomAuditorAware implements AuditorAware<User> {

    @Autowired
    private HttpSession session;

    @Override
    public User getCurrentAuditor() {
        return (User) session.getAttribute("user");
    }
}
