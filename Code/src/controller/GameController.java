package controller;

import model.game.Game;
import model.game.GameManager;
import model.player.Bot;
import model.player.Human;
import model.strategy.EasyStrategy;
import model.strategy.HardStrategy;
import model.strategy.MediumStrategy;
import model.strategy.Strategy;
import view.console.VueGame;
import view.console.VueGameManager;

/**Classe qui agit comme un controller de la partie*/
public class GameController {

	/**Attribut correspondant a la vue du jeu*/
	private VueGame vueJeu;

	/**Attribut correspondant a la parte*/
	private Game game;

	/**Constructeur*/
	public GameController(){
		game = new Game();
		vueJeu = new VueGame(this, game);
	}

	/**Methode permettant de demarrer une partie*/
	public void startGame(){
		game.initGame();
	}

	/**Methode permettant de verifier si le nombre de joueur est compris entre 2 et 10
	 * @return boolean true si le nombre est correct
	 */
	public boolean checkNbJoueur(int nb){
		if (nb >=2 && nb <= game.getNbJoueurMax()){
			return true;
		}
		return false;
	}

	/**Methode permettant la creation d'un joueur et de l'ajouter a la partie
	 * @param nom le nom du joueur
	 * @param age l'age du joueur 
	 */
	public void CreationJoueur(String nom, int age){
		Human joueur = new Human(nom, age);
		joueur.attacher(new JoueurController(joueur));
		game.ajouterJoueurReel(joueur); //on ajoute le joueur a la partie
	}

	/**Methode permettant la creation d'un bot et de l'ajouter a la partie
	 * @param nom le nom du joueur
	 * @param age l'age du joueur 
	 */
	public void CreationBot(String nom, String nomStrat){
		Strategy strat;

		switch(nomStrat){
		case "facile":
			strat = new EasyStrategy();
			break;
		case "medium":
			strat = new MediumStrategy();
			break;
		case "difficile":
			strat = new HardStrategy();
		default:
			strat = new MediumStrategy();
		}
		Bot bot = new Bot(nom, strat);
		game.ajouterBot(bot);//on ajoute le bot a la partie
	}

	/**Methode permettant de lancer la partie*/
	public void lancerPartie(){
		game.nouvellePartie();
		GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(new VueGameManager()));
		GameManager.getInstanceUniqueManager().startGame();
	}

	/**Methode permettant de lancer la partie en mode console ou en mode IHM*/
	public void lancerPartie(String mode){
		game.nouvellePartie();
		GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(new VueGameManager()));
		switch(mode){
		case "console"://lance la partie en mode console
			GameManager.getInstanceUniqueManager().startGameConsole();
			vueJeu.MenuPrincipal();//obliger de le laisser pour le main 4 mais ne marche pas//TODO VOIR SI ON PEUT METTRE CETTE METHODE A CET ENDROIT GameManager.getInstanceUniqueManager().startGameConsole(); //l'appel Console
			break;
		case "IHM"://lance la partie en mode graphique
			GameManager.getInstanceUniqueManager().startGameIHM();
		default:
			System.out.println("ce mode n'existe pas");
		}
	}

	/**gettre du model
	 * @return le mode du jeu
	 */
	public Game getGame(){
		return game;
	}

	/**Getter de la vue
	 * @return la vue du jeu
	 */
	public VueGame getVueGame(){
		return vueJeu;
	}
}
