package com.spring.picpay.dto.TransactionDto;

import com.spring.picpay.Models.UserModel.User;

import java.math.BigDecimal;

public record TransactionDto(BigDecimal value, Long senderId, Long receiverId) {
}
