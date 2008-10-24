package p1rnd;

import cbworld.Entity;
import cbworld.EntityBuilder;

public class RNDEntityBuilderB extends EntityBuilder {

	@Override
	public Entity createEntity() {
		RNDEntity ret = new RNDEntity(1000,1000,450,300,30,20,false);
		return ret;
	}

}