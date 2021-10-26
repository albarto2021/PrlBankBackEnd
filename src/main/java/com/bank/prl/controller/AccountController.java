package com.bank.prl.controller;


import com.bank.prl.dao.AccountDAO;
import com.bank.prl.dao.TransactionDAO;
import com.bank.prl.dao.UserDAO;
import com.bank.prl.model.Account;
import com.bank.prl.model.User;
import com.bank.prl.payload.request.AssignAccountForm;
import com.bank.prl.payload.request.CreateAccountForm;
import com.bank.prl.payload.request.TransactionRequestForm;
import com.bank.prl.payload.request.TransferRequest;
import com.bank.prl.payload.response.Response;
import com.bank.prl.payload.response.TransactionResponse;
import com.bank.prl.repository.AccountRepo;
import com.bank.prl.repository.UserRepo;
import com.bank.prl.service.AccountService;
import com.bank.prl.service.TransactionService;
import com.bank.prl.service.UserService;
import com.bank.prl.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/accounts") //YAZILACAK!!!!!!!!!!!!!!
public class AccountController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;
    
    @Autowired
    TransactionService transactionService;


    @PostMapping("/createAccount")
    public ResponseEntity<Response> createAccount(@Valid
                                                  @RequestBody CreateAccountForm createAccountForm){

        Response response = new Response();
        //System.out.println("deneme");
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    // OLUP OLMAMASI DÜŞÜNÜLECEK
  //      if (accountRepo.existsByDescription(createAccountForm.getDescription()) &&
  //          accountRepo.existsByAccountType(createAccountForm.getAccountType())){
  //          response.setMessage("Error: Account is already created");
  //          response.setSuccess(false);
  //          return new ResponseEntity<>(response, HttpStatus.OK);
  //      }
        // SILINECEK
        // User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("Not found"));



        //Create a new account
        Account account = new Account(
                createAccountForm.getDescription(),
                createAccountForm.getAccountBalance(),
                createAccountForm.getAccountType(),
                createAccountForm.getAccountStatusType(),
                createAccountForm.getCreateDate(),
                createAccountForm.getClosedDate(),
                createAccountForm.getEmployee()
        );

        //user.setAccount((List<Account>) account);


        accountService.createAccount(account);

        response.setMessage("Account created successfuly");
        response.setSuccess(true);


        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/deposit")
    public  ResponseEntity<Response> deposit(
            @Valid @RequestBody TransactionRequestForm request){

        Response response = new Response();
        Account account = accountRepo.findById(request.getAccountDescriptions()).get();

        User user = userRepo.findById(account.getUser().getUserId()).get();

        double amount = request.getAmount();
        if( amount > 0){
            accountService.deposit(request);

            response.setMessage("Amount successfully deposited");
            response.setSuccess(true);
            UserDAO userDAO = userService.getUserDAO(user);
            response.setUserDAO(userDAO);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            response.setMessage("Amount should be greater than 0");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/withdraw")
    public ResponseEntity<Response> withdraw(@RequestBody TransactionRequestForm request){
        Response response = new Response();

        Account account = accountRepo.findById(request.getAccountDescriptions()).get();
        User user = userRepo.findById(account.getUser().getUserId()).get();

        double amount = request.getAmount();

        if( account.getAccountBalance().doubleValue() >= amount && amount > 0 ){
            accountService.withdraw(request);
            response.setMessage("Amount successfully deposited");
            response.setSuccess(true);
            UserDAO userDAO = userService.getUserDAO(user);
            response.setUserDAO(userDAO);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {

            response.setMessage("Amount should be greater than 0");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/moneyTransfer/{id}")
    public ResponseEntity<Response> accountTransfer(@Valid @PathVariable Long id, @RequestBody TransferRequest transferRequest){

        Response response = new Response();

        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));

        accountService.transfer(transferRequest);


        response.setMessage("Account assigned successfuly");
        response.setSuccess(true);


        UserDAO userDAO = userService.getUserDAO(user);
        response.setUserDAO(userDAO);
        //return new ResponseEntity<>(updateResponse, HttpStatus.OK);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDAO>> getAllTransactions(){

        List<TransactionDAO> allTransactionDAOs = transactionService.getAllTransactionDAOs();
        TransactionResponse transactionResponse=new TransactionResponse();
        transactionResponse.setAllTransactionDAOList(allTransactionDAOs);

        return new ResponseEntity<>(allTransactionDAOs, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Response> deleteAccount(@PathVariable Long accountId){
        Response response = new Response();
        accountService.deleteAccount(accountId);

        response.setSuccess(true);
        response.setMessage("Account has been successfully deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
  

}
