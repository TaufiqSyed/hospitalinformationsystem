package inforepository;

import java.util.ArrayList;

import models.Patient;

public class PatientService {
	private static PatientService service = null;
    private ArrayList<Patient> atRiskPatients, safePatients; // determined by vital signs

    private PatientService()
    {
    	atRiskPatients = new ArrayList<Patient>();
    	safePatients = new ArrayList<Patient>();
    }
    
    public static synchronized PatientService getInstance()
    {
        if (service == null)
        	service = new PatientService();
  
        return service;
    }
   
    public synchronized void addPatient(Patient p) {
    	if (p.anyVitalOutOfRange()) {
    		atRiskPatients.add(p);
    	} else {
    		safePatients.add(p);
    	}
    }

	public ArrayList<Patient> getAtRiskPatients() {
		return atRiskPatients;
	}
	
	public ArrayList<Patient> getSafePatients() {
		return safePatients;
	}
	
	public ArrayList<Patient> getPatients() {
		ArrayList<Patient> result = new ArrayList<Patient>(atRiskPatients);
		result.addAll(safePatients);
		return result;
	}
	
	public Patient getPatientFromId(String id) throws Exception {
		for (Patient p : atRiskPatients) {
			if (p.getId().equals(id)) return p;
		}
		for (Patient p : safePatients) {
			if (p.getId().equals(id)) return p;
		}
		throw new Exception("Invalid Patient ID");
	}
}
