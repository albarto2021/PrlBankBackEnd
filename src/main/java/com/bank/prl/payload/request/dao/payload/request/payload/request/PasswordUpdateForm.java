package com.bank.prl.payload.request.dao.payload.request.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PasswordUpdateForm {
		
	
		
	   private String oldPassword;
	   
	   @NotBlank
	   @Size(min=6, max=40)
	   private String newPassword;

}
