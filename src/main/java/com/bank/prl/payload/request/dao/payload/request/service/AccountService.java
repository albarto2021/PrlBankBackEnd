package com.bank.prl.payload.request.dao.payload.request.service;

import com.bank.prl.payload.request.dao.payload.request.model.Account;
import com.bank.prl.payload.request.dao.payload.request.payload.request.TransferRequest;

public interface AccountService {

    void createAccount(Account account); // employee account oluşturacak

    void transfer(TransferRequest transferRequest);
}
