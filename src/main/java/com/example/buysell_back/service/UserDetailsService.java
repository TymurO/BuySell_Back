package com.example.buysell_back.service;

import com.example.buysell_back.dto.RegistrationUserDto;
import com.example.buysell_back.model.User;
import com.example.buysell_back.repository.RoleRepository;
import com.example.buysell_back.repository.UserRepository;
import com.example.buysell_back.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserWrapper(userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        )));
    }

    public User registration(RegistrationUserDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRoles(List.of(roleRepository.getRoleByName("ROLE_USER").get()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username).orElse(null);
    }
}
