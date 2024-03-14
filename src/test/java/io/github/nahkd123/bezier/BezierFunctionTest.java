package io.github.nahkd123.bezier;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BezierFunctionTest {
	@Test
	void test() {
		Bezier curve = new Bezier(2, 0, 0, 0.5, 0, 0.5, 1, 1, 1);
		BezierFunction func = new BezierFunction(curve);

		for (double t = -1.0; t < 2.0; t += 0.1) {
			double[] xy = curve.interpolate(t);
			assertEquals(xy[1], func.applyAsDouble(xy[0]), 1E-5);
		}
	}
}
