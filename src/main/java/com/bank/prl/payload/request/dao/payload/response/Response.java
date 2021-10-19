package com.bank.prl.payload.request.dao.payload.response;

import com.bank.prl.payload.request.dao.dao.UserDAO;
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
