package growthCalclulator.assets;

import java.util.Arrays;

import org.springframework.lang.NonNull;

public class Stock {

	private String ticker;
	private double buyAndHoldValue;
	private double discountedValue;
	private long desiredReturn;
	private long currentEquity;
	private String marginOfSafety;
	private long shares;
	@NonNull
	private long[] cashFlows;
	@NonNull
	private long[] capitalExpenditures;
	private long[] freeCashFlow;
	private double[] change;
	private double avgChange;

	public Stock(@NonNull long[] cashFlows, @NonNull long[] capitalExpenditures, long currentEquity,
			long desiredReturn) {
		super();
		this.cashFlows = cashFlows;
		this.capitalExpenditures = capitalExpenditures;
		this.currentEquity = currentEquity;
		this.desiredReturn = desiredReturn;
	}

	public Stock(@NonNull long[] cashFlows, @NonNull long[] capitalExpenditures, long currentEquity) {
		super();
		this.cashFlows = cashFlows;
		this.capitalExpenditures = capitalExpenditures;
		this.currentEquity = currentEquity;
	}

	public Stock(@NonNull long[] cashFlows, @NonNull long[] capitalExpenditures, long currentEquity, long desiredReturn,
			String mos, long shares) {
		super();
		this.cashFlows = cashFlows;
		this.capitalExpenditures = capitalExpenditures;
		this.currentEquity = currentEquity;
		this.desiredReturn = desiredReturn;
		this.marginOfSafety = mos;
		this.shares = shares;
	}

	public Stock(@NonNull long[] freeCashFlow, long currentEquity, long desiredReturn, String mos, long shares) {
		super();
		this.freeCashFlow = freeCashFlow;
		this.currentEquity = currentEquity;
		this.desiredReturn = desiredReturn;
		this.marginOfSafety = mos;
		this.shares = shares;
	}

	public Stock(@NonNull long[] cashFlows, @NonNull long[] capitalExpenditures) {
		super();
		this.cashFlows = cashFlows;
		this.capitalExpenditures = capitalExpenditures;
	}

	public Stock() {

	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public long[] getFreeCashFlow() {
		return freeCashFlow;
	}

	public void setFreeCashFlow(long[] freeCashFlow) {
		this.freeCashFlow = freeCashFlow;
	}

	public double getAvgChange() {
		return avgChange;
	}

	public void setAvgChange(double fcfChange) {
		this.avgChange = fcfChange;
	}

	public String getMarginOfSafety() {
		return marginOfSafety;
	}

	public void setMarginOfSafety(String marginOfSafety) {
		this.marginOfSafety = marginOfSafety;
	}

	public long getShares() {
		return shares;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}

	public long getDesiredReturn() {
		return desiredReturn;
	}

	public void setDesiredReturn(long desiredReturn) {
		this.desiredReturn = desiredReturn;
	}

	public long getCurrentEquity() {
		return currentEquity;
	}

	public void setCurrentEquity(long currentEquity) {
		this.currentEquity = currentEquity;
	}

	public double[] getChange() {
		return change;
	}

	public void setChange(double[] percentChange) {
		this.change = percentChange;
	}

	public double getBuyAndHoldValue() {
		return buyAndHoldValue;
	}

	public void setBuyAndHoldValue(double buyAndHoldValue) {
		this.buyAndHoldValue = buyAndHoldValue;
	}

	public double getDiscountedValue() {
		return discountedValue;
	}

	public void setDiscountedValue(double discountedValue) {
		this.discountedValue = discountedValue;
	}

	public long[] getCashFlows() {
		return cashFlows;
	}

	public void setCashFlows(long[] cashFlows) {
		this.cashFlows = cashFlows;
	}

	public long[] getCapitalExpenditures() {
		return capitalExpenditures;
	}

	public void setCapitalExpenditures(long[] capitalExpenditures) {
		this.capitalExpenditures = capitalExpenditures;
	}

	@Override
	public String toString() {
		return "stock [buyAndHoldValue=" + buyAndHoldValue + ", discountedValue=" + discountedValue + ", cashFlows="
				+ Arrays.toString(cashFlows) + ", capitalExpenditures=" + Arrays.toString(capitalExpenditures) + "]";
	}

}
