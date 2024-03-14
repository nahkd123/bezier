package io.github.nahkd123.bezier;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class BezierTest {
	@Test
	void test() {
		Bezier linear = new Bezier(2, 0, 0, 1, 1);
		System.out.println(Arrays.toString(linear.interpolate(0.5)));

		Bezier quadratic = new Bezier(3, 0, 0, 0.5, 0, 0.5, 1, 1, 1);
		System.out.println(Arrays.toString(quadratic.interpolate(0.9)));
	}
}
