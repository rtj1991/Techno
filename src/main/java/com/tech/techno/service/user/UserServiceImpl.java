package com.tech.techno.service.user;

import com.tech.techno.model.Role;
import com.tech.techno.model.User;
import com.tech.techno.repository.RoleRepository;
import com.tech.techno.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean hasAdmin() {
        return userRepository.findByUserName("root") != null;
    }

    @Override
    public void createRootUser(User user) {
        try {
            Role role = roleRepository.save(new Role("ROLE ADMIN"));
            user.setRoles(Arrays.asList(role));
            role.setDescription("admin");
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    @Override
    public void isloggedIn(boolean status, User user, HttpServletRequest request) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
