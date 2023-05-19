package inforepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controllers.FloorController;
import controllers.PatientController;
import models.Illness;
import models.Patient;
import models.VitalSign;
import views.PatientView;

public class PatientService {
	private static PatientService service = null;
    private ArrayList<Patient> patients; 
    
    private PatientService()
    {
    	patients = new ArrayList<Patient>();
    }
    
    public static synchronized PatientService getInstance()
    {
        if (service == null)
        	service = new PatientService();
  
        return service;
    }
   
    public synchronized void addPatient(Patient p) {
    	patients.add(p);
    }
	
	public ArrayList<Patient> getPatients() {
		// Ordered from at risk patients to safe patients
		ArrayList<Patient> result = new ArrayList<Patient>();
		for (Patient p : patients) if (p.anyVitalOutOfRange()) result.add(p);
		for (Patient p : patients) if (!p.anyVitalOutOfRange()) result.add(p);
		return result;
	}
	
	public Patient getPatientFromId(String id) throws Exception {
		for (Patient p : patients)
			if (p.getId().equals(id)) return p;
		throw new Exception("Invalid Patient ID");
	}
	
	public void importPatients(FloorController fc) {
		// Add patients from file
		// FORMAT:
		// <ID>;<NAME>;<PHYSICIAN>;<BIRTHDAY>;<GENDER>;<ILLNESS>
		JFileChooser fileChooser = new JFileChooser("C:\\Users");
		int option = fileChooser.showOpenDialog(null);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(line, ";");

					String id, name, physician, birthday, gender, illness;
					id = st.nextToken(); name = st.nextToken(); physician = st.nextToken(); 
					birthday = st.nextToken(); gender = st.nextToken(); illness = st.nextToken();
					
					ArrayList<Illness> illnesses = new ArrayList<Illness>(Arrays.asList(new Illness(illness, "General")));
					ArrayList<VitalSign> vitalSigns = new ArrayList<VitalSign>();
					Patient patient = new Patient(id, name, physician, birthday, gender, illnesses, vitalSigns);
					
					
					if (fc.getRegisteredPatients().size() >= fc.getCapacity()) {
						PatientView pv = new PatientController(patient).getView();
						JOptionPane.showMessageDialog(null, pv, "Capacity Exceeded - Patient Printout", JOptionPane.INFORMATION_MESSAGE);
					} else {
						fc.addRegisteredPatient(patient);
						patient.setRegisteredFloor(fc.getModel());
						addPatient(patient);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void importVitalSigns() {
		// Add vital signs from file
		// FORMAT: 
		// <PATIENT_ID>;<VITAL_SIGN_LETTER_CODE>;<VITAL_SIGN_NUMERIC_CODE>
		JFileChooser fileChooser = new JFileChooser("C:\\Users");
		int option = fileChooser.showOpenDialog(null);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(line, ";");

					String patientId, vitalSignLetter;
					double vitalSignNumeric;
					
					patientId = st.nextToken(); 
					vitalSignLetter = st.nextToken();
					vitalSignNumeric = Double.parseDouble(st.nextToken()); 

					Patient patient = getPatientFromId(patientId);
					patient.addVitalSign(new VitalSign(vitalSignLetter, vitalSignNumeric));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
