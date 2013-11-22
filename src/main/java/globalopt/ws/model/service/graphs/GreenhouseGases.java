/**
 * 
 */
package globalopt.ws.model.service.graphs;

import globalopt.ws.model.Helper;
import globalopt.ws.model.service.Objective;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Emission;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author stefano
 * 
 */
public class GreenhouseGases extends AbstractGraph {

	public static final Emission[] GASES;

	static {
		int i = 0;
		GASES = new Emission[6];
		for (Emission emission : Emission.getEmissionsList(Helper.ENG)) {
			String name = emission.getName();
			if (name.equals("Carbon dioxide") || //
					name.equals("Methane") || //
					name.equals("Carbon monoxide") || //
					name.equals("Nitrogen oxides") || //
					name.equals("Sulphur oxides") || //
					name.equals("Nitrous oxide"))
				GASES[i++] = emission;
		}
	}

	/**
	 * Default constructor.
	 * 
	 * @param obiectives
	 *            the objective functions
	 * @param results
	 *            the computed results
	 */
	public GreenhouseGases(Objective[] objectives, GlobalOptOutput[] scenarios) {
		super(objectives, scenarios);
	}

	// private static double THOUSAND = 1000.0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.service.graphs.AbstractGraph#compute()
	 */
	@Override
	protected void compute() {
		int v, d = 0;
		int sl = scenarios().length;
		String[] values;
		String[] data = new String[GASES.length];
		Locale locale = results()[0].getMylocale();
		for (Emission emission : GASES) {
			v = 0;
			values = new String[sl];
			String name = emission.convertLocale(locale).getName();
			String unit = emission.getMeasurementUnit();
			for (GlobalOptOutput output : results()) {
				double value = output.getTheEmittedEmissions().getEmissions().get(emission.getShortName()).getValue();
				// if (unit.equals("microgTeq") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "milligTeq";
				// }
				// if (unit.equals("milligTeq") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "gTeq";
				// }
				// if (unit.equals("gTeq") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "kgTeq";
				// }
				// if (unit.equals("kgTeq") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "tTeq";
				// }
				// if (unit.equals("tTeq") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "qTeq";
				// }
				// if (unit.equals("g") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "kg";
				// }
				// if (unit.equals("kg") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "t";
				// }
				// if (unit.equals("t") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "kt";
				// }
				// if (unit.equals("kt") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "Mt";
				// }
				// if (unit.equals("Mt") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "Gt";
				// }
				// if (unit.equals("Gt") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "Tt";
				// }
				// if (unit.equals("Tt") && value >= THOUSAND) {
				// value /= THOUSAND;
				// unit = "Pt";
				// }
				values[v++] = String.format("{ y: %f, unit: '%s' }", value, unit);
			}
			data[d++] = String.format("{ name: '%s', data: %s }", name, Arrays.toString(values));
		}
		setSeries(Arrays.toString(data));
	}

}