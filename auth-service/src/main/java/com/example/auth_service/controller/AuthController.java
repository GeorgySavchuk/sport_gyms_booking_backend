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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "Контроллер для аутентификации и регистрации пользователей")
public class AuthController {
    AuthService authorizationService;
    UserService userService;

    @Operation(summary = "Регистрация нового пользователя", description = "Регистрирует нового пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован"),
        @ApiResponse(responseCode = "400", description = "Пользователь уже существует")
    })
    @PostMapping("/signup")
    public String signUp(@Parameter(description = "Данные пользователя для регистрации", required = true) @RequestBody UserDTO userData){
        if(authorizationService.signUp(userData)){
            return "User created";
        }else{
            return "User exists";
        }
    }

    @Operation(summary = "Вход пользователя", description = "Выполняет вход пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешный вход"),
        @ApiResponse(responseCode = "400", description = "Неверные учетные данные")
    })
    @PostMapping("/signin")
    public String signIn(@Parameter(description = "Данные пользователя для входа", required = true) @RequestBody UserDTO userData, HttpServletRequest request, HttpServletResponse response){
        if(authorizationService.signIn(userData, request, response)){
            return "Signed in successfully";
        }else{
            return "Bad credentials";
        }
    }

    @Operation(summary = "Получение информации о пользователе", description = "Возвращает информацию о текущем пользователе")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Информация о пользователе успешно получена")
    })
    @GetMapping("/my-info")
    public UserInfoDTO myInfo() {
        return userService.getMyInfo();
    }
}