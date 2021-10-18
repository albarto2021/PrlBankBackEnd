package com.bank.prl.payload.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateAccountForm {

    private String description;
    private BigDecimal accountBalance;
    private String accountType;
    private String accountStatusType;
    private Date createDate;
    private Date closedDate;
    private String Employee;

}
