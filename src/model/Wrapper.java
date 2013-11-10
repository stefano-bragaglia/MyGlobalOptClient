/**
 * 
 */
package model;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.PrimaryActivity;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GOParetoOutput;
import it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

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

	/**
	 * The namespace of the Global Optimiser service.
	 */
	private static final String NAMESPACE;

	/**
	 * The URI where the Global Optimiser service is deployed.
	 */
	private static final String URI;

	static {
		NAMESPACE = "http://Services.WS.GlobalOpt.ePolicy.ai.unibo.it/";
		URI = "http://localhost:8080/GlobalOptWS/services/GlobalOpt";
		// URI ="http://137.204.45.59:8079/GlobalOptWS/services/GlobalOpt";
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
	 * The counter for computations during the current session.
	 */
	private volatile int computations;

	/**
	 * The duration of the last computation.
	 */
	private volatile long duration;

	/**
	 * The current locale.
	 */
	private Locale locale;

	/**
	 * The proxy for the Global Optimiser service.
	 */
	private GlobalOptWSSEI proxy;

	/**
	 * The time required for the last computation.
	 */
	private volatile String timestamp;

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
		computations = 0;
		duration = 0;
		locale = ENG;
		QName nameService = new QName(NAMESPACE, "GlobalOpt");
		QName namePort = new QName(NAMESPACE, "GlobalOptSimpleWSSEIPort");
		Service service = Service.create(new URL(URI + "/?wsdl"), nameService);
		service.addPort(namePort, SOAPBinding.SOAP11HTTP_BINDING, URI);
		proxy = service.getPort(GlobalOptWSSEI.class);
		// Client client = ClientProxy.getClient(proxy);
		// HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
		// httpConduit.getClient().setReceiveTimeout(1000 * 60 * 30);
		timestamp = null;
		version = null;
		assert invariant() : "Illegal state in Wrapper()";
	}

	/**
	 * @param builder
	 */
	public void compute(ParetoBuilder builder) {
		if (builder == null)
			throw new IllegalArgumentException("Illegal 'builder' argument in Wrapper.compute(InputBuilder): "
					+ builder);
		// HttpServletRequest request = builder.getRequest();
		locale = builder.getLocale();
		try {
			duration = System.currentTimeMillis();
			GOParetoOutput result = proxy.computePareto(builder.build());
			System.err.println(">>> " + result);
			computations += 1;
			timestamp = null;
			duration = System.currentTimeMillis() - duration;
			// if (computations >= 0)
			// throw new IllegalArgumentException("Computations: " +
			// computations + " - Duration: " + duration);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert invariant() : "Illegal state in Wrapper.compute(GOParetoInputParam)";
	}

	/**
	 * Returns the number of computations during the current session.
	 * 
	 * @return the number of computations during the current session
	 */
	public int getComputations() {
		assert invariant() : "Illegal state in Wrapper.getComputations()";
		return computations;
	}

	/**
	 * Returns the duration of the last computation (if any).
	 * 
	 * @return the duration of the last computation
	 */
	public long getDuration() {
		assert invariant() : "Illegal state in Wrapper.getDuration()";
		return duration;
	}

	/**
	 * Returns the time required by the last computation (if any).
	 * 
	 * @return the time required by the last computation
	 */
	public String getTimestamp() {
		if (timestamp == null) {
			long hours = (duration / 3600000) % 24;
			long minutes = (duration / 60000) % 60;
			long seconds = (duration / 1000) % 60;
			long millis = duration % 1000;
			timestamp = millis + "ms";
			if (seconds > 0 || minutes > 0 || hours > 0)
				timestamp = seconds + "s " + timestamp;
			if (minutes > 0 || hours > 0)
				timestamp = minutes + "m " + timestamp;
			if (hours > 0)
				timestamp = hours + "h " + timestamp;
		}
		assert invariant() : "Illegal state in Wrapper.getTimestamp()";
		return timestamp;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		if (version == null)
			version = proxy.getVersion();
		assert invariant() : "Illegal state in Wrapper.getVersion()";
		return version;
	}

	/**
	 * @return
	 */
	private boolean invariant() {
		return (computations >= 0 && locale != null && proxy != null);
	}

	/**
	 * Tells whether at least a computations has been executed.
	 * 
	 * @return <code>true</code> if a least a computations has been executed,
	 *         <code>false</code> otherwise
	 */
	public boolean isComputed() {
		boolean result = (computations > 0);
		assert invariant() : "Illegal state in Wrapper.isComputed()";
		return result;
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
