package models;

import java.util.ArrayList;

public class Floor {
	String name;
	int number, capacity;
	String floorCode;
	String medicalDepartment;
	String chiefResidentDoctor;
	ArrayList<Patient> registeredPatients;
	
	public Floor() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public String getMedicalDepartment() {
		return medicalDepartment;
	}

	public void setMedicalDepartment(String medicalDepartment) {
		this.medicalDepartment = medicalDepartment;
	}

	public String getChiefResidentDoctor() {
		return chiefResidentDoctor;
	}

	public void setChiefResidentDoctor(String chiefResidentDoctor) {
		this.chiefResidentDoctor = chiefResidentDoctor;
	}

	public ArrayList<Patient> getRegisteredPatients() {
		return registeredPatients;
	}

	public void setRegisteredPatients(ArrayList<Patient> registeredPatients) {
		this.registeredPatients = registeredPatients;
	}
	
	public void addRegisteredPatient(Patient registeredPatient) {
		this.registeredPatients.add(registeredPatient);
	}
	
	@Override
	public String toString() {
		return "Floor [name=" + name + ", number=" + number + ", floorCode=" + floorCode + ", medicalDepartment="
				+ medicalDepartment + ", chiefResidentDoctor=" + chiefResidentDoctor + ", registeredPatients="
				+ registeredPatients + "]";
	}
	
	public Floor(String name, int number, String floorCode, String medicalDepartment, String chiefResidentDoctor) {
		super();
		this.name = name;
		this.number = number;
		this.floorCode = floorCode;
		this.medicalDepartment = medicalDepartment;
		this.chiefResidentDoctor = chiefResidentDoctor;
		this.capacity = 5; // default capacity is 5
		this.registeredPatients = new ArrayList<Patient>();
	}
	
	public Floor(String name, int number, String floorCode, String medicalDepartment, String chiefResidentDoctor, int capacity,
			ArrayList<Patient> registeredPatients) {
		super();
		this.name = name;
		this.number = number;
		this.floorCode = floorCode;
		this.medicalDepartment = medicalDepartment;
		this.chiefResidentDoctor = chiefResidentDoctor;
		this.capacity = capacity;
		this.registeredPatients = registeredPatients;
	}
	
}
