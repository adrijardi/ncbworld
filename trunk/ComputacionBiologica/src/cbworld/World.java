package cbworld;

import java.util.Collection;

public class World {

	private int cycles;

	private WorldProperties wp;

	private Entity bestentity;

	private int nevaluations;

	private FileWriter fw;

	public World(WorldProperties wp) {
		cycles = 0;
		this.wp = wp;
		fw = new FileWriter(wp.getFile());
	}

	public void incrementNevaluations() {
		nevaluations++;
	}

	public void init() {
		wp.randomizePopulation();
		nevaluations = 0;
	}

	public void run() {
		int c = wp.getCycles();
		double bestfitness = Double.MAX_VALUE;
		calculateFitness();
		printPopulation();
		printBest();
		for (int i = 0; bestfitness == 0 || i < c; i++) {
			cycle();
			// showPopulation();
			if (i % 50 == 0) {
				printPopulation(i);
				printBest();
			}
			bestfitness = bestentity.getFitness().getFitness();
		}
		if (wp.isShow()) {
			printPopulation();
			printBest();
			showResults();
			showCycle();
		}
		fw.close();
	}

	private void printPopulation() {
		fw.println("\n\nPoblación:");
		for (Entity entity : wp.getPopulation()) {
			fw.println(entity);
		}
		;
	}

	private void printPopulation(int cycle) {
		fw.println("\n\nPoblación del ciclo nº: " + cycle);
		for (Entity entity : wp.getPopulation()) {
			fw.println(entity);
		}
		;
	}

	private void printBest() {
		fw
				.println("----------------------------------------------------\nMostrando el mejor caso:");
		fw.println(bestentity);
	}

	private void showResults() {
		System.out.println("- Mostrando el mejor caso:");
		System.out.println(bestentity);
	}

	private void showCycle() {
		System.out.println("- Mostrando el mejor caso del ciclo: " + cycles);
		System.out.println(bestentity);

	}

	public void cycle() {
		// Se eligen los individuos que se aparearan.
		Collection<Entity> fathers = selectFathers();

		// Se determina la siguiente generacion.
		wp.setPopulation(nextGeneration(fathers));
		fathers.clear();

		// Se calcula el fitness de cada individuo de la poblacion.
		calculateFitness();
		
		// Se matan los individuos
		

		// mostrar resultados si variable activada
		if (wp.isShow() && cycles % 10 == 0)
			showCycle();
		cycles++;
	}

	private Collection<Entity> nextGeneration(Collection<Entity> fathers) {
		Collection<Entity> ng = null;
		Engender f = wp.getEngender();
		f.setFathers(fathers);
		f.setPopulation(wp.getPopulation());
		ng = f.engender();
		return ng;
	}

	private Collection<Entity> selectFathers() {
		Collection<Entity> ret = null;
		FathersSelector fs = wp.getFatherSelector();
		ret = fs.getFathers(wp.getPopulation());
		return ret;
	}

	private void calculateFitness() {
		double fitness = Double.MAX_VALUE;
		bestentity = null;
		double nfit;
		for (Entity entity : wp.getPopulation()) {
			nfit = entity.calculateFitness();
			if (fitness > nfit) {
				bestentity = entity;
				fitness = entity.getDFitness();
			}
		}
	}

	public void showPopulation() {
		wp.printPopulation();

	}

	public Entity getBestEntity() {
		return bestentity;
	}

}
