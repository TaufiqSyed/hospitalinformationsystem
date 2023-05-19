package controllers;

import java.util.ArrayList;

import models.Floor;
import models.Illness;
import models.Patient;
import models.VitalSign;
import views.PatientView;

public class PatientController {
	private Patient model;
	private PatientView view;
	
	public PatientController(Patient p) {
		model = p;
		view = new PatientView(p);
	}
	public boolean anyVitalOutOfRange() {
		for (VitalSign v: model.getVitalSigns()) {
			if (v.outOfRange()) return false;
		}
		return true;
	}
	
	public PatientView getView() {
		return view;
	}
	
	public void updateView() {
		view.updateFromModel(model);
		view.refresh();
	}
	
	public String getId() {
		return model.getId();
	}
	public void setId(String id) {
		model.setId(id);
	}
	public String getName() {
		return model.getName();
	}
	public void setName(String name) {
		model.setName(name);
	}
	public String getPhysician() {
		return model.getPhysician();
	}
	public void setPhysician(String physician) {
		setPhysician(physician);
	}
	public String getBirthday() {
		return model.getBirthday();
	}
	public void setBirthday(String birthday) {
		model.setBirthday(birthday);
	}
	public String getGender() {
		return model.getGender();
	}
	public void setGender(String gender) {
		model.setGender(gender);
	}
	public ArrayList<Illness> getIllnesses() {
		return model.getIllnesses();
	}
	public void setIllnesses(ArrayList<Illness> illnesses) {
		model.setIllnesses(illnesses);
	}
	public ArrayList<VitalSign> getVitalSigns() {
		return model.getVitalSigns();
	}
	public void setVitalSigns(ArrayList<VitalSign> vitalSigns) {
		model.setVitalSigns(vitalSigns);
	}
	public Floor getRegisteredFloor() {
		return model.getRegisteredFloor();
	}
	public void setRegisteredFloor(Floor registeredFloor) {
		model.setRegisteredFloor(registeredFloor);
	}
}
