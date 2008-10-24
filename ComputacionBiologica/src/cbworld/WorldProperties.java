package cbworld;

import java.util.Collection;

public class WorldProperties {
	
	private boolean show;
	private final int cycles;
	private int populationSize;
	private Collection<Entity> population;
	private final Engender engender;
	private final FathersSelector fatherSelector;
	private final EntityBuilder entityBuilder;
	private final String file;

	public WorldProperties(int cycles, int psize, Engender fornicator, FathersSelector fatherSelector, Collection<Entity> population, EntityBuilder entityBuilder, boolean show, String file) {
		this.cycles = cycles;
		this.populationSize = psize;
		this.population = population;
		this.fatherSelector = fatherSelector;
		this.engender = fornicator;
		this.entityBuilder = entityBuilder;
		this.show = show;
		this.file = file;
	}
	
	public void setPopulation(Collection<Entity> population) {
		this.population = population;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public int getCycles() {
		return cycles;
	}

	public Collection<Entity> getPopulation() {
		return population;
	}
	
	public FathersSelector getFatherSelector(){
		return fatherSelector;
	}

	public Engender getEngender() {
		return engender;
	}

	public void randomizePopulation(){
		population.clear();
		System.out.println("- Generando la poblacion:");
		for(int i = 0; i < populationSize; i++){
			population.add(entityBuilder.getEntity());
		}
		
	}

	public void printPopulation() {
		System.out.println("- Imprimiendo la poblacion:");
		for (Entity entity : population) {
			System.out.println(entity);
		}
	}

	public String getFile() {
		return file;
	}

	
	

}
