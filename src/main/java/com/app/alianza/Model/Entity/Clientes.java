package com.app.alianza.Model.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "clientes")
public class Clientes {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String sharedkey;
	private String bussinessid;
	private String email;
	private String phone;
	private String data;
	
	public Clientes() {
		super();
	}

	public Clientes(int id, String sharedkey, String bussinessid, String email, String phone, String data) {
		super();
		this.id = id;
		this.sharedkey = sharedkey;
		this.bussinessid = bussinessid;
		this.email = email;
		this.phone = phone;
		this.data = data;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSharedkey() {
		return sharedkey;
	}
	
	public void setSharedkey(String sharedkey) {
		this.sharedkey = sharedkey;
	}
	
	public String getBussinessid() {
		return bussinessid;
	}
	
	public void setBussinessid(String bussinessid) {
		this.bussinessid = bussinessid;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
}
