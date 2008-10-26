package ncbworld.problems.aerialsp1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class AerialsReader {
	private String route;
	private BufferedReader br;
	private Aerial[] a;
	//IMPLEMENTS PATTERN: SINGLETON//
	private static AerialsReader INSTANCE = null;

	// Private constructor suppresses 
	private AerialsReader() {
		route = getRoute();
		init();
		readAerials();
		close();
	}

	private String getRoute() {
		StringBuilder sb = new StringBuilder();
		String fs = System.getProperty("file.separator");
		sb.append(".");
		sb.append(fs);
		sb.append("bin");
		sb.append(fs);
		sb.append(this.getClass().getPackage().getName().replaceAll("\\.", fs));
		sb.append(fs);
		sb.append("files");
		sb.append(fs);
		sb.append("Candidatas.txt");
		return sb.toString();
	}

	public Aerial[] getA() {
		return a;
	}

	// creador sincronizado para protegerse de posibles problemas  multi-hilo
	// otra prueba para evitar instantiación múltiple 
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AerialsReader();
		}
	}

	public static AerialsReader getInstance() {
		if (INSTANCE == null)
			createInstance();
		return INSTANCE;
	}

	private void init() {
		try {
			br = new BufferedReader(new FileReader(route));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void readAerials() {
		LinkedList<Aerial> lle = new LinkedList<Aerial>();
		int x, y;
		try {
			String[] aecoord;
			String sx;
			String sy;
			StringBuilder nline = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				nline.append(line.trim());
				line = br.readLine();
			}

			line = nline.toString().replaceAll(" ", "");
			aecoord = line.split(",");
			for (int i = 0; i < aecoord.length; i += 2) {
				sx = aecoord[i];
				sy = aecoord[i + 1];
				if (sx != null && sx.length() >= 0) {
					x = Integer.parseInt(sx);
					y = Integer.parseInt(sy);
					lle.add(new Aerial(x, y));
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a = lle.toArray(new Aerial[0]);
	}

	private void close() {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
