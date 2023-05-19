package views.menupanels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChoosePatientId extends JPanel {
	private static final long serialVersionUID = 1L;
	public JTextField txtPatientId;
	public ChoosePatientId() {
		setBounds(133, 65, 221, 138);
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbPatientId = new JLabel("Enter patient ID:");
		lbPatientId.setBorder(new EmptyBorder(15, 12, 15, 12));
		add(lbPatientId);
		
		txtPatientId = new JTextField();
		add(txtPatientId);
		txtPatientId.setColumns(10);
	}
}
