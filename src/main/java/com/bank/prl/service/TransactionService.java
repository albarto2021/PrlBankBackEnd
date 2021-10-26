package com.bank.prl.service;

import com.bank.prl.dao.AccountDAO;
import com.bank.prl.dao.TransactionDAO;
import com.bank.prl.model.Transaction;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    void saveTransaction(Transaction transaction);
    
    List<TransactionDAO> getAllTransactionDAOs();
    
}
