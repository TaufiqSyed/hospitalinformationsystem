package views.menupanels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPatient extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txtId, txtName, txtGender, txtFloor, txtIllness, txtPhysician, txtBirthday;
	public AddPatient() {
		setBounds(133, 65, 221, 138);
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbId = new JLabel("Patient ID:");
		add(lbId);
		
		txtId = new JTextField();
		add(txtId);
		txtId.setColumns(10);
		
		JLabel lbName = new JLabel("Patient Name:");
		add(lbName);
		
		txtName = new JTextField();
		add(txtName);
		txtName.setColumns(10);
		
		JLabel lbPhysician = new JLabel("Doctor:");
		add(lbPhysician);
		
		txtPhysician = new JTextField();
		add(txtPhysician);
		txtPhysician.setColumns(10);
		
		JLabel lbBirthday = new JLabel("Date of Birth (dd/mm/yyyy):");
		add(lbBirthday);
		
		txtBirthday = new JTextField();
		add(txtBirthday);
		txtBirthday.setColumns(10);
		
		JLabel lbGender = new JLabel("Patient Gender:");
		add(lbGender);
		
		txtGender = new JTextField();
		add(txtGender);
		txtGender.setColumns(10);
		
		JLabel lbFloor = new JLabel("Patient Floor Number:");
		add(lbFloor);
		
		txtFloor = new JTextField();
		add(txtFloor);
		txtFloor.setColumns(10);
		
		JLabel lbIllness = new JLabel("Patient Illness:");
		add(lbIllness);
		
		txtIllness = new JTextField();
		add(txtIllness);
		txtIllness.setColumns(10);
	}
}
