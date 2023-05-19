package inforepository;

import java.util.ArrayList;

import models.Doctor;

public class DoctorService {
	private static DoctorService service = null;
    private ArrayList<Doctor> doctors;

    private DoctorService()
    {
    	doctors = new ArrayList<Doctor>();
    }
    
    public static synchronized DoctorService getInstance()
    {
        if (service == null)
        	service = new DoctorService();
  
        return service;
    }
   
    public synchronized void addDoctor(Doctor d) {
    	doctors.add(d);
    }

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}
	
	public Doctor getDoctorById(String id) throws Exception {
		for (Doctor d : doctors) 
			if (d.getId().equals(id)) return d;
		throw new Exception("Invalid Doctor ID");
	}
}
