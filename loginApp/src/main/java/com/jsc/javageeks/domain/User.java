package com.jsc.javageeks.domain;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private int id;
	private String email;
	private String password;
	private Date date;

	public User() {
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.date = Calendar.getInstance().getTime();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", date=" + date + "]";
	}

}
