package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;


import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;
    private final HashService hashService;
    private final EncryptionService encryptionService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, HashService hashService, EncryptionService encryptionService) {
        this.userService = userService;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/signup")
    public String createUser(@RequestParam String username, @RequestParam String firstname,
                             @RequestParam String lastname, @RequestParam String password,
                             RedirectAttributes redirectAttributes) {
        logger.info("Attempting to create user: " + username);

        String salt = hashService.generateSalt();
        String hashedPassword = hashService.getHashedValue(password, salt);
        String encryptedFirstname = encryptionService.encryptValue(firstname, "secure-key");
        String encryptedLastname = encryptionService.encryptValue(lastname, "secure-key");

        User newUser = new User(username, encryptedFirstname, encryptedLastname, hashedPassword, salt);
        if (!userService.existsByUsername(username) && userService.registerUser(newUser)) {
            logger.info("User registered successfully: " + username);
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully. Please log in.");
            return "redirect:/login";
        } else {
            logger.warn("Username already exists or registration failed: " + username);
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to register user. Username may already exist or another error occurred.");
            return "redirect:/signup";
        }
    }


    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid Username or password.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Logged out successfully.");
        }
        return "home"; // Ensure you have a home.html or redirect to a different page if "home" isn't correct
    }
}
