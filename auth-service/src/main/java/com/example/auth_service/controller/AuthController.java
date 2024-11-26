package com.example.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.service.AuthService;
import com.example.auth_service.service.UserService;
import com.example.common.model.DTO.UserDTO;
import com.example.common.model.DTO.UserInfoDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authorizationService;
    UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserDTO userData){
        if(authorizationService.signUp(userData)){
            return "User created";
        }else{
            return "User exists";
        }
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody UserDTO userData, HttpServletRequest request, HttpServletResponse response){
        if(authorizationService.signIn(userData, request, response)){
            return "Signed in successfully";
        }else{
            return "Bad credentials";
        }
    }

    @GetMapping("/my-info")
    public UserInfoDTO myInfo() {
        return userService.getMyInfo();
    }
}