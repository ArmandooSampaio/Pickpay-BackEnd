package com.spring.picpay.Controllers;

import com.spring.picpay.Models.UserModel.User;
import com.spring.picpay.Services.UserService.UserService;
import com.spring.picpay.dto.UserDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        User user = userService.createUser(userDto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


}
