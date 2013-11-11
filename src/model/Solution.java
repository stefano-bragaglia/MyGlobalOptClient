/**
 * 
 */
package model;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GOParetoInputParam;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GOParetoOutput;

import java.util.Arrays;

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
	 * The output of the Global Optimiser web service.
	 */
	private GOParetoOutput output;

	/**
	 * The params for the Global Optimiser web service.
	 */
	private GOParetoInputParam params;

	/**
	 * Default constructor.
	 * 
	 * @param time
	 *            the computing time of the query
	 */
	public Solution(GOParetoInputParam params, GOParetoOutput output, long time) {
		if (params == null)
			throw new IllegalArgumentException(
					"Illegal 'params' argument in Solution(GOParetoInputParam, GOParetoOutput, long): " + params);
		if (output == null)
			throw new IllegalArgumentException(
					"Illegal 'output' argument in Solution(GOParetoInputParam, GOParetoOutput, long): " + output);
		if (time < 0)
			throw new IllegalArgumentException(
					"Illegal 'time' argument in Solution(GOParetoInputParam, GOParetoOutput, long): " + time);
		this.params = params;
		this.output = output;
		this.time = time;
		assert invariant() : "Illegal state in Solution(GOParetoInputParam, GOParetoOutput, long)";
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
	 * 
	 */
	private String[] dimensions;

	/**
	 * @return
	 */
	public String getDimensions() {
		if (dimensions == null) {
			dimensions = params.getObjList();
//			for (int i = 0; i < dimensions.length; i++) {
//				if ((dimensions[i].startsWith("max(") || dimensions[i].startsWith("min("))
//						&& dimensions[i].endsWith(")"))
//					dimensions[i] = dimensions[i].substring("max(".length(), dimensions[i].length() - 1).trim();
//				if ((dimensions[i].startsWith("rec(") || dimensions[i].startsWith("ric("))
//						&& dimensions[i].endsWith(")"))
//					dimensions[i] = Receptor.getReceptorByShortName(
//							dimensions[i].substring("max(".length(), dimensions[i].length() - 1).trim(),
//							params.getLoc()).getName();
//				dimensions[i] = "'" + dimensions[i] + "'";
//			}
			// [ 'rec(9)', 'rec(2)', 'cost', 'rec(10)', 'rec(5)' ],
		}
		assert invariant() : "Illegal state in Solution.getDimensions()";
		return Arrays.toString(dimensions);
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
		return (output != null && time >= 0);
	}

}
