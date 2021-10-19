package com.bank.prl.payload.request.dao.payload.request.service;

import com.bank.prl.payload.request.dao.payload.request.dao.AccountDAO;
import com.bank.prl.payload.request.dao.payload.request.dao.UserDAO;
import com.bank.prl.payload.request.dao.payload.request.model.Account;
import com.bank.prl.payload.request.dao.payload.request.model.User;
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
