package com.finwise.service;


import com.finwise.exception.TransactionNotFoundException;
import com.finwise.exception.UserNotFoundException;
import com.finwise.model.Transaction;
import com.finwise.model.User;
import com.finwise.repository.TransactionRepository;
import com.finwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        User user = userRepository.findById(transaction.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException(transaction.getUser().getId()));
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUserId(user.getId());
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }
}
