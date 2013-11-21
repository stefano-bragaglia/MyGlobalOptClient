/**
 * 
 */
package globalopt.ws.model.service;

import globalopt.ws.model.InvokeBuilder;
import globalopt.ws.model.ParetoBuilder;
import globalopt.ws.model.Proxy;
import globalopt.ws.model.Wrapper;
import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;
import it.unibo.ai.ePolicy.GlobalOpt.WS.Services.GlobalOptWSSEI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author stefano
 * 
 */
public class Computer {

	private static final String CONSTRAINTS = "min_max_source(\"Big Hydroelectric Plants\",0,0)\nmin_max_source(\"Biomass-based Thermal Plants\",0,0)\nmin_max_source(\"Carbon-based Thermoelectric Plants\",0,0)\nmin_max_source(\"Methane-based Thermoelectric Plants\",0,0)\nmin_max_source(\"Oil-based Thermoelectric Plants\",0,0)\nmin_max_source(\"Photovoltaic Plants\",885,4540)\nmin_max_source(\"Small Hydroelectric Plants\",15,60)\nmin_max_source(\"Solar Thermal Panels\",0,0)\nmin_max_source(\"Superficial Geothermal Plants\",0,0)\nmin_max_source(\"Thermodynamic Solar Plants\",15,60)\nmin_max_source(\"Thermoelectric Biomass Plants\",735,2940)\nmin_max_source(\"Windmill Electrical Generators\",115,560)\n";

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws IOException, InterruptedException, MalformedURLException {
		System.out.println("Here we go!");

		GlobalOptWSSEI proxy = Proxy.getInstance();

		Map<String, Range> bounds = new HashMap<String, Range>();
		System.out.println("Map<String, Range> bounds = new HashMap<String, Range>();");

		ParetoBuilder pareto = new ParetoBuilder().setLocale("en").setConstraints(CONSTRAINTS);
		Locale locale = pareto.getLocale();
		String name, shortname;
		InvokeBuilder invoke;
		GlobalOptOutput out;
		double min, max;

		Receptor[] receptors = Receptor.getReceptorList(locale);
		Arrays.sort(receptors, new Wrapper.RComparator());

		for (Receptor r : receptors) {
			name = r.getName();
			shortname = r.getShortName();
			invoke = pareto.stem();
			
			invoke.setFunction(String.format("min(%s)", shortname));
			out = proxy.invoke(invoke.build());
			min = out.getImpacts().computeTotalRecByShortName(shortname, locale);

			invoke.setFunction(String.format("max(%s)", r.getShortName()));
			out = proxy.invoke(invoke.build());
			max = out.getImpacts().computeTotalRecByShortName(shortname, locale);

			bounds.put(name, new Range(min, max));
			System.out.println(String.format("bounds.put(\"%s\", new Range(%f, %f));", name, min, max));
		}

		System.out.println("Done.");
	}

}
