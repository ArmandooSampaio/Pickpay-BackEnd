package com.spring.picpay.Services.TransactionService;

import com.spring.picpay.Models.TransactionModel.Transaction;
import com.spring.picpay.Models.UserModel.User;
import com.spring.picpay.Repositories.TransactionRepository.TransactionRepository;
import com.spring.picpay.Services.UserService.UserService;
import com.spring.picpay.dto.TransactionDto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;


    public Transaction createTransaction(TransactionDto transactionDto)  {
        try{
            User sender = userService.findUserById(transactionDto.sender_id());
            User receiver = userService.findUserById(transactionDto.sender_id());

            userService.validateSenderType(sender);
            userService.validateUserBalance(sender, transactionDto.value());
            transactionAutorization();

            sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
            receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

            Transaction transaction = new Transaction();
            transaction.setSender(sender);
            transaction.setReceiver(receiver);
            transaction.setValue(transactionDto.value());
            transaction.setTransactionDate(LocalDateTime.now());

            saveTransaction(transaction);
            return transaction;

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public void transactionAutorization() throws Exception {
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
                Map.class);

        String message = (String) response.getBody().get("message");

        if(!message.equalsIgnoreCase("autorizado") || response.getStatusCode() != HttpStatus.OK){
            throw new Exception("Transaction Denied");
        }
    }

    public void saveTransaction(Transaction transaction){

        transactionRepository.save(transaction);
    }


}
