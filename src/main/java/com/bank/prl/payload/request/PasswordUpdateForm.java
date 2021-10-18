package com.bank.prl.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateForm {
		
	
		
	   private String oldPassword;
	   
	   @NotBlank
	   @Size(min=6, max=40)
	   private String newPassword;

}
