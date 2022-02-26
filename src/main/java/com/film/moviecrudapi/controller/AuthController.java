package com.film.moviecrudapi.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.film.moviecrudapi.common.baserespons.BaseResponse;
import com.film.moviecrudapi.common.enums.UserEnumRole.UserRoleEnums;
import com.film.moviecrudapi.controller.interfaces.ErrorHandlerInterface;
import com.film.moviecrudapi.database.model.User;
import com.film.moviecrudapi.dto.auth.CreateUserDto;
import com.film.moviecrudapi.dto.auth.LoginUserDto;
import com.film.moviecrudapi.service.JwtService;
import com.film.moviecrudapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AuthController", description = "Auth controller API, For login Admin and User here, Register user account, and get profil account")
@RestController
@RequestMapping("/api")
public class AuthController implements ErrorHandlerInterface {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping(path = "/login")
    public ResponseEntity<HashMap<String, Object>> loginUser(@Valid @RequestBody LoginUserDto loginUserDto) {
        User user = userService.login(loginUserDto);
        if (user != null) {
            return new BaseResponse(true, jwtService.createJWT(user), "Success Login User ", HttpStatus.OK)
                    .toResponse();
        }
        return new BaseResponse(false, null, "Email Or Password Not Exist", HttpStatus.NOT_FOUND)
                .toResponse();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<HashMap<String, Object>> addNewUser(@Valid @RequestBody CreateUserDto createUserDto) {
        createUserDto.role = UserRoleEnums.User;
        User user = userService.create(createUserDto);
        if (user != null) {
            return new BaseResponse(true, user, "Success Create User Data", HttpStatus.OK)
                    .toResponse();
        } else {
            return new BaseResponse(false, null, "Email already exist", HttpStatus.BAD_REQUEST)
                    .toResponse();
        }
    }

    @GetMapping(path = "/user/profile")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> getUserData(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");

        User user = userService.findUserById(id);
        if (user != null) {
            return new BaseResponse(true, user, "Success Get Users Data",
                    HttpStatus.OK).toResponse();
        }
        return new BaseResponse(false, null, "Fail Get Users Data", HttpStatus.BAD_REQUEST)
                .toResponse();
    }

}
