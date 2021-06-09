import java.lang.Integer;
import java.util.Scanner;
import java.lang.Math;

public record Point (int x, int y) {
	public double distance(int x, int y) {
		return Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
	}

	public static void main(String[] args) {
		Point monPoint = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

		System.out.println("x = " + monPoint.x + ", y = " + monPoint.y);
		System.out.println("Distance = " + monPoint.distance(0, 0));
	}


    
}

/*
	Question 1 :
	La ligne de commande pour compiler "Point.java" est : java --enable-preview -source 15 Point.java
	
	Question 3 :
	Statique pour une méthode

	Question 4 :
	On a un message d'erreur, et le calcul ne peut pas s'effectuer

	Question 6 :
	Distance paramètre : int x et int y tel que (x, y) un point
	Distance return : La distance entre (Point.x, Point.y) et le point (x, y)
    */