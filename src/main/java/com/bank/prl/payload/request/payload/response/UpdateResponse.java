package com.bank.prl.payload.request.payload.response;

import com.bank.prl.payload.request.dao.UserDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UpdateResponse {

    private String message;
    private Boolean isSuccess;
    private UserDAO userDAO;

}
