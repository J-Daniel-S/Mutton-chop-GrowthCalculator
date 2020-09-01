package growthCalclulator.assets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedTest {

	@Test
	public void testFixedLongDoubleIntInt() {
		Fixed fixed = new Fixed(50000, 0.07, 10, 10);
		assertThat(fixed).isNotNull();
	}

	@Test
	public void testFixed() {
		Fixed fixed = new Fixed();
		assertThat(fixed).isNotNull();
	}

	@Test
	public void testGetCompoundFrequency() {
		Fixed fixed = new Fixed(50000, 0.07, 10, 10);
		assertEquals(10, fixed.getCompoundFrequency());
	}

	@Test
	public void testSetCompoundFrequency() {
		Fixed fixed = new Fixed();
		fixed.setCompoundFrequency(10);
		assertEquals(10, fixed.getCompoundFrequency());
	}

	@Test
	public void testGetPrinciple() {
		Fixed fixed = new Fixed(5000, 0.07, 10, 10);
		assertEquals(5000, fixed.getPrinciple());
	}

	@Test
	public void testSetPrinciple() {
		Fixed fixed = new Fixed();
		fixed.setPrinciple(5000);
		assertEquals(5000, fixed.getPrinciple());
	}

	@Test
	public void testGetInterestRate() {
		Fixed fixed = new Fixed(5000, 0.07, 10, 10);
		assertEquals("0.07", String.valueOf(fixed.getInterestRate()));
	}

	@Test
	public void testSetInterestRate() {
		Fixed fixed = new Fixed();
		fixed.setInterestRate(0.07);
		assertEquals("0.07", String.valueOf(fixed.getInterestRate()));
	}

	@Test
	public void testGetLength() {
		Fixed fixed = new Fixed(5000, 0.07, 10, 10);
		assertEquals("10", String.valueOf(fixed.getiLength()));
	}

	@Test
	public void testSetLength() {
		Fixed fixed = new Fixed();
		fixed.setiLength(10);
		assertEquals("10", String.valueOf(fixed.getiLength()));
	}

	@Test
	public void testSetEndValue() {
		Fixed fixed = new Fixed(5000, 0.07, 10, 10);
		fixed.setEndValue(20000);
		assertEquals(20000, fixed.getEndValue());
	}

}
