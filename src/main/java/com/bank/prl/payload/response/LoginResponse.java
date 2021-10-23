package com.bank.prl.payload.response;

import com.bank.prl.dao.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;


@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private UserDAO userDAO;
    private String jwt;
}
