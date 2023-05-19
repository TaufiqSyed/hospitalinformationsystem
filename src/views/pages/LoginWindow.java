package views.pages;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import constants.Colors;
import inforepository.AuthService;
import models.Doctor;
import utils.ImageUtil;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

class StaffDashboardThread extends Thread {
	public void run() {
		StaffDashboard.main(null);
	}
}

class DoctorDashboardThread extends Thread {
	private Doctor doctor;
	public DoctorDashboardThread(Doctor doctor) {
		this.doctor = doctor;
	}
	public void run() {
		DoctorDashboard.main(doctor);
	}
}



public class LoginWindow implements ActionListener{

	private JFrame frame;
    private boolean isDoctor;
    private JButton loginBtn;
    private JPanel panel_1;
    private JLabel usernameLbl;
    private JTextField usernameField;
    private JLabel passwordLbl;
    private JPasswordField passwordField;
    private JLabel lblHospitalInfo;
    
    public LoginWindow(boolean isDoctor) {
    	this.isDoctor = isDoctor;
    	
        frame = new JFrame();
		frame.setTitle("VitalSys Information System - Login");
		frame.setIconImage(ImageUtil.getIcon("images\\vitalsys.png", 64, 64).getImage());
        frame.getContentPane().setBackground(Colors.blue);
        frame.setBounds(100, 100, 980, 480);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);
        frame.getContentPane().add(loginBtn, BorderLayout.SOUTH);
        
        panel_1 = new JPanel();
        panel_1.setBackground(Colors.transparent);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));
        
        usernameLbl = new JLabel("Username: (hint: " + (isDoctor ? "doc12345" : "admin") + ")");
        panel_1.add(usernameLbl);
        
        usernameField = new JTextField();
        panel_1.add(usernameField);
        
        passwordLbl = new JLabel("Password: (hint: password)");
        panel_1.add(passwordLbl);
        
        passwordField = new JPasswordField();
        panel_1.add(passwordField);
        
        lblHospitalInfo = new JLabel(String.format("VitalSys %s Login", isDoctor ? "Doctor" : "Staff"));
        lblHospitalInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblHospitalInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
        frame.getContentPane().add(lblHospitalInfo, BorderLayout.NORTH);
        
        int padH = 10, padW = 12;
        panel_1.setBorder(new EmptyBorder(padH, padW, padH, padW));
        padH = 20; padW = 24;
        lblHospitalInfo.setBorder(new EmptyBorder(padH, padW, padH, padW));
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(450, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (authenticate(username, password)) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            frame.dispose();
            
            // Place the code here for main application
            
        } 
        else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    private boolean authenticate(String username, String password) {
        if (this.isDoctor) {
        	Doctor d = AuthService.authenticateDoctor(username, password);
        	if (d == null) return false;
        	DoctorDashboardThread thread = new DoctorDashboardThread(d);
        	thread.start();
        	return true;
        } else {
        	if (!AuthService.authenticateStaff(username, password)) {
        		return false;
        	}
        	StaffDashboardThread thread = new StaffDashboardThread();
        	thread.start();
        	return true;
        }
        
    }
    
    public static void main(boolean isDoctor) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow(isDoctor);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
	
}