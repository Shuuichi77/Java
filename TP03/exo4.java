import java.util.Arrays;

public class exo4 {
	/**
 	 * Echange la valeur de 2 cases d'un tableau de int
 	 *
 	 * @param array Le tableau qu'on va modifier
 	 * @param index1 L'indice de la 1ere case 
 	 * @param index2 L'indice de la 2nde case 
 	 */
	public static void swap(int[] array, int index1, int index2) {
		if (index1 < 0 || index2 < 0 || index1 >= array.length || index2 >= array.length) {
			throw new IllegalArgumentException("Erreur (swap) : Un des indices est en-dessous ou au-dessus de la taille du tableau)");
		}

		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	/**
 	 * Renvoie l'indice de la plus petite valeur d'un tableau de int entre les indices "start" et "end"
 	 *
 	 * @param array Le tableau qu'on etudie
 	 * @param index1 L'indice a partir duquel on commence la recherche (inclu)
 	 * @param index2 L'indice a partir duquel on termine la recherche (exclu)
 	 *
 	 * @return Indice du plus petit int trouvé entre les indices indiqués
 	 */
	public static int indexOfMin(int[] array, int start, int end) {
		if (start < 0 || end < 0 || start >= array.length || end > array.length) {
			throw new IllegalArgumentException("Erreur (indexOfMin) : Un des indices est en-dessous ou au-dessus de la taille du tableau)");
		}

		else if (start >= end) {
			/* On comprend aussi start = end, puisqu'on doit normalement inclure start mais pas end, donc un peu ambigu */
			throw new IllegalArgumentException("Erreur (indexOfMin) : Les indices ne sont pas valides (start >= end)");
		}

		int indiceMin = start;
		for (int i = start + 1 ; i < end ; i++) {
			if (array[i] < array[indiceMin]) {
				indiceMin = i;
			}
		}

		return indiceMin;
	}

	/**
 	 * Trie un tableau de int dans l'ordre croissant
 	 *
 	 * @param array Le tableau qu'on va trier
 	 */
	public static void sort(int[] array) {
		for (int i = 0 ; i < array.length - 1 ; i++) {	
			swap(array, i, indexOfMin(array, i, array.length));
		}
	}


	/* Main */
	public static void main(String[] args) {
		int[] myArray = new int[] {9, 7, 5, 3, 2, 1, -3, -5};
		
		System.out.println("Tableau avant tri : " + Arrays.toString(myArray));
		sort(myArray);
		System.out.println("Tableau apres tri : " + Arrays.toString(myArray));
	}
}