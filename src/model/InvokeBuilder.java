/**
 * 
 */
package model;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.AbsoluteMaxBound;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.AbsoluteMinBound;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Constraint;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.GenericConstraint;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GlobalOptInputParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author stefano
 * 
 */
public class InvokeBuilder {

	private static final String MINMAX = "min_max_source(";
	private static final String PAR = ")";

	/**
	 * The chosen budget.
	 */
	private Double budget;

	/**
	 * The chosen constraints.
	 */
	private Constraint[] constraints;

	/**
	 * A flag to remember
	 */
	private boolean derivate;

	/**
	 * The chosen electrical outcome.
	 */
	private Double electricalOutcome;

	/**
	 * The chosen function.
	 */
	private String function;

	/**
	 * The chosen locale.
	 */
	private Locale locale;

	/**
	 * The chosen thermal outcome.
	 */
	private Double thermalOutcome;

	/**
	 * Default constructor.
	 */
	public InvokeBuilder() {
		this.derivate = false;
		this.clear();
		assert invariant() : "Illegal state in InvokeBuilder()";
	}

	protected InvokeBuilder(Locale locale, Constraint[] constraints) {
		if (locale == null)
			throw new IllegalArgumentException("Illegal 'locale' argument in InvokeBuilder(Locale, Constraint[]): "
					+ locale);
		if (constraints == null)
			throw new IllegalArgumentException(
					"Illegal 'constraints' argument in InvokeBuilder(Locale, Constraint[]): " + constraints);
		this.constraints = constraints;
		this.derivate = true;
		this.locale = locale;
		this.clear();
		assert invariant() : "Illegal state in InvokeBuilder(HttpServletRequest, Locale, String[])";
	}

	/**
	 * Returns an instance of <code>GlobalOptInputParam</code> created from the
	 * fields set on this builder.
	 * 
	 * @return a <code>GlobalOptInputParam</code>
	 */
	public GlobalOptInputParam build() {
		GlobalOptInputParam result = new GlobalOptInputParam();
		result.setBudget(budget);
		result.setcList(constraints);
		result.setElectricalOutcome(electricalOutcome);
		result.setOptFunction(function);
		result.setLoc(locale);
		result.setThermalOutcome(thermalOutcome);
		assert invariant() : "Illegal state in InputParam.build()";
		return result;
	}

	/**
	 * Resets the InvokeBuilder to its initial, empty state.
	 * 
	 * @return this InvokeBuilder
	 */
	public InvokeBuilder clear() {
		if (!derivate) {
			constraints = new Constraint[0];
			locale = new Locale("en", "US");
		}
		budget = null;
		function = null;
		electricalOutcome = null;
		thermalOutcome = null;
		assert invariant() : "Illegal state in InvokeBuilder.clear()";
		return this;
	}

	/**
	 * Returns the budget.
	 * 
	 * @return the current budget
	 */
	public Double getBudget() {
		assert invariant() : "Illegal state in InvokeBuilder.getBudget()";
		return budget;
	}

	/**
	 * Returns the objective functions.
	 * 
	 * @return the current objective functions (as a <code>String</code>)
	 */
	public String getConstraints() {
		if (constraints == null)
			return "";
		int iMax = constraints.length - 1;
		if (iMax == -1)
			return "";

		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(constraints[i]);
			if (i == iMax)
				return b.toString();
			b.append("\n");
		}
	}

	/**
	 * Returns the electrical outcome.
	 * 
	 * @return the current electrical outcome
	 */
	public Double getElectricalOutcome() {
		assert invariant() : "Illegal state in InvokeBuilder.getElectricalOutcome()";
		return electricalOutcome;
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
	 * Returns the locale.
	 * 
	 * @return the current locale
	 */
	public Locale getLocale() {
		assert invariant() : "Illegal state in InvokeBuilder.getLocale()";
		return locale;
	}

	/**
	 * Returns the thermal outcome.
	 * 
	 * @return the current thermal outcome
	 */
	public Double getThermalOutcome() {
		assert invariant() : "Illegal state in InvokeBuilder.getThermalOutcome()";
		return thermalOutcome;
	}

	/**
	 * Invariant check.
	 * 
	 * @return <code>true</code> if the state is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean invariant() {
		return (true);
	}

	/**
	 * Sets the budget.
	 * 
	 * @param desc
	 *            a string describing the budget
	 * @return this InvokeBuilder
	 */
	public InvokeBuilder setBudget(String desc) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in InvokeBuilder.setBudget(String): " + desc);
		try {
			budget = Double.parseDouble(desc);
		} catch (NumberFormatException ex) {
			budget = null;
		}
		assert invariant() : "Illegal state in InvokeBuilder.setBudget(String)";
		return this;
	}

	/**
	 * Sets the electrical outcome.
	 * 
	 * @param desc
	 *            a string describing the electrical outcome
	 * @return this InvokeBuilder
	 */
	public InvokeBuilder setElectricalOutcome(String desc) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'desc' argument in InvokeBuilder.setElectricalOutcome(String): " + desc);
		try {
			electricalOutcome = Double.parseDouble(desc);
		} catch (NumberFormatException ex) {
			electricalOutcome = null;
		}
		assert invariant() : "Illegal state in InvokeBuilder.setElectricalOutcome(String)";
		return this;
	}

	/**
	 * Sets the objective function.
	 * 
	 * @param desc
	 *            a string describing the objective function
	 * @return this InvokeBuilder
	 */
	public InvokeBuilder setFunction(String desc) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in InvokeBuilder.setFunction(String): " + desc);
		this.function = desc;
		assert invariant() : "Illegal state in InvokeBuilder.setFunction(String)";
		return this;
	}

	/**
	 * Sets the thermal outcome.
	 * 
	 * @param desc
	 *            a string describing the thermal outcome
	 * @return this InvokeBuilder
	 */
	public InvokeBuilder setThermalOutcome(String desc) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in InvokeBuilder.setThermalOutcome(String): "
					+ desc);
		try {
			thermalOutcome = Double.parseDouble(desc);
		} catch (NumberFormatException ex) {
			thermalOutcome = null;
		}
		assert invariant() : "Illegal state in InvokeBuilder.setThermalOutcome(String)";
		return this;
	}

	/**
	 * Sets the locale.
	 * 
	 * @param desc
	 *            a string describing the locale
	 * @return this InvokeBuilder
	 */
	public InvokeBuilder setLocale(String desc) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in InvokeBuilder.setLocale(String): " + desc);
		if (!derivate) {
			locale = new Locale("en", "US");
			if ("it".equals(desc))
				locale = new Locale("it", "IT");
			if ("en".equals(desc))
				locale = new Locale("en", "US");
		}
		assert invariant() : "Illegal state in InvokeBuilder.setLocale(String)";
		return this;
	}

	/**
	 * Sets the constraints.
	 * 
	 * @param desc
	 *            a string describing the constraints
	 * @return this InvokeBuilder
	 */
	public InvokeBuilder setConstraints(String desc) {
		// TODO More sophisticated parsers are required to cope with undesired
		// line breaks
		if (!derivate) {
			List<Constraint> list = new ArrayList<Constraint>();
			if (desc != null && !(desc = desc.trim()).isEmpty()) {
				String[] lines = desc.split("\n");
				for (String line : lines) {
					line = line.trim();
					if (line.startsWith(MINMAX) && line.endsWith(PAR)) {
						String params[] = line.substring(MINMAX.length(), line.length() - PAR.length()).split(",");
						String key = params[0].trim();
						key = key.substring(1, key.length() - 1);
						double min = Double.parseDouble(params[1].trim());
						double max = Double.parseDouble(params[2].trim());
						list.add(new AbsoluteMinBound(key, locale, min));
						list.add(new AbsoluteMaxBound(key, locale, max));
					} else
						list.add(new GenericConstraint(line));
				}
			}
			constraints = list.toArray(new Constraint[list.size()]);
		}
		assert invariant() : "Illegal state in InvokeBuilder.setConstraints(String)";
		return this;
	}

}
