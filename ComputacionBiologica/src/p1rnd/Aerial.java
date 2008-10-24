package p1rnd;

public class Aerial {
	private final int x;
	private final int y;
	private final String rep;
	
	public Aerial(int x, int y){
		this.x = x;
		this.y = y;
		StringBuilder sb = new StringBuilder();
		sb.append("X: ");
		sb.append(x);
		sb.append("Y: ");
		sb.append(y);
		rep = sb.toString();
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

}
