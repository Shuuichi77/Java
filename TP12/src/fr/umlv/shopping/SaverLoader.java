package fr.umlv.shopping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SaverLoader {
	static final String SEPARATOR = "#";
	static final String BOOK_TYPE = "B";
	static final String VIDEO_GAME_TYPE = "G";
	static final String PREPAID_TYPE = "P";
	static final String saveFiled = "saveFiled.txt";
	
	public static void saveInTextFormat(List<Product> products, BufferedWriter bw) throws IOException {
		Objects.requireNonNull(products);
		Objects.requireNonNull(bw);
		
		/* Erase the content of saveFile.txt if it exists */
		try (FileWriter writer = new FileWriter(saveFiled)) {
			for (var product : products) {
				bw.write(product.toTextFormat() + "\n");
			}
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
	}
	
	
	public static List<Product> loadFromTextFormat(BufferedReader br) throws IOException {
		Objects.requireNonNull(br);
		
		List<Product> products = new ArrayList<>();
		String line;
		
		/* While we still have a line to read in the BufferedReader */
		while ((line = br.readLine()) != null) {
			var product = line.split(SEPARATOR);
			
			/* We add the appropriate Product depending of the first line's letter */
			switch(product[0]) {
			case BOOK_TYPE:
				var book = new Book(product[3], product[2], Integer.parseInt(product[1]));
				products.add(book);
				break;
				
			case VIDEO_GAME_TYPE:
				var videoGame = new VideoGame(product[2], VideoGame.Console.valueOf(product[3]), Integer.parseInt(product[1]));
				products.add(videoGame);
				break;
				
			case PREPAID_TYPE:
				var prepaid = new PrePaid(Integer.parseInt(product[1]), Integer.parseInt(product[2]));
				products.add(prepaid);
				break;
				
			default: /* We are not able to read the product's type */
				throw new IllegalArgumentException("The first line's letter (the type of the product) isn't recognized (" + product[0] + ")\n");
			}				
		}

		return products;
	}
	
	
	public static void main(String[] args) throws IOException {
		var sdb = new Book("S. de Beauvoir", "Mémoires d'une jeune fille rangée", 990);
		System.out.println(sdb.toTextFormat());
		// B#990#Mémoires d'une jeune fille rangée#S. de Beauvoir
		
		var zelda = new VideoGame("The legend of Zelda", VideoGame.Console.WII, 4950);
		System.out.println(zelda.toTextFormat());
		// G#4950#The legend of Zelda#WII
		
		var pp100 = new PrePaid(10000, 10);
		System.out.println(pp100.toTextFormat());
		// P#10000#10
		
		var list = List.of(sdb, zelda, pp100);
	    Path saveFilePath = Paths.get("./" + saveFiled); // nom du fichier de sauvegarde: "saveFile.txt"  
	    try(var writer = Files.newBufferedWriter(saveFilePath, 
	                                         StandardCharsets.UTF_8, 
	                                         StandardOpenOption.CREATE)) {
	    	
	      SaverLoader.saveInTextFormat(list, writer);
	    }
	    
	    try(FileReader fr = new FileReader("./" + saveFiled); 
	            BufferedReader br = new BufferedReader(fr);) {
	    	var list2 = SaverLoader.loadFromTextFormat(br); 
	    	
	    	System.out.println("--------------------------------------");
	    	for (var product : list2) {
		    	System.out.println(product);
		    }
	    }
	    
	    try(FileReader fr = new FileReader("./example.txt"); 
	            BufferedReader br = new BufferedReader(fr);) {
	    	var list2 = SaverLoader.loadFromTextFormat(br); 
	    	
	    	System.out.println("--------------------------------------");
	    	for (var product : list2) {
		    	System.out.println(product);
		    }
	    }
	}
}
