public class PrintArgs {
    public static void main(String[] args) {
    	for(String arg : args) {
    		System.out.println(arg);
    	}
    }

    /* 
	Question 1 :
	Si on ne met pas d'argument et qu'on veut afficher seulement le 1er argument
	en faisant la ligne de code "System.out.println(args[0]);"" on obtient :
	Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0 at PrintArgs.main(PrintArgs.java:5)
    Le tableau args n'existe pas
	*/
}	