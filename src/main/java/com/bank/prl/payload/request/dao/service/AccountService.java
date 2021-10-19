package com.bank.prl.payload.request.dao.service;

import com.bank.prl.payload.request.dao.model.Account;
import com.bank.prl.payload.request.dao.payload.request.TransferRequest;

public interface AccountService {

    void createAccount(Account account); // employee account oluşturacak

    void transfer(TransferRequest transferRequest);
}
