/**
 * 
 */
package model;

import it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI;

import java.net.MalformedURLException;

/**
 * @author stefano
 * 
 */
public class Helper {

	/**
	 * The proxy for the Global Optimiser.
	 */
	private static GlobalOptWSSEI proxy;

	/**
	 * The version of the web service.
	 */
	private static String version;

	static {
		try {
			proxy = Proxy.getInstance();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Illegal 'proxy' argument in Helper.resolve()");
		}
	}

	/**
	 * Returns the version of the web service.
	 * 
	 * @return the version of the web service
	 */
	public static String getVersion() {
		if (version == null)
			version = proxy.getVersion();
		return version;
	}

	/**
	 * Computes the scenarios for the
	 * 
	 * @param builder
	 *            the <code>ParetoBuilder</code> of the query to compute
	 */
	public static Solution compute(ParetoBuilder builder) {
		if (builder == null)
			throw new IllegalArgumentException("Illegal 'builder' argument in Helper.compute(ParetoBuilder): "
					+ builder);
		long duration = System.currentTimeMillis();
		// TODO

		duration = System.currentTimeMillis() - duration;
		return new Solution(duration);
	}
}
