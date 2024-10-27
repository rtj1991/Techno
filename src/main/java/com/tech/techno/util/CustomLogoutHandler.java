package com.tech.techno.util;


import com.tech.techno.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Transactional
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        User user = (User) request.getSession().getAttribute("user");

        if (authentication != null && authentication.getDetails() != null) {
            try {
                request.getSession().invalidate();
                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect("/login");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
