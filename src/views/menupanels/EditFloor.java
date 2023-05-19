package views.menupanels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditFloor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel lbFloorNameEdit, lbFloorNumberEdit;
	public JTextField txtSpecialization, txtCapacity;
	public EditFloor() {
		setBounds(133, 65, 221, 138);
		setLayout(new GridLayout(0, 2, 0, 0));
		
		lbFloorNameEdit = new JLabel();
		lbFloorNumberEdit = new JLabel();
		add(lbFloorNumberEdit);
		add(lbFloorNameEdit);
		
		JLabel lbSpecialization = new JLabel("Specialization:");
		add(lbSpecialization);
		
		txtSpecialization = new JTextField();
		add(txtSpecialization);
		txtSpecialization.setColumns(10);
		
		JLabel lbCapacity = new JLabel("Maximum Capacity:");
		add(lbCapacity);
		
		txtCapacity = new JTextField();
		add(txtCapacity);
		txtCapacity.setColumns(10);
	}
}
