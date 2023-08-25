package com.spring.picpay.Services.TransactionService;

import com.spring.picpay.Models.TransactionModel.Transaction;
import com.spring.picpay.Models.UserModel.User;
import com.spring.picpay.Repositories.TransactionRepository.TransactionRepository;
import com.spring.picpay.Services.UserService.UserService;
import com.spring.picpay.dto.TransactionDto.TransactionDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;


    public Transaction createTransaction(TransactionDto transactionDto) throws Exception {
            User sender = userService.findUserById(transactionDto.senderId());
            User receiver = userService.findUserById(transactionDto.receiverId());

            userService.validateSenderType(sender);
            userService.validateUserBalance(sender, transactionDto.value());
            transactionAuthorization();

            sender.setBalance(sender.getBalance().subtract(transactionDto.value()));
            receiver.setBalance(receiver.getBalance().add(transactionDto.value()));

            Transaction newTransaction = new Transaction();
            newTransaction.setSender(sender);
            newTransaction.setReceiver(receiver);
            newTransaction.setTransaction_value(transactionDto.value());
            newTransaction.setTransactionDate(LocalDateTime.now());

            userService.saveUser(sender);
            userService.saveUser(receiver);

            saveTransaction(newTransaction);
            return newTransaction;


    }


    public void transactionAuthorization() throws Exception {
        ResponseEntity<Map> response = restTemplate.getForEntity(
                "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
                Map.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Failed to retrieve authorization status");
        }

        String message = (String) response.getBody().get("message");
        if (!"Autorizado".equalsIgnoreCase(message)) {
            throw new Exception("Transaction Denied");
        }
    }


    public void saveTransaction(Transaction transaction){

        transactionRepository.save(transaction);
    }

    public Transaction findTransaction(Long id){
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

}
