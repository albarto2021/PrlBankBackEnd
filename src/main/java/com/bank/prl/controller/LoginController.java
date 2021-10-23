package com.bank.prl.controller;

import com.bank.prl.config.jwt.JwtUtil;
import com.bank.prl.dao.UserDAO;
import com.bank.prl.model.Role;
import com.bank.prl.model.User;
import com.bank.prl.model.UserRole;
import com.bank.prl.payload.request.LoginForm;
import com.bank.prl.payload.request.SignUpForm;
import com.bank.prl.payload.request.UpdateForm;
import com.bank.prl.payload.response.LoginResponse;
import com.bank.prl.payload.response.Response;
import com.bank.prl.payload.response.UpdateResponse;
import com.bank.prl.repository.RoleRepo;
import com.bank.prl.repository.UserRepo;
import com.bank.prl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser( @Valid @RequestBody SignUpForm signUpForm){
        Response response = new Response();

        if(userRepo.existsBySsn(signUpForm.getSsn())){
            response.setMessage("Error: SSN has already been taken");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

/*      if(signUpForm.getSsn().charAt(3) != '-' || signUpForm.getSsn().charAt(6) != '-'){
            response.setMessage("Error: Please consider the syntax of SSN");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } */

        if(userRepo.existsByEmail(signUpForm.getEmail())){
            response.setMessage("Error: Email has already been taken");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // ssn, firstName, lastName, dob, email, username, password

        User user = new User(signUpForm.getSsn() ,
                signUpForm.getFirstName(), signUpForm.getLastName(),
                signUpForm.getDob(), signUpForm.getEmail(), signUpForm.getUsername(),
                encoder.encode(signUpForm.getPassword()));

        Set<String> stringRoles = new HashSet(); // the ones come from signup form
        Set<UserRole> userRoles = new HashSet(); // the ones we'll store


        stringRoles.forEach( roleName -> {
            Role role = roleRepo.findByName(roleName).
                        orElseThrow( () -> new RuntimeException("User Role not found"));
            userRoles.add( new UserRole(user, role));
        });

        user.setUserRoles(userRoles);
        userRepo.save(user);

        response.setMessage("User has been successfully registered");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login( @Valid @RequestBody LoginForm loginForm){

        Authentication authentication = authenticationManager
                .authenticate(  new UsernamePasswordAuthenticationToken(
                        loginForm.getSsn(),
                        loginForm.getPassword())  );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();


        String jwt = jwtUtil.generateToken(authentication);

        UserDAO userDAO = userService.getUserDAO(user);

        return ResponseEntity.ok( new LoginResponse(userDAO, jwt));
    }


/*
    @PatchMapping("/userInfoUpdate")
    public ResponseEntity<UpdateResponse> update(HttpServletRequest request,
                                                 @Valid @RequestBody UpdateForm updateForm
                                                 ){

        // httpservletrequest
        String ssn = (String) request.getAttribute("ssn");
        UpdateResponse updateResponse = new UpdateResponse();
        User currentUser = userRepo.findBySsn(ssn).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));

        currentUser.setFirstName(updateForm.getFirstName());
        currentUser.setLastName(updateForm.getLastName());
        currentUser.setEmail(updateForm.getEmail());

        User updatedUser = userRepo.save(currentUser);
        updateResponse.setMessage("Updated successfully");
        updateResponse.setIsSuccess(true);

        return ResponseEntity.ok(updateResponse);
    } */

}
