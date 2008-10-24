package ncbworld;

import ncbworld.Population.PopulationSelection;

public class World {
	
	private int cycle;
	private int nEvaluations;
	private int nMAXcycles;
	private Population population;
	private boolean show;
	
	//IMPLEMENTS PATTERN: SINGLETON//
	private static World INSTANCE = null;

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instantiación múltiple 
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new World();
		}
	}

	public static World getInstance() {
		if (INSTANCE == null)
			createInstance();
		return INSTANCE;
	}

	// Private constructor suppresses 
	private World() {
		cycle = 0;
		nMAXcycles = 50;
		nEvaluations = 0;
	}
	
	public void cyclemm() {
		this.cycle++;
	}

	private void death() {
		// TODO Auto-generated method stub
		
	}

	public int getCycle() {
		return cycle;
	}

	public int getNEvaluations() {
		return nEvaluations;
	}

	public int getNMAXcycles() {
		return nMAXcycles;
	}

	public void init(){
		cycle = 0;
		nEvaluations = 0;
		population.initPopulation();
	}

	public void nEvaluations() {
		this.nEvaluations++;
	}
	
	private void printCycle() {
		// TODO Auto-generated method stub
		
	}
	
	private void printResults() {
		// TODO Auto-generated method stub
		
	}

	private void reproduction() {
		this.population.reproduction();
		
	}

	public void run(){
		init();
		while(cycle <= nMAXcycles){
			cyclemm();
			selection();
			reproduction();
			death();			
			printCycle();
		}
		printResults();
	}

	private void selection() {
		this.population.selection();
	}

	public void setNMAXcycles(int xcycles) {
		nMAXcycles = xcycles;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
}
