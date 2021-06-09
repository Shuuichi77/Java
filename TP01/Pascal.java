public class Pascal {
    public static int pascal(int nBut, int pBut) {
    	int n, i;
    	int[] tab = new int[nBut + 1];
    	
    	tab[0] = 1;

    	for (n = 1; n <= nBut ; n++) {
	        tab[n] = 1;

	        for(i = n - 1; i > 0; i--)
	            tab[i] = tab[i - 1] + tab[i];
  		}

        return tab[pBut];
    }


    public static void main(String[] args) {
        System.out.println(" Cn, p = " + pascal(30000, 250));
    }

    /* 
	Question :
	Le programme en Java est beaucoup plus rapide qu'en C.
	La différence de vitesse s'explique au fait qu'en C, on doit allouer l'espace pour un le tableau, qui ici est de taille 30 000
    */
}