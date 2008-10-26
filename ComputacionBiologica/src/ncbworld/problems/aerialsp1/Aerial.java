package ncbworld.problems.aerialsp1;

public class Aerial {
	private final int x;
	private final int y;
	private final int radio_antena;
	private final String rep;
	
	public Aerial(int x, int y){
		this.x = x;
		this.y = y;
		this.radio_antena = 30;
		StringBuilder sb = new StringBuilder();
		sb.append("X: ");
		sb.append(x);
		sb.append("Y: ");
		sb.append(y);
		this.rep = sb.toString();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return rep;
	}
	
	public int generarCoberturaAntena(int[][] grid, int x_map, int y_map) {
		int belonging_covered_points = 0;
		double h;
		for (int x1 = (x - this.radio_antena); x1 <= (x + this.radio_antena); x1++) {
			if (x1 >= 0 && x1 <= (x_map - 1)) {
				for (int y1 = (y - this.radio_antena); y1 <= (y + this.radio_antena); y1++) {
					if (y1 >= 0 && y1 <= (y_map - 1)) {
						h = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
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

}
