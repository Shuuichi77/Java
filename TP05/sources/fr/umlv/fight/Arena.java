package fr.umlv.fight;

public class Arena {
	/**
	 * Combat 2 robots jusqu'à que l'un meurt
	 * 
	 * @param robot1 Le premier combattant de type Robot
	 * @param robot2 Le second combattant de type Robot
	 * 
	 * @return Le robot qui gagne le combat
	 */
	public static Robot fight(Robot robot1, Robot robot2) {
		while (true) {
			/* On ne vérifie pas que robot1 est mort puisqu'on vérifie déjà à la fin :
			   on ne refera pas de boucle si robot2 l'a tué */
			robot1.fire(robot2);
			
			/* Si robot2 n'a pas été tué, il tire sur robot1 */
			if (!robot2.isDead()) {
				robot2.fire(robot1);
			}
			/* Affichage des pv pour s'assurer qu'il n'y ait aucun bug
			System.out.println(robot1 + " pv : " + robot1.getPv());
			System.out.println(robot2 + " pv : " + robot2.getPv());
			*/
			System.out.println();
			
			/* S'il y a eu un mort, on renvoie le vainqueur */
			if (robot1.isDead() || robot2.isDead()) {
				return robot1.isDead() ? robot2 : robot1;
			}
		}
	}
	
	public static void main(String[] args) {
		var d2r2 = new Robot("D2R2");
		var data = new Robot("Data");
		var john = new Fighter("John", 1);
		var jane = new Fighter("Jane", 2);
		
		System.out.println("D2R2 vs Data :");
		System.out.println(Arena.fight(d2r2, data) + " wins");
		
		System.out.println("\n\nJohn vs Jane :");
		System.out.println(Arena.fight(john, jane) + " wins");
	}
}
