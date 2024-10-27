package com.tech.techno.util;


import com.tech.techno.model.Module;
import com.tech.techno.repository.UserRepository;
import com.tech.techno.service.module.ModuleService;
import com.tech.techno.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Transactional
public class LoginHandler implements AuthenticationSuccessHandler {

    public RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.tech.techno.model.User userSession = userRepository.findByUserName(user.getUsername());
        HttpSession session = request.getSession();
        Module module = new Module();
        module.setId(1);
        session.setAttribute("user", userSession);
        session.setAttribute("username", user.getUsername());
        try {
            session.setAttribute("modules", moduleService.userHasModules(userSession.getRoles(), module, Const.STATUS_VISIBILITY));
            userService.isloggedIn(true, userSession, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectStrategy.sendRedirect(request, response, "/dashboard");
    }
}
