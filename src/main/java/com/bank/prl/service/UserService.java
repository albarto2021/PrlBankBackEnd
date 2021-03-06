package com.bank.prl.service;

import com.bank.prl.dao.AccountDAO;
import com.bank.prl.dao.TransactionDAO;
import com.bank.prl.dao.UserDAO;
import com.bank.prl.model.Account;
import com.bank.prl.model.Transaction;
import com.bank.prl.model.User;
import com.bank.prl.service.impl.UserDetailServiceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDAO getUserDAO(User user);
    UserDAO getUserDAOByName(String username);
    List<UserDAO> getAllUsers();
    //void updateUser(String ssn, User user);
    void updateUser(User user);
    void deleteUser(Long id);
    UserDetails loadUserByUsername(String ssn);
    AccountDAO transformAccountDAO(Account account);
    UserDAO getUserDAOById(Long id);
    TransactionDAO transformTransactionDAO(Transaction tran);
}
