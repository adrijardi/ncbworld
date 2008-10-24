package p1rnd;

import cbworld.Fitness;

public class DatosFitness implements Fitness{
	
	private final double coverage;
	private final double fitness;
	private final int nareials;
	
	
	public DatosFitness(int naerials, double coverage, double fitness){
		this.nareials = naerials;
		this.coverage = coverage;
		this.fitness = fitness;
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	public double getCoverage() {
		return coverage;
	}

	public int getNareials() {
		return nareials;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Fitness: ");
		sb.append(fitness);
		sb.append(" Coverage: ");
		sb.append(coverage);
		sb.append(" Nº de Antenas: ");
		sb.append(nareials);
		return sb.toString();
	}

}
