/**
 * 
 */
package globalopt.ws.model.service.graphs;

import globalopt.ws.model.service.Objective;
import it.unibo.ai.ePolicy.GlobalOpt.IO.GlobalOptMultipleValue;
import it.unibo.ai.ePolicy.GlobalOpt.IO.GlobalOptSingleValue;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;

import java.util.Arrays;
import java.util.Map;

/**
 * @author stefano
 * 
 */
public class Costs extends AbstractGraph implements Graph {

	/**
	 * Default constructor.
	 * 
	 * @param obiectives
	 *            the objective functions
	 * @param results
	 *            the computed results
	 */
	public Costs(Objective[] objectives, GlobalOptOutput[] results) {
		super(objectives, results);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.service.graphs.AbstractGraph#compute()
	 */
	@Override
	protected void compute() {
		String[] invoices = results()[0].getCosts().getCosts().keySet().toArray(new String[0]);
		Arrays.sort(invoices);

		int sl = scenarios().length;
		int il = invoices.length;
		double[][] matrix = new double[il][sl];

		for (int s = 0; s < sl; s++) {
			Map<String, GlobalOptMultipleValue> costs = results()[s].getCosts().getCosts();
			for (int i = 0; i < il; i++) {
				matrix[i][s] = 0.0;
				Map<String, GlobalOptSingleValue> values = costs.get(invoices[i]).getValues();
				if (values != null)
					for (GlobalOptSingleValue item : values.values())
						matrix[i][s] += item.getValue();
			}
		}

		String[] plans = new String[il];
		for (int i = 0; i < il; i++)
			plans[i] = String.format("{ name : \"%s\", data : %s }", invoices[i], Arrays.toString(matrix[i]));
		setSeries(Arrays.toString(plans));
	}
}
