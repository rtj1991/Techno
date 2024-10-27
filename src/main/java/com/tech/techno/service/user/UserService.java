package com.tech.techno.service.user;

import com.tech.techno.model.Role;
import com.tech.techno.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    public boolean hasAdmin();

    public void createRootUser(User user);

    public void isloggedIn(boolean status,User user,HttpServletRequest request);

    /**
     * session user update
     *
     * @return user
     */
    public User saveUser(User user);
}
