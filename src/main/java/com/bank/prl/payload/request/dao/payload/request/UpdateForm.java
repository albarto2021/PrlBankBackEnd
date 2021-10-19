package com.bank.prl.payload.request.dao.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateForm {


    private String firstName;
    private String lastName;
    private String email;

    //private UserDAO userDAO;
}
