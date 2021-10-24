package com.bank.prl.dao;

import com.bank.prl.model.AccountStatusType;
import com.bank.prl.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AccountDAO {
    private Long id;

    private String description;
    private BigDecimal accountBalance;
    private String accountType;
    private String accountStatusType;
    private String createDate;
    private String closedDate;
    private String employee;
    private Long userId;


    public AccountDAO(String description, BigDecimal accountBalance, String accountType, String accountStatusType, String createDate, String closedDate, String employee) {
        this.description = description;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.accountStatusType = accountStatusType;
        this.createDate = createDate;
        this.closedDate = closedDate;
        this.employee = employee;
    }
}
