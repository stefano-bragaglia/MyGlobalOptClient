/**
 * 
 */
package globalopt.ws.model;

import globalopt.ws.model.service.Objective;
import globalopt.ws.model.service.graphs.Comparison;
import globalopt.ws.model.service.graphs.Costs;
import globalopt.ws.model.service.graphs.Electrics;
import globalopt.ws.model.service.graphs.Functions;
import globalopt.ws.model.service.graphs.Graph;
import globalopt.ws.model.service.graphs.Thermals;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Emission;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.IO.GlobalOptMultipleValue;
import it.unibo.ai.ePolicy.GlobalOpt.IO.GlobalOptSingleValue;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GOParetoInputParam;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.DetailedCosts;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.EmittedEmissions;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.EnergyAssignments;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GOParetoOutput;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.TotalCosts;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

/**
 * @author stefano
 * 
 */
public class Solution {
	
//	private static final 
//	
//	1. 'Limitaz.subsidenza e stabilita` falde': -6179.706528397277 
//	1. 'Limitaz.subsidenza e stabilita` falde': 1298.6177968749998 
//	2. 'Stabilita` di versanti e scarpate': -314529.68046351173 
//	2. 'Stabilita` di versanti e scarpate': -2786.1958937499994 
//	3. 'Stabilita` di litorali o fondali mare': -37043.1857098924 
//	3. 'Stabilita` di litorali o fondali mare': -183.05586875000003 
//	4. 'Stabilita` di rive o alvei fluviali': -130433.2301006342 
//	4. 'Stabilita` di rive o alvei fluviali': -1096.6462500000005 
//	5. 'Qualita` pedologica di suoli': -305557.5722811347 
//	5. 'Qualita` pedologica di suoli': -2899.6204437499996 
//	6. 'Qualita` mare': -189023.0388695859 
//	6. 'Qualita` mare': -1357.8259375000002 
//	7. 'Qualita` acque interne superficiali': -329369.4203612669 
//	7. 'Qualita` acque interne superficiali': -2333.7355374999993 
//	8. 'Qualita` acque sotterranee': -459370.35666489246 
//	8. 'Qualita` acque sotterranee': -3671.23493125 
//	9. 'Qualita` atmosfera': -424068.2229526892 
//	9. 'Qualita` atmosfera': 73.14381250000073 
//	10. 'Qualita` clima': -154583.90628334114 
//	10. 'Qualita` clima': 2923.720481249999 
//	11. 'Benessere vegetazione terrestre': -651403.2122003649 
//	11. 'Benessere vegetazione terrestre': -4845.85731875 
//	12. 'Benessere fauna terrestre': -862655.8001683186 
//	12. 'Benessere fauna terrestre': -7144.56509375 
//	13. 'Beness.biocenosi acquatic. e palustri': -676752.3578627767 
//	13. 'Beness.biocenosi acquatic. e palustri': -5367.547524999998 
//	14. 'Benessere e salute uomo': -32410.868368519634 
//	14. 'Benessere e salute uomo': 12714.614037500001 
//	15. 'Qualita` di paesaggi sensibili': -898461.7415429621 
//	15. 'Qualita` di paesaggi sensibili': -8557.805681250002 
//	16. 'Valore beni culturali e/o storici': -561651.3476453229 
//	16. 'Valore beni culturali e/o storici': -5948.834937499999 
//	17. 'Accessibilita` di risorse per lo svago': -100973.66962237039 
//	17. 'Accessibilita` di risorse per lo svago': -114.71320312499998 
//	18. 'Disponibilita` risorse idriche': -431061.0537254023 
//	18. 'Disponibilita` risorse idriche': -3166.68959375 
//	19. 'Disponibilita` agronomica di suoli fertili': -376675.02008478047 
//	19. 'Disponibilita` agronomica di suoli fertili': -3429.1998812500005 
//	20. 'Disponibilita` risorse litoidi': 740.2063062499999 
//	20. 'Disponibilita` risorse litoidi': 76969.6279755807 
//	21. 'Disponibilita` energia': -15711.461196872733 
//	21. 'Disponibilita` energia': 3559.836125 
//	22. 'Disponibilita` risorse produttive': 12266.206437500003 
//	22. 'Disponibilita` risorse produttive': 999999.9999999986 
//	23. 'Valore di opere e di beni materiali': 9943.904118750002 
//	23. 'Valore di opere e di beni materiali': 761471.1472190408 
//	
	
	private String[] boundaries;

	private Graph comparison;

	private Graph costs;

	/**
	 * The duration of the last computation.
	 */
	private String duration;

	private Graph electrics;

	private Graph[] functions;

	/**
	 * 
	 */
	private Objective[] objectives;

	/**
	 * The output of the Global Optimiser web service.
	 */
	private GOParetoOutput output;

	/**
	 * The params for the Global Optimiser web service.
	 */
	private GOParetoInputParam params;

	private String[] names;

	private Graph thermals;

	/**
	 * The computing time of the query.
	 */
	private long time;

	private String[] transitionals;

	/**
	 * Default constructor.
	 * 
	 * @param time
	 *            the computing time of the query
	 */
	public Solution(GOParetoInputParam params, GOParetoOutput output, long time) {
		if (params == null)
			throw new IllegalArgumentException(
					"Illegal 'params' argument in Solution(GOParetoInputParam, GOParetoOutput, long): " + params);
		if (output == null)
			throw new IllegalArgumentException(
					"Illegal 'output' argument in Solution(GOParetoInputParam, GOParetoOutput, long): " + output);
		if (time < 0)
			throw new IllegalArgumentException(
					"Illegal 'time' argument in Solution(GOParetoInputParam, GOParetoOutput, long): " + time);
		this.params = params;
		this.output = output;
		this.time = time;
		assert invariant() : "Illegal state in Solution(GOParetoInputParam, GOParetoOutput, long)";
	}

	public String getBoundaries() {
		loadBoundaries();
		assert invariant() : "Illegal state in Solution.getBoundaries()";
		return Arrays.toString(boundaries);
	}

	/**
	 * Returns the utility for the comparison graph.
	 * 
	 * @return the utility for the comparison graph
	 */
	public Graph getComparison() {
		if (comparison == null) {
			loadObjectives();
			comparison = new Comparison(objectives, output.getPlansList());
		}
		assert invariant() : "Illegal state in Solution.getComparison()";
		return comparison;
	}

	/**
	 * Returns the utility for the costs graph.
	 * 
	 * @return the utility for the costs graph
	 */
	public Graph getCosts() {
		if (costs == null) {
			loadObjectives();
			costs = new Costs(objectives, output.getPlansList());
		}
		assert invariant() : "Illegal state in Solution.getCosts()";
		return costs;
	}

	/**
	 * @return
	 */
	public String getDimensions() {
		loadObjectives();
		assert invariant() : "Illegal state in Solution.getDimensions()";
		return Arrays.toString(objectives);
	}

	/**
	 * Returns the duration of the last computation.
	 * 
	 * @return the duration of the last computation
	 */
	public String getDuration() {
		if (duration == null) {
			long hours = (time / 3600000) % 24;
			long minutes = (time / 60000) % 60;
			long seconds = (time / 1000) % 60;
			long millis = time % 1000;
			duration = millis + "ms";
			if (seconds > 0 || minutes > 0 || hours > 0)
				duration = seconds + "s " + duration;
			if (minutes > 0 || hours > 0)
				duration = minutes + "m " + duration;
			if (hours > 0)
				duration = hours + "h " + duration;
		}
		assert invariant() : "Illegal state in Solution.getDuration()";
		return duration;
	}

	/**
	 * Returns the utility for the electrics graph.
	 * 
	 * @return the utility for the electrics graph
	 */
	public Graph getElectrics() {
		if (electrics == null) {
			loadObjectives();
			electrics = new Electrics(objectives, output.getPlansList());
		}
		assert invariant() : "Illegal state in Solution.getElectrics()";
		return electrics;
	}

	public Graph getFunction(int index) {
		loadObjectives();
		if (index < 0 || index >= objectives.length)
			throw new IndexOutOfBoundsException("Index 'index' out of bounds in Solution.getFunction(int):" + index);
		if (functions == null)
			functions = new Graph[objectives.length];
		if (functions[index] == null)
			functions[index] = new Functions(objectives, index, output.getPlansList());
		assert invariant() : "Illegal state in Solution.getFunction(int)";
		return functions[index];
	}

	public String getScenarios() {
		loadScenarios();
		assert invariant() : "Illegal state in Solution.getScenarios()";
		return Arrays.toString(names);
	}

	/**
	 * Returns the utility for the thermics graph.
	 * 
	 * @return the utility for the thermics graph
	 */
	public Graph getThermals() {
		if (thermals == null) {
			loadObjectives();
			thermals = new Thermals(objectives, output.getPlansList());
		}
		assert invariant() : "Illegal state in Solution.getThermals()";
		return thermals;
	}

	/**
	 * Returns the computing time of the query.
	 * 
	 * @return the computing time of the query
	 */
	public long getTime() {
		assert invariant() : "Illegal state in Solution.getTime()";
		return time;
	}

	public String getTransitionals() {
		loadTransitionals();
		assert invariant() : "Illegal state in Solution.getTransitionals()";
		return Arrays.toString(transitionals);
	}

	/**
	 * Invariant check.
	 * 
	 * @return <code>true</code> if the state is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean invariant() {
		return (output != null && time >= 0);
	}

	private void loadBoundaries() {
		loadObjectives();
		if (boundaries == null) {
			boundaries = new String[objectives.length];
			for (int i = 0; i < boundaries.length; i++)
				boundaries[i] = (i + 1) + ". " + objectives[i].getName();
		}
		assert invariant() : "Illegal state in Solution.loadBoundaries()";
	}

	private void loadObjectives() {
		if (objectives == null) {
			int i = 0;
			objectives = new Objective[params.getObjList().length];
			for (String obj : params.getObjList())
				objectives[i++] = new Objective(obj, params.getLoc());
		}
		assert invariant() : "Illegal state in Solution.loadObjectives()";
	}

	private void loadScenarios() {
		loadBoundaries();
		loadTransitionals();
		if (names == null) {
			names = new String[boundaries.length + transitionals.length];
			int i = 0;
			// for (String boundary : boundaries)
			// names[i++] = "\"" + boundary + "\"";
			// for (String transitional : transitionals)
			// names[i++] = "\"" + transitional + "\"";
			for (String boundary : boundaries)
				names[i++] = boundary;
			for (String transitional : transitionals)
				names[i++] = transitional;
		}
		assert invariant() : "Illegal state in Solution.loadScenarios()";
	}

	private void loadTransitionals() {
		if (transitionals == null) {
			int b = params.getObjList().length;
			transitionals = new String[output.getPlansList().length - b];
			for (int i = 0; i < transitionals.length; i++)
				transitionals[i] = (i + b) + ". Scenario";
		}
		assert invariant() : "Illegal state in Solution.loadTransitionals()";
	}

	/**
	 * @return
	 */
	public Objective[] objectives() {
		loadObjectives();
		assert invariant() : "Illegal state in Solution.objectives()";
		return objectives;
	}

	public String[] names() {
		loadScenarios();
		assert invariant() : "Illegal state in Solution.names()";
		return names;
	}

	public int dimensions() {
		loadObjectives();
		assert invariant() : "Illegal state in Solution.dimensions()";
		return objectives.length;
	}

	public int scenarios() {
		loadScenarios();
		assert invariant() : "Illegal state in Solution.scenarios()";
		return names.length;
	}

	private GlobalOptOutput[] scenarios;

	/**
	 * @param index
	 * @return
	 */
	public String getTableSources(int index) {
		if (scenarios == null)
			scenarios = output.getPlansList();
		if (index < 0 || index >= scenarios.length)
			throw new IndexOutOfBoundsException("Index 'index' out of bounds in Solution.getTableSources(int): "
					+ index);
		boolean head = false;
		String sign = "tableSources";
		String result;
		result = String.format("<script type=\"text/javascript\">$(function() { tablesort($('#%s%d')); });</script>\n",
				sign, index);
		result += String.format("<div class=\"text-center\"><table id=\"%s%d\" class=\"tablesorter\">", sign, index);
		EnergyAssignments assignments = scenarios[index].getEnergySources();
		Map<String, GlobalOptSingleValue> map = assignments.getAssignments();
		if (map != null) {
			String unit = "";
			Double value = Double.NaN;
			GlobalOptSingleValue single;
			for (String key : map.keySet()) {
				single = map.get(key);
				if (!head) {
					result += String.format(
							"<thead>\n<tr><th>%s</th><th colspan=\"2\">%s</th></tr>\n</thead>\n<tbody>\n",
							assignments.getEnergySourceLabel(), assignments.getInstalledPowerLabel());
					head = true;
				}
				if (single != null) {
					value = single.getValue();
					unit = single.getMeasurementUnit();
					if (value == null)
						value = Double.NaN;
					if (unit == null)
						unit = "";
				}
				result += String
						.format("<tr><td>%s</td><td class=\"text-right\">%,.2f</td><td class=\"col-units\"><small class=\"muted\">%s</small></td></tr>\n",
								key, value, unit);
			}
		}
		result += "</tbody>\n</table>\n</div>\n";
		assert invariant() : "Illegal state in Solution.getTableSources(int)";
		return result;
	}

	/**
	 * @param index
	 * @return
	 */
	public String getTableEmissions(int index) {
		if (scenarios == null)
			scenarios = output.getPlansList();
		if (index < 0 || index >= scenarios.length)
			throw new IndexOutOfBoundsException("Index 'index' out of bounds in Solution.getTableEmissions(int): "
					+ index);
		boolean head = false;
		String sign = "tableEmissions";
		String result;
		result = String.format("<script type=\"text/javascript\">$(function() { tablesort($('#%s%d')); });</script>\n",
				sign, index);
		result += String.format("<div class=\"text-center\"><table id=\"%s%d\" class=\"tablesorter\">", sign, index);
		Locale locale = scenarios[index].getMylocale();
		EmittedEmissions emitted = scenarios[index].getTheEmittedEmissions();
		Map<String, GlobalOptSingleValue> map = emitted.getEmissions();
		if (map != null) {
			String unit = "";
			Double value = Double.NaN;
			GlobalOptSingleValue single;
			for (String key : map.keySet()) {
				value = Double.NaN;
				single = map.get(key);
				if (!head) {
					result += String
							.format("<thead>\n<tr><th>%s</th><th>%s</th><th class=\"col-units\">%s</th></tr>\n</thead>\n<tbody>\n",
									emitted.getPollutantLabel(), emitted.getQuantityLabel(), scenarios[index]
											.getDetailed().getQuantityUnitLabel());
					head = true;
				}
				if (single != null) {
					value = single.getValue();
					unit = single.getMeasurementUnit();
					if (value == null)
						value = Double.NaN;
					if (unit == null)
						unit = "";
				}
				result += String
						.format("<tr><td>%s <small class=\"muted\">(%s)</small></td><td class=\"text-right\">%,.2f</td><td class=\"col-units\"><small class=\"muted\">%s</small></td></tr>\n", //
								Emission.getEmissionByShortName(key, locale).getName(), key, value, unit);
			}
		}
		result += "</tbody>\n</table>\n</div>\n";
		assert invariant() : "Illegal state in Solution.getTableEmissions(int)";
		return result;
	}

	/**
	 * @param index
	 * @return
	 */
	public String getTableCosts(int index) {
		if (scenarios == null)
			scenarios = output.getPlansList();
		if (index < 0 || index >= scenarios.length)
			throw new IndexOutOfBoundsException("Index 'index' out of bounds in Solution.getTableCosts(int): " + index);
		boolean head = false;
		String sign = "tableCosts";
		String result;
		result = String.format("<script type=\"text/javascript\">$(function() { tablesort($('#%s%d')); });</script>\n",
				sign, index);
		result += String.format("<div class=\"text-center\"><table id=\"%s%d\" class=\"tablesorter\">", sign, index);
		DetailedCosts detailed = scenarios[index].getDetailed();
		Map<String, GlobalOptMultipleValue> map = detailed.getTheDetailedCosts();
		if (map != null) {
			String unitCost = "";
			String unitQuantity = "";
			Double cost = Double.NaN;
			Double quantity = Double.NaN;
			GlobalOptSingleValue single;
			GlobalOptMultipleValue multiple;
			for (String key : map.keySet()) {
				multiple = map.get(key);
				if (!head) {
					result += String
							.format("<thead>\n<tr><th>%s</th><th>%s</th><th class=\"col-units\">%s</th><th colspan=\"2\">%s</th></tr>\n</thead>\n<tbody>\n",
									detailed.getActionTypeLabel(), detailed.getQuantityLabel(),
									detailed.getQuantityUnitLabel(), detailed.getActionCostLabel());
					head = true;
				}
				if (multiple != null) {
					Map<String, GlobalOptSingleValue> smap = multiple.getValues();
					if (smap != null) {
						single = smap.get(detailed.getQuantityLabel());
						if (single != null) {
							quantity = single.getValue();
							unitQuantity = single.getMeasurementUnit();
							if (quantity == null)
								quantity = Double.NaN;
							if (unitQuantity == null)
								unitQuantity = "";
						}
						single = smap.get(detailed.getActionCostLabel());
						if (single != null) {
							cost = single.getValue();
							unitCost = single.getMeasurementUnit();
							if (cost == null)
								cost = Double.NaN;
							if (unitCost == null)
								unitCost = "";
						}
						result += String
								.format("<tr><td>%s</td><td class=\"text-right\">%,.2f</td><td class=\"col-units\"><small class=\"muted\">%s</small></td><td class=\"text-right\">%,.2f</td><td class=\"col-units\"><small class=\"muted\">%s</small></td></tr>\n", //
										key, quantity, unitQuantity, cost, unitCost);
					}
				}
			}
		}
		result += "</tbody>\n</table>\n</div>\n";
		assert invariant() : "Illegal state in Solution.getTableCosts(int)";
		return result;
	}

	/**
	 * @param index
	 * @return
	 */
	public String getTableActions(int index) {
		if (scenarios == null)
			scenarios = output.getPlansList();
		if (index < 0 || index >= scenarios.length)
			throw new IndexOutOfBoundsException("Index 'index' out of bounds in Solution.getTableActions(int): "
					+ index);
		boolean head = false;
		String sign = "tableActions";
		String result;
		result = String.format("<script type=\"text/javascript\">$(function() { tablesort($('#%s%d')); });</script>\n",
				sign, index);
		result += String.format("<div class=\"text-center\"><table id=\"%s%d\" class=\"tablesorter\">", sign, index);
		TotalCosts costs = scenarios[index].getCosts();
		String pricost = "Primary Works";
		String seccost = "Secondary Works";
		if (Helper.ITA.equals(scenarios[index].getMylocale())) {
			pricost = costs.getPrimaryWorksCostLabel();
			seccost = costs.getSecondaryWorksLabel();
		}
		Map<String, GlobalOptMultipleValue> map = costs.getCosts();
		if (map != null) {
			String unitPrimary = "";
			String unitSecondary = "";
			String unitQuantity = "";
			Double primary = Double.NaN;
			Double secondary = Double.NaN;
			Double quantity = Double.NaN;
			GlobalOptSingleValue single;
			GlobalOptMultipleValue multiple;
			for (String key : map.keySet()) {
				multiple = map.get(key);
				if (!head) {
					result += String
							.format("<thead>\n<tr><th>%s</th><th>%s</th><th class=\"co-units\">%s</th><th colspan=\"2\">%s</th><th colspan=\"2\">%s</th>\n</thead>\n<tbody>\n",
									costs.getEnergySourceTypeLabel(), costs.getQuantityLabel(),
									costs.getQuantityUnitLabel(), pricost, seccost);
					head = true;
				}
				if (multiple != null) {
					Map<String, GlobalOptSingleValue> smap = multiple.getValues();
					if (smap != null) {
						single = smap.get(costs.getQuantityLabel());
						if (single != null) {
							quantity = single.getValue();
							unitQuantity = single.getMeasurementUnit();
							if (quantity == null)
								quantity = Double.NaN;
							if (unitQuantity == null)
								unitQuantity = "";
						}
						single = smap.get(costs.getPrimaryWorksCostLabel());
						if (single != null) {
							primary = single.getValue();
							unitPrimary = single.getMeasurementUnit();
							if (primary == null)
								primary = Double.NaN;
							if (unitPrimary == null)
								unitPrimary = "";
						}
						single = smap.get(costs.getSecondaryWorksLabel());
						if (single != null) {
							secondary = single.getValue();
							unitSecondary = single.getMeasurementUnit();
							if (secondary == null)
								secondary = Double.NaN;
							if (unitSecondary == null)
								unitSecondary = "";
						}
						result += String
								.format("<tr><td>%s</td><td class=\"text-right\">%,.2f</td><td class=\"col-units\"><small class=\"muted\">%s</small></td><td class=\"text-right\">%,.2f</td><td class=\"col-units\"><small class=\"muted\">%s</small></td><td class=\"text-right\">%,.2f</td><td class=\"col-units\"><small class=\"muted\">%s</small></td></tr>\n", //
										key, quantity, unitQuantity, primary, unitPrimary, secondary, unitSecondary);
					}
				}
			}
		}
		result += "</tbody>\n</table>\n</div>\n";
		assert invariant() : "Illegal state in Solution.getTableActions(int)";
		return result;
	}

	private Map<Receptor, java.awt.geom.Point2D.Double> bounds;

	public String getGraphReceptor(int tag, int index, Receptor receptor) {
		if (receptor == null)
			throw new IllegalArgumentException("Illegal 'receptor' argument in Solution.getGraphReceptor(int, int, Receptor" + receptor);
		double min = bounds.get(receptor).getX();
		double max = bounds.get(receptor).getY();
		double third = (max - min) / 3.0;
		double left = min + third;
		double right = max - third;
		double value = scenarios[index].getImpacts().computeTotalRecByShortName(receptor.getShortName(),
				scenarios[index].getMylocale());
		STGroup group = new STGroupDir("/tmp");
		ST st = group.getInstanceOf("receptor");
		st.add("tag", tag);
		st.add("title", receptor.getName());
		st.add("min", min);
		st.add("max", max);
		st.add("left", left);
		st.add("right", right);
		st.add("value", value);
		String result = st.render();
		assert invariant() : "Illegal state in Solution.getTableActions(int)";
		return result;
	}

}
