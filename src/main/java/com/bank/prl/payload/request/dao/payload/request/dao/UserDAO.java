package com.bank.prl.payload.request.dao.payload.request.dao;

import com.bank.prl.payload.request.dao.payload.request.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {

    private Long userId;
    private String ssn;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String username;
    @JsonIgnore
    private String password;
    private Boolean isAdmin;
    private Boolean isEmployee;
    private Set<UserRole> userRoles;

    /////////////
    private Long accountNumber;
    private BigDecimal accountBalance;
    private List<TransactionDAO> transactions;
    private List<AccountDAO> accounts;
}
