package com.bank.prl.payload.request.controller;

import com.bank.prl.payload.request.dao.UserDAO;
import com.bank.prl.payload.request.model.User;
import com.bank.prl.payload.request.payload.request.PasswordUpdateForm;
import com.bank.prl.payload.request.payload.request.UpdateForm;
import com.bank.prl.payload.request.payload.response.UpdateResponse;
import com.bank.prl.payload.request.repository.UserRepo;
import com.bank.prl.payload.request.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UpdateController {
	
	

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;
    
    @Autowired
    PasswordEncoder encoder;


    @PatchMapping("/updateUserInfo/{id}")
    public ResponseEntity<UpdateResponse> updateUserInfo(@Valid @PathVariable Long id, @RequestBody UpdateForm updateForm
                                                        ){
        UpdateResponse updateResponse = new UpdateResponse();

        // String ssn = (String) request.getAttribute("ssn");
        // userService.updateUser(ssn, user);
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        user.setFirstName(updateForm.getFirstName());
        user.setLastName(updateForm.getLastName());
        user.setEmail(updateForm.getEmail());

        userService.updateUser(user);
        //userRepo.save(user);

        updateResponse.setMessage("User has been successfully modified");
        updateResponse.setIsSuccess(true);
        UserDAO userDAO = userService.getUserDAO(user);
        updateResponse.setUserDAO(userDAO);
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
    
    @PatchMapping("/updatePassword/{id}")
    public ResponseEntity<UpdateResponse> updatePassword(@Valid @PathVariable Long id, @RequestBody PasswordUpdateForm passwordUpdateForm
                                                        ){
        UpdateResponse updateResponse = new UpdateResponse();

//        String ssn = (String) request.getAttribute("ssn");
        System.out.println("burdayim");
        
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        
        System.out.println(user.getSsn());
        
//        User user= (User) userService.loadUserByUsername(passwordUpdateForm.getSsn());
        
//        User user= (User) userService.loadUserByUsername(ssn);
        
//        buradan itibaren yeni
        UserDAO userDAO=userService.getUserDAO(user);
        if(encoder.matches(passwordUpdateForm.getOldPassword(), user.getPassword())) {
        	user.setPassword(encoder.encode(passwordUpdateForm.getNewPassword()));
        	userService.updateUser(user);
            
        	updateResponse.setMessage("User has been successfully modified");
            updateResponse.setIsSuccess(true);
            updateResponse.setUserDAO(userDAO);

            return new ResponseEntity<>(updateResponse, HttpStatus.OK);
        } else {
        	updateResponse.setIsSuccess(false);
        	updateResponse.setMessage("Password doesnt match");
        	updateResponse.setUserDAO(userDAO);
        	
        	return new ResponseEntity<>(updateResponse, HttpStatus.ACCEPTED);
        }
        
//        user.setPassword(encoder.encode(passwordUpdateForm.getNewPassword()));

//        userService.updateUser(user);
//        UserDAO userDAO=userService.getUserDAO(user);
// 
//
//        updateResponse.setMessage("User has been successfully modified");
//        updateResponse.setIsSuccess(true);
//        updateResponse.setUserDAO(userDAO);
//
//        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
    
}
