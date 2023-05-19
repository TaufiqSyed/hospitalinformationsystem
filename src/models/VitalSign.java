package models;

public class VitalSign {
	String letterCode;
	double numericCode;
	double rangeLowerBound, rangeUpperBound;
	public VitalSign() {}
	public VitalSign(String letterCode, double numericCode) {
		super();
		this.letterCode = letterCode;
		this.numericCode = numericCode;
		this.rangeLowerBound = 20;
		this.rangeUpperBound = 100;
	}
	public VitalSign(String letterCode, double numericCode, double rangeLowerBound, double rangeUpperBound) {
		super();
		this.letterCode = letterCode;
		this.numericCode = numericCode;
		this.rangeLowerBound = rangeLowerBound;
		this.rangeUpperBound = rangeUpperBound;
	}
	@Override
	public String toString() {
		return "VitalSign [letterCode=" + letterCode + ", numericCode=" + numericCode + ", rangeLowerBound="
				+ rangeLowerBound + ", rangeUpperBound=" + rangeUpperBound + "]";
	}
	public String getLetterCode() {
		return letterCode;
	}
	public void setLetterCode(String letterCode) {
		this.letterCode = letterCode;
	}
	public double getNumericCode() {
		return numericCode;
	}
	public void setNumericCode(double numericCode) {
		this.numericCode = numericCode;
	}
	public double getRangeLowerBound() {
		return rangeLowerBound;
	}
	public void setRangeLowerBound(double rangeLowerBound) {
		this.rangeLowerBound = rangeLowerBound;
	}
	public double getRangeUpperBound() {
		return rangeUpperBound;
	}
	public void setRangeUpperBound(double rangeUpperBound) {
		this.rangeUpperBound = rangeUpperBound;
	}
	public boolean outOfRange() {
		return numericCode < rangeLowerBound || numericCode > rangeUpperBound;
	}
}
