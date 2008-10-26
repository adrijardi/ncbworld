package ncbworld;

import java.util.Random;

public class MutatorAgent {

	// IMPLEMENTS PATTERN: SINGLETON//
	private static MutatorAgent INSTANCE = null;
	// Probabilidad de mutar un individuo con distintos padres
	private int pmenf;
	// Probabilidad de mutar un gen por translocaciones
	private int pmgtnf;
	// Probabilidad de mutar un gen por delecciones o duplicaciones
	private int pmgdnf;
	// Probabilidad de mutar un gen por inversion
	private int pmginf;

	// Probabilidad de mutar un individuo con los mismos padres
	private int pmesf;
	// Probabilidad de mutar un gen por translocaciones
	private int pmgtsf;
	// Probabilidad de mutar un gen por delecciones o duplicaciones
	private int pmgdsf;
	// Probabilidad de mutar un gen por inversion
	private int pmgisf;

	private Random r = new Random();
	
	public void initMA(int pmenf, int pmgdnf, int pmginf, int pmgtnf, int pmesf, int pmgdsf, int pmgisf, int pmgtsf){
		this.pmenf = pmenf;
		this.pmesf = pmesf;
		this.pmgdnf = pmgdnf;
		this.pmgdsf = pmgdsf;
		this.pmginf = pmginf;
		this.pmgisf = pmgisf;
		this.pmgtnf = pmgtnf;
		this.pmgtsf = pmgtsf;
	}

	// Private constructor suppresses
	public int getPmenf() {
		return pmenf;
	}

	public void setPmenf(int pmenf) {
		this.pmenf = pmenf;
	}

	public int getPmgtnf() {
		return pmgtnf;
	}

	public void setPmgtnf(int pmgtnf) {
		this.pmgtnf = pmgtnf;
	}

	public int getPmgdnf() {
		return pmgdnf;
	}

	public void setPmgdnf(int pmgdnf) {
		this.pmgdnf = pmgdnf;
	}

	public int getPgminf() {
		return pmginf;
	}

	public void setPgminf(int pgminf) {
		this.pmginf = pgminf;
	}

	public int getPmesf() {
		return pmesf;
	}

	public void setPmesf(int pmesf) {
		this.pmesf = pmesf;
	}

	public int getPmgtsf() {
		return pmgtsf;
	}

	public void setPmgtsf(int pmgtsf) {
		this.pmgtsf = pmgtsf;
	}

	public int getPmgdsf() {
		return pmgdsf;
	}

	public void setPmgdsf(int pmgdsf) {
		this.pmgdsf = pmgdsf;
	}

	public int getPgmisf() {
		return pmgisf;
	}

	public void setPgmisf(int pgmisf) {
		this.pmgisf = pgmisf;
	}

	private MutatorAgent() {
	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
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

	public boolean[] mutate(boolean[] dna, boolean sameFathers) {
		boolean[] ret = dna;
		if (sameFathers) {
			if (getProbabilityCase(pmesf))
				ret = mutateSameFathers(dna);
		} else {
			if (getProbabilityCase(pmenf))
				ret = mutateNotSameFathers(dna);
		}
		return ret;
	}

	private boolean[] mutateNotSameFathers(boolean[] dna) {
		boolean aux;
		int pos;
		for (int i = 0; i < dna.length; i++) {
			if (getProbabilityCase(pmginf)) {
				dna[i] = !dna[i];
			}
			if (getProbabilityCase(pmgtnf)) {
				pos = (i + r.nextInt(dna.length)) % dna.length;
				aux = dna[i];
				dna[i] = dna[pos];
				dna[pos] = aux;
			}
			if(getProbabilityCase(pmgdnf)){
				//TODO Not implemented yet
			}
		}
		return dna;
	}

	private boolean[] mutateSameFathers(boolean[] dna) {
		boolean aux;
		int pos;
		for (int i = 0; i < dna.length; i++) {
			if (getProbabilityCase(pmgisf)) {
				dna[i] = !dna[i];
			}
			if (getProbabilityCase(pmgtsf)) {
				pos = (i + r.nextInt(dna.length)) % dna.length;
				aux = dna[i];
				dna[i] = dna[pos];
				dna[pos] = aux;
			}
			if(getProbabilityCase(pmgdsf)){
				//TODO Not implemented yet
			}
		}
		return dna;
	}

	private boolean getProbabilityCase(int p) {
		boolean ret = r.nextInt(100) < p;
		return ret;
	}

}
