package com.bank.prl.service.impl;

import com.bank.prl.model.Account;
import com.bank.prl.model.User;
import com.bank.prl.payload.request.TransferRequest;
import com.bank.prl.repository.AccountRepo;
import com.bank.prl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl  implements AccountService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public void createAccount(Account account) {
        accountRepo.save(account);

    }

    @Override
    public void transfer(TransferRequest transferRequest) {

        Account fromAccount = accountRepo.
                                findById(transferRequest.getFromAccount()).
                                orElseThrow(()-> new RuntimeException("From Account Bulunamadı"));

        Account toAccount = accountRepo.
                                findById(transferRequest.getToAccount()).
                                orElseThrow(()-> new RuntimeException("To Account Bulunamadı"));



        Double amount = transferRequest.getAmount();
        toAccount.setAccountBalance(toAccount.getAccountBalance().add(new BigDecimal(amount)));

        fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(new BigDecimal(amount)));
      /*  Date date = new Date();
        Transaction transaction = new Transaction(date, request.getComment(),
                                            TransactionType.DEPOSIT.toString(),
                                            amount,account.getAccountBalance(),
                  false, account );
        List<Transaction> transactions = account.getTransactions();
        transactions.add(transaction);
        account.setTransactions(transactions);
        transactionService.saveTransaction(transaction); */
        //Description da setlenecek
        accountRepo.save(toAccount);
        accountRepo.save(fromAccount);

    }
}
