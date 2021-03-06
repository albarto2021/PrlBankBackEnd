package com.bank.prl.payload.request.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AccountDAO {
    private Long id;

    private String description;
    private BigDecimal accountBalance;
    private String accountType;
    private String accountStatusType;
    private Date createDate;
    private Date closedDate;
    private String Employee;

 //   private final List<String> accStatTypeList =
 //           Arrays.stream(AccountStatusType.values()).map(t->t.toString()).collect(Collectors.toList());

    public AccountDAO(String description, BigDecimal accountBalance, String accountType, String accountStatusType, Date createDate, Date closedDate, String employee) {
        this.description = description;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.accountStatusType = accountStatusType;
 //       this.accountStatusType = (accStatTypeList.contains(accountStatusType))? accountStatusType : null;
        this.createDate = createDate;
        this.closedDate = closedDate;
        Employee = employee;
    }
}
