package controllers;

import models.Doctor;
import views.DoctorView;

public class DoctorController {
	private Doctor model;
	private DoctorView view;
	
	public DoctorController(Doctor p) {
		model = p;
		view = new DoctorView(p);
	}
	
	public DoctorView getView() {
		return view;
	}
	public void setScale(double scale) {
		view.setScale(scale);
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
	public String getSpecialization() {
		return model.getSpecialization();
	}
	public void setSpecialization(String specialization) {
		model.setSpecialization(specialization);
	}
	public String getId() {
		return model.getId();
	}
	public void setId(String id) {
		model.setId(id);
	}
}
