package com.bank.prl.payload.response;

import com.bank.prl.dao.UserDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    List<UserDAO> userDAOList;
}
