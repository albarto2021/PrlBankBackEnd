package com.bank.prl.payload.request.payload.response;

import com.bank.prl.payload.request.dao.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private UserDAO userDAO;
    private String jwt;
}
