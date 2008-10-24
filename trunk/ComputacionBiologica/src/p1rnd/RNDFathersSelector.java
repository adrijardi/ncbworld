package p1rnd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import cbworld.Entity;
import cbworld.FathersSelector;

public class RNDFathersSelector extends FathersSelector {
	
	private final int tTorneo;
	private final int nHijos;
	private int mf;
	private int f;
	
	public RNDFathersSelector(int tTorneo, int nHijos) {
		this.tTorneo = tTorneo;
		this.nHijos = nHijos;
	}
	
	
	
	@Override
	public Collection<Entity> getFathers(Collection<Entity> population) {
		Collection<Entity> fathers = new ArrayList<Entity>(mf);
		f = 0;
		mf = ((population.size()-1) /2);
		Entity[] e = population.toArray(new Entity[0]);
		while(mf >= f){
			fathers.add(tournament(e));
			f++;
		}
		return fathers;
	}



	private Entity tournament(Entity[] population) {
		Random dado = new Random();
		Entity best = population[dado.nextInt(population.length)];
		Entity next;
		for (int i = 0; i < tTorneo-1; i++) {
			next = population[dado.nextInt(population.length)];
			if(next.getDFitness() < best.getDFitness())
				best = next;
		}
		return best;
	}


}
