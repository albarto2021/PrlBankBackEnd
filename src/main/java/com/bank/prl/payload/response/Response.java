package com.bank.prl.payload.response;

import com.bank.prl.dao.UserDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {

    boolean isSuccess;
    String message;
    private UserDAO userDAO;
}
