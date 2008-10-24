package ncbworld;

public class Fitness implements Comparable<Fitness> {
	private final double fitness;
	
	public Fitness(double fitness){
		this.fitness = fitness;
	}

	@Override
	public int compareTo(Fitness o) {
		int ret = 0;
		double of = o.getFitness();
		if(fitness > of)
			ret = 1;
		else if(fitness < of)
			ret = -1;
		return ret;
	}

	public double getFitness() {
		return fitness;
	}

}
