package ncbworld.problems.aerialsp1;

import ncbworld.EntityBuilder;
import ncbworld.MutatorAgent;
import ncbworld.Population;
import ncbworld.World;
import ncbworld.Population.PopulationSelection;

public class AerialsMain {
	private World w = World.getInstance();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 3) {
			AerialsMain am = null;
			if (args[0].compareTo("a") == 0)
				am = new AerialsMain(true, Integer.parseInt(args[1]), Integer
						.parseInt(args[2]));
			else if (args[0].compareTo("b") == 0)
				am = new AerialsMain(false, Integer.parseInt(args[1]), Integer
						.parseInt(args[2]));
			if (am != null)
				am.run();
		}
	}

	private void run() {
		w.run();
	}

	public AerialsMain(boolean b, int popSize, int cycles) {
		System.out.println(b + " " + popSize + " " + cycles);
		String file = getRoute(b, popSize, cycles);
		System.out.println(file);
		Aerial[] a = AerialsReader.getInstance().getA();
		if (a != null) {
			EntityBuilder eb = new AerialsEntityBuilder(b, a.length);
			Population p = new Population(popSize, eb);
			p.setSelection(PopulationSelection.TOURNAMENT);
			p.setDefaultDeath(true);
			p.setDefaultReproduction(true);
			w.setFile(file);
			w.setNMAXcycles(cycles);
			w.setPopulation(p);
			w.setShow(true);
			MutatorAgent ma = MutatorAgent.getInstance();
			if (b)
				ma.initMA(15, 0, 15, 10, 85, 0, 35, 50);
			else
				ma.initMA(15, 0, 5, 10, 75, 0, 2, 0);
		}
	}

	private String getRoute(boolean b, int popSize, int cycles) {
		StringBuilder sb = new StringBuilder();
		String fs = System.getProperty("file.separator");
		sb.append(".");
		sb.append(fs);
		sb.append("src");
		sb.append(fs);
		sb.append(this.getClass().getPackage().getName().replaceAll("\\.", fs));
		sb.append(fs);
		sb.append("results");
		sb.append(fs);
		sb.append("salida_");
		sb.append(b);
		sb.append("-");
		sb.append(popSize);
		sb.append("-");
		sb.append(cycles);
		sb.append(".txt");
		return sb.toString();
	}
}
