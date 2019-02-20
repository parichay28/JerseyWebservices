package com.qainfotech;



import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement (name="user")
public class UserReg {
	private String name, email;
	private String password;
	
	public UserReg() {}

	public UserReg(String name, String email, String password) {
		this.name=name;
		this.email=email;
		this.password=password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
//	@Override
//	public String toString() {
//		return email+"::"+name;
//	}

}
