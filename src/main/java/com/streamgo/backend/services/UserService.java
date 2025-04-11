package com.streamgo.backend.services;

import com.streamgo.backend.exceptions.ResourceNotFoundException;
import com.streamgo.backend.models.User;
import com.streamgo.backend.repositories.UserRepository;
import com.streamgo.backend.security.JwtUtils;
import com.streamgo.backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new UserDetailsImpl(user);
    }

    public User registerUser(User user) throws Exception {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Email already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLastUpdated(new Date());
        return userRepository.save(user);
    }

    public String authenticateUser(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new ResourceNotFoundException("Invalid credentials");
        }

        return jwtUtils.generateToken(userDetails);
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}