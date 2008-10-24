package p1rnd;

import java.util.LinkedList;
import java.util.Random;

import cbworld.Entity;
import cbworld.Fitness;

public class RNDEntity extends Entity {

	private final int maxantenas;
	private final int numantenas;
	private final int x_map;
	private final int y_map;
	private int[][] grid;
	private final int radio_antena;
	private Aerial[] aerials = null;
	private final boolean ej1;

	public RNDEntity(int maxantenas, int numantenas, int x, int y,
			int radio_antena, int nchromosome, boolean ej1) {
		this.maxantenas = maxantenas;
		this.numantenas = numantenas;
		this.x_map = x;
		this.y_map = y;
		this.radio_antena = radio_antena;
		this.nchromosome = nchromosome;
		fitness = new DatosFitness(0, 0, 0);
		this.ej1 = ej1;
	}

	public RNDEntity(RNDEntity best) {
		super(best);
		this.maxantenas = best.maxantenas;
		this.numantenas = best.numantenas;
		this.x_map = best.x_map;
		this.y_map = best.y_map;
		this.radio_antena = best.radio_antena;
		this.ej1 = best.ej1;
	}

	@Override
	protected Fitness obtainFitness() {
		if (ej1)
			fitness = fitness();
		else
			fitness = fitness2();
		return fitness;
	}

	private Fitness fitness2() {
		RNDMain rndm = RNDMain.getInstance();
		this.grid = new int[x_map][y_map];
		// AUMENTO EL NUMERO DE EVALUACIONES
		rndm.incrementNevaluations();
		// SE OBTIENEN LAS ANTENAS
		if (aerials == null)
			aerials = rndm.getAerials();
		double puntos_cubiertos = 0;
		int numero_antenas = 0;
		int numero_puntos = this.x_map * this.y_map;

		for (int i = 0; i < dna.length; i++) {
			if (dna[i]) {
				numero_antenas++;
				Aerial a = aerials[i];
				puntos_cubiertos += generarCoberturaAntena(a.getX(), a.getY());
			}
		}

		double coverage_rate = 100.0 * (puntos_cubiertos / numero_puntos);
		if (numero_antenas == 0) {
			return new DatosFitness(0, 0.0, 0.0);
		}
		double fitness;
		double pant = numero_antenas * 100 / maxantenas;
		if((85 - coverage_rate)> 0)
			fitness = (1+ (85 - coverage_rate))*pant;
		else
			fitness = pant+((coverage_rate-85)/100);
	
		//System.out.println(fitness);
		return new DatosFitness(numero_antenas, coverage_rate, fitness);
	}

	private int generarCoberturaAntena(int x, int y) {
		int belonging_covered_points = 0;
		double h;
		for (int x1 = (x - this.radio_antena); x1 <= (x + this.radio_antena); x1++) {
			if (x1 >= 0 && x1 <= (this.x_map - 1)) {
				for (int y1 = (y - this.radio_antena); y1 <= (y + this.radio_antena); y1++) {
					if (y1 >= 0 && y1 <= (this.y_map - 1)) {
						h = Math
								.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
						if (h <= this.radio_antena) {
							grid[x1][y1]++; /* Increase the coverage */
							if (grid[x1][y1] == 1) { /* If new coverage, */
								belonging_covered_points++; /* take account */
							}
						}
					}
				}
			}
		}
		return belonging_covered_points;
	}

	public DatosFitness fitness() {
		RNDMain rndm = RNDMain.getInstance();
		this.grid = new int[x_map][y_map];
		// AUMENTO EL NUMERO DE EVALUACIONES
		rndm.incrementNevaluations();
		// SE OBTIENEN LAS ANTENAS
		if (aerials == null)
			aerials = rndm.getAerials();
		double puntos_cubiertos = 0;
		int numero_antenas = 0;
		int numero_puntos = this.x_map * this.y_map;

		for (int i = 0; i < dna.length; i++) {
			if (dna[i]) {
				numero_antenas++;
				Aerial a = aerials[i];
				puntos_cubiertos += generarCoberturaAntena(a.getX(), a.getY());
			}
		}

		double coverage_rate = 100.0 * (puntos_cubiertos / numero_puntos);
		if (numero_antenas == 0) {
			return new DatosFitness(0, 0.0, 0.0);
		}
		double fitness = 100 - coverage_rate;
		fitness *= 1 + (Math.abs(maxantenas - numero_antenas)) / 2;
		return new DatosFitness(numero_antenas, coverage_rate, fitness);
	}

	@Override
	protected void randomize() {
		Random d = new Random();
		int a;
		if(ej1)
			a = maxantenas;
		else
			a = d.nextInt(maxantenas);
		Integer nr;
		LinkedList<Integer> lli = new LinkedList<Integer>();
		if (maxantenas <= numantenas) {
			while (a > 0) {
				nr = new Integer(d.nextInt(numantenas));
				if (!lli.contains(nr)) {
					lli.add(nr);
					a--;
				}
			}
			for (Integer antena : lli) {
				dna[antena] = true;
			}
		}
	}

	@Override
	protected void init() {
		dna = new boolean[numantenas];
		for (int i = 0; i < dna.length; i++) {
			dna[i] = false;
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\n fit: ");
		sb.append(fitness);
		return sb.toString();
	}

	@Override
	public Entity getSon(boolean[] dna) {
		RNDEntity ret = new RNDEntity(maxantenas, numantenas, x_map, y_map,
				radio_antena, nchromosome, ej1);
		ret.dna = dna;
		;
		return ret;
	}

}
