/**
 * 
 */
package model.service;

import it.unibo.ai.ePolicy.GlobalOpt.Domain.PrimaryActivity.ActivityType;
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

	public enum Kind {
		COST, ELECTRIC, RECEPTOR, THERMAL, UNKNOWN
	}

	private static final String CONSTRS = "min_max_source(\"Centrali termoelettriche a biomassa\",735,2940)\nmin_max_source(\"Impianti fotovoltaici\",885,4540)\nmin_max_source(\"Centrali termoelettriche a metano\",0,0)\nmin_max_source(\"Centrali termoelettriche a olio\",0,0)\nmin_max_source(\"Centrali termoelettriche a carbone\",0,0)\nmin_max_source(\"Centrali idroelettriche\",0,0)\nmin_max_source(\"Centrale mini-idroelettrica\",15,60)\nmin_max_source(\"Aerogeneratori\",115,560)\nmin_max_source(\"Impianti solari termodinamici\",15,60)\nmin_max_source(\"Impianti geotermici superficiali\",0,0)\nmin_max_source(\"Centrali termoelettriche a biomassa\",0,0)\nmin_max_source(\"Pannelli solari termici\",0,0)";
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
	private static final String TERMICA = "termica";

	private static final String THERMAL = "thermal";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Done.");
	}

	private String id;

	private Kind kind;

	private Locale locale;

	private boolean max;

	private String name;

	private String unit;

	public Kind kind() {
		assert invariant() : "Illegal state in Objective.getKind()";
		return kind;
	}

	public Objective(String desc, Locale locale) {
		if (desc == null || (desc = desc.trim()).isEmpty())
			throw new IllegalArgumentException("Illegal 'desc' argument in Objective(String, Locale): " + desc);
		if (!desc.startsWith(MAX) && !desc.startsWith(MIN) || !desc.endsWith(PAR))
			throw new IllegalArgumentException("Illegal 'desc' argument in Objective(String, Locale): " + desc);
		if (locale == null)
			throw new IllegalArgumentException("Illegal 'locale' argument in Objective(String, Locale): " + locale);
		this.locale = locale;
		this.max = desc.startsWith(MAX);
		this.id = desc = desc.substring(MAX.length(), desc.length() - 1).trim();
		if ((desc.startsWith(REC) && locale.equals(Helper.ENG) //
		|| desc.startsWith(RIC) && locale.equals(Helper.ITA)) //
				&& desc.endsWith(PAR)) {
			this.kind = Kind.RECEPTOR;
			Receptor receptor = Receptor.getReceptorByShortName(desc, locale);
			if (receptor != null) {
				desc = receptor.getName();
				this.unit = receptor.getMeasurementUnit();
			}
		} else if (desc.equals(COST) && locale.equals(Helper.ENG) //
				|| desc.equals(COSTO) && locale.equals(Helper.ITA)) {
			this.kind = Kind.COST;
			desc = desc.substring(0, 1).toUpperCase() + desc.substring(1).toLowerCase();
			this.unit = "EUR";
		} else if (desc.equals(ELECTRIC) && locale.equals(Helper.ENG) //
				|| desc.equals(ELETTRICA) && locale.equals(Helper.ITA)) {
			this.kind = Kind.ELECTRIC;
			desc = desc.substring(0, 1).toUpperCase() + desc.substring(1).toLowerCase();
			this.unit = "ktoe";
		} else if (desc.equals(THERMAL) && locale.equals(Helper.ENG) //
				|| desc.equals(TERMICA) && locale.equals(Helper.ITA)) {
			this.kind = Kind.THERMAL;
			desc = desc.substring(0, 1).toUpperCase() + desc.substring(1).toLowerCase();
			this.unit = "ktoe";
		} else
			this.kind = Kind.UNKNOWN;
		this.name = desc;
		if (unit == null || unit.equals(PURE_NUMBER))
			unit = "";
		assert invariant() : "Illegal state in Objective(String, Locale)";
	}

	/**
	 * @param plan
	 * @return
	 */
	public double extract(GlobalOptOutput plan) {
		if (plan == null)
			throw new IllegalArgumentException("Illegal 'plan' argument in Objective.extract(GlobalOptOutput): " + plan);
		double result;
		switch (kind) {
		case COST:
			result = plan.getCosts().getTotal();
			break;
		case ELECTRIC:
			result = plan.getEnergySources().getTotal(ActivityType.ELECTRIC);
			break;
		case RECEPTOR:
			result = plan.getImpacts().computeTotalRecByShortName(id, locale);
			break;
		case THERMAL:
			result = plan.getEnergySources().getTotal(ActivityType.THERMAL);
			break;
		default:
			result = Double.NaN;
		}
		assert invariant() : "Illegal state in Objective.extract(GlobalOptOutput)";
		return result;
	}

	public String getLabel() {
		assert invariant() : "Illegal state in Objective.getLabel()";
		return name;
	}

	public String getName() {
		String result = (max ? "max '" : "min '") + name + "'";
		assert invariant() : "Illegal state in Objective.getName()";
		return result;
	}

	public String getUnit() {
		assert invariant() : "Illegal state in Objective.getUnit()";
		return unit;
	}

	public String getId() {
		assert invariant() : "Illegal state in Objective.getId()";
		return id;
	}

	public boolean hasUnit() {
		boolean result = (unit != null && !unit.isEmpty());
		assert invariant() : "Illegal state in Objective.hasUnit()";
		return result;
	}

	private boolean invariant() {
		return (true);
	}

	public boolean isMax() {
		assert invariant() : "Illegal state in Objective.isMax()";
		return max;
	}

	public boolean isMin() {
		assert invariant() : "Illegal state in Objective.isMin()";
		return !max;
	}

	@Override
	public String toString() {
		assert invariant() : "Illegal state in Objective.toString()";
		return "'" + name + "'";
	}
}
