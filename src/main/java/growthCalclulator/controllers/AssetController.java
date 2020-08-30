package growthCalclulator.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import growthCalclulator.assets.Fixed;
import growthCalclulator.assets.Stock;
import growthCalclulator.utils.Calculator;

@RestController
@RequestMapping("/compound-calculator")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = { RequestMethod.POST, RequestMethod.GET,
		RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
public class AssetController {

	@PostMapping("/stock")
	public ResponseEntity<Stock> getPrice(@RequestBody Stock stock) {
		long total = Calculator.calculateTotal(stock);
		Calculator.setPrices(stock, total);
		return ResponseEntity.ok(stock);
	}

	@PostMapping("/stock-fcf")
	public ResponseEntity<Stock> getPriceFcf(@RequestBody Stock stock) {
		long total = Calculator.calculateTotalFcf(stock);
		Calculator.setPrices(stock, total);
		return ResponseEntity.ok(stock);
	}

	@PostMapping("/stock/{growthRate}")
	public ResponseEntity<Stock> getPrice(@RequestBody Stock stock, @PathVariable long growthRate) {
		long total = Calculator.calculateTotal(stock, growthRate);
		Calculator.setPrices(stock, total);
		return ResponseEntity.ok(stock);
	}

	@PostMapping("/stock-fcf/{growthRate}")
	public ResponseEntity<Stock> getPriceFcf(@RequestBody Stock stock, @PathVariable long growthRate) {
		long total = Calculator.calculateTotal(stock, growthRate);
		Calculator.setPrices(stock, total);
		return ResponseEntity.ok(stock);
	}

	@PostMapping("/savings")
	public ResponseEntity<Fixed> getEndValue(@RequestBody Fixed fixed) {
		return ResponseEntity.ok(Calculator.calculateValue(fixed));
	}

}
