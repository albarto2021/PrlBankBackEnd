package com.bank.prl.controller;

import com.bank.prl.dao.UserDAO;
import com.bank.prl.model.User;
import com.bank.prl.payload.request.PasswordUpdateForm;
import com.bank.prl.payload.request.SignUpForm;
import com.bank.prl.payload.request.UpdateForm;
import com.bank.prl.payload.response.Response;
import com.bank.prl.payload.response.UpdateResponse;
import com.bank.prl.repository.UserRepo;
import com.bank.prl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    
    @PatchMapping("/updatePassword")
    public ResponseEntity<UpdateResponse> updatePassword(@Valid @RequestBody PasswordUpdateForm passwordUpdateForm,
                                                        HttpServletRequest request){
        UpdateResponse updateResponse = new UpdateResponse();

        String ssn = (String) request.getAttribute("ssn");
        
        System.out.println(passwordUpdateForm.getSsn());
        
//        User user= (User) userService.loadUserByUsername(passwordUpdateForm.getSsn());
        
        User user= (User) userService.loadUserByUsername(ssn);
        
        System.out.println(passwordUpdateForm.getSsn());
        
        user.setPassword(encoder.encode(passwordUpdateForm.getNewPassword()));

        userService.updateUser(user);
 

        updateResponse.setMessage("User has been successfully modified");
        updateResponse.setIsSuccess(true);

        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
    
}
