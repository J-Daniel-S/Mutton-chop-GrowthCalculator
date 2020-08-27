package growthCalclulator.assets;

public class Fixed {

	private long principle;
	private double interestRate;
	private int length;
	private long endValue;
	private int compoundFrequency;

	public Fixed(long principle, double interestRate, int length, int compoundFrequency) {
		super();
		this.principle = principle;
		this.interestRate = interestRate;
		this.length = length;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getEndValue() {
		return endValue;
	}

	public void setEndValue(double d) {
		this.endValue = (long) d;
	}

	@Override
	public String toString() {
		return "Fixed [principle=" + principle + ", interestRate=" + interestRate + ", length=" + length + ", endValue="
				+ endValue + "]";
	}

}
