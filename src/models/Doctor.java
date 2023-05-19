package models;

import utils.PasswordUtil;

public class Doctor {
	private String name, specialization;
	private String id;
	private String password;
	private boolean loggedInOnce = false;
	public Doctor(String id, String name, String specialization) {
		super();
		this.id = id;
		this.name = name;
		this.specialization = specialization;
		this.password = PasswordUtil.generatePassword();
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Doctor [name=" + name + ", specialization=" + specialization + ", id=" + id + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean getLoggedInOnce() {
		return this.loggedInOnce;
	}
	public void setLoggedInOnce(boolean loggedInOnce) {
		this.loggedInOnce = loggedInOnce;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
