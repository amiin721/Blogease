package com.project.bloggingapis.controller;

import com.project.bloggingapis.dto.JwtAuthenticationRequest;
import com.project.bloggingapis.dto.JwtAuthenticationResponse;
import com.project.bloggingapis.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidAlgorithmParameterException;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {

    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> createToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws InvalidAlgorithmParameterException {

        authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtTokenHelper.generateToken(userDetails);

        return new ResponseEntity<>(new JwtAuthenticationResponse(token), HttpStatusCode.valueOf(201));

    }

    private void authenticate(String username, String password) throws InvalidAlgorithmParameterException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try{
            authenticationManager.authenticate(authenticationToken);
        }catch(BadCredentialsException ex){
            throw new InvalidAlgorithmParameterException("Invalid login credentials");
        }

    }
}
