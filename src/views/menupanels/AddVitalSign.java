package views.menupanels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddVitalSign extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txtPatientId, txtVitalSignLetter, txtVitalSignNumeric, txtFloorNumber;

	public AddVitalSign() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		
		JLabel lbUserId = new JLabel("User ID:");
		add(lbUserId);
		txtPatientId = new JTextField();
		add(txtPatientId);
		txtPatientId.setColumns(10);
		
		JLabel lbVitalSignLetter = new JLabel("Vital Sign Letter Code:");
		add(lbVitalSignLetter);
		txtVitalSignLetter = new JTextField();
		add(txtVitalSignLetter);
		
		JLabel lbVitalSignNumeric = new JLabel("Vital Sign Numeric Code:");
		add(lbVitalSignNumeric);
		txtVitalSignNumeric = new JTextField();
		add(txtVitalSignNumeric);
	}
}
