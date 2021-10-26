package com.bank.prl.service.impl;

import com.bank.prl.dao.AccountDAO;
import com.bank.prl.dao.TransactionDAO;
import com.bank.prl.model.Account;
import com.bank.prl.model.Transaction;
import com.bank.prl.repository.TransactionRepo;
import com.bank.prl.service.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
    }
    
    @Override
    public List<TransactionDAO> getAllTransactionDAOs() {
        List<Transaction> allTransactions = (List<Transaction>) transactionRepo.findAll();
        UserDetailServiceImpl serviceImpl = new UserDetailServiceImpl();
        return allTransactions.stream().map(serviceImpl::transformTransactionDAO).collect(Collectors.toList());
    }

	
    
}
