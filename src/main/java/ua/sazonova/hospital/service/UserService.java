package ua.sazonova.hospital.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    }

    public void save(User user) {
        if (user.getIsActive() == null) {
            user.setIsActive(false);
        }
        userRepository.save(user);
    }
}