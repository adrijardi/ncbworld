package ncbworld;

import java.util.LinkedList;

public class Population {
	private final int maxSize;
	private final LinkedList<Entity> entities;
	private final EntityBuilder eb;
	private Entity bestEverEntity;
	private Entity bestCycleEntity;

	public Population(int maxSize, EntityBuilder eb) {
		this.maxSize = maxSize;
		entities = new LinkedList<Entity>();
		this.eb = eb;
	}

	private void checkAndSetBestEntity(Entity newEntity) {
		if (newEntity != null) {
			if (bestCycleEntity == null) {
				bestCycleEntity = newEntity;
				print("New bestCycle: ",bestCycleEntity);
				if (bestEverEntity == null){
					bestEverEntity = bestCycleEntity;
					print("New bestEver: ",bestEverEntity);
				}
				else if (bestEverEntity.compareTo(bestCycleEntity) > 0){
					bestEverEntity = bestCycleEntity;
					print("New bestEver: ",bestEverEntity);
				}
			} else if (bestCycleEntity.compareTo(newEntity) > 0) {
				bestCycleEntity = newEntity;
				print("New bestCycle: ",bestCycleEntity);
				if (bestEverEntity == null){
					bestEverEntity = bestCycleEntity;
					print("New bestEver: ",bestEverEntity);
				}
				else if (bestEverEntity.compareTo(bestCycleEntity) > 0){
					bestEverEntity = bestCycleEntity;
					print("New bestEver: ",bestEverEntity);
				}
			}
		}
	}

	private void print(String ent, Entity e) {
		if(World.getInstance().isShow()){
			System.out.print(ent);
			System.out.println(e);
		}
	}

	private void defaultDeathMethoth() {
		int toDeath = entities.size() - maxSize;
		if (toDeath > 0) {
			//TODO
		}
	}

	public void initPopulation() {
		for (int i = 0; i < maxSize; i++) {
			entities.add(eb.getRandomEntity());
		}
	}

}
