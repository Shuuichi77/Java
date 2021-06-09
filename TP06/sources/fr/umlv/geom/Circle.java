package fr.umlv.geom;

public class Circle {
	private Point center;
	private int radius;
	
	/* Constructor */
	public Circle(Point center, int radius) {
		if (radius < 0) {
			throw new IllegalArgumentException("radius < 0");
		}
		
		this.center = new Point(center.x(), center.y());
		this.radius = radius;
	}
	
	/* Another constructor, in case we are not using a Point in parameters but 2 integers */
	public Circle(int x, int y, int radius) {
		if (radius < 0) {
			throw new IllegalArgumentException("Error : radius < 0");
		}
		
		this.center = new Point(x, y);
		this.radius = radius;
	}

	public int radius() {
		return radius;
	}
	
	public Point center() {
		Point temp = new Point(center.x(), center.y());
		return temp;
	}
	
	@Override
	public String toString() {
		return center + ", Radius : " + radius + ", Area : " + this.surface();
	}
	
	
	/**
	 * Add integers dx and dy to field center
	 * 
	 * @param dx Increased value of x
	 * @param dy Increased value of y
	 */
	public void translate(int dx, int dy) {
		center = new Point(center.x() + dx, center.y() + dy);
	}
	
	
	/**
	 * Calculates the area of the circle.
	 * @return Area of the circle.
	 */
	public double surface() {
		return Math.PI * radius * radius;
	}
	
	
	/**
	 * Calculates the distance between circle's center and point p.
	 * 
	 * @param p Point p.
	 * @return The distance between circle's center and point p.
	 */
	public double distance(Point p) {
		return Math.sqrt(Math.pow(p.x() - center.x(), 2) + Math.pow(p.y() - center.y(), 2));
	}
	
	
	/**
	 * Return true if the Point p is in the circle
	 * 
	 * @param p The point.
	 * @return True if p is in the circle, else false (false if it's on the border of the circle */
	public boolean contains(Point p) {	
		/* (Distance between Center and Point) < Circle's Radius */
		return this.distance(p) < radius;
	}
	
	
	/**
	 * Return true if the Point p is in one of the circles in the parameters, or the circle which use the method.
	 * 
	 * @param p The point.
	 * @param circles All the circles other than the one which use the method.
	 * @return True if p was in one of the circles.
	 */
	public boolean contains(Point p, Circle... circles) {
		/* All the circles in the parameters */
		for (Circle circle : circles) {
			if (circle.contains(p)) {
				return true;
			}
		}
		
		/* The circle using the method */
		return this.contains(p);
	}
}
