package com.example.Reto2Grupo2.user.modelo;

public class UserPostRequest {

	private String username;

	private String password;

	private Integer rolId;

	public UserPostRequest() {
	}

	public UserPostRequest(String email, String password, Integer rolId) {
		super();
		this.username = email;
		this.password = password;
		this.rolId = rolId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String email) {
		this.username = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@Override
	public String toString() {
		return "UserPostRequest [username=" + username + ", password=" + password + ", rolId=" + rolId + "]";
	}

}
