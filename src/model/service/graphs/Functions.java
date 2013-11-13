/**
 * 
 */
package model.service.graphs;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.PrimaryActivity;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.PrimaryActivity.ActivityType;
import it.unibo.ai.ePolicy.GlobalOpt.IO.GlobalOptMultipleValue;
import it.unibo.ai.ePolicy.GlobalOpt.IO.GlobalOptSingleValue;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import model.service.Objective;

/**
 * @author stefano
 * 
 */
public class Functions extends AbstractGraph implements Graph {

	/**
	 * 
	 */
	private int index;

	/**
	 * @param objectives
	 * @param results
	 */
	public Functions(Objective[] objectives, int index, GlobalOptOutput[] results) {
		super(objectives, results);
		if (index < 0 || index >= objectives.length)
			throw new IndexOutOfBoundsException(
					"Index 'index' out of bounds in Function(Objective[], int, GlobalOptOutput[]): " + index);
		this.index = index;
		assert invariant() : "Illegal state in Function(Objective[], int, GlobalOptOutput[])";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.service.graphs.AbstractGraph#compute()
	 */
	@Override
	protected void compute() {
		int sl = scenarios().length;
		double[] array = new double[sl];

		Locale locale = results()[0].getMylocale();

		for (int s = 0; s < sl; s++) {
			array[s] = 0.0;
			Map<String, GlobalOptMultipleValue> costs = results()[s].getCosts().getCosts();
			Map<String, GlobalOptSingleValue> assignments = results()[s].getEnergySources().getAssignments();

			switch (objectives()[index].kind()) {
			case COST:
				if (costs != null)
					for (GlobalOptMultipleValue values : costs.values())
						if (values != null)
							for (GlobalOptSingleValue value : values.getValues().values())
								if (value != null)
									array[s] += value.getValue();
				break;
			case ELECTRIC:
				if (assignments != null)
					for (String key :assignments.keySet())
						if (ActivityType.ELECTRIC.equals(PrimaryActivity.getPrimaryActivity(key, locale).getActivityType())) {
							GlobalOptSingleValue value = assignments.get(key);
							if (value != null)
								array[s] += value.getValue();
						}
				break;
			case RECEPTOR:
				array[s] += results()[s].getImpacts().computeTotalRecByShortName(objectives()[index].getId(), locale);
				break;
			case THERMAL:
				if (assignments != null)
					for (String key :assignments.keySet())
						if (ActivityType.THERMAL.equals(PrimaryActivity.getPrimaryActivity(key, locale).getActivityType())) {
							GlobalOptSingleValue value = assignments.get(key);
							if (value != null)
								array[s] += value.getValue();
						}
				break;
			case UNKNOWN:
				array[s] = Double.NaN;
				break;
			default:
				array[s] = Double.NaN;
				break;
			}
		}
		String[] plans = new String[1];
		plans[0] = String
				.format("{ name : \"%s\", data : %s }", objectives()[index].getLabel(), Arrays.toString(array));
		setSeries(Arrays.toString(plans));
	}

	private boolean invariant() {
		return (0 <= index && index < objectives().length);
	}

}
