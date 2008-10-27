package ncbworld;

import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;

public class Population {
	private final int maxSize;
	private final TreeMap<Fitness, Entity> population; // No se utilia la
														// interfaz del map, no
														// se desea editar el
														// equal de 2 entidades
	private final LinkedList<Entity> fathers;
	private final EntityBuilder eb;
	private Entity bestEverEntity;
	private Entity bestCycleEntity;
	private PopulationSelection selection;
	private boolean defaultReproduction;
	private boolean defaultDeath;
	private int lastUpdate;

	public void setSelection(PopulationSelection selection) {
		this.selection = selection;
	}

	public Entity getBestEverEntity() {
		return bestEverEntity;
	}

	public enum PopulationSelection {
		ROULETTE, HIERARCHICAL, TOURNAMENT, OTHER;
	}

	public Population(int maxSize, EntityBuilder eb) {
		this.maxSize = maxSize;
		population = new TreeMap<Fitness, Entity>();
		fathers = new LinkedList<Entity>();
		this.eb = eb;
		this.defaultReproduction = true;
		this.defaultDeath = true;
		lastUpdate = 0;
	}

	public boolean isDefaultReproduction() {
		return defaultReproduction;
	}

	public void setDefaultReproduction(boolean defaultReproduction) {
		this.defaultReproduction = defaultReproduction;
	}

	public boolean isDefaultDeath() {
		return defaultDeath;
	}

	public void setDefaultDeath(boolean defaultDeath) {
		this.defaultDeath = defaultDeath;
	}

	private void checkAndSetBestEntity(Entity newEntity) {
		World w = World.getInstance();
		if (newEntity != null) {
			if (bestCycleEntity == null) {
				bestCycleEntity = newEntity;
				//print("New bestCycle: ", bestCycleEntity);
			} else if (bestCycleEntity.compareTo(newEntity) > 0) {
				bestCycleEntity = newEntity;
				//print("New bestCycle: ", bestCycleEntity);
			}
			
			if (bestEverEntity == null) {
				bestEverEntity = bestCycleEntity;
				lastUpdate = w.getCycle();
				w.printBestChangeCycle();
				print("New bestEver: ", bestEverEntity);
			} else if (bestEverEntity.compareTo(bestCycleEntity) > 0) {
				bestEverEntity = bestCycleEntity;
				lastUpdate = w.getCycle();
				w.printBestChangeCycle();
				print("New bestEver: ", bestEverEntity);
			}
		}
	}

	public int getLastUpdate() {
		return lastUpdate;
	}

	private void print(String ent, Entity e) {
		StringBuilder sb = new StringBuilder();
		World w = World.getInstance();
		if (w.isShow()) {
			sb.append(w.getCycle());
			sb.append(" ");
			sb.append(ent);
			sb.append(e);
			System.out.println(sb.toString());
		}
	}

	private void defaultDeathMethoth() {
		int toDeath = population.size() - maxSize;
		while (toDeath > 0) {
			population.remove(population.lastKey());
			toDeath--;
		}
	}

	public void initPopulation() {
		Entity e;
		population.clear();
		bestCycleEntity = null;
		bestEverEntity = null;
		
		for (int i = 0; i < maxSize; i++) {
			e = eb.getRandomEntity();
			checkAndSetBestEntity(e);
			population.put(e.getFitness(), e);
		}
	}

	public void selection() {
		fathers.clear();
		switch (selection) {
		case TOURNAMENT:
			defaultTournamentSelection();
			break;
		case HIERARCHICAL:
			System.out.println("Not implemented yet");
			System.exit(-1);
			break;
		case ROULETTE:
			System.out.println("Not implemented yet");
			System.exit(-1);
			break;
		case OTHER:
			System.out.println("Not implemented yet");
			System.exit(-1);
			break;

		}

	}

	private void defaultTournamentSelection() {
		Random r = new Random();
		int psize = population.size();
		Entity first, second;
		Fitness[] sfk= population.keySet().toArray(new Fitness[0]);
		for (int i = 0; i < psize / 2; i++) {
			first = population.get(sfk[r.nextInt(sfk.length)]);
			second = population.get(sfk[r.nextInt(sfk.length)]);
			if (first.compareTo(second) < 0)
				fathers.add(first);
			else
				fathers.add(second);
		}
	}

	public void reproduction() {
		if (defaultReproduction)
			defaultReproductionMethod();
	}

	private void defaultReproductionMethod() {
		Entity f1 = null;
		Entity f2 = null;
		Entity[] sons;
		for (Entity entity : fathers) {
			f2 = f1;
			f1 = entity;
			if (f2 != null) {
				sons = f1.reproduction(f2, f1.familiar(f2));
				for (int i = 0; i < sons.length; i++) {
					checkAndSetBestEntity(sons[i]);
					population.put(sons[i].getFitness(), sons[i]);
				}
			}
		}

	}

	public void death() {
		if (defaultDeath)
			defaultDeathMethoth();
		else
			System.out.println("Not implemented yet");

	}
	
	public String bestToString(){
		StringBuilder sb = new StringBuilder();
		//sb.append("\nBestCycleEntity: ");
		//sb.append(bestCycleEntity);
		sb.append("BestEverEntity: ");
		sb.append(bestEverEntity);
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("-------\nPoblación: ");
		sb.append(population.size());
		bestToString();
		for (Entity e : population.values()) {
			sb.append("\n");
			sb.append(e);
		}
		sb.append("\n-------");
		return sb.toString();
	}

	public void setInitialPopulation(Entity[] pe) {
		population.clear();
		bestCycleEntity = null;
		bestEverEntity = null;
		for (int i = 0; i < pe.length; i++) {
			checkAndSetBestEntity(pe[i]);
			population.put(pe[i].getFitness(), pe[i]);
		}
	}

}
