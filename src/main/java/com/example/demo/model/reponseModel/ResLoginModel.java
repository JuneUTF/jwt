package com.example.demo.model.reponseModel;

import lombok.Data;

@Data
public class ResLoginModel {
	 private String username;
	 private String roles;
	 private String startsWith ="JuneUTF";
	 private int tokentime = 86400;
	 private String token;
}
