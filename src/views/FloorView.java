package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.Colors;
import models.Floor;
import models.Illness;
import models.Patient;
import models.VitalSign;
import utils.ImageUtil;
import utils.OrdinalUtil;
import utils.PanelUtil;

public class FloorView extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel lblId, lblNewLabel, lblNewLabel2, lblCapacity, lblNumPatients;
	
	String name, floorCode, medicalDepartment, chiefResidentDoctor;
	int number, capacity;
	ArrayList<Patient> registeredPatients;

	public FloorView(Floor model) {
		updateFromModel(model);
		render();
	}
	public void updateFromModel(Floor model) {
		name = model.getName();
		floorCode = model.getFloorCode();
		medicalDepartment = model.getMedicalDepartment();
		chiefResidentDoctor = model.getChiefResidentDoctor();
		number = model.getNumber();
		capacity = model.getCapacity();
			registeredPatients = model.getRegisteredPatients();
	}
	public void render() {
		setBackground(Colors.blue);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			JLabel floorIcon = new JLabel(ImageUtil.getIcon("images\\floor.png", 30, 30));
			PanelUtil.pad(floorIcon, 0,10,0,10);
			add(floorIcon);
			
			
			JPanel pnlfloorInfo = new JPanel();
			pnlfloorInfo.setBackground(Colors.transparent);
			pnlfloorInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(pnlfloorInfo);
			pnlfloorInfo.setLayout(new BoxLayout(pnlfloorInfo, BoxLayout.Y_AXIS));
			
			lblId = new JLabel(
					String.format("Chief Resident Doctor: %s    Floor Code: %s", chiefResidentDoctor, floorCode)
			);
			lblId.setBackground(Colors.blue);
			lblId.setFont(new Font("Tahoma", Font.PLAIN, 8));
			pnlfloorInfo.add(lblId);
			
			lblNewLabel = new JLabel(String.format("%s Floor - %s", OrdinalUtil.ordinalize(number), name));
			lblNewLabel.setBackground(Colors.transparent);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			pnlfloorInfo.add(lblNewLabel);
			
			lblNewLabel2 = new JLabel(String.format("%s Department", medicalDepartment));
			lblNewLabel2.setBackground(Colors.transparent);
			lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			pnlfloorInfo.add(lblNewLabel2);			
			
			add(Box.createHorizontalGlue());
			
			JPanel pnlCapacityInfo = new JPanel();
			pnlCapacityInfo.setLayout(new BoxLayout(pnlCapacityInfo, BoxLayout.Y_AXIS));
			pnlCapacityInfo.setBackground(Colors.transparent);
			
			lblCapacity = new JLabel(
					String.format("Maximum Capacity: %d ", capacity)
			);
			lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 9));
			pnlCapacityInfo.add(lblCapacity);
			
			lblNumPatients = new JLabel(
					String.format("Number of Patients: %d ", registeredPatients.size())
			);
			lblNumPatients.setFont(new Font("Tahoma", Font.PLAIN, 9));
			pnlCapacityInfo.add(lblNumPatients);
			
			int pad = 20;
			EmptyBorder padding = new EmptyBorder(0, 0, 0, pad);
			pnlCapacityInfo.setBorder(padding);

			add(pnlCapacityInfo);
			
			pad = 6;
			padding = new EmptyBorder(pad, 0, pad, 0);
			setBorder(padding);
	}
	public void refresh() {
		lblId.setText(String.format("Chief Resident Doctor: %s    Floor Code: %s", chiefResidentDoctor, floorCode));
		lblNewLabel.setText(String.format("%s Floor - %s", OrdinalUtil.ordinalize(number), name));
		lblNewLabel2.setText(String.format("%s Department", medicalDepartment));
		lblCapacity.setText(String.format("Maximum Capacity: %d ", capacity));
		lblNumPatients.setText(String.format("Number of Patients: %d ", registeredPatients.size()));
	}
}
