package com.bank.prl.payload.request.dao.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpForm {

    // (String ssn, String password, String firstName, String lastName, String dob,
    //                String email, String username)
    @NotBlank
    @Size(min=11, max=11)
    private String ssn;

    private String firstName;
    private String lastName;
    private String dob;

    @NotBlank
    @Email( message = "Please provide a valid email address")
    private String email;

    private String username;

    @NotBlank
    @Size(min=6, max=40)
    private String password;


}
