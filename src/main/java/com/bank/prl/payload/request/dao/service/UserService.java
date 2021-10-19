package com.bank.prl.payload.request.dao.service;

import com.bank.prl.payload.request.dao.dao.AccountDAO;
import com.bank.prl.payload.request.dao.dao.UserDAO;
import com.bank.prl.payload.request.dao.model.Account;
import com.bank.prl.payload.request.dao.model.User;
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
}
