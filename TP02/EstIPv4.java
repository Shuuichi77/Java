import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EstIPv4 {

	/**
 	 * Vérifie qu'un String est une adresse IPv4
 	 *
 	 * @param string Chaine de caractère qu'on va tester
 	 * @return True ou False si c'est une adresse IPv4 ou non
 	 */
	public static boolean isIPv4(String string) {
		String octet = "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
		StringBuilder regExpIpv4 = new StringBuilder();

		/* octet 	  = expression régulière d'un nombre entre 0 et 255
		   regExpIpv4 = octet.octet.octet.octet */
		for (int i = 0 ; i < 4 ; i++) {
			regExpIpv4.append(octet);
			if (i < 3) {
				regExpIpv4.append("\\.");
			}
		}

		return Pattern.matches(regExpIpv4.toString(), string);
	}


	/**
 	 * Convertit un String en tableau de byte s'il est de la forme d'une adresse IPv4 ()
 	 *
 	 * @param adresse Chaine de caractère correspondant à l'adresse IPv4
 	 * @return Tableau de byte de taille 4 contenant l'adresse IPv4 
 	 */
	public static byte[] IPv4Array(String adresse) {
		String octet = "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
		StringBuilder regExpIpv4 = new StringBuilder();

		/* octet 	  = expression régulière d'un nombre entre 0 et 255
		   regExpIpv4 = octet.octet.octet.octet */
		for (int i = 0 ; i < 4 ; i++) {
			regExpIpv4.append(octet);
			if (i < 3) {
				regExpIpv4.append("\\.");
			}
		}

		Pattern ipv4Pattern = Pattern.compile(regExpIpv4.toString());
		Matcher matcher = ipv4Pattern.matcher(adresse);
		byte[] res = new byte[4];

		if (matcher.matches()) {
			for (int i = 0 ; i < 4 ; i++) {
				int tmp = Integer.parseInt(matcher.group(i + 1));
				res[i] = (byte)tmp;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		byte[] byteArray = new byte[4];

		for (String arg : args) {
			if (isIPv4(arg)) {
				byteArray = IPv4Array(arg);

				/* On peut print les éléments du tableau pour vérifier */
				System.out.println(arg + " :");
				for (byte b : byteArray) {
					int n = b & 0xFF;
					System.out.println(n);
				}

				System.out.println();
			}
		}
	}
}