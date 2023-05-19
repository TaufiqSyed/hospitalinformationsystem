package models;

import java.util.ArrayList;

public class Patient {
	String id, name, physician, gender;
	String birthday; // dd/mm/yyyy
	ArrayList<Illness> illnesses;
	ArrayList<VitalSign> vitalSigns;
	Floor registeredFloor;
	public Patient(String id, String name, String physician, String birthday, String gender,
			ArrayList<Illness> illnesses, ArrayList<VitalSign> vitalSigns, Floor registeredFloor) {
		super();
		this.id = id;
		this.name = name;
		this.physician = physician;
		this.birthday = birthday;
		this.gender = gender;
		this.illnesses = illnesses;
		this.vitalSigns = vitalSigns;
		this.registeredFloor = registeredFloor;
	}
	public Patient(String id, String name, String physician, String birthday, String gender,
			ArrayList<Illness> arrayList, ArrayList<VitalSign> arrayList2) {
		super();
		this.id = id;
		this.name = name;
		this.physician = physician;
		this.birthday = birthday;
		this.gender = gender;
		this.illnesses = arrayList;
		this.vitalSigns = arrayList2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhysician() {
		return physician;
	}
	public void setPhysician(String physician) {
		this.physician = physician;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public ArrayList<Illness> getIllnesses() {
		return illnesses;
	}
	public void setIllnesses(ArrayList<Illness> illnesses) {
		this.illnesses = illnesses;
	}
	public ArrayList<VitalSign> getVitalSigns() {
		return vitalSigns;
	}
	public void setVitalSigns(ArrayList<VitalSign> vitalSigns) {
		this.vitalSigns = vitalSigns;
	}
	public void addVitalSign(VitalSign vitalSign) {
		this.vitalSigns.add(vitalSign);
	}
	public Floor getRegisteredFloor() {
		return registeredFloor;
	}
	public void setRegisteredFloor(Floor registeredFloor) {
		this.registeredFloor = registeredFloor;
	}
	public boolean anyVitalOutOfRange() {
		for (VitalSign v: vitalSigns) {
			if (v.outOfRange()) return true;
		}
		return false;
	}
	public Patient() {}
}
