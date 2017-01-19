package controller;

import java.util.Iterator;

import java.util.List;

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
		vueJeu = new VueGame(this, game);
	}

	/**Methode permettant de demarrer une partie
	 * @param listeNomBot les noms des bots
	 * @param strategie la stratégie des bots
	 * @param listeNomHumain les noms des joueurs humains
	 * @param listeAgeHumain les ages des joueurs humains
	 */
	public void startGame(List<String> listeNomBot, String strategie, List<String> listeNomHumain, List<Integer> listeAgeHumain){
		game.initGame();
		Game.setStrategie(strategie);//on lui donne la strategie qu'aurons les bots
		if (listeNomHumain.size() == 0){
			
			Iterator<String> itBot = listeNomBot.iterator();
			while(itBot.hasNext()){
				game.ajouterBot(new Bot(itBot.next(), getStrategie(strategie)));
			}
			TableJeu table = new TableJeu(null);
		
			GameManagerController GAController = new GameManagerController(table.getPanelTableJeu());
			De.getInstanceDe().attacher(GAController);
			
			GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(table.getPanelTableJeu()));
			game.nouvellePartie();
			GameManager.getInstanceUniqueManager().startGame();
		}else{
			Human joueur = new Human(listeNomHumain.get(0), listeAgeHumain.get(0));
			
			Iterator<String> itBot = listeNomBot.iterator();
			while(itBot.hasNext()){
				game.ajouterBot(new Bot(itBot.next(), getStrategie(strategie)));
			}
			game.ajouterJoueurReel(joueur);
			TableJeu table = new TableJeu(joueur);
			
			JoueurController controllerJ = new JoueurController(joueur, table.getPanelJoueur());
			joueur.attacher(controllerJ);
			table.getPanelJoueur().initializeController(controllerJ);
			
			GameManagerController GAController = new GameManagerController(table.getPanelTableJeu());
			De.getInstanceDe().attacher(GAController);
			
			GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(table.getPanelTableJeu()));
			game.nouvellePartie();
			GameManager.getInstanceUniqueManager().startGame();
		}
	}

	/**Methode permettant de verifier si le nombre de joueur est compris entre 2 et 10
	 * @param nb le nombre de joueur
	 * @return boolean true si le nombre est correct
	 */
	public boolean checkNbJoueur(int nb){
		if (nb >=2 && nb <= game.getNbJoueurMax())
			return true;
		return false;
	}

	/**Methode permettant la creation d'un joueur et de l'ajouter a la partie
	 * @param nom le nom du joueur
	 * @param age l'age du joueur 
	 */
	public void CreationJoueur(String nom, int age){
		Human joueur = new Human(nom, age);
		game.ajouterJoueurReel(joueur);
	}

	/**Methode permettant la creation d'un bot et de l'ajouter a la partie
	 * @param nom le nom du joueur
	 * @param nomStrat l'age du joueur 
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
	
	/**Methode permettant de definir la strategie des bots
	 * @param nomStrat la stratégie choisi
	 * @return l'objet de type strategie
	 */
	public Strategy getStrategie(String nomStrat){
		switch(nomStrat){
		case "facile":
			return new EasyStrategy();
		case "medium":
			return new MediumStrategy();
		case "difficile":
			return new HardStrategy();
		default:
			return new MediumStrategy();
		}
	}

	/**Methode permettant de lancer la partie*/
	public void lancerPartie(){
		game.nouvellePartie();
		GameManager.getInstanceUniqueManager().startGame();
	}

	/**getter du model
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
