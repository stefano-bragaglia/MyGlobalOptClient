/**
 * 
 */
package model;

/**
 * @author stefano
 * 
 */
public class Solution {

	/**
	 * The duration of the last computation.
	 */
	private String duration;

	/**
	 * The computing time of the query.
	 */
	private long time;

	/**
	 * Default constructor.
	 * 
	 * @param time
	 *            the computing time of the query
	 */
	public Solution(long time) {
		if (time < 0)
			throw new IllegalArgumentException("Illegal 'time' argument in Solution(long): " + time);
		this.time = time;
		assert invariant() : "Illegal state in Solution(long)";
	}

	/**
	 * Returns the duration of the last computation.
	 * 
	 * @return the duration of the last computation
	 */
	public String getDuration() {
		if (duration == null) {
			long hours = (time / 3600000) % 24;
			long minutes = (time / 60000) % 60;
			long seconds = (time / 1000) % 60;
			long millis = time % 1000;
			duration = millis + "ms";
			if (seconds > 0 || minutes > 0 || hours > 0)
				duration = seconds + "s " + duration;
			if (minutes > 0 || hours > 0)
				duration = minutes + "m " + duration;
			if (hours > 0)
				duration = hours + "h " + duration;
		}
		assert invariant() : "Illegal state in Solution.getDuration()";
		return duration;
	}

	/**
	 * Returns the computing time of the query.
	 * 
	 * @return the computing time of the query
	 */
	public long getTime() {
		assert invariant() : "Illegal state in Solution.getTime()";
		return time;
	}

	/**
	 * Invariant check.
	 * 
	 * @return <code>true</code> if the state is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean invariant() {
		return (time >= 0);
	}

}
