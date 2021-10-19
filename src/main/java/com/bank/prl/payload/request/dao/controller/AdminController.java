package com.bank.prl.payload.request.dao.controller;

import com.bank.prl.payload.request.dao.dao.UserDAO;
import com.bank.prl.payload.request.dao.payload.response.Response;
import com.bank.prl.payload.request.dao.payload.response.UserResponse;
import com.bank.prl.payload.request.dao.service.UserService;
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
}
