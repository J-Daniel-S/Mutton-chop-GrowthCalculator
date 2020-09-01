package growthCalclulator.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import growthCalclulator.assets.Fixed;
import growthCalclulator.assets.Stock;

public class Calculator {

	public static Fixed calculateValue(Fixed fixed) {
		long p = fixed.getPrinciple();
		double r = fixed.getInterestRate() / 100;
		double l = fixed.getiLength();
		int n = fixed.getCompoundFrequency();

		double t = p * Math.pow(1 + (r / n), n * l);

		fixed.setEndValue(t);

		return fixed;
	}

//	calculates and fills the dcf multipliers array: used only by the discounted cash flow method
//	Each multiplier is = 1 / 1.%^i+1 where i = the years into the future and % = yoy percent growth
	static double[] calculateDcfMultipliers(long desiredGrowthPercentage) {
		double[] dcfMultipliers = new double[20];
		String decimals = new String();
		if (desiredGrowthPercentage < 10) {
			decimals = "0" + desiredGrowthPercentage;
		} else {
			decimals = String.valueOf(desiredGrowthPercentage);
		}
		String percent = "1." + decimals;
		for (int i = 1; i < 19; i++) {
			dcfMultipliers[i - 1] = (1 / Math.pow(Double.valueOf(percent), i));
		}
		return dcfMultipliers;
	}

	// capital expenditures taken from cash flow from operations
	public static long[] calculateFreeCashFlow(Stock stock) {
		long[] cf = stock.getCashFlows();
		long[] capex = stock.getCapitalExpenditures();
		long[] fcf = new long[5];

		for (int i = 0; i < capex.length; i++) {
			fcf[i] = cf[i] - capex[i];
		}

		return fcf;
	}

	// each index holds the percentage of the prior fcf that is the current fcf e.g.
	// fcf[4] is xxx% of fcf[3]
	public static double[] calculatePercentChange(long[] fcf) {
		double[] changeArr = new double[4];

		for (int i = 3; i >= 0; i--) {
			changeArr[i] = ((fcf[i + 1] * 100) / fcf[i]);
		}
//		Arrays.stream(changeArr).forEach(System.out::println);

		return changeArr;
	}

	// Average of the last five years of free cash flow with this last year's and
	// the year prior double weighted
	public static long getFcfForCalculation(long[] fcf) {
		long val = (fcf[0] + fcf[1] + fcf[2] + (2 * fcf[3]) + (2 * fcf[4])) / 7;
		return val;
	}

	// round down to the nearest full percent
	// yoy growth of fcf
	public static double getChangeForCalc(double[] change) {
		double val = (change[0] + change[1] + (change[2] * 2) + (change[3] * 2)) / 6;
		return val;
	}

	public static long totalDcf(long calcDcf, double fcfChange, double[] dcfMultipliers) {
		long totalDcf = 0;
		double lastFcf = calcDcf;
		double dcf;

		MathContext mc = new MathContext(4, RoundingMode.HALF_EVEN);
		BigDecimal multiplier = BigDecimal.valueOf(fcfChange).divide(BigDecimal.valueOf(100), mc);

		for (int i = 0; i < 19; i++) {
			if (i < 11) {
				multiplier = multiplier.multiply(BigDecimal.valueOf(0.9931), mc);
			} else if (i != 0) {
				// assumes a 5% growth rate for >10 years in the future
				multiplier = BigDecimal.valueOf(1.05);
			}

			dcf = lastFcf * dcfMultipliers[i];
//			System.out.println(dcf);
			lastFcf = (lastFcf * multiplier.doubleValue());
			totalDcf += (long) dcf;
		}

		if (totalDcf < 50000) {
			totalDcf *= 1.1;
		} else if (totalDcf < 100000) {
			totalDcf *= 1.04;
		} else if (totalDcf < 150000) {
			totalDcf *= 1.02;
		}

		return totalDcf;
	}

	public static long calculateTotal(Stock stock) {
		stock.setMarginOfSafety(String.valueOf(Double.valueOf(stock.getMarginOfSafety()) / 100));
		long[] fcf = calculateFreeCashFlow(stock);
		double[] percentChange = calculatePercentChange(fcf);
		stock.setChange(percentChange);
		double[] dcfMultipliers = calculateDcfMultipliers(stock.getDesiredReturn());
		long calcFcf = getFcfForCalculation(fcf);
		double fcfChange = getChangeForCalc(percentChange);
		long equity = stock.getCurrentEquity();
		stock.setAvgChange(fcfChange);
		long totalDcf = totalDcf(calcFcf, fcfChange, dcfMultipliers);
		return totalDcf + equity;
	}

	public static long calculateTotal(Stock stock, long fcfChange) {
		stock.setMarginOfSafety(String.valueOf(Double.valueOf(stock.getMarginOfSafety()) / 100));
		long[] fcf = calculateFreeCashFlow(stock);
		double[] percentChange = calculatePercentChange(fcf);
		stock.setChange(percentChange);
		double[] dcfMultipliers = calculateDcfMultipliers(stock.getDesiredReturn());
		long calcFcf = getFcfForCalculation(fcf);
		stock.setAvgChange(fcfChange);
		long equity = stock.getCurrentEquity();
		long totalDcf = totalDcf(calcFcf, fcfChange, dcfMultipliers);
		return totalDcf + equity;
	}

	public static long calculateTotalFcf(Stock stock) {
		stock.setMarginOfSafety(String.valueOf(Double.valueOf(stock.getMarginOfSafety()) / 100));
		double[] percentChange = calculatePercentChange(stock.getFreeCashFlow());
		stock.setChange(percentChange);
		double[] dcfMultipliers = calculateDcfMultipliers(stock.getDesiredReturn());
		long calcFcf = getFcfForCalculation(stock.getFreeCashFlow());
		double fcfChange = getChangeForCalc(percentChange);
		long equity = stock.getCurrentEquity();
		stock.setAvgChange(fcfChange);
		long totalDcf = totalDcf(calcFcf, fcfChange, dcfMultipliers);
		return totalDcf + equity;
	}

	public static long calculateTotalFcf(Stock stock, long fcfChange) {
		stock.setMarginOfSafety(String.valueOf(Double.valueOf(stock.getMarginOfSafety()) / 100));
		double[] percentChange = calculatePercentChange(stock.getFreeCashFlow());
		stock.setChange(percentChange);
		double[] dcfMultipliers = calculateDcfMultipliers(stock.getDesiredReturn());
		long calcFcf = getFcfForCalculation(stock.getFreeCashFlow());
		long equity = stock.getCurrentEquity();
		stock.setAvgChange(fcfChange);
		long totalDcf = totalDcf(calcFcf, fcfChange, dcfMultipliers);
		return totalDcf + equity;
	}

	public static Stock setPrices(Stock stock, long total) {
		double fairPrice = (double) total / stock.getShares();
		double discountedPrice = fairPrice * (1 - Double.valueOf(stock.getMarginOfSafety()));
		stock.setBuyAndHoldValue(fairPrice);
		stock.setDiscountedValue(discountedPrice);
		return stock;
	}
}
