package com.spring.picpay.Repositories.TransactionRepository;

import com.spring.picpay.Models.TransactionModel.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
