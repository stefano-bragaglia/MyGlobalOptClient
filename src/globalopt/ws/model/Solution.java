/**
 * 
 */
package globalopt.ws.model;

import globalopt.ws.model.service.Objective;
import globalopt.ws.model.service.graphs.Comparison;
import globalopt.ws.model.service.graphs.Costs;
import globalopt.ws.model.service.graphs.Electrics;
import globalopt.ws.model.service.graphs.Functions;
import globalopt.ws.model.service.graphs.Graph;
import globalopt.ws.model.service.graphs.Thermals;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GOParetoInputParam;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GOParetoOutput;

import java.util.Arrays;

/**
 * @author stefano
 * 
 */
public class Solution {

	private String[] boundaries;

	private Graph comparison;

	private Graph costs;

	/**
	 * The duration of the last computation.
	 */
	private String duration;

	private Graph electrics;

	private Graph[] functions;

	/**
	 * 
	 */
	private Objective[] objectives;

	/**
	 * The output of the Global Optimiser web service.
	 */
	private GOParetoOutput output;

	/**
	 * The params for the Global Optimiser web service.
	 */
	private GOParetoInputParam params;

	private String[] names;

	private Graph thermals;

	/**
	 * The computing time of the query.
	 */
	private long time;

	private String[] transitionals;

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

	public String getBoundaries() {
		loadBoundaries();
		assert invariant() : "Illegal state in Solution.getBoundaries()";
		return Arrays.toString(boundaries);
	}

	/**
	 * Returns the utility for the comparison graph.
	 * 
	 * @return the utility for the comparison graph
	 */
	public Graph getComparison() {
		if (comparison == null) {
			loadObjectives();
			comparison = new Comparison(objectives, output.getPlansList());
		}
		assert invariant() : "Illegal state in Solution.getComparison()";
		return comparison;
	}

	/**
	 * Returns the utility for the costs graph.
	 * 
	 * @return the utility for the costs graph
	 */
	public Graph getCosts() {
		if (costs == null) {
			loadObjectives();
			costs = new Costs(objectives, output.getPlansList());
		}
		assert invariant() : "Illegal state in Solution.getCosts()";
		return costs;
	}

	/**
	 * @return
	 */
	public String getDimensions() {
		loadObjectives();
		assert invariant() : "Illegal state in Solution.getDimensions()";
		return Arrays.toString(objectives);
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
	 * Returns the utility for the electrics graph.
	 * 
	 * @return the utility for the electrics graph
	 */
	public Graph getElectrics() {
		if (electrics == null) {
			loadObjectives();
			electrics = new Electrics(objectives, output.getPlansList());
		}
		assert invariant() : "Illegal state in Solution.getElectrics()";
		return electrics;
	}

	public Graph getFunction(int index) {
		loadObjectives();
		if (index < 0 || index >= objectives.length)
			throw new IndexOutOfBoundsException("Index 'index' out of bounds in Solution.getFunction(int):" + index);
		if (functions == null)
			functions = new Graph[objectives.length];
		if (functions[index] == null)
			functions[index] = new Functions(objectives, index, output.getPlansList());
		assert invariant() : "Illegal state in Solution.getFunction(int)";
		return functions[index];
	}

	public String getScenarios() {
		loadScenarios();
		assert invariant() : "Illegal state in Solution.getScenarios()";
		return Arrays.toString(names);
	}

	/**
	 * Returns the utility for the thermics graph.
	 * 
	 * @return the utility for the thermics graph
	 */
	public Graph getThermals() {
		if (thermals == null) {
			loadObjectives();
			thermals = new Thermals(objectives, output.getPlansList());
		}
		assert invariant() : "Illegal state in Solution.getThermals()";
		return thermals;
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

	public String getTransitionals() {
		loadTransitionals();
		assert invariant() : "Illegal state in Solution.getTransitionals()";
		return Arrays.toString(transitionals);
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

	private void loadBoundaries() {
		loadObjectives();
		if (boundaries == null) {
			boundaries = new String[objectives.length];
			for (int i = 0; i < boundaries.length; i++)
				boundaries[i] = (i + 1) + ". " + objectives[i].getName();
		}
		assert invariant() : "Illegal state in Solution.loadBoundaries()";
	}

	private void loadObjectives() {
		if (objectives == null) {
			int i = 0;
			objectives = new Objective[params.getObjList().length];
			for (String obj : params.getObjList())
				objectives[i++] = new Objective(obj, params.getLoc());
		}
		assert invariant() : "Illegal state in Solution.loadObjectives()";
	}

	private void loadScenarios() {
		loadBoundaries();
		loadTransitionals();
		if (names == null) {
			names = new String[boundaries.length + transitionals.length];
			int i = 0;
//			for (String boundary : boundaries)
//				names[i++] = "\"" + boundary + "\"";
//			for (String transitional : transitionals)
//				names[i++] = "\"" + transitional + "\"";
			for (String boundary : boundaries)
				names[i++] = boundary;
			for (String transitional : transitionals)
				names[i++] = transitional;
		}
		assert invariant() : "Illegal state in Solution.loadScenarios()";
	}

	private void loadTransitionals() {
		if (transitionals == null) {
			int b = params.getObjList().length;
			transitionals = new String[output.getPlansList().length - b];
			for (int i = 0; i < transitionals.length; i++)
				transitionals[i] = (i + b) + ". Scenario";
		}
		assert invariant() : "Illegal state in Solution.loadTransitionals()";
	}

	/**
	 * @return
	 */
	public Objective[] objectives() {
		loadObjectives();
		assert invariant() : "Illegal state in Solution.objectives()";
		return objectives;
	}

	public String[] names() {
		loadScenarios();
		assert invariant() : "Illegal state in Solution.names()";
		return names;
	}

	public int dimensions() {
		loadObjectives();
		assert invariant() : "Illegal state in Solution.dimensions()";
		return objectives.length;
	}

	public int scenarios() {
		loadScenarios();
		assert invariant() : "Illegal state in Solution.scenarios()";
		return names.length;
	}

}
