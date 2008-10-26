package ncbworld;

public class World {

	private int cycle;
	private int nEvaluations;
	private int nMAXcycles;
	private Population population;
	private boolean show;
	private String file;
	private FileWriter fw;

	// IMPLEMENTS PATTERN: SINGLETON//
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
		file = "results.txt";
	}

	public void cyclemm() {
		this.cycle++;
	}

	private void death() {
		this.population.death();

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

	public void init() {
		fw = new FileWriter(file);
		cycle = 0;
		nEvaluations = 0;
		population.initPopulation();
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void nEvaluationsmm() {
		this.nEvaluations++;
	}

	private void printCycle() {
		//fw.println("\n\nPoblación del ciclo nº: " + cycle);
		//fw.println(population);
		fw.println(cycle+" "+population.bestToString());
	}
	
	public void printBestChangeCycle(){
		fw.println(cycle+" "+population.bestToString());
		//fw.println(population);
	}

	private void printResults() {
		fw.println("\n\nResultados");
		fw.println(population.bestToString());
		fw.println("\n Evaluations: "+nEvaluations);

	}

	private void reproduction() {
		this.population.reproduction();

	}
	
	public int getLastUpdate(){
		return population.getLastUpdate();
	}

	public void run() {
		init();
		while (cycle <= nMAXcycles) {
			cyclemm();
			selection();
			reproduction();
			death();
			//printCycle();
		}
		printResults();
		close();
	}

	private void close() {
		fw.close();
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
