package views.menupanels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddDoctor extends JPanel {
	public JTextField txtId, txtName, txtSpecialization;
	public AddDoctor() {
		setBounds(133, 65, 221, 138);
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbId = new JLabel("Doctor ID:");
		add(lbId);
		
		txtId = new JTextField();
		add(txtId);
		txtId.setColumns(10);
		
		JLabel lbName = new JLabel("Doctor Name:");
		add(lbName);
		
		txtName = new JTextField();
		add(txtName);
		txtName.setColumns(10);
		
		JLabel lbSpecialization = new JLabel("Specialization:");
		add(lbSpecialization);
		
		txtSpecialization = new JTextField();
		add(txtSpecialization);
		txtSpecialization.setColumns(10);
	}
}
