package com.qainfotech;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="login")
public class UserLogin {
	private String email, password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
