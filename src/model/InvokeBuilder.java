/**
 * 
 */
package model;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.Constraint;

import java.util.Locale;

import javax.servlet.ServletContext;

/**
 * @author stefano
 * 
 */
public class InvokeBuilder {

	private static final String MINMAX = "min_max_source(";
	private static final String PAR = ")";

	/**
	 * The chosen constraints.
	 */
	private Constraint[] constraints;

	/**
	 * The HTTP servlet context.
	 */
	private ServletContext context;

	/**
	 * The chosen function.
	 */
	private String function;

	/**
	 * The chosen locale.
	 */
	private Locale locale;

	/**
	 * Default constructor.
	 * 
	 * @param context
	 */
	public InvokeBuilder(ServletContext context) {
		if (context == null)
			throw new IllegalArgumentException("Illegal 'context' argument in InputBuilder(ServletContext): " + context);
		this.context = context;
		this.clear();
		assert invariant() : "Illegal state in InputBuilder(HttpServletRequest)";
	}

	protected InvokeBuilder(ServletContext context, Locale locale, Constraint[] constraints) {
		this.context = context;
		// TODO altri settaggi, flag per salvarli
		this.clear();
		assert invariant() : "Illegal state in InputBuilder(HttpServletRequest, Locale, String[])";
	}

	/**
	 * Resets the InvokeBuilder to its initial, empty state.
	 * 
	 * @return this InvokeBuilder
	 */
	public InvokeBuilder clear() {
		constraints = new Constraint[0];
		function = null;
		locale = new Locale("en", "US");
		assert invariant() : "Illegal state in InvokeBuilder.clear()";
		return this;
	}

	/**
	 * Returns the objective function.
	 * 
	 * @return the current objective function
	 */
	public String getFunction() {
		assert invariant() : "Illegal state in InvokeBuilder.getFunction()";
		return function;
	}

	/**
	 * Invariant check.
	 * 
	 * @return <code>true</code> if the state is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean invariant() {
		return (context != null);
	}

	/**
	 * Sets the objective function.
	 * 
	 * @param desc
	 *            a string describing the objective function
	 * @return this ParetoBuilder
	 */
	public InvokeBuilder setFunction(String desc) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in InvokeBuilder.setFunction(String): " + desc);
		context.log("> function: '" + desc + "'");
		this.function = desc;
		assert invariant() : "Illegal state in InvokeBuilder.setFunction(String)";
		return this;
	}

}
