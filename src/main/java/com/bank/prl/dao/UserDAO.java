package com.bank.prl.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
