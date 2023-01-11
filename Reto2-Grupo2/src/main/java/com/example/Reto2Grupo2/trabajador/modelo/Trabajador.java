package com.example.Reto2Grupo2.trabajador.modelo;

import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trabajadores")
public class Trabajador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 60)
	private String username;
	@Column(length = 60)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zoo_id", foreignKey = @ForeignKey(name = "FK_zoo_id_Trab"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Zoo zoo;

	@Column(name = "zoo_id", insertable = false, updatable = false)
	private Integer zooId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_rol", foreignKey=@ForeignKey(name = "FK_id_trabajadorRoles"))
//	@JsonManagedReference
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Rol rol;

//	@Column(name="id_rol", insertable=false, updatable=false)
//	private int idRol;

	public Trabajador() {

	}

	public Trabajador(Integer id, String username, String password, Zoo zoo, Integer zooId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.zoo = zoo;
		this.zooId = zooId;
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

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer zooId) {
		this.zooId = zooId;
	}

	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", username=" + username + ", password=" + password + ", zoo=" + zoo
				+ ", zooId=" + zooId + "]";
	}

}