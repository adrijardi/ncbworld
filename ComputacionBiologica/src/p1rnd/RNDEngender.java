package p1rnd;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import cbworld.Entity;
import cbworld.Engender;

public class RNDEngender extends Engender {
	private final int emutation;
	private final int atmutation;
	private final int acmutation;
	private final int acfmutation;
	private Entity best;
	private final Random dado;

	public RNDEngender(int emutation, int atmutation, int acmutation, int acfmutation) {
		this.emutation = emutation;
		this.atmutation = atmutation;
		this.acmutation = acmutation;
		this.acfmutation = acfmutation;
		dado = new Random();
	}

	@Override
	public Collection<Entity> engender() {
		RNDMain rndm = RNDMain.getInstance();
		best = rndm.getBestEntity();
		int elitsm = population.size() - ((fathers.size() - 1) * 2);
		Collection<Entity> ret = new LinkedList<Entity>();
		setElitsm(ret, elitsm);
		Entity f1 = null;
		Entity f2 = null;
		for (Entity entity : fathers) {
			f2 = f1;
			f1 = entity;
			if (f2 != null) {
				if (f1.compareTo(f2) == 0) {
					ret.add(familiarMutate(getSon(f1, f2, f1.getNchromosome())));
					ret.add(familiarMutate(getSon(f2, f1, f2.getNchromosome())));
				} else {
					ret.add(willmutate(getSon(f1, f2, f1.getNchromosome())));
					ret.add(willmutate(getSon(f2, f1, f2.getNchromosome())));
				}
			}
		}
		return ret;
	}

	private Entity willmutate(Entity son) {
		int p = dado.nextInt(100);
		Entity ret;
		if (p < emutation)
			ret = mutate(son);
		else
			ret = son;
		return ret;
	}

	private Entity getSon(Entity f1, Entity f2, int crossing) {
		Entity ret;
		boolean p;
		int dnal = f1.getDna().length;
		boolean dna[] = new boolean[dnal];
		boolean dnaf1[] = f1.getDna();
		boolean dnaf2[] = f2.getDna();
		int lenght = 0;
		for (int i = 0; i + crossing < dna.length; i += crossing) {
			p = dado.nextBoolean();
			if (p) {
				System.arraycopy(dnaf1, i, dna, i, crossing);
			} else {
				System.arraycopy(dnaf1, i, dna, i, crossing);
			}
			lenght = i + crossing;
		}
		if (lenght < dna.length) {
			p = dado.nextBoolean();
			if (p) {
				System.arraycopy(dnaf1, lenght, dna, lenght, dna.length
						- lenght);
			} else {
				System.arraycopy(dnaf1, lenght, dna, lenght, dna.length
						- lenght);
			}
		}

		ret = f1.getSon(dna);
		return ret;
	}

	private void setElitsm(Collection<Entity> ret, int elitsm) {
		Entity c;
		if (elitsm >= 1)
			ret.add(best);
		for (int i = 1; i < elitsm; i++) {
			c = new RNDEntity((RNDEntity)best);
			ret.add(familiarMutate(c));
		}
	}

	private Entity mutate(Entity entity) {
		boolean[] dna = entity.getDna();
		int pt, pc, pos;
		boolean aux;
		for (int i = 0; i < dna.length; i++) {
			pt = dado.nextInt(100);
			pc = dado.nextInt(100);

			if (pc < acmutation) {
				dna[i] = !dna[i];
			}
			if (pt < atmutation) {
				pos = (i + dado.nextInt(dna.length)) % dna.length;
				aux = dna[i];
				dna[i] = dna[pos];
				dna[pos] = aux;
			}
		}
		entity.setDna(dna);
		return entity;
	}
	
	private Entity familiarMutate(Entity entity) {
		boolean[] dna = entity.getDna();
		int pt, pcf,pos;
		boolean aux;
		for (int i = 0; i < dna.length; i++) {
			pt = dado.nextInt(100);
			pcf = dado.nextInt(100);

			if (pcf < acfmutation) {
				dna[i] = !dna[i];
			}
			if (pt < atmutation) {
				pos = (i + dado.nextInt(dna.length)) % dna.length;
				aux = dna[i];
				dna[i] = dna[pos];
				dna[pos] = aux;
			}
		}
		entity.setDna(dna);
		return entity;
	}

}
