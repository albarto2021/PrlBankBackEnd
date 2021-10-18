package com.bank.prl.payload.request;

import com.bank.prl.model.Account;
import com.bank.prl.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class AssignAccountForm {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String email;
    private String mobilePhoneNumber;
    private String phoneNumber;
    private String zipCode;
    private String address;
    private String city;
    private String ssn;
    private Date createDate;
    private String country;
    private String state;
    private User user;
    private Account account;



}
