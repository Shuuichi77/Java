public class Main {
	public int foo;
	
	public static void main(String[] args) {
		System.out.println("Hello Eclipse");
		
	}
}

/*
1/ sysout + Ctrl + Space = System.out.println();

2/ toStr + Ctrl + Space = 
@Override
public String toString() {
	// TODO Auto-generated method stub
	return super.toString();
}

3/ get + Ctrl + Space = 
public int getFoo() {
	return foo;
}

set + Ctrl + Space = 
public void setFoo(int foo) {
	this.foo = foo;
}

4/ Dans la classe, Source -> Generate Constructor using Fields... -> Selectionner Foo -> Generate

5/ Alt + Shift + R sur le nom de la classe permet de changer tous les identificateurs du nom de la classe par le nouveau nom qu'on lui attribut
Même chose pour le champ foo

6/ Alt + Shift + R sur "2 + 3" permet de donner un nom à une variable qui vaut "2 + 3",
et modifie tous les endroits où on a "2 + 3" par la variable qu'on vient de créer

7/ Permet de créer une nouvelle variable qui vaut "new Integer(2)", ce qu'on vient d'écrire

8/ Ouvre le contenu de la classe "String"

9/ Ouvre la classe de la méthode, ici toString()

10/ Indique où se trouve la variable dans tout le contenu du code

11/ Il ne se passe rien : 0 imports added

12/ Commente la ligne de code sur laquelle on se trouve (commentaire sur 1 ligne avec //)
*/
