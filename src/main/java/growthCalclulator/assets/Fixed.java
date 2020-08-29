package growthCalclulator.assets;

public class Fixed {

	private long principle;
	private double interestRate;
	private double length;
	private long endValue;
	private int compoundFrequency;

	public Fixed(long principle, double interestRate, int iLength, int compoundFrequency) {
		super();
		this.principle = principle;
		this.interestRate = interestRate;
		this.length = iLength;
		this.compoundFrequency = compoundFrequency;
	}

	public Fixed() {

	}

	public int getCompoundFrequency() {
		return compoundFrequency;
	}

	public void setCompoundFrequency(int compoundFrequency) {
		this.compoundFrequency = compoundFrequency;
	}

	public long getPrinciple() {
		return principle;
	}

	public void setPrinciple(long principle) {
		this.principle = principle;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getiLength() {
		return length;
	}

	public void setiLength(double iLength) {
		this.length = iLength;
	}

	public long getEndValue() {
		return endValue;
	}

	public void setEndValue(double d) {
		this.endValue = (long) d;
	}

	@Override
	public String toString() {
		return "Fixed [principle=" + principle + ", interestRate=" + interestRate + ", length=" + length
				+ ", endValue=" + endValue + "]";
	}

}
