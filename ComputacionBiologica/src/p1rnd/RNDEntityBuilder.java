package p1rnd;

import cbworld.Entity;
import cbworld.EntityBuilder;

public class RNDEntityBuilder extends EntityBuilder {

	@Override
	public Entity createEntity() {
		RNDEntity ret = new RNDEntity(50,1000,450,300,30,20,true);
		return ret;
	}

}
