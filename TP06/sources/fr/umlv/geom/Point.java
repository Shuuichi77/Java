package fr.umlv.geom;

public class Point {
	private int x;
	private int y;
	
	/* Constructor */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + ", "+ y + ")";
	}
	
	/**
	 * Add integer dx to field x, and integer dy to field y
	 * 
	 * @param dx Increased value of x
	 * @param dy Increased value of y
	 */
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}
}