/**
 * 
 */
package model.service;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.Receptor;
import it.unibo.ai.ePolicy.GlobalOpt.IO.Output.GlobalOptOutput;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;

import model.Helper;
import model.InvokeBuilder;
import model.ParetoBuilder;
import model.Proxy;

/**
 * @author stefano
 * 
 */
public class Objective {

	private static final String COST = "cost";
	private static final String COSTO = "costo";
	private static final String ELECTRIC = "electric";
	private static final String ELETTRICA = "elettrica";
	private static final String MAX = "max(";
	private static final String MIN = "min(";
	private static final String PAR = ")";
	private static final String PURE_NUMBER = "Pure Number";
	private static final String REC = "rec(";
	private static final String RIC = "ric(";
	private static final String THERMIC = "thermic";
	private static final String TERMICA = "termica";

	private boolean max;

	private Receptor receptor;

	private String name;

	private String unit;

	public Objective(String desc, Locale locale) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in Objective(String, Locale): " + desc);
		if (!desc.startsWith(MAX) && !desc.startsWith(MIN) || !desc.endsWith(PAR))
			throw new IllegalArgumentException("Illegal 'desc' argument in Objective(String, Locale): " + desc);
		if (locale == null)
			throw new IllegalArgumentException("Illegal 'locale' argument in Objective(String, Locale): " + locale);
		this.max = desc.startsWith(MAX);
		desc = desc.substring(MAX.length(), desc.length() - 1).trim();
		if ((desc.startsWith(REC) && locale.equals(Helper.ENG) //
		|| desc.startsWith(RIC) && locale.equals(Helper.ITA)) //
				&& desc.endsWith(PAR)) {
			receptor = Receptor.getReceptorByShortName(desc, locale);
			if (receptor != null) {
				desc = receptor.getName();
				unit = receptor.getMeasurementUnit();
			}
		} else if (desc.equals(COST) && locale.equals(Helper.ENG) //
				|| desc.equals(COSTO) && locale.equals(Helper.ITA)) {
			desc = desc.substring(0, 1).toUpperCase() + desc.substring(1).toLowerCase();
			unit = "EUR";
		} else if (desc.equals(ELECTRIC) && locale.equals(Helper.ENG) //
				|| desc.equals(ELETTRICA) && locale.equals(Helper.ITA)) {
			desc = desc.substring(0, 1).toUpperCase() + desc.substring(1).toLowerCase();
			unit = "ktoe";
		} else if (desc.equals(THERMIC) && locale.equals(Helper.ENG) //
				|| desc.equals(TERMICA) && locale.equals(Helper.ITA)) {
			desc = desc.substring(0, 1).toUpperCase() + desc.substring(1).toLowerCase();
			unit = "ktoe";
		}
		this.name = desc;
		if (unit == null || unit.equals(PURE_NUMBER))
			unit = "";
		assert invariant() : "Illegal state in Objective(String, Locale)";
	}

	public boolean isMax() {
		assert invariant() : "Illegal state in Objective.isMax()";
		return max;
	}

	public boolean isMin() {
		assert invariant() : "Illegal state in Objective.isMin()";
		return !max;
	}

	private boolean invariant() {
		return (true);
	}

	public String getName() {
		assert invariant() : "Illegal state in Objective.getName()";
		return name;
	}

	public String getUnit() {
		assert invariant() : "Illegal state in Objective.getUnit()";
		return unit;
	}

	@Override
	public String toString() {
		String result = (max ? "max '" : "min '") + name + "'";
		assert invariant() : "Illegal state in Objective.toString()";
		return result;
	}

	private static final String CONSTRS = "min_max_source(\"Centrali termoelettriche a biomassa\",735,2940)\nmin_max_source(\"Impianti fotovoltaici\",885,4540)\nmin_max_source(\"Centrali termoelettriche a metano\",0,0)\nmin_max_source(\"Centrali termoelettriche a olio\",0,0)\nmin_max_source(\"Centrali termoelettriche a carbone\",0,0)\nmin_max_source(\"Centrali idroelettriche\",0,0)\nmin_max_source(\"Centrale mini-idroelettrica\",15,60)\nmin_max_source(\"Aerogeneratori\",115,560)\nmin_max_source(\"Impianti solari termodinamici\",15,60)\nmin_max_source(\"Impianti geotermici superficiali\",0,0)\nmin_max_source(\"Centrali termoelettriche a biomassa\",0,0)\nmin_max_source(\"Pannelli solari termici\",0,0)";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Objective o;
			GlobalOptOutput out;
			InvokeBuilder invoke;
			ParetoBuilder pareto = new ParetoBuilder().setLocale("it").setConstraints(CONSTRS);
			Locale locale = pareto.getLocale();
			int i = 1;
			for (Receptor r : Receptor.getReceptorList(locale)) {
				invoke = pareto.stem().setFunction(MIN + r.getShortName() + PAR);
				out = Proxy.getInstance().invoke(invoke.build());
				o = new Objective(invoke.getFunction(), locale);
				System.out.println(i + ". " + o.toString() + ": "
						+ out.getImpacts().computeTotalRecByShortName(r.getShortName(), locale) + " " + o.getUnit());
				invoke = pareto.stem().setFunction(MAX + r.getShortName() + PAR);
				out = Proxy.getInstance().invoke(invoke.build());
				o = new Objective(invoke.getFunction(), locale);
				System.out.println(i + ". " + o.toString() + ": "
						+ out.getImpacts().computeTotalRecByShortName(r.getShortName(), locale) + " " + o.getUnit());
				i += 1;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done.");
	}
}
