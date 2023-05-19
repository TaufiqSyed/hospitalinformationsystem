package views.pages;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import constants.Colors;
import constants.HospitalFloors;
import controllers.DoctorController;
import controllers.FloorController;
import controllers.PatientController;
import inforepository.DoctorService;
import inforepository.FloorService;
import inforepository.PatientService;
import models.Doctor;
import models.Floor;
import models.Illness;
import models.Patient;
import models.VitalSign;
import utils.ImageUtil;
import utils.PanelUtil;
import utils.SeedUtil;
import views.PatientView;
import views.menupanels.AddDoctor;
import views.menupanels.AddPatient;
import views.menupanels.AddVitalSign;
import views.menupanels.ChooseFloorNumber;
import views.menupanels.EditFloor;
import views.menupanels.FloorStatistics;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class StaffDashboard implements ActionListener {

	private JFrame frame;
	private JPanel panel, pnlAllPatients, pnlAllDoctors, pnlFloors;
	private JMenuItem mntmEditFloors, mntmStatistics, mntmAddPatient, mntmImportPatients, mntmReadVitalSigns, mntmImportVitalSigns, mntmAddDoctor;
	private AddPatient addPatient;
	private ChooseFloorNumber chooseFloorNumber;
	private EditFloor editFloor;
	private AddVitalSign addVitalSign;
	private AddDoctor addDoctor;
	private ArrayList<FloorController> fcs;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffDashboard window = new StaffDashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffDashboard() {
//		super();
		initialize();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == mntmEditFloors) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), chooseFloorNumber, "Choose Floor to Edit", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			int floorNumber;
			try {
				floorNumber = Integer.parseInt(chooseFloorNumber.txtFloorNumber.getText());
				if (x != 0 || floorNumber < 1 || floorNumber > HospitalFloors.count) {
					throw new Exception();
				}
			} catch (Exception err) {
				chooseFloorNumber.txtFloorNumber.setText("");
				return;
			}
			chooseFloorNumber.txtFloorNumber.setText("");
			
			FloorController fc = fcs.get(floorNumber - 1);
			editFloor.lbFloorNameEdit.setText(fc.getName());
			editFloor.lbFloorNumberEdit.setText(String.format("Floor Number %d", floorNumber));
			
			x = JOptionPane.showOptionDialog(frame.getContentPane(), editFloor, "Edit Floor", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			try {
				if (x == 0) {
					fc.setCapacity(Integer.parseInt(editFloor.txtCapacity.getText()));
					fc.setName(editFloor.txtSpecialization.getText());
					fc.updateView();
				}
			} catch (Exception err) {
			}
			editFloor.txtCapacity.setText("");
			editFloor.txtSpecialization.setText("");

		} else if (src == mntmStatistics) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), chooseFloorNumber, "Choose Floor", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			int floorNumber;
			try {
				floorNumber = Integer.parseInt(chooseFloorNumber.txtFloorNumber.getText());
				if (x != 0 || floorNumber < 1 || floorNumber > HospitalFloors.count) {
					throw new Exception();
				}
			} catch (Exception err) {
				chooseFloorNumber.txtFloorNumber.setText("");
				return;
			}
			chooseFloorNumber.txtFloorNumber.setText("");
			
			FloorController fc = fcs.get(floorNumber - 1);
			JOptionPane.showMessageDialog(frame.getContentPane(), new FloorStatistics(fc), "Statistics", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (src == mntmAddPatient) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), addPatient, "Title", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			if (x == 0) {
				PatientService ps = PatientService.getInstance();
				int floorNumber = Integer.parseInt(addPatient.txtFloor.getText());
				Floor floor;
				try {
					floor = FloorService.getInstance().getFloorFromNumber(floorNumber);
				} catch (Exception e1) {
					return;
				}
				Illness illness = new Illness(addPatient.txtIllness.getText(), floor.getMedicalDepartment());
				Patient patient = new Patient(
						addPatient.txtId.getText(),
						addPatient.txtName.getText(),
						addPatient.txtPhysician.getText(),
						addPatient.txtBirthday.getText(),
						addPatient.txtGender.getText(),
						new ArrayList<Illness>(Arrays.asList(illness)),
						new ArrayList<VitalSign>(),
						floor
				);
				if (floor.getRegisteredPatients().size() >= floor.getCapacity()) {
					PatientView pv = new PatientController(patient).getView();
					JOptionPane.showMessageDialog(frame.getContentPane(), pv, "Capacity Exceeded - Patient Printout", JOptionPane.INFORMATION_MESSAGE);
				} else {
					ps.addPatient(patient);
					refreshPatients();
				}
			}
			addPatient.txtId.setText("");
			addPatient.txtName.setText("");
			addPatient.txtGender.setText("");
			addPatient.txtIllness.setText("");
			addPatient.txtFloor.setText("");

		} else if (src == mntmReadVitalSigns) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), addVitalSign, "Add Vital Sign", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);

			if (x == 0) {
				Patient patient;
				int numericCode;
				try {
					patient = PatientService.getInstance().getPatientFromId(addVitalSign.txtPatientId.getText());
					numericCode = Integer.parseInt(addVitalSign.txtVitalSignNumeric.getText());
				} catch (Exception e1) {
					addVitalSign.txtPatientId.setText("");
					addVitalSign.txtVitalSignNumeric.setText("");
					addVitalSign.txtVitalSignLetter.setText("");
					return;
				}
				patient.addVitalSign(new VitalSign(addVitalSign.txtVitalSignLetter.getText(), numericCode));
				addVitalSign.txtPatientId.setText("");
				addVitalSign.txtVitalSignNumeric.setText("");
				addVitalSign.txtVitalSignLetter.setText("");
				refreshPatients();
			}
			addVitalSign.txtPatientId.setText("");
			addVitalSign.txtVitalSignLetter.setText("");
			addVitalSign.txtVitalSignNumeric.setText("");
		} else if (src == mntmAddDoctor) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), addDoctor, "Add Vital Sign", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			if (x == 0) {
				Doctor d = new Doctor(
						addDoctor.txtId.getText(),
						addDoctor.txtName.getText(),
						addDoctor.txtSpecialization.getText()
				);
				DoctorService.getInstance().addDoctor(d);
				addDoctor.txtId.setText("");
				addDoctor.txtName.setText("");
				addDoctor.txtSpecialization.setText("");
				
				JPanel creds = new JPanel();
				creds.setLayout(new BoxLayout(creds, BoxLayout.Y_AXIS));
				
				
//				d.setPassword("password"); for development
				creds.add(new JLabel("Doctor ID: " + d.getId()));
				creds.add(new JLabel("Generated Password: " + d.getPassword()));
				
				JOptionPane.showMessageDialog(frame.getContentPane(), creds, "Generated Password", JOptionPane.INFORMATION_MESSAGE);
				
				d.getPassword();
				
				refreshDoctors();
			}
		} else if (src == mntmImportPatients) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), chooseFloorNumber, "Select Floor to Import Patients", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			int floorNumber;
			try {
				floorNumber = Integer.parseInt(chooseFloorNumber.txtFloorNumber.getText());
				if (x != 0 || floorNumber < 1 || floorNumber > HospitalFloors.count) {
					throw new Exception();
				}
			} catch (Exception err) {
				chooseFloorNumber.txtFloorNumber.setText("");
				return;
			}
			chooseFloorNumber.txtFloorNumber.setText("");
			
			FloorController fc = fcs.get(floorNumber - 1);
			
			PatientService.getInstance().importPatients(fc);
			refreshPatients();
		} else if (src == mntmImportVitalSigns) {
			PatientService.getInstance().importVitalSigns();
			refreshPatients();
		}
	}

	private void refreshPatients() {
		pnlAllPatients.removeAll();
		for (Patient p: PatientService.getInstance().getPatients()) {
			PatientController pc = new PatientController(p);
			pnlAllPatients.add(pc.getView());
		}
	}
	private void refreshDoctors() {
		pnlAllDoctors.removeAll();
		for (Doctor d: DoctorService.getInstance().getDoctors()) {
			DoctorController dc = new DoctorController(d);
			pnlAllDoctors.add(dc.getView());
		}
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(203, 213, 224));
		frame.setTitle("VitalSys Information System");
		frame.setIconImage(ImageUtil.getIcon("images\\vitalsys.png", 64, 64).getImage());
		frame.setBounds(100, 100, 980, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		int leftPadW = 20, leftPadH = 15;
		int rightPadW = 15, rightPadH = 12;
		
		EmptyBorder leftPad = new EmptyBorder(leftPadH, leftPadW, leftPadH, leftPadW);
		EmptyBorder rightPad = new EmptyBorder(rightPadH, rightPadW, rightPadH, rightPadW);
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(203, 213, 224));
		frame.getContentPane().add(panel);
		
		JPanel pnlLeftDash = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlLeftDash.setBorder(leftPad);
		pnlLeftDash.setLayout(new BorderLayout(0, 0));

		JLabel lblAus = new JLabel("AUS University Hospital");
		lblAus.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAus.setHorizontalAlignment(SwingConstants.LEFT);
		lblAus.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlLeftDash.add(lblAus, BorderLayout.NORTH);
		
		pnlFloors = new JPanel();
		fcs = new ArrayList<FloorController>();
		for (Floor f: FloorService.getInstance().getFloors()) {
			FloorController fc = new FloorController(f);
			fcs.add(fc);
			pnlFloors.add(fc.getView());
		}
		pnlLeftDash.add(pnlFloors, BorderLayout.CENTER);
		pnlFloors.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlPatients = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlPatients.setBorder(rightPad);
		pnlPatients.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPatients = new JLabel("Patients");
		PanelUtil.pad(lblPatients, 0, 0, 8, 0);
		lblPatients.setFont(new Font("Dialog", Font.BOLD, 15));
		pnlPatients.add(lblPatients, BorderLayout.NORTH);
		
		JScrollPane scrPatients = new JScrollPane();
		pnlPatients.add(scrPatients, BorderLayout.CENTER);
		
		pnlAllPatients = new JPanel();
		pnlAllPatients.setBackground(Colors.blue);
		scrPatients.setViewportView(pnlAllPatients);
		pnlAllPatients.setLayout(new BoxLayout(pnlAllPatients, BoxLayout.Y_AXIS));
		
		for (Patient p: PatientService.getInstance().getPatients()) {
			PatientController pc = new PatientController(p);
			pnlAllPatients.add(pc.getView());
		}
		
		
		JPanel pnlDoctors = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlDoctors.setBorder(rightPad);
		
		JPanel pnlRightDash = new JPanel();
		pnlRightDash.setBackground(new Color(0,0,0,0));
		
		pnlRightDash.setLayout(new GridLayout(2, 0, 0, 9));
		pnlRightDash.add(pnlPatients);
		pnlRightDash.add(pnlDoctors);
		pnlDoctors.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDoctors = new JLabel("Doctors");
		PanelUtil.pad(lblDoctors, 0, 0, 8, 0);
		lblDoctors.setFont(new Font("Dialog", Font.BOLD, 15));
		pnlDoctors.add(lblDoctors, BorderLayout.NORTH);
		
		JScrollPane scrDoctors = new JScrollPane();
		pnlDoctors.add(scrDoctors, BorderLayout.CENTER);
		
		pnlAllDoctors = new JPanel();
		pnlAllDoctors.setBackground(Colors.blue);
		scrDoctors.setViewportView(pnlAllDoctors);
		pnlAllDoctors.setLayout(new BoxLayout(pnlAllDoctors, BoxLayout.Y_AXIS));
		
		for (Doctor d : DoctorService.getInstance().getDoctors()) {
			DoctorController dc = new DoctorController(d);
			pnlAllDoctors.add(dc.getView());
		}
		
		
		int marginW = 2, marginH = 2, glueW = 1, glueH = 1, x = 0, y = 0;
		
		// panel, item, width, height, x, y, weight_x, weight_y
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH, x, y, 0.1, 0.05); // margin top & left
		PanelUtil.addGridBagItem(panel, pnlLeftDash, marginW, marginH, x+=glueW, y+=glueH, 0.25, 0.25);
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH, x+=marginW, y, 0.02, 0.05); // center margin
		PanelUtil.addGridBagItem(panel, pnlRightDash, marginW, marginH, x+=glueW, y, 0.33, 0.5);
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH+2, x+=marginW, y+=glueH, 0.1, 0.05); //margin bottom & right 
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(236, 236, 236));
		frame.setJMenuBar(menuBar);
		
		JMenu mnFloors = new JMenu("Floors");
		menuBar.add(mnFloors);
		
		mntmEditFloors = new JMenuItem("Edit Floors");
		mnFloors.add(mntmEditFloors);
		mntmEditFloors.addActionListener(this);
		
		mntmStatistics = new JMenuItem("Statistics");
		mnFloors.add(mntmStatistics);
		mntmStatistics.addActionListener(this);
		
		JMenu mnPatients = new JMenu("Patients");
		menuBar.add(mnPatients);
		
		mntmAddPatient = new JMenuItem("Add Patient");
		mnPatients.add(mntmAddPatient);
		mntmAddPatient.addActionListener(this);
		
		mntmImportPatients = new JMenuItem("Import Patients");
		mnPatients.add(mntmImportPatients);
		mntmImportPatients.addActionListener(this);
		
		JMenu mnVitalSigns = new JMenu("Vital Signs");
		menuBar.add(mnVitalSigns);
		
		mntmReadVitalSigns = new JMenuItem("Read Vital Signs");
		mnVitalSigns.add(mntmReadVitalSigns);
		mntmReadVitalSigns.addActionListener(this);

		mntmImportVitalSigns = new JMenuItem("Import Vital Signs");
		mnVitalSigns.add(mntmImportVitalSigns);
		mntmImportVitalSigns.addActionListener(this);
		
		JMenu mnDoctors = new JMenu("Doctors");
		menuBar.add(mnDoctors);
		
		mntmAddDoctor = new JMenuItem("Add Doctor");
		mnDoctors.add(mntmAddDoctor);
		mntmAddDoctor.addActionListener(this);
		
		chooseFloorNumber = new ChooseFloorNumber();
		editFloor = new EditFloor();
		addPatient = new AddPatient();
		addVitalSign = new AddVitalSign();
		addDoctor = new AddDoctor();
	}
}
