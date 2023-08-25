package com.spring.picpay.Controllers.TransactionController;

import com.spring.picpay.Models.TransactionModel.Transaction;
import com.spring.picpay.Services.TransactionService.TransactionService;
import com.spring.picpay.dto.TransactionDto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> showAllTransactions(){
       List<Transaction> transactions = transactionService.findAll();
       return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        Transaction transaction = transactionService.createTransaction(transactionDto);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

}
