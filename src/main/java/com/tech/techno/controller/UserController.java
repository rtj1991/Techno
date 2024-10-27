package com.tech.techno.controller;

import com.tech.techno.model.User;
import com.tech.techno.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String createRootUser(Model model) {
        if (userService.hasAdmin()) {
            return "login";
        } else if (!model.containsAttribute("user")) {
            User user = new User();
            user.setUserName("root");
            model.addAttribute("user", user);
        }
        return "admin/config";
    }


    @PostMapping(value = "/")
    public String createRootUser(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            ra.addFlashAttribute("user", user);
        }
        userService.createRootUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/dashboard")
    public String dashboard() {
        return "system/dashboard";
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        if (!userService.hasAdmin()) {
            return "redirect:/";
        }
        return "/login";
    }

}