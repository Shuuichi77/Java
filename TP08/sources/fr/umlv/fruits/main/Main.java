package fr.umlv.fruits.main;

import java.util.HashSet;

import fr.umlv.fruits.AppleKind;
import fr.umlv.fruits.Apple;
import fr.umlv.fruits.Pear;
import fr.umlv.fruits.Basket;

public class Main {
	public static void main(String[] args) {
		var apple1 = new Apple(11, AppleKind.Golden);
        var apple2 = new Apple(40, AppleKind.PinkLady);
        var pear = new Pear(5);
   
        var basket = new Basket();
        basket.add(apple1, 5);
        basket.add(apple2);
        basket.add(pear, 7);
        System.out.println(basket);
  	}
}
