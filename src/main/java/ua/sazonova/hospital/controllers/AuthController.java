package ua.sazonova.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.sazonova.hospital.entity.enam.Role;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.repository.UserRepository;

@Controller
@RequestMapping
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        model.addAttribute("user", user);
        if(user.getRole().equals(Role.DOCTOR)){
            return "redirect:/doctor/"+user.getIdMoreInfo();
        }else if(user.getRole().equals(Role.PATIENT)){
            return "redirect:/patient/"+user.getIdMoreInfo();
        }else if(user.getRole().equals(Role.ADMIN)){
            return "redirect:/admin";
        }
        return "redirect:/login";
    }
}