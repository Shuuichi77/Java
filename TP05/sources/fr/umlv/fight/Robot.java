package fr.umlv.fight;

public class Robot {
	private String nom;
	private int pv = 10;
	
	/* Constructeur */	
	public Robot (String nom) {
		this.nom = nom;
	}
	
	/* getter nom */
	public String getNom() {
		return nom;
	}

	/**
	 * Modélise la chance d'atteindre la cible. 
	 * 
	 * @return Toujours true puisque c'est un Robot
	 */
	boolean rollDice() {
		return true;
	}
	
	/**
	 * Le robot courant tire sur le robot pris en parametre en lui enlevant 2pv
	 * 
	 * @param robotEnnemi Robot qui va perdre 2pv
	 */
	public void fire (Robot other) {
		/* On lance rollDice avant d'exécuter fire : si rollDice est true, on tire */
		if (rollDice()) {
			if (other.isDead()) {
				throw new IllegalArgumentException(other + " est déjà mort, vous ne pouvez plus lui tirer dessus");
			}
			
			other.pv -= 2;
			System.out.println(other + " a été touché par " + this + " !");
		}
		
		/* rollDice est faux, on a raté la cible */
		else {
			System.out.println(this + " a raté " + other + " !");
		}
	}
	
	/**
	 * Permet de savoir si un robot est mort, donc a 0pv ou moins (car s'il avait 1pv et on tire sur lui, -1)  
	 * 
	 * @return True s'il a 0pv ou moins, False sinon
	 */
	public boolean isDead () {
		return this.pv == 0;
	}
	
	@Override
	public String toString () {
		return "Robot " + this.getNom();
	}
}

/*
Exercice 1

1/ On ne peut pas utiliser un record pour la classe Robot puisqu'on ne pourrait plus 
modifier les points de vie du Robot une fois qu'on les a initialisés : le champ "pv" serait final

*/


