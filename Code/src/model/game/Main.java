package model.game;


import controller.GameController;
import controller.JoueurController;
import model.EnumType.EnumCosmogonie;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import model.strategy.MediumStrategy;

/**Classe de lancement du programme*/
public class Main{
	
	/**Methode pour lancer l'application*/
	public static void main(String[] args) {	

		int valeur = 4;
		//1: lancement du jeu
		//2: test sur la classe joueur
		
		switch(valeur){
			case 1:
				//Game game = new Game();
				//game.initGame();
				
				//GameController gc = new GameController();
				//gc.startGame();
				testCreationPartie();
				break;
				
			case 2:
				testJoueur();
				break;
			case 3:
				testBot();
				break;
				
			case 4:
				Game game = new Game();
				game.initGame();
				GameController gc = new GameController();
				gc.startGame();
				break;
			default:
				//ne rien mettre
		}
	}
	
	//test du bot
	private static void testBot() {
		Game game = new Game();
		game.initGame();
		
		Bot b1 = new Bot("bot1", new MediumStrategy());
		Bot b2 = new Bot("bot2", new MediumStrategy());
		/*Bot b3 = new Bot("bot2", new EasyStrategy());
		Bot b4 = new Bot("bot2", new EasyStrategy());
		Bot b5 = new Bot("bot2", new EasyStrategy());*/
		
		game.ajouterBot(b1);
		game.ajouterBot(b2);
		/*game.ajouterBot(b3);
		game.ajouterBot(b4);
		game.ajouterBot(b5);*/
		
		game.nouvellePartie();	
		GameManager.getInstanceUniqueManager().startGame();
	}


	public static void testCreationPartie(){
		Game game = new Game();
		game.initGame();
		
		Human joueur1 = new Human("valentin", 18);
		joueur1.attacher(new JoueurController(joueur1));
		Human joueur2 = new Human("David", 20);
		joueur2.attacher(new JoueurController(joueur2));
//		Human joueur3 = new Human("Lucas", 20);
//		joueur3.attacher(new JoueurController(joueur3));
//		Bot IA1 = new Bot("henry", new EasyStrategy());
//		Bot IA2 = new Bot("jean jacque", new MediumStrategy());
//
//		game.ajouterBot(IA1);
//		game.ajouterBot(IA2);

		game.ajouterJoueurReel(joueur1);
		game.ajouterJoueurReel(joueur2);
//		game.ajouterJoueurReel(joueur3);
		
		game.nouvellePartie();
		GameManager.getInstanceUniqueManager().startGame();
		GameManager.getInstanceUniqueManager().afficherJoueur();
	}
	
	
	public static void testJoueur(){
		Player p1 = new Human("jean Yves", 12);
		p1.incrementerPointAction(EnumCosmogonie.JOUR, 2);
		System.out.println(p1.toString());
		//p1.decrementerPointAction(Cosmogonie.JOUR, 1);
		System.out.println(p1.toString());
	}
}

