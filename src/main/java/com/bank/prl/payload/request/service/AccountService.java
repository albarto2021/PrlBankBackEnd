package com.bank.prl.payload.request.service;

import com.bank.prl.payload.request.model.Account;
import com.bank.prl.payload.request.payload.request.TransferRequest;

public interface AccountService {

    void createAccount(Account account); // employee account oluşturacak

    void transfer(TransferRequest transferRequest);
}
