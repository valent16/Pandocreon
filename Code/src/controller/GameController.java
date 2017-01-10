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

	/**Attribut correspondant a la partie*/
	private Game game;

	/**Constructeur*/
	public GameController(){
		game = new Game();
		vueJeu = new VueGame(this, game);//TODO A ENLEVER
	}

	/**Methode permettant de demarrer une partie*/
	public void startGame(){
		game.initGame();

		/*Human valentin = new Human("valentin",12);
		
		game.ajouterBot(new Bot("lala", new MediumStrategy()));
		game.ajouterBot(new Bot("fhazihfze", new MediumStrategy()));
		game.ajouterJoueurReel(valentin);
		
		TableJeu table = new TableJeu(valentin);
		JoueurController controllerJ = new JoueurController(valentin, table.getPanelJoueur());
		valentin.attacher(controllerJ);
		table.getPanelJoueur().initializeController(controllerJ);;
		
		GameManagerController GAController = new GameManagerController(table.getPanelTableJeu());
		De.getInstanceDe().attacher(GAController);
		
		GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(table.getPanelTableJeu()));*/
		
		game.nouvellePartie();
		
		GameManager.getInstanceUniqueManager().startGame();
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

//		joueur.attacher(new JoueurController(joueur));
		game.ajouterJoueurReel(joueur);

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
//		GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(new VueGameManager()));
		GameManager.getInstanceUniqueManager().startGame();
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
