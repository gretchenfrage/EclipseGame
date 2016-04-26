package com.phoenixkahlo.eclipse;

/**
 * Static class of mathematics utilities.
 */
public class MathUtils {
	
	private MathUtils() {}

	public static double max(double[] arr) {
		double max = -Double.MAX_VALUE;
		for (double i : arr) {
			if (i > max) max = i;
		}
		return max;
	}

	public static double min(double[] arr) {
		double min = Double.MAX_VALUE;
		for (double i : arr) {
			if (i < min) min = i;
		}
		return min;
	}
	
	public static double roundDown(double n, double multiple) {
		return n > 0 ? n - n % multiple : n - multiple - n % multiple;
	}
	
}
