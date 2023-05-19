package views;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.Colors;
import models.Illness;
import models.Doctor;
import models.VitalSign;
import utils.ImageUtil;
import utils.PanelUtil;


public class DoctorView extends JPanel {
	final private double fontScale = 1;
	private int scaled(int size) {
		return (int) (scale * size);
	}
	private int fscaled(int size) {
		return (int) (fontScale * (scale * size - size)) + size;
	}
	private static final long serialVersionUID = 1L;
	JLabel DoctorIcon;
	
	JLabel lblId, lblNewLabel, lblNewLabel2;
	JPanel pnlIllnesses, pnlVitalSigns;
	String name, id, specialization;
	boolean atRisk;
	ArrayList<VitalSign> vitalSigns;
	ArrayList<Illness> illnesses;
	double scale = 1;
	public DoctorView(Doctor model) {
		updateFromModel(model);
		render();
	}
	public void setScale(double scale) {
		this.scale = scale;
	}
	public void updateFromModel(Doctor model) {
		name = model.getName();
		id = model.getId();
		specialization = model.getSpecialization();
	}
	public void render() {
		setBackground(Colors.blue);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			DoctorIcon = new JLabel(ImageUtil.getIcon("images\\doctoricon.png", scaled(26), scaled(30)));
			PanelUtil.pad(DoctorIcon, scaled(4), scaled(10), 0, scaled(10));
			add(DoctorIcon);
			
			
			JPanel pnlDoctorInfo = new JPanel();
			pnlDoctorInfo.setBackground(Colors.transparent);
			pnlDoctorInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(pnlDoctorInfo);
			pnlDoctorInfo.setLayout(new BoxLayout(pnlDoctorInfo, BoxLayout.Y_AXIS));
			
			lblId = new JLabel(
					String.format("ID: %s", id)
			);
			double fontFactor = 1;
			lblId.setBackground(Colors.blue);
			lblId.setFont(new Font("Tahoma", Font.PLAIN, (int)(fscaled(8))));
			pnlDoctorInfo.add(lblId);
			
			lblNewLabel = new JLabel(String.format("Dr. %s", name));
			lblNewLabel.setBackground(Colors.transparent);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, (int)(fscaled(12))));
			pnlDoctorInfo.add(lblNewLabel);
			
			lblNewLabel2 = new JLabel(specialization);
			lblNewLabel2.setBackground(Colors.transparent);
			lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, (int)(fscaled(12))));
			pnlDoctorInfo.add(lblNewLabel2);
			
			pnlIllnesses = new JPanel();
			pnlIllnesses.setBackground(Colors.transparent);
			pnlIllnesses.setAlignmentX(Component.LEFT_ALIGNMENT);
			pnlDoctorInfo.add(pnlIllnesses);
			pnlIllnesses.setLayout(new BoxLayout(pnlIllnesses, BoxLayout.X_AXIS));

			add(Box.createHorizontalGlue());
			
			int pad = 10;
			EmptyBorder padding = new EmptyBorder(pad, 0, pad, 0);
			setBorder(padding);
	}
	public void refresh() {
		DoctorIcon.setIcon(ImageUtil.getIcon("images\\doctoricon.png", scaled(26), scaled(30)));
//		JLabel DoctorIcon = new JLabel(ImageUtil.getIcon("images\\doctoricon.png", scaled(26), scaled(30)));
		PanelUtil.pad(DoctorIcon, scaled(4), scaled(10), 0, scaled(10));
		
		lblId.setText(String.format("ID: %s", id));
		lblId.setFont(new Font("Tahoma", Font.PLAIN, (int)(fscaled(8))));
		
		lblNewLabel.setText(String.format("Dr. %s", name));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, (int)(fscaled(12))));
		
		lblNewLabel2.setText(specialization);
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, (int)(fscaled(12))));
	}
}
