package ncbworld;

public class MutatorAgent {

	//IMPLEMENTS PATTERN: SINGLETON//
	private static MutatorAgent INSTANCE = null;

	// Private constructor suppresses 
	private MutatorAgent() {
	}

	// creador sincronizado para protegerse de posibles problemas  multi-hilo
	// otra prueba para evitar instantiación múltiple 
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MutatorAgent();
		}
	}

	public static MutatorAgent getInstance() {
		if (INSTANCE == null)
			createInstance();
		return INSTANCE;
	}
	
	public boolean[] mutate(boolean[] dna){
		return dna;
	}
	
	

}
