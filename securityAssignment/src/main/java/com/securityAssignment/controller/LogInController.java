package com.securityAssignment.controller;

import com.securityAssignment.dto.JwtAuthenticationResponse;
import com.securityAssignment.dto.SignInRequest;
import com.securityAssignment.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LogInController {

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @PostMapping()
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

}
