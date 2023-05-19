package inforepository;

import java.util.ArrayList;

import models.Doctor;

public class AuthService {
	public static boolean authenticateStaff(String id, String password) {
    	//Using a map to store username-password details
        java.util.Map<String, String> userCredentials = new java.util.HashMap<>();
        userCredentials.put("Mohamad", "password");
        userCredentials.put("Taufiq", "password");
        userCredentials.put("admin", "password");
        
        return userCredentials.containsKey(id) && userCredentials.get(id).equals(password);
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
