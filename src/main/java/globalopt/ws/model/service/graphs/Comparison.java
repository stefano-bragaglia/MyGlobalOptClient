/**
 * 
 */
package globalopt.ws.model.service.graphs;

import globalopt.ws.model.service.Objective;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;

import java.util.Arrays;

/**
 * @author stefano
 * 
 */
public class Comparison extends AbstractGraph {

	/**
	 * Default constructor.
	 * 
	 * @param obiectives
	 *            the objective functions
	 * @param results
	 *            the computed results
	 */
	public Comparison(Objective[] objectives, GlobalOptOutput[] scenarios) {
		super(objectives, scenarios);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.service.graphs.AbstractGraph#compute()
	 */
	@Override
	protected void compute() {
		int ol = objectives().length;
		int sl = scenarios().length;
		double[] mins = new double[ol];
		double[] maxs = new double[ol];
		double[][] matrix = new double[ol][sl];

		for (int o = 0; o < ol; o++)
			for (int s = 0; s < sl; s++) {
				matrix[o][s] = objectives()[o].extract(results()[s]);
				if (s == 0 || matrix[o][s] < mins[o])
					mins[o] = matrix[o][s];
				if (s == 0 || matrix[o][s] > maxs[o])
					maxs[o] = matrix[o][s];
			}

		double[][] normal = new double[ol][sl];
		for (int o = 0; o < ol; o++) {
			double den = maxs[o] - mins[o];
			for (int s = 0; s < scenarios().length; s++) {
				normal[o][s] = (matrix[o][s] - mins[o]) / den;
				if (objectives()[o].isMin())
					normal[o][s] = 1.0 - normal[o][s];
			}
		}

		String[] plans = new String[sl];
		for (int s = 0; s < sl; s++) {
			String[] items = new String[ol];
			for (int o = 0; o < ol; o++)
				items[o] = String.format("{ y : %f, ttip: %f, unit : '%s' }", //
						normal[o][s], matrix[o][s], objectives()[o].getUnit());
			plans[s] = String.format("{ name : %s, data : %s, pointPlacement : 'on' }", //
					scenarios()[s], Arrays.toString(items));
		}
		setSeries(Arrays.toString(plans));
	}

}
