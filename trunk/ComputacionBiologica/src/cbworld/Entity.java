package cbworld;

public abstract class Entity implements Comparable<Entity> {
	
	protected boolean[] dna;
	protected int nchromosome;
	protected Fitness fitness;
	protected boolean ncfitness;
	
	public Entity() {
		ncfitness = true;
		fitness = null;
	}

	public Entity(Entity best) {
		boolean dna2[] = best.dna;
		this.dna = new boolean[dna2.length];
		for (int i = 0; i < dna2.length; i++) {
			this.dna[i] = dna2[i];
		}
		
		this.nchromosome = best.nchromosome;
		this.fitness = best.fitness;
		this.ncfitness = best.ncfitness;
	}

	public double calculateFitness() {
		//if (ncfitness) {
			obtainFitness();
		//	ncfitness = false;
		//}
		return fitness.getFitness();
	}
	
	public double getDFitness() {
		return fitness.getFitness();
	}
	
	public boolean[] getDna() {
		return dna;
	}
	
	protected int getDNAOnes(){
		int ret = 0;
		for (int i = 0; i < dna.length; i++) {
			if(dna[i]== true)
				ret++;			
		}
		return ret;
	}

	protected int getDNAZeros(){
		return dna.length - getDNAOnes();
	}
	
	public Fitness getFitness(){
		return fitness;
	}

	public int getNchromosome() {
		return nchromosome;
	}

	public abstract Entity getSon(boolean dna[]);
	
	protected abstract void init();
	
	protected abstract Fitness obtainFitness();

	protected abstract void randomize();

	public void setDna(boolean[] dna) {
		this.dna = dna;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dna.length; i++) {
			if(dna[i])
				sb.append("1");
			else
				sb.append("0");
		}
		return sb.toString();
	}

	public int compareTo(Entity f2) {
		int ret = 0;
		ret = dna.length-f2.dna.length;
		for (int i = 0; ret == 0 && i < dna.length; i++) {
			ret = boolresta(dna[i], f2.dna[i]);
		}
		return ret;
	}

	private int boolresta(boolean b, boolean c) {
		int ret = 0;
		if(b == true && c == false)
			ret = 1;
		else if(c == true && b == false)
			ret = -1;
		return ret;
	}
}
