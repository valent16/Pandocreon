package controller;

import model.game.De;
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
import view.ihm.TableJeu;

/**Classe qui agit comme un controller de la partie*/
public class GameController {

	/**Attribut correspondant a la vue du jeu*/
	private VueGame vueJeu;

	/**Attribut correspondant a la parte*/
	private Game game;

	/**Constructeur*/
	public GameController(){
		game = new Game();
		
		
//		vueJeu = new VueGame(this, game);
	}

	/**Methode permettant de demarrer une partie*/
	public void startGame(){
		game.initGame();
		Human valentin = new Human("valentin",12);
		
		game.ajouterBot(new Bot("lala", new MediumStrategy()));
		game.ajouterBot(new Bot("fhazihfze", new MediumStrategy()));
		game.ajouterJoueurReel(valentin);
		
		TableJeu table = new TableJeu(valentin);
		valentin.attacher(new JoueurController(valentin, table.getPanelJoueur()));
		
		GameManagerController GAController = new GameManagerController(table.getPanelTableJeu());
		De.getInstanceDe().attacher(GAController);
		
		GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(table.getPanelTableJeu()));
		
		game.nouvellePartie();
		
		GameManager.getInstanceUniqueManager().startGame();
		//vueJeu.MenuPrincipal();/////////////////////////////////////TODO A ANLEVER PEUT ETRE
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

	/**Methode permettant la creation d'un joueur a partir de parametres
	 * @param nom le nom du joueur
	 * @param age l'age du joueur 
	 */
	public void CreationJoueur(String nom, int age){
		Human joueur = new Human(nom, age);
//		joueur.attacher(new JoueurController(joueur));
		game.ajouterJoueurReel(joueur);
	}

	/**Methode permettant la creation d'un bot a partir de parametres
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
			//Lancement Exception
			strat = new MediumStrategy();
		}

		Bot bot = new Bot(nom, strat);
		game.ajouterBot(bot);//TODO voir si c'est comme ca qu'on ajoute le bot
	}

	/**Methode permettant de lancer la partie*/
	public void lancerPartie(){
		game.nouvellePartie();
//		GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(new VueGameManager()));
		GameManager.getInstanceUniqueManager().startGame();
	}

	/**Methode permettant de lancer la partie en mode console ou en mode IHM*/
	public void lancerPartie(String mode){
		game.nouvellePartie();
//		GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(new VueGameManager()));
		switch(mode){
		case "console"://lance la partie en mode console
			GameManager.getInstanceUniqueManager().startGameConsole();
			//vueJeu.MenuPrincipal();
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
