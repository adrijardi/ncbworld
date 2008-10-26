package ncbworld.problems.aerialsp1;

import java.util.LinkedList;
import java.util.Random;

import ncbworld.Entity;
import ncbworld.EntityBuilder;

public class AerialsEntityBuilder implements EntityBuilder {
	
	private final boolean problem;
	private final int maxDNAsize;
	
	public AerialsEntityBuilder(boolean problem, int maxDNAsize){
		this.problem = problem;
		this.maxDNAsize = maxDNAsize;
	}
	

	@Override
	public Entity getRandomEntity() {
		boolean dna[];
		Entity ret;
		if(problem){
			dna = randomDNAA();
			ret = new AerialsEntityA(dna);
		}else{
			dna = randomDNAB();
			ret = new AerialsEntityB(dna);
		}
		return ret;
	}
	
	protected boolean[] randomDNAA() {
		boolean ret[] = new boolean[maxDNAsize];
		Random d = new Random();
		int a = 50;
		Integer nr;
		LinkedList<Integer> lli = new LinkedList<Integer>();
		if (50 <= maxDNAsize) {
			while (a > 0) {
				nr = new Integer(d.nextInt(maxDNAsize));
				if (!lli.contains(nr)) {
					lli.add(nr);
					a--;
				}
			}
			for (Integer antena : lli) {
				ret[antena] = true;
			}
		}
		return ret;
	}


	private boolean[] randomDNAB() {
		Random r = new Random();
		boolean ret[] = new boolean[maxDNAsize];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = r.nextBoolean();
		}
		return ret;
	}

}
