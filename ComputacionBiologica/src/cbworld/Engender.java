package cbworld;

import java.util.Collection;

public abstract class Engender {
	
	protected Collection<Entity> fathers;
	protected Collection<Entity> population;
	
	public Engender(){
		
	}

	public void setFathers(Collection<Entity> fathers) {
		this.fathers = fathers;
	}

	public void setPopulation(Collection<Entity> population) {
		this.population = population;
	}

	public abstract Collection<Entity> engender();
	
}
