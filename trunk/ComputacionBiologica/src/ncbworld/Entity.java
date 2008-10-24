package ncbworld;

import java.util.Random;

public abstract class Entity implements Comparable<Entity> {

	protected final boolean[] dna;
	protected Fitness fitness;

	public Entity(boolean[] dna) {
		this.dna = dna;
		init();
		fitness = fitness();
	}

	abstract protected void init();

	abstract protected Entity[] reproduction(Entity e);

	abstract protected Fitness fitness();

	abstract protected Entity giveBirth(boolean[] dna);

	protected Entity[] defaultSimpleCrossReproduction(Entity e) {
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
			ret[0] = giveBirth(ma.mutate(dna1));
			ret[1] = giveBirth(ma.mutate(dna2));
		}
		return ret;
	}

	protected Entity[] defaultUniformCrossReproduction(Entity e) {
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
			ret[0] = giveBirth(ma.mutate(dna1));
			ret[1] = giveBirth(ma.mutate(dna2));
		}
		return ret;
	}

	protected Entity[] defaultMultiPercentageCrossReproduction(Entity e, int per) {
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
			ret[0] = giveBirth(ma.mutate(dna1));
			ret[1] = giveBirth(ma.mutate(dna2));
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
}
