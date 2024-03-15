package io.github.nahkd123.bezier;

/**
 * <p>
 * Multi-dimension bézier curve. This is a parametric curve.
 * </p>
 * <p>
 * <b>How to use:</b> The first parameter of record constructor is the number of
 * dimensions (n-th dimension), the second parameter is an array of values for
 * control points, where each control point uses n elements in the array. For
 * example, a 2D curve with 3 control points (quadratic curve) will have 6
 * elements in data array: index 0 and 1 is for the first control point, 2 and 3
 * for second, 4 and 5 for third.
 * </p>
 * <p>
 * To interpolate, use {@link #interpolate(double)} or
 * {@link #interpolate(double, double[], int)}. The
 * {@link #interpolate(int, double, int, double[], int, double[], int)} is the
 * actual algorithm, but it might be confusing to use for most people.
 * </p>
 * 
 * @see #interpolate(double)
 * @see #interpolate(double, double[], int)
 * @see #interpolate(int, double, int, double[], int, double[], int)
 */
public record Bezier(int dimensions, double... data) {
	public Bezier {
		if (data.length % dimensions != 0)
			throw new IllegalArgumentException("The number of values must be a multiple of " + dimensions);
		if (data.length < dimensions * 2)
			throw new IllegalArgumentException("Number of control points must be greater than or equals to 2");
	}

	public int getPoints() { return data.length / dimensions; }

	public void interpolate(double t, double[] out, int offset) {
		if (out.length < (offset + dimensions))
			throw new IndexOutOfBoundsException("out.length (" + out.length
				+ ") is less than offset + " + dimensions + " (" + offset + dimensions + ")");

		double[] aux = new double[triangularSum(getPoints() - 1) * dimensions];
		interpolate(dimensions, t, getPoints(), data, 0, aux, 0);
		System.arraycopy(aux, aux.length - dimensions, out, offset, dimensions);
	}

	public double[] interpolate(double t) {
		double[] xy = new double[dimensions];
		interpolate(t, xy, 0);
		return xy;
	}

	public static void interpolate(int dimensions, double t, int depth, double[] source, int sourceOffset, double[] target, int targetOffset) {
		if (depth <= 1) return;

		for (int i = 0; i < depth - 1; i++) {
			for (int d = 0; d < dimensions; d++) {
				double a = source[sourceOffset + i * dimensions + d];
				double b = source[sourceOffset + (i + 1) * dimensions + d];
				target[targetOffset + i * dimensions + d] = a + t * (b - a);
			}
		}

		interpolate(dimensions, t, depth - 1, target, targetOffset, target, targetOffset + (depth - 1) * dimensions);
	}

	private static int triangularSum(int count) {
		int sum = 0;
		for (int i = 1; i <= count; i++) sum += i;
		return sum;
	}
}
