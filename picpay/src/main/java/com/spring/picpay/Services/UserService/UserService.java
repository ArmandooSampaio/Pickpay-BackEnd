package com.spring.picpay.Services.UserService;

import com.spring.picpay.Models.UserModel.User;
import com.spring.picpay.Models.UserModel.UserType;
import com.spring.picpay.Repositories.UserRepository.UserRepository;
import com.spring.picpay.dto.UserDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public void validateSenderType(User sender) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Merchants can not send money.");
        }
    }

    public  void validateUserBalance(User sender, BigDecimal transactionValue) throws Exception {
        if(sender.getBalance().compareTo(transactionValue) < 0){
            throw new Exception("insufficient funds");
        }
    }

    public User createUser(UserDto userDto){
        User newUser = new User(userDto);
        saveUser(newUser);
        return newUser;
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }


    public void saveUser(User user){
        userRepository.save(user);
    }

}
