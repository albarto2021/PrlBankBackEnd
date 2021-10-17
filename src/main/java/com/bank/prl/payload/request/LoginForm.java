package com.bank.prl.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginForm {


    @NotBlank
    @Size(min = 11, max = 11)
    private String ssn;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


}
