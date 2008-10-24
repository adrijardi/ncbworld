package ncbworld;

import java.util.LinkedList;
import java.util.Random;

public class Population {
	private final int maxSize;
	private final LinkedList<Entity> population;
	private final LinkedList<Entity> fathers;
	private final EntityBuilder eb;
	private Entity bestEverEntity;
	private Entity bestCycleEntity;
	private PopulationSelection selection;

	public void setSelection(PopulationSelection selection) {
		this.selection = selection;
	}

	public enum PopulationSelection {
		ROULETTE, HIERARCHICAL, TOURNAMENT, OTHER;
	}

	public Population(int maxSize, EntityBuilder eb) {
		this.maxSize = maxSize;
		population = new LinkedList<Entity>();
		fathers = new LinkedList<Entity>();
		this.eb = eb;
	}

	private void checkAndSetBestEntity(Entity newEntity) {
		if (newEntity != null) {
			if (bestCycleEntity == null) {
				bestCycleEntity = newEntity;
				print("New bestCycle: ", bestCycleEntity);
				if (bestEverEntity == null) {
					bestEverEntity = bestCycleEntity;
					print("New bestEver: ", bestEverEntity);
				} else if (bestEverEntity.compareTo(bestCycleEntity) > 0) {
					bestEverEntity = bestCycleEntity;
					print("New bestEver: ", bestEverEntity);
				}
			} else if (bestCycleEntity.compareTo(newEntity) > 0) {
				bestCycleEntity = newEntity;
				print("New bestCycle: ", bestCycleEntity);
				if (bestEverEntity == null) {
					bestEverEntity = bestCycleEntity;
					print("New bestEver: ", bestEverEntity);
				} else if (bestEverEntity.compareTo(bestCycleEntity) > 0) {
					bestEverEntity = bestCycleEntity;
					print("New bestEver: ", bestEverEntity);
				}
			}
		}
	}

	private void print(String ent, Entity e) {
		if (World.getInstance().isShow()) {
			System.out.print(ent);
			System.out.println(e);
		}
	}

	private void defaultDeathMethoth() {
		int toDeath = population.size() - maxSize;
		if (toDeath > 0) {
			// TODO
		}
	}

	public void initPopulation() {
		for (int i = 0; i < maxSize; i++) {
			population.add(eb.getRandomEntity());
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

		for (int i = 0; i < psize / 2; i++) {
			first = population.get(r.nextInt(psize));
			second = population.get(r.nextInt(psize));
			if (first.compareTo(second) < 0)
				fathers.add(first);
			else
				fathers.add(second);
		}
	}

	public void reproduction() {
		Entity f1;
		Entity f2;
		
	}

}
