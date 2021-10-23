package com.bank.prl.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleUserUpdateForm {

	private String ssn;
	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String username;
	private String[] role;
	/*private String[] userrole;
	private String[] adminrole;
	private String[] employeerole;*/
	private Long accounts;
	private Long assignerId; // admin or employee
	
}
//ssn: currentUser.ssn,
//firstName: currentUser.firstName,
//lastName: currentUser.lastName,
//dob: currentUser.dob,
//email: currentUser.email,
//username: currentUser.username,
//role: role,
//accounts: "",