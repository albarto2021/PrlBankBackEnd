package com.bank.prl.payload.request.dao.service.impl;

import com.bank.prl.payload.request.dao.dao.AccountDAO;
import com.bank.prl.payload.request.dao.dao.UserDAO;
import com.bank.prl.payload.request.dao.model.Account;
import com.bank.prl.payload.request.dao.model.User;
import com.bank.prl.payload.request.dao.repository.UserRepo;
import com.bank.prl.payload.request.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserRepo userRepo;
    // User(String ssn, String password, String firstName, String lastName, String dob,
    //                String email, String username)
    @Override
    public UserDAO getUserDAO(User user) {
        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(user.getUserId());
        userDAO.setSsn(user.getSsn());
        userDAO.setFirstName(user.getFirstName());
        userDAO.setLastName(user.getLastName());
        userDAO.setDob(user.getDob());
        userDAO.setEmail(user.getEmail());
        userDAO.setUsername(user.getUsername());
        userDAO.setPassword(user.getPassword());
        userDAO.setUserRoles(user.getUserRoles());
        Boolean isAdmin = user.getUserRoles().
                            stream().anyMatch( role -> role.getRole().getName().equals("ADMIN"));
        Boolean isEmployee = user.getUserRoles().
                stream().anyMatch( role -> role.getRole().getName().equals("EMPLOYEE"));
        userDAO.setIsAdmin(isAdmin);
        userDAO.setIsEmployee(isEmployee);
        List<AccountDAO> newAccountDAOs = user.getAccount().stream().map(this::transformAccountDAO).collect(Collectors.toList());

        userDAO.setAccounts(newAccountDAOs);
//  private List<AccountDAO> accounts;   userDAO
     // private List<Account> account; user
        return userDAO;
    }
    @Override
    public AccountDAO transformAccountDAO(Account account){

        AccountDAO accountDAO = new AccountDAO();

        accountDAO.setId(account.getId());
        accountDAO.setDescription(account.getDescription());
        accountDAO.setAccountBalance(account.getAccountBalance());
        accountDAO.setAccountType(account.getAccountType());
        accountDAO.setAccountStatusType(account.getAccountStatusType());
        accountDAO.setCreateDate(account.getCreateDate());
        accountDAO.setClosedDate(account.getClosedDate());
        accountDAO.setEmployee(account.getEmployee());

        return  accountDAO;
    }

    @Override
    public UserDAO getUserDAOByName(String username) {
        UserDAO userDAO = null;
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if(optionalUser.isPresent()){
            userDAO = getUserDAO(optionalUser.get());
        }
        return userDAO;
    }

    @Override
    public List<UserDAO> getAllUsers() {
        List<User> users = (List<User>) userRepo.findAll();

        //return users.stream().map(this::transformUsers).collect(Collectors.toList());
        return users.stream().map(this::getUserDAO).collect(Collectors.toList());
    }

    @Override
    public void updateUser(User user) {
        /*Optional<User> optionalUser = userRepo.findBySsn(ssn);
        if(optionalUser.isPresent()){
            userRepo.update(ssn, user.getFirstName(), user.getLastName(), user.getEmail());
        } */
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if(userRepo.findById(id).isPresent() ){
            userRepo.deleteById(id);
        }else{
            throw new IllegalArgumentException("No such user found");
        }

    }

/*
    public UserDAO transformUsers(User user) {
        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(user.getUserId());
        userDAO.setSsn(user.getSsn());
        userDAO.setFirstName(user.getFirstName());
        userDAO.setLastName(user.getLastName());
        userDAO.setDob(user.getDob());
        userDAO.setEmail(user.getEmail());
        userDAO.setUsername(user.getUsername());
        userDAO.setPassword(user.getPassword());

        return userDAO;
    } */

    @Override
    public UserDetails loadUserByUsername(String ssn) throws UsernameNotFoundException {
        User user = userRepo.findBySsn(ssn).
                orElseThrow( () -> new UsernameNotFoundException("SSN " + ssn + " not found"));
        return user;
    }



}
