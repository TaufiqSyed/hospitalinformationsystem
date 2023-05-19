package views.menupanels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.FloorController;
import models.Floor;
import models.Patient;
import models.VitalSign;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

public class FloorStatistics extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String, ArrayList<Double>> vitalSignMap; // letterCode -> numericCodes
	
	Double min(ArrayList<Double> v) {
		Double result = Double.MAX_VALUE;
		for (Double d: v) {
			if (d < result) result = d;
		}
		return result;
	}
	Double max(ArrayList<Double> v) {
		Double result = Double.MIN_VALUE;
		for (Double d: v) {
			if (d > result) result = d;
		}
		return result;
	}
	Double avg(ArrayList<Double> v) {
		Double sum = 0.0;
		for (Double d: v) {
			sum += d;
		}
		return sum / v.size();
	}
	
	public FloorStatistics(FloorController floor) {
		vitalSignMap = new HashMap<String, ArrayList<Double>>();
		for (Patient p : floor.getRegisteredPatients()) {
			for (VitalSign v: p.getVitalSigns()) {
				if (!vitalSignMap.containsKey(v.getLetterCode())) {
					vitalSignMap.put(v.getLetterCode(), new ArrayList<Double>());
				}
				vitalSignMap.get(v.getLetterCode()).add(v.getNumericCode());
			}
		}
		
		setBounds(133, 65, 221, 138);
		setLayout(new GridLayout(0, 4, 0, 0));
		
		add(new JLabel("Patient Count: "));
		add(new JLabel(Integer.toString(floor.getRegisteredPatients().size())));
		add(new JLabel("Max Capacity: "));
		add(new JLabel(Integer.toString(floor.getCapacity())));
		
		
		add(new JLabel("Vital Signs"));
		add(new JLabel("Maximum"));
		add(new JLabel("Minimum"));
		add(new JLabel("Average"));
		
		vitalSignMap.forEach((letterCode, nums) -> {
			add(new JLabel(letterCode));
			add(new JLabel(max(nums).toString()));
			add(new JLabel(min(nums).toString()));
			add(new JLabel(avg(nums).toString()));
		});
	}
}
