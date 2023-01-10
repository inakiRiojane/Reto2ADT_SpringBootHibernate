package com.example.Reto2Grupo2.billete.modelo;

import java.sql.Date;
import com.example.Reto2Grupo2.user.modelo.User;
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
@Table(name = "billetes")
public class Billete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Date fecha;
	@Column
	private Integer cantidad;
	@Column
	private float importe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_zoo", foreignKey = @ForeignKey(name = "FK_id_zooBillete"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Zoo zoo;

	@Column(name = "zoo_id", insertable = false, updatable = false)
	private Integer idZoo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_id_userBillete"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;

	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer idUser;

	public Billete() {
	}

	public Billete(Integer id, Date fecha, Integer cantidad, float importe, Zoo zoo,User user) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		this.zoo = zoo;
		this.user=user;

	}

	public Billete(Integer id, Date fecha, Integer cantidad, float importe) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
	}

	public Billete(Integer id, Date fecha, Integer cantidad, float importe, Zoo zoo, Integer idZoo, User user,
			Integer idUser) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		this.zoo = zoo;
		this.idZoo = idZoo;
		this.user = user;
		this.idUser = idUser;
	}

	public Billete(Integer id, Date fecha, Integer cantidad, float importe, Zoo zoo, Integer idZoo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		this.zoo = zoo;
		this.idZoo = idZoo;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public Integer getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(Integer idZoo) {
		this.idZoo = idZoo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Billete [id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + ", importe=" + importe + ", zoo="
				+ zoo + ", idZoo=" + idZoo + ", user=" + user + ", idUser=" + idUser + "]";
	}

}