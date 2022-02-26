package com.film.moviecrudapi.controller.admin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.film.moviecrudapi.common.baserespons.BaseResponse;
import com.film.moviecrudapi.controller.interfaces.AdminHandlerInterface;
import com.film.moviecrudapi.controller.interfaces.ErrorHandlerInterface;
import com.film.moviecrudapi.database.model.User;
import com.film.moviecrudapi.dto.auth.CreateUserDto;
import com.film.moviecrudapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserController", description = "User Admin controller API, for controlling user account, create account, get all user, get user by id and delete user")
@RestController
@RequestMapping("/api/admin/user")
public class UserController implements ErrorHandlerInterface, AdminHandlerInterface {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<HashMap<String, Object>> addNewUser(@Valid @RequestBody CreateUserDto createUserDto,
            HttpServletRequest credential) {
        if (!validateRoleAdmin(credential))
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        User user = userService.create(createUserDto);
        if (user != null) {
            return new BaseResponse(true, user, "Success Create User Data", HttpStatus.OK)
                    .toResponse();
        } else {
            return new BaseResponse(false, null, "Email already exist", HttpStatus.BAD_REQUEST)
                    .toResponse();
        }
    }

    @GetMapping(path = "")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> getAllUsers(HttpServletRequest credential) {
        if (!validateRoleAdmin(credential))
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        return new BaseResponse(true, userService.getAllUsers(), "Success Get Users Data", HttpStatus.OK).toResponse();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> getUserData(@PathVariable("id") String id,
            HttpServletRequest credential) {

        if (!validateRoleAdmin(credential))
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        User user = userService.getById(id);
        if (user != null) {
            return new BaseResponse(true, user, "Success Get Users Data",
                    HttpStatus.OK).toResponse();
        }
        return new BaseResponse(false, null, "Fail Get Users Data", HttpStatus.BAD_REQUEST)
                .toResponse();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<HashMap<String, Object>> deletUser(@PathVariable("id") String id,
            HttpServletRequest credential) {

        if (!validateRoleAdmin(credential))
            return new BaseResponse(false, null, "Forbidden", HttpStatus.UNAUTHORIZED).toResponse();

        User user = userService.getById(id);
        if (user != null) {
            userService.deletedUser(user);
            return new BaseResponse(true, user, "Success Deleted Users Data", HttpStatus.OK).toResponse();
        } else {
            return new BaseResponse(false, null, "Failed Deleted Users Data", HttpStatus.NOT_FOUND).toResponse();
        }
    }

}
