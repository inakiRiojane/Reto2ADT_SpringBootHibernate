package com.example.Reto2Grupo2.Trabajador;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Trabajdor")

public class Trabajador {
	
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	   @Column(length = 60)
	   	private String username;
	   @Column(length = 60)
	   	private String password;
	   
	
	public Trabajador() {
		super();
	}
	public Trabajador(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	   	
	
	
	

}
