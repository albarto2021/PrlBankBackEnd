package com.bank.prl.controller;

import com.bank.prl.dao.AccountDAO;
import com.bank.prl.dao.UserDAO;
import com.bank.prl.model.Account;
import com.bank.prl.model.User;
import com.bank.prl.model.UserRole;
import com.bank.prl.payload.request.SingleUserUpdateForm;
import com.bank.prl.payload.request.UpdateForm;
import com.bank.prl.payload.response.Response;
import com.bank.prl.payload.response.UpdateResponse;
import com.bank.prl.payload.response.UserResponse;
import com.bank.prl.repository.AccountRepo;
import com.bank.prl.repository.RoleRepo;
import com.bank.prl.repository.UserRepo;
import com.bank.prl.service.AccountService;
import com.bank.prl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    @Autowired
    UserService userService;
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountService accountService;
    
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    RoleRepo roleRepo;



    @GetMapping("/allusers")
    public ResponseEntity<UserResponse> getAllUsers(){
        UserResponse userResponse = new UserResponse();
        List<UserDAO> userDAOList = userService.getAllUsers();
        userResponse.setUserDAOList(userDAOList);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id){
        Response response = new Response();

        userService.deleteUser(id);

        response.setSuccess(true);
        response.setMessage("User has been successfully deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/singleUserDetails/{id}")
    public ResponseEntity<Response> getSingleUser(@PathVariable Long id){
        Response response = new Response();
        UserDAO userDAO = userService.getUserDAOById(id);
        response.setSuccess(true);
        response.setMessage("User found");
        response.setUserDAO(userDAO);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/edituser")
    public ResponseEntity<List<AccountDAO>> getAllAccounts(){

        List<AccountDAO> allAccountDAOs = accountService.getAllAccountDAOs();
        return new ResponseEntity<>(allAccountDAOs, HttpStatus.OK);
    }




    @PatchMapping("/updateSingleUserInfo")
    public ResponseEntity<UpdateResponse> updateSingleUserInfo(@Valid @RequestBody SingleUserUpdateForm singleUserUpdateForm){

    	UpdateResponse updateResponse = new UpdateResponse();

			User user = userRepo.findBySsn(singleUserUpdateForm.getSsn()).orElseThrow(() -> new RuntimeException("Not found"));
			
			user.setFirstName(singleUserUpdateForm.getFirstName());
			user.setLastName(singleUserUpdateForm.getLastName());
			user.setEmail(singleUserUpdateForm.getEmail());
			user.setDob(singleUserUpdateForm.getDob());
			user.setUsername(singleUserUpdateForm.getUsername());

			
			//Role nasil guncellenecek
        /*
        * request body role array al {"ADMIN", "USER", "EMP"}
        * rol array rollerin id'lerini rol tablosundan alicaz
        * request body'deki ssn ile user'in id sini alicaz
        * user id ile role id'sini user_role tablosunda guncelle
        * */

            Arrays.stream(singleUserUpdateForm.getRole()).
                    forEach(t-> user.getUserRoles().add( new UserRole(user, roleRepo.findByName(t).get())));

        if(singleUserUpdateForm.getAccounts() != 0){
            Account account=accountRepo.findById(singleUserUpdateForm.getAccounts()).orElseThrow(()-> new RuntimeException("Account not found"));
            accountService.assignAccount(account, user, singleUserUpdateForm.getAssignerId());
        }
			//Acccount nasil guncellenecek

			userService.updateUser(user);
			userRepo.save(user);


			updateResponse.setMessage("User has been successfully modified");
			updateResponse.setIsSuccess(true);
			UserDAO userDAO = userService.getUserDAO(user);
			updateResponse.setUserDAO(userDAO);
			return new ResponseEntity<>(updateResponse, HttpStatus.OK);
			}
}