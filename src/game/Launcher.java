package game;

/**Classe de lancement du programme*/
public class Launcher{
	
	/**Methode pour lancer l'application*/
	public static void main(String[] args) {	
		Game game = new Game();  //Creation de la partie	
		game.initGame();  //lancement de la partie
	}
}
