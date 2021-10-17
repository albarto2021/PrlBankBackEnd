package com.bank.prl.payload.response;

import com.bank.prl.dao.UserDAO;
import com.bank.prl.repository.UserRepo;
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
