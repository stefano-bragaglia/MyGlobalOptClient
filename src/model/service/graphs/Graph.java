package model.service.graphs;

public interface Graph {

	/**
	 * Returns the string with the categories.
	 * 
	 * @return the string with the categories
	 */
	public abstract String getCategories();

	/**
	 * Returns the string with the scenarios.
	 * 
	 * @return the string with the scenarios
	 */
	public abstract String getNames();

	/**
	 * Returns the string with the series.
	 * 
	 * @return the string with the series
	 */
	public abstract String getSeries();

}