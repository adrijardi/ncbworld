package ncbworld;

public class Fitness implements Comparable<Fitness> {
	protected final double fitness;

	public Fitness(double fitness) {
		this.fitness = fitness;
	}

	@Override
	public int compareTo(Fitness o) {
		int ret = 0;
		double of = o.getFitness();
		if (fitness > of)
			ret = 1;
		else if (fitness < of)
			ret = -1;
		return ret;
	}

	public double getFitness() {
		return fitness;
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if (obj instanceof Fitness) {
			Fitness fo = (Fitness) obj;
			ret = compareTo(fo) == 0;
		}
		return ret;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Fitness: ");
		sb.append(truncate(fitness,4));
		return sb.toString();
	}
	
	protected String truncate(double value, int dec){
		double v = value;
		int iv = (int)v;
		StringBuilder sb = new StringBuilder();
		sb.append(iv);
		sb.append('.');
		v -= iv;
		for (int i = 0; i < dec; i++) {
			v *= 10;
			iv = (int)v;
			sb.append(iv);
			v -= iv;
		}
		return sb.toString();
	}
}
