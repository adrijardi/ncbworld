package ncbworld;

import java.util.Random;

public abstract class Entity implements Comparable<Entity> {

	protected final boolean[] dna;
	protected Fitness fitness;
	protected int familiar_per;

	public Entity(boolean[] dna) {
		this.dna = dna;
		init();
		fitness = fitness();
		familiar_per = 85;
	}

	abstract protected void init();

	abstract protected Entity[] reproduction(Entity e, boolean sameFathers);

	abstract protected Fitness fitness();

	abstract protected Entity giveBirth(boolean[] dna);

	protected Entity[] defaultSimpleCrossReproduction(Entity e, boolean sameFathers) {
		MutatorAgent ma = MutatorAgent.getInstance();
		Entity ret[] = new Entity[2];
		boolean dnaf[] = e.getDna();
		boolean dna1[], dna2[];
		int i = 0;
		if (dnaf.length == dna.length) {
			dna1 = new boolean[dna.length];
			dna2 = new boolean[dna.length];
			while (i < (dna.length / 2)) {
				dna1[i] = dna[i];
				dna2[i] = dnaf[i];
				i++;
			}
			while (i < dna.length) {
				dna1[i] = dnaf[i];
				dna2[i] = dna[i];
				i++;
			}
			ret[0] = giveBirth(ma.mutate(dna1, sameFathers));
			ret[1] = giveBirth(ma.mutate(dna2, sameFathers));
		}
		return ret;
	}

	protected Entity[] defaultUniformCrossReproduction(Entity e, boolean sameFathers) {
		MutatorAgent ma = MutatorAgent.getInstance();
		Random r = new Random();
		Entity ret[] = new Entity[2];
		boolean dnaf[] = e.getDna();
		boolean dna1[], dna2[];
		int i = 0;
		if (dnaf.length == dna.length) {
			dna1 = new boolean[dna.length];
			dna2 = new boolean[dna.length];

			while (i < dna.length) {
				if (r.nextBoolean()) {
					dna1[i] = dnaf[i];
					dna2[i] = dna[i];
				} else {
					dna1[i] = dna[i];
					dna2[i] = dnaf[i];
				}
				i++;
			}
			ret[0] = giveBirth(ma.mutate(dna1, sameFathers));
			ret[1] = giveBirth(ma.mutate(dna2, sameFathers));
		}
		return ret;
	}

	protected Entity[] defaultMultiPercentageCrossReproduction(Entity e, int per, boolean sameFathers) {
		MutatorAgent ma = MutatorAgent.getInstance();
		Random r = new Random();
		Entity ret[] = new Entity[2];
		boolean dnaf[] = e.getDna();
		boolean dna1[], dna2[];
		int i = 0;
		boolean c = true;
		if (dnaf.length == dna.length) {
			dna1 = new boolean[dna.length];
			dna2 = new boolean[dna.length];
			while (i < dna.length) {
				if ((r.nextInt(100)+1) < per)
					c = !c;
				if (c) {
					dna1[i] = dnaf[i];
					dna2[i] = dna[i];
				} else {
					dna1[i] = dna[i];
					dna2[i] = dnaf[i];
				}
				i++;
			}
			ret[0] = giveBirth(ma.mutate(dna1, sameFathers));
			ret[1] = giveBirth(ma.mutate(dna2, sameFathers));
		}
		return ret;
	}

	protected boolean[] getDna() {
		return dna;
	}

	public Fitness getFitness() {
		return fitness;
	}

	@Override
	public int compareTo(Entity o) {
		return fitness.compareTo(o.getFitness());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(fitness);
		sb.append(" DNA:");
		for (int i = 0; i < dna.length; i++) {
			if(dna[i])
				sb.append('1');
			else
				sb.append('0');
		}
		return sb.toString();
	}

	public boolean familiar(Entity f2) {
		int smax, smin, count;
		boolean dna2[] = f2.getDna();
		boolean ret = false;
		double p;
		smax = Math.max(dna.length, dna2.length);
		smin = Math.min(dna.length, dna2.length);
		count = 0;
		for (int i = 0; i < smin; i++) {
			if(dna[i] == dna2[i])
				count++;
		}
		p = (count * 100)/smax;
		if(p >= familiar_per)
			ret = true;
		return ret;
	}

	public int getFamiliar_per() {
		return familiar_per;
	}

	public void setFamiliar_per(int familiar_per) {
		this.familiar_per = familiar_per;
	}
}
