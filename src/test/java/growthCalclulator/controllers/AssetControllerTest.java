package growthCalclulator.controllers;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

import growthCalclulator.assets.Fixed;
import growthCalclulator.assets.Stock;
import growthCalclulator.utils.Calculator;

public class AssetControllerTest {

	AssetController controller;
	Stock stock;
	Fixed fixed;
	DecimalFormat df;

	@Before
	public void setup() throws Exception {
		controller = new AssetController();
		df = new DecimalFormat("0.00");
		long[] cf = { 12000, 13380, 14405, 16044, 19400 };
		long[] capex = { 1500, 1750, 1480, 2440, 2450 };
		stock = new Stock(cf, capex, 15000, 7, "0.25", 5000);

	}

	@Test
	public void testGetPriceStock() {
		long total = Calculator.calculateTotal(stock);
		Calculator.setPrices(stock, total);
		assertEquals("54.65", df.format(stock.getBuyAndHoldValue()));
		assertEquals("40.99", df.format(stock.getDiscountedValue()));
	}

	@Test
	public void testGetPriceStockLong() {
		long total = Calculator.calculateTotal(stock, 9);
		Calculator.setPrices(stock, total);
		assertEquals("43.46", df.format(stock.getBuyAndHoldValue()));
		assertEquals("32.60", df.format(stock.getDiscountedValue()));
	}

	@Test
	public void testGetEndValue() {
		fixed = new Fixed(10000, 0.06, 12, 12);
		Calculator.calculateValue(fixed);
		assertEquals(20507, fixed.getEndValue());
	}

}
