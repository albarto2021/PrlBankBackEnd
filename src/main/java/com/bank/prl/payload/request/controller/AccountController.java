package com.bank.prl.payload.request.controller;


import com.bank.prl.payload.request.dao.UserDAO;
import com.bank.prl.payload.request.model.Account;
import com.bank.prl.payload.request.model.User;
import com.bank.prl.payload.request.payload.request.AssignAccountForm;
import com.bank.prl.payload.request.payload.request.CreateAccountForm;
import com.bank.prl.payload.request.payload.request.TransferRequest;
import com.bank.prl.payload.request.payload.response.Response;
import com.bank.prl.payload.request.repository.AccountRepo;
import com.bank.prl.payload.request.repository.UserRepo;
import com.bank.prl.payload.request.service.AccountService;
import com.bank.prl.payload.request.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth") //YAZILACAK!!!!!!!!!!!!!!
public class AccountController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;



    @PostMapping("/createAccount/{id}")
    public ResponseEntity<Response> createAccount(@Valid @PathVariable Long id,
                                                  @RequestBody CreateAccountForm createAccountForm){

        Response response = new Response();
        System.out.println("deneme");
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    // OLUP OLMAMASI DÜŞÜNÜLECEK
  //      if (accountRepo.existsByDescription(createAccountForm.getDescription()) &&
  //          accountRepo.existsByAccountType(createAccountForm.getAccountType())){
  //          response.setMessage("Error: Account is already created");
  //          response.setSuccess(false);
  //          return new ResponseEntity<>(response, HttpStatus.OK);
  //      }
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("Not found"));



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

    @PatchMapping("/auth/assignAccountUser")
    public  ResponseEntity<Response> assignAccountUser(
                @Valid @RequestBody AssignAccountForm assignAccountForm){

        Response response=new Response();

        System.out.println("ÇALIŞTI MI");

        User user= userRepo.findById(assignAccountForm.getUser().getUserId()).orElseThrow(
                ()->new UsernameNotFoundException("User not found")
        );
        Account account = accountRepo.findById(assignAccountForm.getAccount().getId()).orElseThrow(
                ()-> new RuntimeException("Account not fount")
        );

        account.setUser(user);

        accountService.createAccount(account);

        response.setMessage("Account assigned successfuly");
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
/*
User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        user.setFirstName(updateForm.getFirstName());
        user.setLastName(updateForm.getLastName());
        user.setEmail(updateForm.getEmail());

        userService.updateUser(user);
*/
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


}
