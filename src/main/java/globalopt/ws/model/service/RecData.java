/**
 * 
 */
package globalopt.ws.model.service;

import java.util.Comparator;

/**
 * @author stefano
 * 
 */
public class RecData {

	public class NormalComparator implements Comparator<RecData> {
		@Override
		public int compare(RecData rd1, RecData rd2) {
			if (rd1 == null)
				throw new IllegalArgumentException(
						"Illegal 'rd1' argument in NormalComparator.compare(RecData, RecData): " + rd1);
			if (rd2 == null)
				throw new IllegalArgumentException(
						"Illegal 'rd2' argument in NormalComparator.compare(RecData, RecData): " + rd2);
			return Double.compare(rd1.getNormal(), rd2.getNormal());
		}
	}

	public class ShortnameComparator implements Comparator<RecData> {
		@Override
		public int compare(RecData rd1, RecData rd2) {
			if (rd1 == null)
				throw new IllegalArgumentException(
						"Illegal 'rd1' argument in NormalComparator.compare(RecData, RecData): " + rd1);
			if (rd2 == null)
				throw new IllegalArgumentException(
						"Illegal 'rd2' argument in NormalComparator.compare(RecData, RecData): " + rd2);
			return rd1.getShortname().compareTo(rd2.getShortname());
		}
	}

	private String name;

	private double normal;

	private String shortname;

	private double value;

	public RecData(String shortname, String name, double value, double normal) {
		if (shortname == null || (shortname = shortname.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'shortname' exception in RecData(String, String, double, double): " + shortname);
		if (name == null || (name = name.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'name' exception in RecData(String, String, double, double): "
					+ name);
		if (normal != Double.NaN && (normal < 0.0 || normal > 1.0))
			throw new IllegalArgumentException(
					"Illegal 'normal' exception in RecData(String, String, double, double): " + normal);
		this.normal = normal;
		this.shortname = shortname;
		this.value = value;
		assert invariant() : "Illegal state in RecData(String, String, double, double)";
	}

	/**
	 * @return the normal
	 */
	public double getNormal() {
		assert invariant() : "Illegal state in RecData.getNormal()";
		return normal;
	}

	/**
	 * @return the shortname
	 */
	public String getName() {
		assert invariant() : "Illegal state in RecData.getName()";
		return name;
	}

	/**
	 * @return the shortname
	 */
	public String getShortname() {
		assert invariant() : "Illegal state in RecData.getShortname()";
		return shortname;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		assert invariant() : "Illegal state in RecData.getValue()";
		return value;
	}

	/**
	 * @return
	 */
	private boolean invariant() {
		return (0 <= normal && normal <= 1.0 && shortname != null && !shortname.isEmpty());
	}

}
