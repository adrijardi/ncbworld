package ncbworld.problems.aerialsp1;

import ncbworld.Entity;
import ncbworld.Fitness;
import ncbworld.World;

public class AerialsEntityB extends Entity {

	public AerialsEntityB(boolean[] dna) {
		super(dna);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Fitness fitness() {
		int x = 450;
		int y = 300;
		int[][] grid = new int[x][y];
		// AUMENTO EL NUMERO DE EVALUACIONES
		World.getInstance().nEvaluationsmm();
		// SE OBTIENEN LAS ANTENAS
		Aerial[] aerials = AerialsReader.getInstance().getA();
		double puntos_cubiertos = 0;
		int numero_antenas = 0;
		int numero_puntos = x * y;

		for (int i = 0; i < dna.length; i++) {
			if (dna[i]) {
				numero_antenas++;
				Aerial a = aerials[i];
				puntos_cubiertos += a.generarCoberturaAntena(grid, x, y);
			}
		}

		double coverage_rate = 100.0 * (puntos_cubiertos / numero_puntos);
		if (numero_antenas == 0) {
			return new DatosFitness(0, 0.0, 0.0);
		}
		double fitness;
		double pant = numero_antenas;
		if((85 - coverage_rate)> 0)
			fitness = (1+ (85 - coverage_rate))*pant;
		else
			fitness = pant+((100 - coverage_rate)/100);
		return new DatosFitness(numero_antenas, coverage_rate, fitness);
	}

	@Override
	protected Entity giveBirth(boolean[] dna) {
		return new AerialsEntityB(dna);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Entity[] reproduction(Entity e, boolean sameFathers) {
		Entity[] ret;
		World w = World.getInstance();
		int c = w.getCycle();
		int l = w.getLastUpdate();
		int p = c - l;
		switch (p % 50) {
		case 0:
		case 25:
			ret = defaultUniformCrossReproduction(e, sameFathers);
			break;
		default:
			ret = defaultMultiPercentageCrossReproduction(e, 4, sameFathers);
			break;
		}
		return ret;
	}

}
