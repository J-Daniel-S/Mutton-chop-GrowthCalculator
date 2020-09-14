package growthCalclulator.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.Test;

import growthCalclulator.assets.Fixed;
import growthCalclulator.assets.Stock;

public class CalculatorTest {

	Fixed fixed;
	Stock stock;
	long[] cf = { 1000, 1100, 1210, 1331, 1464 };
	long[] capex = { 50, 55, 61, 66, 72 };

	@Test
	public void testCalculateValueCompoundMonthly() {
		fixed = new Fixed(1000, 5, 10, 12);
		Calculator.calculateValue(fixed);
		assertEquals(1647, fixed.getEndValue());
	}

	@Test
	public void testCalculateValueCompoundAnnually() {
		fixed = new Fixed(1000, 5, 10, 1);
		Calculator.calculateValue(fixed);
		assertEquals(1628, fixed.getEndValue());
	}

	@Test
	public void testCalculateValueCompoundSemiAnnually() {
		fixed = new Fixed(1000, 5, 10, 2);
		Calculator.calculateValue(fixed);
		assertEquals(1638, fixed.getEndValue());
	}

	@Test
	public void testCalculateValueCompoundDaily() {
		fixed = new Fixed(1000, 5, 10, 365);
		Calculator.calculateValue(fixed);
		assertEquals(1648, fixed.getEndValue());
	}

	@Test
	public void testCalculateFreeCashFlow() {
		stock = new Stock(cf, capex);
		long[] fcf = Calculator.calculateFreeCashFlow(stock);
		assertEquals(1149, fcf[2]);
	}

	@Test
	public void testCalculateFcfChange() {
		stock = new Stock(cf, capex);
		long[] fcf = Calculator.calculateFreeCashFlow(stock);
		double[] change = Calculator.calculatePercentChange(fcf);
		assertEquals(BigDecimal.valueOf(110.0), BigDecimal.valueOf(change[2]));
	}

	@Test
	public void testFcfForCalculation() {
		stock = new Stock(cf, capex);
		long[] fcf = Calculator.calculateFreeCashFlow(stock);
		long calc = Calculator.getFcfForCalculation(fcf);
		assertEquals(1208, calc);
	}

	@Test
	public void testGetChangeForCalc() {
		stock = new Stock(cf, capex);
		long[] fcf = Calculator.calculateFreeCashFlow(stock);
		double[] percentChange = Calculator.calculatePercentChange(fcf);
		double changeForCalc = Calculator.getChangeForCalc(percentChange);
		assertEquals(BigDecimal.valueOf(109.83333333333333), BigDecimal.valueOf(changeForCalc));
	}

	@Test
	public void testDcfMultipliersSingleDigits() {
		double[] multipliers = Calculator.calculateDcfMultipliers(8);
		// multipliers = 1 / 1.08^3
		assertTrue(0.7938322410201696 == multipliers[2]);
	}

	@Test
	public void testDcfMultipliersDoubleDigits() {
		double[] multipliers = Calculator.calculateDcfMultipliers(14);
		assertTrue(0.8771929824561404 == multipliers[0]);
	}

	@Test
	public void testDcfMultipliersTenPercent() {
		double[] multipliers = Calculator.calculateDcfMultipliers(10);
		assertTrue(0.8264462809917354 == multipliers[1]);
	}

	@Test
	public void testTotalDiscountedCashFlow() {
		stock = new Stock(cf, capex);
		long[] fcf = Calculator.calculateFreeCashFlow(stock);
		double[] change = Calculator.calculatePercentChange(fcf);
		long calcFcf = Calculator.getFcfForCalculation(fcf);
		double fcfChange = Calculator.getChangeForCalc(change);
		double[] dcfMultipliers = Calculator.calculateDcfMultipliers(10);
		long totalDcf = Calculator.totalDcf(calcFcf, fcfChange, dcfMultipliers);
		assertEquals(16161, totalDcf);
	}

	@Test
	public void testCalculateTotal() {
		stock = new Stock(cf, capex, 10000, 10);
		stock.setMarginOfSafety("25");
		long total = Calculator.calculateTotal(stock);
		assertEquals(26161, total);
	}

	@Test
	public void testCalculateTotalArbitraryGrowth() {
		stock = new Stock(cf, capex, 10000, 10);
		stock.setMarginOfSafety("25");
		long total = Calculator.calculateTotal(stock, 112);
		assertEquals(28290, total);
	}

	@Test
	public void testSetPrices() {
		stock = new Stock(cf, capex, 10000, 10, "25", 500);
		long total = Calculator.calculateTotal(stock);
		Calculator.setPrices(stock, total);
		DecimalFormat df = new DecimalFormat("0.00");
		assertEquals("52.32", df.format(stock.getBuyAndHoldValue()));
		assertEquals("39.24", df.format(stock.getDiscountedValue()));
	}

	@Test
	public void testSetPricesArbitraryGrowth() {
		stock = new Stock(cf, capex, 10000, 10, "30", 500);
		long total = Calculator.calculateTotal(stock, 112);
		Calculator.setPrices(stock, total);
		DecimalFormat df = new DecimalFormat("0.00");

		assertEquals("56.58", df.format(stock.getBuyAndHoldValue()));
		assertEquals("39.61", df.format(stock.getDiscountedValue()));
	}

	@Test
	public void testSetPricesFcf() {
		long[] fcf = { 950, 1045, 1149, 1265, 1392 };
		stock = new Stock(fcf, 10000, 10, "25", 500);
		long total = Calculator.calculateTotalFcf(stock);
		Calculator.setPrices(stock, total);
		DecimalFormat df = new DecimalFormat("0.00");

		assertEquals("52.32", df.format(stock.getBuyAndHoldValue()));
		assertEquals("39.24", df.format(stock.getDiscountedValue()));

	}

}
