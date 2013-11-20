/**
 * 
 */
package globalopt.ws.model;

import globalopt.ws.model.service.Ranges;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Input.GOParetoInputParam;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GOParetoOutput;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;
import it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;

/**
 * @author stefano
 * 
 */
public class Helper {

	/**
	 * The English locale.
	 */
	public static final Locale ENG;

	/**
	 * The Italian local.
	 */
	public static final Locale ITA;

	/**
	 * The proxy for the Global Optimiser.
	 */
	private static GlobalOptWSSEI proxy;

	/**
	 * The version of the web service.
	 */
	private static String version;

	static {
		ENG = new Locale("en", "US");
		ITA = new Locale("it", "IT");
		try {
			proxy = Proxy.getInstance();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Illegal 'proxy' argument in Helper.resolve()");
		}
	}

	/**
	 * Computes the scenarios for the
	 * 
	 * @param builder
	 *            the <code>ParetoBuilder</code> of the query to compute
	 */
	public static Solution compute(ParetoBuilder builder, Ranges ranges) {
		if (builder == null)
			throw new IllegalArgumentException("Illegal 'builder' argument in Helper.compute(ParetoBuilder, Ranges): "
					+ builder);
		
		// System.out.println(">> Ranges ("+builder.getRanges().hashCode()+"): " + builder.getRanges());
		
		GOParetoInputParam params = null;
		GOParetoOutput output = null;
		long duration = System.currentTimeMillis();
		try {
			if (FULL) {
				GlobalOptOutput result;
				InvokeBuilder invoker = builder.stem();
				for (Receptor rec : Receptor.getReceptorList(builder.getLocale())) {
					invoker.setFunction("min(" + rec.getShortName() + ")");
					result = proxy.invoke(invoker.build());
					// TODO Depends on how I'll use these values later
					invoker.setFunction("max(" + rec.getShortName() + ")");
					result = proxy.invoke(invoker.build());
					// TODO Depends on how I'll use these values later
				}
			}
			output = proxy.computePareto(params = builder.build());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		duration = System.currentTimeMillis() - duration;
		return new Solution(params, output, ranges, duration);
	}

	private static final boolean FULL = false;

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
}
