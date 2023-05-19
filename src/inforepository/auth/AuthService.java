package inforepository.auth;

import java.util.ArrayList;

import inforepository.DoctorService;
import models.Doctor;

public class AuthService {
	public static boolean authenticateStaff(String id, String password) {
		return id.equals("admin") && password.equals("admin");
	}
	public static Doctor authenticateDoctor(String id, String password) {
		try {
			Doctor d = DoctorService.getInstance().getDoctorById(id);
			if (!d.getPassword().equals(password)) return null;
			return d;
		} catch (Exception e) {
			return null;
		}
	}
}
