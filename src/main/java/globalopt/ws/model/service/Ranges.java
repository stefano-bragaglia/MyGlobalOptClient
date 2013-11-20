/**
 * 
 */
package globalopt.ws.model.service;

import globalopt.ws.model.Helper;
import globalopt.ws.model.InvokeBuilder;
import globalopt.ws.model.ParetoBuilder;
import globalopt.ws.model.Proxy;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;
import it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stefano
 * 
 */
public class Ranges {

	private Map<String, Range> data;

	private int hash;

	/**
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws MalformedURLException
	 * 
	 */
	public Ranges(ParetoBuilder builder) throws IOException, InterruptedException, MalformedURLException {
		if (builder == null || builder.getBounds().length == 0)
			throw new IllegalArgumentException("Illegal 'builder' argument in Ranges(ParetoBuilder): " + builder);
		this.data = new HashMap<String, Range>();
		this.hash = builder.getHash();

		GlobalOptOutput output;
		GlobalOptWSSEI proxy = Proxy.getInstance();
		InvokeBuilder invoker = builder.stem();
		
		invoker.getConstraints();
		
		Receptor[] receptors = Receptor.getReceptorList(Helper.ENG);
		for (int i = 0; i < receptors.length; i++) {
			String shortname = receptors[i].getShortName();
			System.out.println(">> Assessing the bounds for receptor '" + receptors[i].getName() + "'...");

			invoker.setFunction(String.format("min(%s)", shortname));
			output = proxy.invoke(invoker.build());
			double min = output.getImpacts().computeTotalRecByShortName(shortname, Helper.ENG);

			invoker.setFunction(String.format("max(%s)", shortname));
			output = proxy.invoke(invoker.build());
			double max = output.getImpacts().computeTotalRecByShortName(shortname, Helper.ENG);

			this.data.put(shortname, new Range(min, max));
		}
		assert invariant() : "Illegal state in Ranges(ParetoBuilder)";
	}

	private boolean invariant() {
		return (data != null && data.size() == Receptor.getReceptorList(Helper.ENG).length);
	}

	public int getHash() {
		assert invariant() : "Illegal state in Ranges.getHash()";
		return hash;
	}

	public Range getRange(String shortname) {
		if (shortname == null || (shortname = shortname.trim()).isEmpty() || !data.containsKey(shortname))
			throw new IllegalArgumentException("Illegal 'shortname' argument in Range.getRange(String): " + shortname);
		Range result = data.get(shortname);
		assert invariant() : "Illegal state in Ranges.getRange(String)";
		return result;
	}

	public double getDelta(String shortname) {
		if (shortname == null || (shortname = shortname.trim()).isEmpty() || !data.containsKey(shortname))
			throw new IllegalArgumentException("Illegal 'shortname' argument in Range.getDelta(String): " + shortname);
		assert invariant() : "Illegal state in Ranges.getDelta(String)";
		return data.get(shortname).getDelta();
	}

	public Double getMin(String shortname) {
		if (shortname == null || (shortname = shortname.trim()).isEmpty() || !data.containsKey(shortname))
			throw new IllegalArgumentException("Illegal 'shortname' argument in Range.getMin(String): " + shortname);
		assert invariant() : "Illegal state in Ranges.getMin(String)";
		return data.get(shortname).getMin();
	}

	public Double getMax(String shortname) {
		if (shortname == null || (shortname = shortname.trim()).isEmpty() || !data.containsKey(shortname))
			throw new IllegalArgumentException("Illegal 'shortname' argument in Range.getMax(String): " + shortname);
		assert invariant() : "Illegal state in Ranges.getMax(String)";
		return data.get(shortname).getMax();
	}

}
