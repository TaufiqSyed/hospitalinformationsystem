package controllers;

import java.util.ArrayList;

import models.Floor;
import models.Patient;
import views.FloorView;

public class FloorController {
	private Floor model;
	private FloorView view;
	
	public FloorController(Floor p) {
		model = p;
		view = new FloorView(p);
	}
	
	public Floor getModel() {
		return model;
	}
	
	public FloorView getView() {
		return view;
	}
	
	public void updateView() {
		view.updateFromModel(model);
		view.refresh();
	}
	
	public String getName() {
		return model.getName();
	}

	public void setName(String name) {
		model.setName(name);
	}

	public int getNumber() {
		return model.getNumber();
	}

	public void setNumber(int number) {
		model.setNumber(number);
	}
	
	public int getCapacity() {
		return model.getCapacity();
	}

	public void setCapacity(int capacity) {
		model.setCapacity(capacity);
	}

	public String getFloorCode() {
		return model.getFloorCode();
	}

	public void setFloorCode(String floorCode) {
		model.setFloorCode(floorCode);
	}

	public String getMedicalDepartment() {
		return model.getMedicalDepartment();
	}

	public void setMedicalDepartment(String medicalDepartment) {
		model.setMedicalDepartment(medicalDepartment);
	}

	public String getChiefResidentDoctor() {
		return model.getChiefResidentDoctor();
	}

	public void setChiefResidentDoctor(String chiefResidentDoctor) {
		model.setChiefResidentDoctor(chiefResidentDoctor);
	}

	public ArrayList<Patient> getRegisteredPatients() {
		return model.getRegisteredPatients();
	}

	public void setRegisteredPatients(ArrayList<Patient> registeredPatients) {
		model.setRegisteredPatients(registeredPatients);
	}
	
	public void addRegisteredPatient(Patient registeredPatient) {
		model.addRegisteredPatient(registeredPatient);
	}
}
