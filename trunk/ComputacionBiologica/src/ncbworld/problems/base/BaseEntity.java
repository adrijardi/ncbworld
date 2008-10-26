package ncbworld.problems.base;

import ncbworld.Entity;
import ncbworld.Fitness;

public class BaseEntity extends Entity {

	public BaseEntity(boolean[] dna) {
		super(dna);
	}

	@Override
	protected Fitness fitness() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Entity giveBirth(boolean[] dna) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Entity[] reproduction(Entity e, boolean sameFathers) {
		// TODO Auto-generated method stub
		return null;
	}

}
