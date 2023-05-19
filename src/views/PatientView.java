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
import models.Patient;
import models.VitalSign;
import utils.ImageUtil;
import utils.PanelUtil;

public class PatientView extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel lblId, lblNewLabel;
	JPanel pnlIllnesses, pnlVitalSigns;
	String name, id, gender, birthday, physician;
	boolean atRisk;
	ArrayList<VitalSign> vitalSigns;
	ArrayList<Illness> illnesses;
	public PatientView(Patient model) {
		updateFromModel(model);
		render();
	}
	public void updateFromModel(Patient model) {
		name = model.getName();
		id = model.getId();
		gender = model.getGender();
		birthday = model.getBirthday();
		physician = model.getPhysician();
		vitalSigns = model.getVitalSigns();
		illnesses = model.getIllnesses();
		atRisk = model.anyVitalOutOfRange();
	}
	public void render() {
		setBackground(
				atRisk
				? Colors.warning
				: Colors.blue
			);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			
			JLabel patientIcon = new JLabel(ImageUtil.getIcon("images\\patient.png", 30, 30));
			PanelUtil.pad(patientIcon, 0,10,0,10);
			add(patientIcon);
			
			
			JPanel pnlPatientInfo = new JPanel();
			pnlPatientInfo.setBackground(Colors.transparent);
			pnlPatientInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(pnlPatientInfo);
			pnlPatientInfo.setLayout(new BoxLayout(pnlPatientInfo, BoxLayout.Y_AXIS));
			
			lblId = new JLabel(
					String.format("ID: %s    Gender: %s    DOB: %s", 
					id, gender, birthday)
			);
			lblId.setBackground(Colors.blue);
			lblId.setFont(new Font("Tahoma", Font.PLAIN, 8));
			pnlPatientInfo.add(lblId);
			
			lblNewLabel = new JLabel(String.format("%s (%s)", name, physician));
			lblNewLabel.setBackground(Colors.transparent);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			pnlPatientInfo.add(lblNewLabel);
			
			pnlIllnesses = new JPanel();
			pnlIllnesses.setBackground(Colors.transparent);
			pnlIllnesses.setAlignmentX(Component.LEFT_ALIGNMENT);
			pnlPatientInfo.add(pnlIllnesses);
			pnlIllnesses.setLayout(new BoxLayout(pnlIllnesses, BoxLayout.X_AXIS));
			
			int pad = 5;
			
			JLabel lblIllness = new JLabel("Illnesses:");
			lblIllness.setBackground(Colors.transparent);
			lblIllness.setFont(new Font("Tahoma", Font.BOLD, 10));
			PanelUtil.pad(lblIllness, 0,5,0,pad);
			pnlIllnesses.add(lblIllness);
			
			for (Illness i: illnesses) {
				lblIllness = new JLabel(i.getIllnessName());
				lblIllness .setBackground(Colors.transparent);
				lblIllness .setFont(new Font("Tahoma", Font.PLAIN, 10));
				PanelUtil.pad(lblIllness , 0,pad,0,pad);
				pnlIllnesses.add(lblIllness );
			}
			
			pnlVitalSigns = new JPanel();
			pnlVitalSigns.setBackground(Colors.transparent);
			pnlVitalSigns.setAlignmentX(Component.LEFT_ALIGNMENT);
			pnlPatientInfo.add(pnlVitalSigns);

			PanelUtil.pad(pnlVitalSigns, 0,pad,0,pad);
			pnlVitalSigns.setLayout(new BoxLayout(pnlVitalSigns, BoxLayout.X_AXIS));
				
			JLabel lblVitalSign = new JLabel("Vital Signs:");
			lblVitalSign.setBackground(Colors.transparent);
			lblVitalSign.setFont(new Font("Tahoma", Font.BOLD, 10));
			PanelUtil.pad(lblVitalSign, 0,0,0,pad);
			pnlVitalSigns.add(lblVitalSign);
			
			for (VitalSign i: vitalSigns) {
				lblIllness = new JLabel(i.getLetterCode() + " " + i.getNumericCode());
				lblIllness .setBackground(Colors.transparent);
				if (i.outOfRange()) {
					lblIllness.setFont(new Font("Tahoma", Font.BOLD, 10));
					lblIllness.setForeground(Colors.warningText);
				} else {
					lblIllness .setFont(new Font("Tahoma", Font.PLAIN, 10));
				}
				PanelUtil.pad(lblIllness , 0,pad,0,pad);
				pnlVitalSigns.add(lblIllness );
			}
			
			add(Box.createHorizontalGlue());
			pad = 10;
			EmptyBorder padding = new EmptyBorder(pad, 0, pad, 0);
			setBorder(padding);
	}
	public void refresh() {
		setBackground(
				atRisk
				? Colors.warning
				: Colors.blue
		);
		lblId.setText(String.format("ID: %s    Gender: %s    DOB: %s", 
					id, gender, birthday));
		lblNewLabel.setText(String.format("%s (%s)", name, physician));

		int pad = 5;
		pnlIllnesses.removeAll();
		JLabel lblIllness = new JLabel("Illnesses:");
		lblIllness.setBackground(Colors.transparent);
		lblIllness.setFont(new Font("Tahoma", Font.BOLD, 10));
		PanelUtil.pad(lblIllness, 0,5,0,pad);
		pnlIllnesses.add(lblIllness);
		for (Illness i: illnesses) {
			lblIllness = new JLabel(i.getIllnessName());
			lblIllness .setBackground(Colors.transparent);
			lblIllness .setFont(new Font("Tahoma", Font.PLAIN, 10));
			PanelUtil.pad(lblIllness , 0,pad,0,pad);
			pnlIllnesses.add(lblIllness );
		}
		pnlVitalSigns.removeAll();
		JLabel lblVitalSign = new JLabel("Vital Signs:");
		lblVitalSign.setBackground(Colors.transparent);
		lblVitalSign.setFont(new Font("Tahoma", Font.BOLD, 10));
		PanelUtil.pad(lblVitalSign, 0,0,0,pad);
		pnlVitalSigns.add(lblVitalSign);
		for (VitalSign i: vitalSigns) {
			lblIllness = new JLabel(i.getLetterCode() + " " + i.getNumericCode());
			lblIllness .setBackground(Colors.transparent);
			if (i.outOfRange()) {
				lblIllness.setFont(new Font("Tahoma", Font.BOLD, 10));
				lblIllness.setForeground(Colors.warningText);
			} else {
				lblIllness .setFont(new Font("Tahoma", Font.PLAIN, 10));
			}
			PanelUtil.pad(lblIllness , 0,pad,0,pad);
			pnlVitalSigns.add(lblIllness );
		}
	}
}
