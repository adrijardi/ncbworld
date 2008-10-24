package p1rnd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class AerialsReader {
	private String fs = System.getProperty("file.separator");
	private String route = "." + fs + "p1rnd" + fs + "Candidatas.txt";
	private BufferedReader br;

	public AerialsReader() {
		init();
	}

	private void init() {
		try {
			br = new BufferedReader(new FileReader(route));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Aerial[] readAerials() {
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
		return lle.toArray(new Aerial[0]);
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
