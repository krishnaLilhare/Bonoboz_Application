package com.securityAssignment.service.impl;


import com.securityAssignment.dto.JwtAuthenticationResponse;
import com.securityAssignment.dto.SignInRequest;
import com.securityAssignment.dto.UserDto;
import com.securityAssignment.model.Role;
import com.securityAssignment.model.User;
import com.securityAssignment.repository.UserRepository;
import com.securityAssignment.service.AuthenticationService;
import com.securityAssignment.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signUp(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
        var user =userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Email not found"));
        var jwt =jwtService.generateToken(user);
        var refreshToken =jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse =new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }


    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public List<User> getAll(){
        List<User> users=userRepository.findAll();
        return users.stream().filter(e -> e.getRole().equals(Role.USER)).collect(Collectors.toList());
    }


}
