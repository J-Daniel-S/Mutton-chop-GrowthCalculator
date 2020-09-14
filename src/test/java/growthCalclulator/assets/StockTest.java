package growthCalclulator.assets;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StockTest {

	@Test
	public void testStock() {
		Stock stock = new Stock();
		assertThat(stock).isNotNull();
	}

}
