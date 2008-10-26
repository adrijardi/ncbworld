package ncbworld.problems.aerialsp1;

import ncbworld.Fitness;

public class DatosFitness extends Fitness{
	
	private final double coverage;
	private final int nareials;
	
	
	public DatosFitness(int naerials, double coverage, double fitness){
		super(fitness);
		this.nareials = naerials;
		this.coverage = coverage;
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
		sb.append(super.toString());
		sb.append(" Coverage: ");
		sb.append(truncate(coverage,4));
		sb.append(" NA: ");
		sb.append(nareials);
		return sb.toString();
	}

}
