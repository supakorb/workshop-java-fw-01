package com.example.kbtg;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DemoServiceTest {

	@Test
	@DisplayName("ในการทำงานต้อง random ได้ค่า 5")
	public void random_5() {
		DemoService demoService = new DemoService();
		demoService.setRandom(new MockRandom(5));
		String actualResult = demoService.generateData("somkiat");
		assertEquals("somkiat5", actualResult);
	}
	
	@Test
	@DisplayName("ในการทำงานต้อง random 3 ได้ Exception")
	public void random_3() {
		DemoService demoService = new DemoService();
		demoService.setRandom(new MockRandom(3));
		Exception exception = assertThrows(RuntimeException.class, () -> {
			demoService.generateData("somkiat");
		});
		assertEquals("Invalid number with 3", exception.getMessage());
	}
	
	@Test
	@DisplayName("ในการทำงานต้อง random 9 ได้ Exception")
	public void random_9() {
		DemoService demoService = new DemoService();
		demoService.setRandom(new MockRandom(9));
		assertThrows(RuntimeException.class, () -> {
			demoService.generateData("somkiat");
		}, "Invalid number with 9");
	}
}

class MockRandom extends Random {
	
	private final int result;
	
	public MockRandom(int result) {
		this.result = result;
	}
	
	@Override
	public int nextInt(int bound) {
		return result;
	}
}
