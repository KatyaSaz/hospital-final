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

import javax.servlet.http.HttpServletRequest;

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
    public String defaultAfterLogin(HttpServletRequest request, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));

        model.addAttribute("user", user);

        System.out.println(user.getUsername()+" "+ user.getRole());


        if(user.getRole().equals(Role.DOCTOR)){
            System.out.println("in doctor");
            return "redirect:/doctor/"+user.getIdMoreInfo();
        }else if(user.getRole().equals(Role.PATIENT)){
            System.out.println("in patient");
            return "redirect:/patient/"+user.getIdMoreInfo();
        }else if(user.getRole().equals(Role.ADMIN)){
            System.out.println("in admin");
            return "redirect:/admin";
        }

        return "redirect:/login";

    }
}