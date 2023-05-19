package views.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
import views.DoctorView;
import views.PatientView;
import views.menupanels.AddDoctor;
import views.menupanels.AddPatient;
import views.menupanels.AddVitalSign;
import views.menupanels.ChooseFloorNumber;
import views.menupanels.ChoosePatientId;
import views.menupanels.EditFloor;
import views.menupanels.FloorStatistics;

public class DoctorDashboard implements ActionListener {

	private JFrame frame;
	private JPanel panel, pnlAllPatients;
	private JMenuItem mntmDisplayFloor, mntmLookupPatient, mntmEditProfile;
	private ChooseFloorNumber chooseFloorNumber;
	private ChoosePatientId choosePatientId;
	private AddDoctor addDoctor;
	private ArrayList<FloorController> fcs;
	private Doctor loggedInDoctor;
	private DoctorController dc;
	private JScrollPane scrDoctors;
	private JMenuItem mntmSetPassword;
	
	public static void main(String[] args) {
		try {
			main(DoctorService.getInstance().getDoctorById("doc12345"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void main(Doctor d) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println(d.getName());
					DoctorDashboard window = new DoctorDashboard(d);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoctorDashboard(Doctor d) {
//		super();
		loggedInDoctor = d;
		dc = new DoctorController(loggedInDoctor);
		initialize();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == mntmDisplayFloor) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), chooseFloorNumber, "Choose Floor", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			int floorNumber;
			try {
				if (x != 0) {
					chooseFloorNumber.txtFloorNumber.setText("");
					return;
				}
				floorNumber = Integer.parseInt(chooseFloorNumber.txtFloorNumber.getText());
				if (floorNumber < 1 || floorNumber > HospitalFloors.count) return;
			} catch (Exception err) {
				chooseFloorNumber.txtFloorNumber.setText("");
				return;
			}
			chooseFloorNumber.txtFloorNumber.setText("");
			
			FloorController fc = fcs.get(floorNumber - 1);
			JOptionPane.showMessageDialog(frame.getContentPane(), new FloorStatistics(fc), "Statistics", JOptionPane.INFORMATION_MESSAGE);

		} else if (src == mntmEditProfile) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), addDoctor, "Edit Profile", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			if (x == 0) {
				dc.setId(addDoctor.txtId.getText());
				dc.setName(addDoctor.txtName.getText());
				dc.setSpecialization(addDoctor.txtSpecialization.getText());
				dc.updateView();
				refreshDoctor();
				
				addDoctor.txtId.setText("");
				addDoctor.txtName.setText("");
				addDoctor.txtSpecialization.setText("");
			}
		} else if (src == mntmLookupPatient) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			
			
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), choosePatientId, "View Patient", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			String patientId;
			Patient patient;
			try {
				if (x != 0) {
					choosePatientId.txtPatientId.setText("");
					return;
				}
				patientId = choosePatientId.txtPatientId.getText();
				patient = PatientService.getInstance().getPatientFromId(patientId);
			} catch (Exception err) {
				choosePatientId.txtPatientId.setText("");
				return;
			}
			choosePatientId.txtPatientId.setText("");
			PatientView pv = new PatientController(patient).getView();
			JOptionPane.showMessageDialog(frame.getContentPane(), pv, "View Patient", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (src == mntmSetPassword) {
			String[] options = new String[2];
			options[0] = "Confirm";
			options[1] = "Cancel";
			
			
			JTextField txtNewPassword = new JTextField();
			JPanel pnlSetPass = new JPanel();
			pnlSetPass.setLayout(new GridLayout(2, 0, 0, 0));
			JLabel lblNewPassword = new JLabel("New Password: ");
			
			pnlSetPass.add(lblNewPassword);
			pnlSetPass.add(txtNewPassword);
			
			int x = JOptionPane.showOptionDialog(frame.getContentPane(), pnlSetPass, "Reset Password", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			
			if (x == 0) {
				loggedInDoctor.setPassword(txtNewPassword.getText());
			}
			txtNewPassword.setText("");
		}
	}
	private void refreshDoctor() {
		DoctorView dv = dc.getView();
		PanelUtil.pad(dv, 0, 0, 20, 0);
		scrDoctors.setViewportView(dv);
	}
	private void refreshPatients() {
		pnlAllPatients.removeAll();
		for (Patient p: PatientService.getInstance().getPatients()) {
			PatientController pc = new PatientController(p);
			pnlAllPatients.add(pc.getView());
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
		
		JPanel pnlRightDash = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlRightDash.setBorder(leftPad);
		pnlRightDash.setLayout(new BorderLayout(0, 0));

		JLabel lblAus = new JLabel("AUS University Hospital");
		lblAus.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAus.setHorizontalAlignment(SwingConstants.LEFT);
		lblAus.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlRightDash.add(lblAus, BorderLayout.NORTH);
		
		JPanel pnlFloors = new JPanel();
		fcs = new ArrayList<FloorController>();
		for (Floor f: FloorService.getInstance().getFloors()) {
			FloorController fc = new FloorController(f);
			fcs.add(fc);
			pnlFloors.add(fc.getView());
		}
		pnlRightDash.add(pnlFloors, BorderLayout.CENTER);
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
		
		JPanel pnlLeftDash = new JPanel();
		pnlLeftDash.setBackground(new Color(0,0,0,0));
		
		
		JPanel pnlDoctors = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlDoctors.setLayout(new BorderLayout(0, 0));
		pnlDoctors.setBorder(rightPad);
		
		
		JLabel lblDoctors = new JLabel("My Profile");
		PanelUtil.pad(lblDoctors, 0, 0, 8, 0);
		lblDoctors.setFont(new Font("Dialog", Font.BOLD, 15));
		pnlDoctors.add(lblDoctors, BorderLayout.NORTH);
		
		scrDoctors = new JScrollPane();
		scrDoctors.setBackground(Colors.warningText);
		pnlDoctors.add(scrDoctors, BorderLayout.CENTER);
		
		JPanel pnlAllDoctors = new JPanel();
		pnlAllDoctors.setLayout(new GridBagLayout());
		pnlAllDoctors.setBackground(Colors.warning);
		
		dc.setScale(1.8); dc.updateView();
		DoctorView dv = dc.getView();
		PanelUtil.pad(dv, 0, 0, 20, 0);
		scrDoctors.setViewportView(dv);
		pnlAllDoctors.setLayout(new BoxLayout(pnlAllDoctors, BoxLayout.Y_AXIS));
	
		
		
		pnlLeftDash.setLayout(new GridLayout(2, 0, 0, 9));
		pnlLeftDash.add(pnlDoctors);
		pnlLeftDash.add(pnlPatients);

		int marginW = 2, marginH = 2, glueW = 1, glueH = 1, x = 0, y = 0;
		
		// panel, item, width, height, x, y, weight_x, weight_y
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH, x, y, 0.1, 0.05); // margin top & left
		PanelUtil.addGridBagItem(panel, pnlLeftDash, marginW, marginH, x+=glueW, y+=glueH, 0.33, 0.5);
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH, x+=marginW, y, 0.02, 0.05); // center margin
		PanelUtil.addGridBagItem(panel, pnlRightDash, marginW, marginH, x+=glueW, y, 0.25, 0.25);
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH+2, x+=marginW, y+=glueH, 0.1, 0.05); //margin bottom & right 
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(236, 236, 236));
		frame.setJMenuBar(menuBar);
		
		JMenu mnFloor = new JMenu("Floors");
		menuBar.add(mnFloor);
		
		mntmDisplayFloor = new JMenuItem("Display Floor Data");
		mnFloor.add(mntmDisplayFloor);
		
		mntmSetPassword = new JMenuItem("Set Password");
		mnFloor.add(mntmSetPassword);
		mntmSetPassword.addActionListener(this);
		
		JMenu mnPatients = new JMenu("Patients");
		menuBar.add(mnPatients);
		
		mntmLookupPatient = new JMenuItem("Look Up Patient");
		mnPatients.add(mntmLookupPatient);
		mntmLookupPatient.addActionListener(this);
		
		JMenu mnProfile = new JMenu("Profile");
		menuBar.add(mnProfile);
		
		mntmEditProfile = new JMenuItem("Edit Profile");
		mnProfile.add(mntmEditProfile);
		mntmEditProfile.addActionListener(this);
		
		chooseFloorNumber = new ChooseFloorNumber();
		addDoctor = new AddDoctor();
		choosePatientId = new ChoosePatientId();
		
		JPanel creds = new JPanel();
		creds.setLayout(new BoxLayout(creds, BoxLayout.Y_AXIS));
		
		
//		d.setPassword("password"); for development
		if (!loggedInDoctor.getLoggedInOnce()) {
			creds.add(new JLabel("Doctor ID: " + loggedInDoctor.getId()));
			creds.add(new JLabel("Generated Password: " + loggedInDoctor.getPassword()));
			
			JOptionPane.showMessageDialog(frame.getContentPane(), creds, "Generated Password", JOptionPane.INFORMATION_MESSAGE);
			loggedInDoctor.setLoggedInOnce(true);
		}
	}
}
