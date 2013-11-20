/**
 * 
 */
package globalopt.ws.model.service;

/**
 * @author stefano
 * 
 */
public class Range {

	private double min;

	private double max;

	public Range(double min, double max) {
		if (max < min)
			throw new IllegalArgumentException("Illegal arguments exception in Range(double, double): " + min + "-"
					+ max);
		this.min = min;
		this.max = max;
		assert invariant() : "Illegal state in Range(double, double)";
	}

	private boolean invariant() {
		return (min <= max);
	}

	public double getMin() {
		assert invariant() : "Illegal state in Range.getMin()";
		return min;
	}

	public double getMax() {
		assert invariant() : "Illegal state in Range.getMax()";
		return max;
	}

	public double getDelta() {
		double result = max - min;
		assert invariant() : "Illegal state in Range.getDelta()";
		return result;
	}

}
