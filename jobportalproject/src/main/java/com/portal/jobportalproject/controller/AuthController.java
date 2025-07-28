package com.portal.jobportalproject.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.portal.jobportalproject.model.User;
import com.portal.jobportalproject.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               RedirectAttributes redirectAttributes) {

        if (userService.existsByUsername(user.getUsername())) {
            redirectAttributes.addFlashAttribute("message", "User already exists. Please login.");
            return "redirect:/login";
        }

        // ✅ Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ✅ Ensure role has proper prefix
        user.setRole("ROLE_" + user.getRole().toUpperCase());

        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Registration successful. Please login.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Authentication auth,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model,
                            @ModelAttribute("message") String message) {

        // ✅ Prevent logged-in users from seeing login again
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/";
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out.");
        }

        return "login";
    }
}