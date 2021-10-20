package com.bank.prl.controller;

import com.bank.prl.dao.UserDAO;
import com.bank.prl.payload.response.Response;
import com.bank.prl.payload.response.UserResponse;
import com.bank.prl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    @Autowired
    UserService userService;



    @GetMapping("/admin/allusers")
    public ResponseEntity<UserResponse> getAllUsers(){
        UserResponse userResponse = new UserResponse();
        List<UserDAO> userDAOList = userService.getAllUsers();
        userResponse.setUserDAOList(userDAOList);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id){
        Response response = new Response();

        userService.deleteUser(id);

        response.setSuccess(true);
        response.setMessage("User has been successfully deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/admin/singleUserDetails/{id}")
    public ResponseEntity<Response> getSingleUser(@PathVariable Long id){
        Response response = new Response();
        UserDAO userDAO = userService.getUserDAOById(id);
        response.setSuccess(true);
        response.setMessage("User found");
        response.setUserDAO(userDAO);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
