/**
 * 
 */
package globalopt.ws.model;

import globalopt.ws.model.service.Range;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.PrimaryActivity;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author stefano
 * 
 */
public class Wrapper {

	/**
	 * A comparator to sort Primary Activities by name.
	 * 
	 * @author stefano
	 */
	public static class PAComparator implements Comparator<PrimaryActivity> {
		@Override
		public int compare(PrimaryActivity pa1, PrimaryActivity pa2) {
			if (pa1 == null)
				throw new IllegalArgumentException(
						"Illegal 'pa1' argument in PrimaryActivityComparator.compare(PrimaryActivity, PrimaryActivity): "
								+ pa1);
			if (pa2 == null)
				throw new IllegalArgumentException(
						"Illegal 'pa2' argument in PrimaryActivityComparator.compare(PrimaryActivity, PrimaryActivity): "
								+ pa2);
			return pa1.getName().compareToIgnoreCase(pa2.getName());
		}
	}

	/**
	 * A comparator to sort Receptors by index.
	 * 
	 * @author stefano
	 */
	public static class RComparator implements Comparator<Receptor> {
		@Override
		public int compare(Receptor r1, Receptor r2) {
			if (r1 == null)
				throw new IllegalArgumentException(
						"Illegal 'r1' argument in ReceptorComparator.compare(Receptor, Receptor): " + r1);
			if (r2 == null)
				throw new IllegalArgumentException(
						"Illegal 'r2' argument in ReceptorComparator.compare(Receptor, Receptor): " + r2);
			String s1 = r1.getShortName();
			s1 = s1.substring(1 + s1.indexOf("("), s1.indexOf(")"));
			int i1 = Integer.parseInt(s1);
			String s2 = r2.getShortName();
			s2 = s2.substring(1 + s2.indexOf("("), s2.indexOf(")"));
			int i2 = Integer.parseInt(s2);
			return Integer.compare(i1, i2);
		}
	}

	/**
	 * A comparator to sort Receptors by name.
	 * 
	 * @author stefano
	 */
	public static class NameComparator implements Comparator<Receptor> {
		@Override
		public int compare(Receptor r1, Receptor r2) {
			if (r1 == null)
				throw new IllegalArgumentException(
						"Illegal 'r1' argument in ReceptorComparator.compare(Receptor, Receptor): " + r1);
			if (r2 == null)
				throw new IllegalArgumentException(
						"Illegal 'r2' argument in ReceptorComparator.compare(Receptor, Receptor): " + r2);
			String s1 = r1.getName();
			String s2 = r2.getName();
			return s1.compareTo(s2);
		}
	}

	/**
	 * The JSon representation of Eng/Ita map of Primary Activities.
	 */
	public static final String MAP_ACTIVITIES;

	/**
	 * The JSon representation of Eng/Ita map of Receptors.
	 */
	public static final String MAP_RECEPTORS;

	public static final Map<String, Range> BOUNDS;

	static {
		BOUNDS = new HashMap<String, Range>();
		BOUNDS.put("Subsidence limitation", new Range(228.651422, 1298.617797));
		BOUNDS.put("Embankments stability", new Range(-12203.866138, -2786.195894));
		BOUNDS.put("Stability of coasts or seafloor", new Range(-858.579913, -183.055869));
		BOUNDS.put("Stability of river banks and beds", new Range(-4799.297813, -1096.646250));
		BOUNDS.put("Soil quality", new Range(-12716.357525, -2899.620444));
		BOUNDS.put("Quality of sea water", new Range(-5960.885625, -1357.825938));
		BOUNDS.put("Quality of inland surface waters", new Range(-9998.568525, -2333.735537));
		BOUNDS.put("Groundwater", new Range(-15222.297725, -3671.234931));
		BOUNDS.put("Air quality", new Range(-8509.736313, 73.143813));
		BOUNDS.put("Quality of climate", new Range(-1222.296731, 2923.720481));
		BOUNDS.put("Wellness of terrestrial vegetation", new Range(-20423.025025, -4845.857319));
		BOUNDS.put("Wellness of wildlife", new Range(-30398.018187, -7144.565094));
		BOUNDS.put("Wellness of aquatic plants", new Range(-22491.804663, -5367.547525));
		BOUNDS.put("Wellness and health of mankind", new Range(2619.039556, 12714.614038));
		BOUNDS.put("Quality of sensitive landscapes", new Range(-37471.212600, -8557.805681));
		BOUNDS.put("Cultural/historical heritage value", new Range(-25980.797250, -5948.834937));
		BOUNDS.put("Recreation resources accessibility", new Range(-650.473828, -114.713203));
		BOUNDS.put("Water availability ", new Range(-12869.941500, -3166.689594));
		BOUNDS.put("Availability of agricultural fertile land", new Range(-15116.925588, -3429.199881));
		BOUNDS.put("Lithoid resource availability", new Range(740.206306, 2976.400413));
		BOUNDS.put("Energy availability", new Range(731.973094, 3559.836125));
		BOUNDS.put("Availability of productive resources", new Range(12266.206438, 53555.732938));
		BOUNDS.put("Value of material goods", new Range(9943.904119, 43761.198850));
		String map = "";
		PrimaryActivity[] pas = PrimaryActivity.getPrimaryActivityList(Helper.ENG);
		for (PrimaryActivity pa : pas) {
			if (!map.isEmpty())
				map += ", ";
			map += "\"" + pa.getName() + "\": \"" + pa.convertLocale(Helper.ITA).getName() + "\"";
		}
		MAP_ACTIVITIES = "var sources = { " + map + " };";
		map = "";
		Receptor[] rec = Receptor.getReceptorList(Helper.ENG);
		for (Receptor r : rec) {
			if (!map.isEmpty())
				map += ", ";
			map += "\"" + r.getName() + "\": \"" + r.getShortName() + "\"";
		}
		rec = Receptor.getReceptorList(Helper.ITA);
		for (Receptor r : rec) {
			if (!map.isEmpty())
				map += ", ";
			map += "\"" + r.getName() + "\": \"" + r.getShortName() + "\"";
		}
		MAP_RECEPTORS = "var receptors = { " + map + " };";
	}

	public static String listSelect(Locale locale, int index, GlobalOptOutput scenario) {
		if (locale == null)
			throw new IllegalArgumentException(
					"Illegal 'locale' argument in Wrapper.listButtons(Locale, GlobalOptOutput): " + locale);
		if (scenario == null)
			throw new IllegalArgumentException(
					"Illegal 'scenario' argument in Wrapper.listButtons(Locale, GlobalOptOutput): " + scenario);
		String name;
		Range range;
		double value;
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("<p><small>Select receptor:</small><select class=\"span5\" id=\"receptorsList%d>\n", index));
		Receptor[] receptors = Receptor.getReceptorList(locale);
		Arrays.sort(receptors, new NameComparator());
		for (Receptor receptor : receptors) {
			name = receptor.getName();
			range = BOUNDS.get(receptor.convertLocale(Helper.ENG).getName());
			if (null == range || 0 == range.getDelta())
				value = Double.NaN;
			else {
				value = scenario.getImpacts().computeTotalRecByShortName(receptor.getShortName(), locale);
				value = (value - range.getMin()) / range.getDelta();
			}
			// Shouldn't happen, if so it is because of the different
			// constraints: hack follows.
			if (Double.NaN != value && 0.0 > value)
				value = 0.0;
			if (Double.NaN != value && 1.0 < value)
				value = 1.0;
			// end of hack
			builder.append(String.format("<option value=\"%.2f\">%s</option>\n", value, name));
		}
		builder.append("</select></p>\n");
		return builder.toString();
	}

	/**
	 * @return
	 */
	public static String listReceptors() {
		return listReceptors(Helper.ENG);
	}

	/**
	 * @param locale
	 * @return
	 */
	public static String listReceptors(Locale locale) {
		if (locale == null || !"en_US".equals(locale.toString()) && !"it_IT".equals(locale.toString()))
			throw new IllegalArgumentException("Illegal 'locale' in Wrapper.listReceptors(Locale): " + locale);
		StringBuilder result = new StringBuilder();
		Receptor[] rec = Receptor.getReceptorList(locale);
		Arrays.sort(rec, new RComparator());
		result.append("<div class='span6'><ul>");
		for (int i = 0; i < rec.length / 2 + rec.length % 2; i++)
			result.append("<li><small><strong>" + rec[i].getShortName() + "</strong>&nbsp;" + rec[i].getName()
					+ "</small></li>");
		result.append("</ul></div><div class='span6'><ul>");
		for (int i = rec.length / 2 + rec.length % 2; i < rec.length; i++)
			result.append("<li><small><strong>" + rec[i].getShortName() + "</strong>&nbsp;" + rec[i].getName()
					+ "</small></li>");
		result.append("</ul></div>");
		return result.toString();
	}

	/**
	 * @return
	 */
	public static String listSources() {
		return listSources(Helper.ENG);
	}

	/**
	 * @param locale
	 * @return
	 */
	public static String listSources(Locale locale) {
		if (locale == null || !"en_US".equals(locale.toString()) && !"it_IT".equals(locale.toString()))
			throw new IllegalArgumentException("Illegal 'locale' in Wrapper.listSources(Locale): " + locale);
		StringBuilder result = new StringBuilder();
		PrimaryActivity[] pa = PrimaryActivity.getPrimaryActivityList(locale);
		Arrays.sort(pa, new PAComparator());
		result.append("<div class='span6'><ul>");
		for (int i = 0; i < pa.length / 2 + pa.length % 2; i++)
			result.append("<li><small><strong>&quot;" + pa[i].getName() + "&quot;</strong></small></li>");
		result.append("</ul></div><div class='span6'><ul>");
		for (int i = pa.length / 2 + pa.length % 2; i < pa.length; i++)
			result.append("<li><small><strong>&quot;" + pa[i].getName() + "&quot;</strong></small></li>");
		result.append("</ul></div>");
		return result.toString();
	}

}
