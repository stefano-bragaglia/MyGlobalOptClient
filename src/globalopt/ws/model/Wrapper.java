/**
 * 
 */
package globalopt.ws.model;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.PrimaryActivity;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

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
	private class PAComparator implements Comparator<PrimaryActivity> {
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
	private class RComparator implements Comparator<Receptor> {
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
	 * The English locale.
	 */
	public static final Locale ENG;

	/**
	 * The Italian local.
	 */
	public static final Locale ITA;

	/**
	 * The JSon representation of Eng/Ita map of Primary Activities.
	 */
	public static final String MAP_ENG_ITA;

	static {
		ENG = new Locale("en", "US");
		ITA = new Locale("it", "IT");
		String map = "";
		PrimaryActivity[] pas = PrimaryActivity.getPrimaryActivityList(ENG);
		for (PrimaryActivity pa : pas) {
			if (!map.isEmpty())
				map += ", ";
			map += "\"" + pa.getName() + "\": \"" + pa.convertLocale(ITA).getName() + "\"";
		}
		MAP_ENG_ITA = "var sources = { " + map + " };";
	}

	/**
	 * The only instance of this class.
	 */
	private static Wrapper instance = null;

	/**
	 * Returns the only instance of this class (lazy initialisation).
	 * 
	 * @return the only instance of this class
	 * @throws MalformedURLException
	 */
	public static Wrapper getInstance() throws MalformedURLException {
		if (instance == null)
			instance = new Wrapper();
		return instance;
	}

	/**
	 * The current locale.
	 */
	private Locale locale;

	/**
	 * The version of the web application.
	 */
	private String version;

	/**
	 * Default constructor.
	 * 
	 * @throws MalformedURLException
	 */
	private Wrapper() throws MalformedURLException {
		locale = ENG;
		assert invariant() : "Illegal state in Wrapper()";
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		if (version == null)
			try {
				version = Proxy.getInstance().getVersion();
			} catch (MalformedURLException e) {
				version = "latest";
			}
		assert invariant() : "Illegal state in Wrapper.getVersion()";
		return version;
	}

	/**
	 * @return
	 */
	private boolean invariant() {
		return (locale != null);
	}

	/**
	 * @return
	 */
	public String listReceptors() {
		String result = listReceptors(locale);
		assert invariant() : "Illegal state in Wrapper.listReceptors()";
		return result;
	}

	/**
	 * @param locale
	 * @return
	 */
	public String listReceptors(Locale locale) {
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
		assert invariant() : "Illegal state in Wrapper.listReceptors(Locale)";
		return result.toString();
	}

	/**
	 * @return
	 */
	public String listSources() {
		String result = listSources(locale);
		assert invariant() : "Illegal state in Wrapper.listSources()";
		return result;
	}

	/**
	 * @param locale
	 * @return
	 */
	public String listSources(Locale locale) {
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
		assert invariant() : "Illegal state in Wrapper.listSources(Locale)";
		return result.toString();
	}

}
