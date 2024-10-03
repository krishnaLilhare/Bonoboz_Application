package com.securityAssignment.service;


import com.securityAssignment.dto.JwtAuthenticationResponse;
import com.securityAssignment.dto.SignInRequest;
import com.securityAssignment.dto.UserDto;
import com.securityAssignment.model.User;

public interface AuthenticationService {

    User signUp(UserDto userDto);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);


}
