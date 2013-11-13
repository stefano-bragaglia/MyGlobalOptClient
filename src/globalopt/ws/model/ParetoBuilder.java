/**
 * 
 */
package globalopt.ws.model;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.AbsoluteMaxBound;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.AbsoluteMinBound;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Constraint;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.GenericConstraint;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GOParetoInputParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author stefano
 * 
 */
public class ParetoBuilder {

	private static final String MINMAX = "min_max_source(";
	private static final String PAR = ")";

	/**
	 * The chosen constraints.
	 */
	private Constraint[] constraints;

	/**
	 * The chosen functions.
	 */
	private String[] functions;

	/**
	 * The chosen locale.
	 */
	private Locale locale;

	/**
	 * The chosen number of scenarios.
	 */
	private Integer scenarios;

	/**
	 * Default constructor.
	 */
	public ParetoBuilder() {
		this.clear();
		assert invariant() : "Illegal state in ParetoBuilder()";
	}

	/**
	 * Returns an instance of <code>GlobalOptInputParam</code> created from the
	 * fields set on this ParetoBuilder.
	 * 
	 * @return a <code>GlobalOptInputParam</code>
	 */
	public GOParetoInputParam build() {
		GOParetoInputParam result = new GOParetoInputParam();
		result.setcList(constraints);
		result.setObjList(functions);
		result.setLoc(locale);
		result.setNumRequestedSolutions(scenarios);
		assert invariant() : "Illegal state in InputParam.build()";
		return result;
	}

	/**
	 * Returns an InvokeBuilder out of this ParetoBuilder.
	 * 
	 * @return the InvokeBuilder out of this ParetoBuilder
	 */
	public InvokeBuilder stem() {
		InvokeBuilder result = new InvokeBuilder(locale, constraints);
		assert invariant() : "Illegal state in ParetoBuilder.build()";
		return result;
	}

	/**
	 * Resets the ParetoBuilder to its initial, empty state.
	 * 
	 * @return this ParetoBuilder
	 */
	public ParetoBuilder clear() {
		constraints = new Constraint[0];
		functions = new String[0];
		locale = new Locale("en", "US");
		scenarios = null;
		assert invariant() : "Illegal state in InputParetoParetoBuilder.clear()";
		return this;
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
	 * @return
	 */
	public String getFunctions() {
		if (functions == null)
			return "";
		int iMax = functions.length - 1;
		if (iMax == -1)
			return "";

		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(functions[i]);
			if (i == iMax)
				return b.toString();
			b.append("\n");
		}
	}

	/**
	 * Returns the locale.
	 * 
	 * @return the current locale
	 */
	public Locale getLocale() {
		assert invariant() : "Illegal state in ParetoBuilder.getLocale()";
		return locale;
	}

	/**
	 * Returns the number of scenarios.
	 * 
	 * @return the current number of scenarios locale
	 */
	public int getScenarios() {
		assert invariant() : "Illegal state in ParetoBuilder.getScenarios()";
		return scenarios;
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
	 * Sets the constraints.
	 * 
	 * @param desc
	 *            a string describing the constraints
	 * @return this ParetoBuilder
	 */
	public ParetoBuilder setConstraints(String desc) {
		// TODO More sophisticated parsers are required to cope with undesired
		// line breaks
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
		assert invariant() : "Illegal state in ParetoBuilder.setConstraints(String)";
		return this;
	}

	/**
	 * Sets the objective functions.
	 * 
	 * @param desc
	 *            a string describing the objective functions
	 * @return this builder
	 */
	public ParetoBuilder setFunctions(String desc) {
		// TODO More sophisticated parsers are required to cope with undesired
		// line breaks
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in ParetoBuilder.setFunctions(String): " + desc);
		try {
			functions = desc.split("\n");
			for (int i = 0; i < functions.length; i++)
				functions[i] = functions[i].trim();
		} catch (NullPointerException ex) {
			functions = new String[0];
		}
		assert invariant() : "Illegal state in InputParetoBuilder.setFunctions(String)";
		return this;
	}

	/**
	 * Sets the locale.
	 * 
	 * @param desc
	 *            a string describing the locale
	 * @return this ParetoBuilder
	 */
	public ParetoBuilder setLocale(String desc) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in ParetoBuilder.setLocale(String): " + desc);
		locale = new Locale("en", "US");
		if ("it".equals(desc))
			locale = new Locale("it", "IT");
		if ("en".equals(desc))
			locale = new Locale("en", "US");
		assert invariant() : "Illegal state in ParetoBuilder.setLocale(String)";
		return this;
	}

	/**
	 * Sets the number of scenarios.
	 * 
	 * @param desc
	 *            a string describing the number of scenarios
	 * @return this ParetoBuilder
	 */
	public ParetoBuilder setScenarios(String desc) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in ParetoBuilder.setScenarios(String): " + desc);
		try {
			scenarios = Integer.parseInt(desc);
		} catch (NumberFormatException ex) {
		}
		assert invariant() : "Illegal state in ParetoBuilder.setScenarios(String)";
		return this;
	}

}
