package fr.umlv.geom;

public class Ring extends Circle {
	private int subRadius;

	/* Constructor */
	public Ring(Point center, int radius, int subRadius) {
		super(center, radius);
		
		if (subRadius > radius) {
			throw new IllegalArgumentException("Error : subradius > radius");
		}
		
		this.subRadius = subRadius;
	}
	
	@Override
	public double surface() {
		return (Math.PI * radius() * radius()) - (Math.PI * subRadius * subRadius);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", InternalRadius : " + this.subRadius;
	}
	
	@Override
	public boolean contains(Point p) {
		/* subCircle is the surface of the ring which equals the hole of the ring.
		   Therefore :
		   - if p is in the circle of radius "radius", but is also in the circle of radius "subRadius",
		   it means p is inside the hole of the ring
		   
		   - if p is not in the circle of radius "subRadius", but is in the circle of radius "radius", 
		   it means p is the ring (not outside of the ring, nor in the hole of the ring) 
		*/
		Circle subCircle = new Circle(center(), subRadius);
		
		/* We add the latter part (this.distance(p) != subRadius) in case p is on the INNER border the ring :
		 * if p is on the inner border of the ring, it's considered to be IN the "radius" circle.
		 * However, !subCircle.contains(p) would also be true, since it's ON the border of subCircle (!false -> true).
		 * Therefore, without "this.distance(p) != subRadius", p would be considered IN the ring */
		return super.contains(p) && !subCircle.contains(p) && this.distance(p) != subRadius;
	}
	
	public boolean contains(Point p, Ring... rings) {
		/* All the circles in the parameters */
		for (Ring ring : rings) {
			if (ring.contains(p)) {
				return true;
			}
		}
		
		/* The circle using the method */
		return this.contains(p);
	}
}
