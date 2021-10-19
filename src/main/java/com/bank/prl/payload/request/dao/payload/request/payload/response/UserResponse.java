package com.bank.prl.payload.request.dao.payload.request.payload.response;

import com.bank.prl.payload.request.dao.payload.request.dao.UserDAO;
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
