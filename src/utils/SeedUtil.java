package utils;

import java.util.ArrayList;
import java.util.Arrays;

import inforepository.DoctorService;
import inforepository.FloorService;
import inforepository.PatientService;
import models.Doctor;
import models.Floor;
import models.Illness;
import models.Patient;
import models.VitalSign;

public class SeedUtil {
	public static void runAllSeeds() {
		runPatientSeed();
		runFloorSeed();
		runDoctorSeed();
	}
	public static void runPatientSeed() {
		PatientService ps = PatientService.getInstance();
		ps.addPatient(
				new Patient("b11012004", "Taufiq Syed", "Dr. John Doe", "22/01/2004", "Male", new ArrayList<Illness>(Arrays.asList(new Illness("Cancer", "Cancerology"), new Illness("Root Canal", "Dentristology"))), new ArrayList<VitalSign>(Arrays.asList(new VitalSign("PBV", 95), new VitalSign("DJL", 57))))
		);
		ps.addPatient(
				new Patient("b24091994", "Mohamad Adas", "Dr. Mikey Smith", "24/09/1994", "Male", new ArrayList<Illness>(Arrays.asList(new Illness("Arthritis", "Cancerology"), new Illness("Headache", "Dentristology"))), new ArrayList<VitalSign>(Arrays.asList(new VitalSign("KJL", 54), new VitalSign("KLDS", 34))))
		);
		ps.addPatient(
				new Patient("b22342353", "Jill Smith", "Dr. John Doe", "12/02/2002", "Female", new ArrayList<Illness>(Arrays.asList(new Illness("Sickness", "Cancerology"), new Illness("Concussion", "Dentristology"), new Illness("Headache", "Dumbness"))), new ArrayList<VitalSign>(Arrays.asList(new VitalSign("KJL", 23), new VitalSign("JKJ", 12))))
		);
	}
	public static void runFloorSeed() {
		PatientService ps = PatientService.getInstance();
		FloorService fs = FloorService.getInstance();
		ArrayList<Patient> patients = ps.getPatients();
		
		ArrayList<Floor> floors = new ArrayList<Floor>();
		floors.add(new Floor("Cancerology", 1, "CAN", "Surgery", "Dr. Mike Stone"));
		floors.add(new Floor("Disease Prevention", 2, "DIS", "Epidemiology", "Dr. Nikolas Mag"));
		
		floors.add(new Floor("Cancerology", 3, "CAN", "Surgery", "Dr. Mike Stone"));
		floors.add(new Floor("Disease Prevention", 4, "DIS", "Epidemiology", "Dr. Nikolas Mag"));

		floors.add(new Floor("Cancerology", 5, "CAN", "Surgery", "Dr. Mike Stone"));
		floors.add(new Floor("Disease Prevention", 6, "DIS", "Epidemiology", "Dr. Nikolas Mag"));		
		
		floors.get(0).addRegisteredPatient(patients.get(0));
		patients.get(0).setRegisteredFloor(floors.get(0));
		
		floors.get(1).addRegisteredPatient(patients.get(1));
		patients.get(1).setRegisteredFloor(floors.get(1));
		
		floors.get(1).addRegisteredPatient(patients.get(2));
		patients.get(2).setRegisteredFloor(floors.get(1));
		
		for (Floor x : floors) fs.addFloor(x); 
	}
	public static void runDoctorSeed() {
		DoctorService ds = DoctorService.getInstance();
		Doctor d = new Doctor("doc12345", "Mike Tyson", "Surgery");
		d.setPassword("password");
		ds.addDoctor(d);
		ds.addDoctor(new Doctor("doc23235", "Muhammad Ali", "Dentist"));
		ds.addDoctor(new Doctor("doc94324", "George Foreman", "Homeopathy"));
	}
}
