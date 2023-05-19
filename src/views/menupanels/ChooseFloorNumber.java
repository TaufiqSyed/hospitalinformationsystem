package views.menupanels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChooseFloorNumber extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txtFloorNumber;
	public ChooseFloorNumber() {
		setBounds(133, 65, 221, 138);
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbFloorNumber = new JLabel("Enter floor number:");
		lbFloorNumber.setBorder(new EmptyBorder(15, 12, 15, 12));
		add(lbFloorNumber);
		
		txtFloorNumber = new JTextField();
		add(txtFloorNumber);
		txtFloorNumber.setColumns(10);
	}
}
