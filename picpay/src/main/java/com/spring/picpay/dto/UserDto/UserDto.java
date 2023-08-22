package com.spring.picpay.dto.UserDto;

import com.spring.picpay.Models.UserModel.UserType;

import java.math.BigDecimal;

public record UserDto(String email, String document, String lastName, String firstName,
                      BigDecimal balance, UserType userType, String password) {}
