package com.bank.prl.service;

import com.bank.prl.dao.AccountDAO;
import com.bank.prl.model.Account;
import com.bank.prl.model.User;
import com.bank.prl.payload.request.CreateAccountForm;
import com.bank.prl.payload.request.TransactionRequestForm;
import com.bank.prl.payload.request.TransferRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    void createAccount(CreateAccountForm createAccountForm); // employee account oluşturacak

    void transfer(TransferRequest transferRequest);

    List<AccountDAO> getAllAccountDAOs();

    void assignAccount(Account account, User user, Long assignerId);

    void deleteAccount(Long accountId);

    void deposit(TransactionRequestForm request);

    void withdraw(TransactionRequestForm request);
}
