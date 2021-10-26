package com.bank.prl.service.impl;

import com.bank.prl.dao.AccountDAO;
import com.bank.prl.model.Account;
import com.bank.prl.model.Transaction;
import com.bank.prl.model.TransactionType;
import com.bank.prl.model.User;
import com.bank.prl.payload.request.TransactionRequestForm;
import com.bank.prl.payload.request.TransferRequest;
import com.bank.prl.repository.AccountRepo;
import com.bank.prl.repository.UserRepo;
import com.bank.prl.service.AccountService;
import com.bank.prl.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl  implements AccountService {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    TransactionService transactionService;


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

        accountRepo.save(toAccount);
        accountRepo.save(fromAccount);

    }

    @Override
    public List<AccountDAO> getAllAccountDAOs() {
        List<Account> allAccounts = (List<Account>) accountRepo.findAll();
        UserDetailServiceImpl serviceImpl = new UserDetailServiceImpl();
        return allAccounts.stream().map(serviceImpl::transformAccountDAO).collect(Collectors.toList());
    }

    @Override
    public void assignAccount(Account account, User user, Long assignerId) {
        account.setUser(user);
        account.setEmployee(userRepo.findById(assignerId).get().getFirstName() +
                " " + userRepo.findById(assignerId).get().getLastName());
        accountRepo.save(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepo.deleteById(accountId);
    }

    @Override
    public void deposit(TransactionRequestForm request) {
        Account account = accountRepo.
                findById(request.getAccountDescriptions()).orElseThrow(()->new RuntimeException("Account not found"));

        account.setAccountBalance(account.getAccountBalance().add(new BigDecimal(request.getAmount())));

        Date date = new Date();
        System.out.println(account.getUser().getUserId());
        Transaction tran = new Transaction(date, request.getComment(), TransactionType.DEPOSIT.toString(),
                request.getAmount(), account.getAccountBalance(), account, account.getUser().getUserId());

        List<Transaction> transactionList = account.getTransactions();
        transactionList.add(tran);
//        System.out.println(tran.getAvailableBalance());
        account.setTransactions(transactionList);
//        System.out.println(account.getTransactions());
        accountRepo.save(account);
//        transactionService.saveTransaction(tran);

    }

    @Override
    public void withdraw(TransactionRequestForm request) {
        Account account = accountRepo.
                findById(request.getAccountDescriptions()).orElseThrow(()-> new RuntimeException("Account not found"));

        account.setAccountBalance(account.getAccountBalance().subtract(new BigDecimal(request.getAmount())));

        Date date = new Date();

        Transaction tran = new Transaction(date, request.getComment(), TransactionType.WITHDRAW.toString(),
                request.getAmount(), account.getAccountBalance(), account,account.getUser().getUserId());

        List<Transaction> transactionList = account.getTransactions();
        transactionList.add(tran);
        account.setTransactions(transactionList);

        accountRepo.save(account);
    }
}
