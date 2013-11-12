/**
 * 
 */
package model.service.graphs;

import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;

import java.util.Arrays;

import model.service.Objective;

/**
 * @author stefano
 * 
 */
public abstract class AbstractGraph implements Graph {

	/**
	 * The string with the categories.
	 */
	private String categories;

	/**
	 * The string with the scenarios.
	 */
	private String names;

	/**
	 * The objective functions.
	 */
	private Objective[] objectives;

	/**
	 * The computed results.
	 */
	private GlobalOptOutput[] results;

	/**
	 * The desired scenarios.
	 */
	private String[] scenarios;

	/**
	 * The string with the series.
	 */
	private String series;

	/**
	 * Default constructor.
	 * 
	 * @param obiectives
	 *            the objective functions
	 * @param results
	 *            the computed results
	 */
	public AbstractGraph(Objective[] objectives, GlobalOptOutput[] results) {
		if (objectives == null || objectives.length < 1)
			throw new IllegalArgumentException(
					"Illegal 'objectives' argument in AbstractGraph(Objective[], GlobalOptOutput[]): " + objectives);
		if (results == null || results.length < objectives.length)
			throw new IllegalArgumentException(
					"Illegal 'results' argument in AbstractGraph(Objective[], GlobalOptOutput[]): " + results);
		this.scenarios = new String[results.length];
		for (int i = 0; i < results.length; i++)
			this.scenarios[i] = "\"" + (i + 1) + ". " + (i < objectives.length ? objectives[i].getName() : "Scenario") + "\"";
		this.objectives = objectives;
		this.results = results;
		assert invariant() : "Illegal state in AbstractGraph(Objective[], GlobalOptOutput[])";
	}

	/**
	 * Computes the data to display.
	 */
	protected abstract void compute();

	/* (non-Javadoc)
	 * @see model.service.graphs.GRaph#getCategories()
	 */
	@Override
	public String getCategories() {
		if (categories == null)
			categories = Arrays.toString(objectives);
		assert invariant() : "Illegal state in AbstractGraph.getCategories()";
		return categories;
	}

	/* (non-Javadoc)
	 * @see model.service.graphs.GRaph#getNames()
	 */
	@Override
	public String getNames() {
		if (names == null)
			names = Arrays.toString(scenarios);
		assert invariant() : "Illegal state in AbstractGraph.getNames()";
		return names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.service.graphs.Graph#getSeries()
	 */
	@Override
	public String getSeries() {
		if (series == null)
			compute();
		assert invariant() : "Illegal state in AbstractGraph.getSeries()";
		return series;
	}

	/**
	 * Invariant check.
	 * 
	 * @return <code>true</code> if the state is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean invariant() {
		return (objectives != null && objectives.length > 0 //
				&& results != null && results.length >= objectives.length //
				&& scenarios != null && scenarios.length == results.length);
	}

	/**
	 * Returns the objective functions.
	 * 
	 * @return the objective functions
	 */
	protected Objective[] objectives() {
		assert invariant() : "Illegal state in AbstractGraph.objectives()";
		return objectives;
	}

	/**
	 * Returns the computed results.
	 * 
	 * @return the computed results
	 */
	protected GlobalOptOutput[] results() {
		assert invariant() : "Illegal state in AbstractGraph.results()";
		return results;
	}

	/**
	 * Returns the desired scenarios.
	 * 
	 * @return the desired scenarios
	 */
	protected String[] scenarios() {
		assert invariant() : "Illegal state in AbstractGraph.scenarios()";
		return scenarios;
	}

	/**
	 * Sets the string with the series.
	 * 
	 * @param series
	 *            the string with the series
	 */
	protected void setSeries(String series) {
		if (series == null || (series = series.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'series' argument in AbstractGraph.setSeries(String): "
					+ series);
		this.series = series;
		assert invariant() : "Illegal state in AbstractGraph.setSeries(String)";
	}

}
