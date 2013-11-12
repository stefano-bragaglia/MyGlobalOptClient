/**
 * 
 */
package model.service.graphs;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.PrimaryActivity;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.PrimaryActivity.ActivityType;
import it.unibo.ai.ePolicy.GlobalOpt.IO.GlobalOptSingleValue;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;

import java.util.Arrays;
import java.util.Map;

import model.service.Objective;

/**
 * @author stefano
 * 
 */
public class Electrics extends AbstractGraph implements Graph {

	/**
	 * Default constructor.
	 * 
	 * @param obiectives
	 *            the objective functions
	 * @param results
	 *            the computed results
	 */
	public Electrics(Objective[] objectives, GlobalOptOutput[] results) {
		super(objectives, results);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.service.graphs.AbstractGraph#compute()
	 */
	@Override
	protected void compute() {
		int il = 0;
		PrimaryActivity[] activities = PrimaryActivity.getPrimaryActivityList(results()[0].getMylocale());
		String[] invoices = new String[activities.length];
		for (PrimaryActivity activity : activities)
			if (activity.getActivityType().equals(ActivityType.ELECTRIC))
				invoices[il++] = activity.getName();
		invoices = Arrays.copyOf(invoices, il);
		Arrays.sort(invoices);

		int sl = scenarios().length;
		double[][] matrix = new double[il][sl];

		for (int s = 0; s < sl; s++) {
			Map<String, GlobalOptSingleValue> values = results()[s].getEnergySources().getAssignments();
			if (values != null)
				for (int i = 0; i < il; i++) {
					matrix[i][s] = 0.0;
					GlobalOptSingleValue value = values.get(invoices[i]);
					if (value != null)
						matrix[i][s] = value.getValue();
				}
		}

		String[] plans = new String[il];
		for (int i = 0; i < il; i++)
			plans[i] = String.format("{ name : \"%s\", data : %s }", invoices[i], Arrays.toString(matrix[i]));
		setSeries(Arrays.toString(plans));
	}

}
