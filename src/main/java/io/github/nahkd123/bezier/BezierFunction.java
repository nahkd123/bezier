package io.github.nahkd123.bezier;

import java.util.function.DoubleUnaryOperator;

/**
 * <p>
 * Parametric curve to {@code y = f(x)}. Uses binary search under the hood, but
 * there can be a better way to approximate. The lower the threshold, the better
 * (and slower). Threshold less than or equals to 0 are not allowed.
 * </p>
 */
public record BezierFunction(Bezier curve, double threshold) implements DoubleUnaryOperator {
	public BezierFunction {
		if (threshold <= 0) throw new IllegalArgumentException("Threshold can't be less than or equals to 0");
		if (curve.dimensions() != 2) throw new IllegalArgumentException("Curve must be 2D");
	}

	public BezierFunction(Bezier curve) {
		this(curve, 1E-6);
	}

	@Override
	public double applyAsDouble(double x) {
		double start = Math.min(x, 0d), end = Math.max(x, 1d);
		double middle = 0.5d;
		double[] xy = new double[2];

		while (Math.abs(end - start) > threshold) {
			curve.interpolate(middle, xy, 0);
			if (x == xy[0]) return xy[1];
			if (x > xy[0]) start = middle;
			if (x < xy[0]) end = middle;
			middle = (start + end) / 2;
		}

		return xy[1];
	}
}
