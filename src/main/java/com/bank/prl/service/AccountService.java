package com.bank.prl.service;

import com.bank.prl.model.Account;
import com.bank.prl.model.User;
import com.bank.prl.payload.request.TransferRequest;

public interface AccountService {

    void createAccount(Account account); // employee account oluşturacak

    void transfer(TransferRequest transferRequest);
}
