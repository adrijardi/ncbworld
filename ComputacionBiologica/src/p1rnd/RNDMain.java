package p1rnd;

import java.util.ArrayList;
import java.util.Collection;

import cbworld.EntityBuilder;
import cbworld.FathersSelector;
import cbworld.Engender;
import cbworld.World;
import cbworld.WorldProperties;
import cbworld.Entity;

public class RNDMain {
	
	private static RNDMain INSTANCE;
	private final Aerial[] aerials;
	private final World w;
	
	private final int tTorneo;
	private final int nhijos;
	private final int emutation;
	private final int atmutation;
	private final int acmutation;
	private final int acfmutation;
	
	public int getTTorneo() {
		return tTorneo;
	}

	public int getNhijos() {
		return nhijos;
	}

	public static RNDMain getInstance(){
		if(INSTANCE == null){
			INSTANCE = new RNDMain();
		}
		return INSTANCE;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RNDMain rnd = RNDMain.getInstance();
		rnd.start();
	}
	
	
	
	public void incrementNevaluations(){
		w.incrementNevaluations();
	}
	
	public RNDMain(){
		int cycles = 20000;
		int psize = 50;
		aerials = obtainAerials();
		/*for (int i = 0; i < aerials.length; i++) {
			System.out.println( aerials[i]);
			
		}*/
		tTorneo = 4;
		nhijos = 2;
		emutation = 10;
		atmutation = 10;
		acmutation = 5;
		acfmutation = 0;
		boolean ej1 = true;
		Engender f = new RNDEngender(emutation,atmutation,acmutation,acfmutation);
		FathersSelector fs = new RNDFathersSelector(tTorneo,nhijos);
		Collection<Entity> ce = new ArrayList<Entity>(psize);
		EntityBuilder eb;
		if(ej1)
			eb = new RNDEntityBuilder();
		else
			eb = new RNDEntityBuilderB();
		boolean show = true;
		String file = "./../salida.txt";
		w = new World(new WorldProperties(cycles, psize, f, fs, ce, eb, show,file));
	}

	public int getEmutation() {
		return emutation;
	}

	public int getAtmutation() {
		return atmutation;
	}

	public int getAcmutation() {
		return acmutation;
	}

	private Aerial[] obtainAerials() {
		return new AerialsReader().readAerials();
	}

	public Aerial[] getAerials() {
		return aerials;
	}

	private void start() {
		w.init();
		w.showPopulation();
		w.run();
	}

	public Entity getBestEntity() {
		return w.getBestEntity();
	}

}
