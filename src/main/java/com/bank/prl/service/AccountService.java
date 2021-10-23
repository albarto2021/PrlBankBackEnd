package com.bank.prl.service;

import com.bank.prl.dao.AccountDAO;
import com.bank.prl.model.Account;
import com.bank.prl.model.User;
import com.bank.prl.payload.request.TransferRequest;

import java.util.List;

public interface AccountService {

    void createAccount(Account account); // employee account oluşturacak

    void transfer(TransferRequest transferRequest);

    List<AccountDAO> getAllAccountDAOs();

    void assignAccount(Account account, User user, Long assignerId);

    void deleteAccount(Long accountId);
}
