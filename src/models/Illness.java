package models;

public class Illness {
	String illnessName, illnessDepartment;

	public String getIllnessName() {
		return illnessName;
	}

	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}

	public String getIllnessDepartment() {
		return illnessDepartment;
	}
	public void setIllnessDepartment(String illnessDepartment) {
		this.illnessDepartment = illnessDepartment;
	}

	@Override
	public String toString() {
		return "Illness [illnessName=" + illnessName + ", illnessDepartment=" + illnessDepartment + "]";
	}

	public Illness(String illnessName, String illnessDepartment) {
		this.illnessName = illnessName;
		this.illnessDepartment = illnessDepartment;
	}
}
