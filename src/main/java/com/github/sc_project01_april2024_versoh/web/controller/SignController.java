package com.github.sc_project01_april2024_versoh.web.controller;

import com.github.sc_project01_april2024_versoh.config.security.JwtTokenProvider;
import com.github.sc_project01_april2024_versoh.service.service.AuthService;
import com.github.sc_project01_april2024_versoh.web.DTO.LoginRequest;
import com.github.sc_project01_april2024_versoh.web.DTO.ResponseDTO;
import com.github.sc_project01_april2024_versoh.web.DTO.SignUpRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/auth")
public class SignController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/sign-up")
    public ResponseDTO register(@RequestBody SignUpRequest signUpRequest){
        boolean isSuccess= authService.signUp(signUpRequest);
//        return isSuccess ? "sign up successful" : "sign up fail";
        java.util.Date date = new java.util.Date();

        if(isSuccess) return new ResponseDTO(HttpStatus.OK.value(), "sign up successful", "sign up since : " + date );
        else return new ResponseDTO(400, "sign up fail");
    }


    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        String token= authService.login(loginRequest);
        httpServletResponse.setHeader("Token", token);
        return new ResponseDTO(HttpStatus.OK.value(), "Welcome! You are successfully logged in.");
    }

    @PostMapping("/logout")
    public ResponseDTO logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        boolean isSuccess= authService.logout(httpServletRequest, httpServletResponse);
        if(isSuccess) return new ResponseDTO(200, "You are successfully logged out." );
        else return new ResponseDTO(400, "logout fail");
    }


}
