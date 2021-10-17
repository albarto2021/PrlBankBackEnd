package com.bank.prl.payload.request;

import com.bank.prl.dao.UserDAO;
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
