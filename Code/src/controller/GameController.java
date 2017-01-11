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
	public void startGame(List<String> listeNomBot, String strategie, List<String> listeNomHumain, List<Integer> listeAgeHumain){
//		game.initGame();
//
//		Human valentin = new Human("valentin",12);
//		
//		game.ajouterBot(new Bot("lala", new MediumStrategy()));
//		game.ajouterBot(new Bot("fhazihfze", new MediumStrategy()));
//		game.ajouterJoueurReel(valentin);
//		
//		TableJeu table = new TableJeu(valentin);
//		
//		JoueurController controllerJ = new JoueurController(valentin, table.getPanelJoueur());
//		valentin.attacher(controllerJ);
//		table.getPanelJoueur().initializeController(controllerJ);;
//		
//		GameManagerController GAController = new GameManagerController(table.getPanelTableJeu());
//		De.getInstanceDe().attacher(GAController);
//		
//		GameManager.getInstanceUniqueManager().initialisationController(new GameManagerController(table.getPanelTableJeu()));
//		
//		game.nouvellePartie();
//		
//		GameManager.getInstanceUniqueManager().startGame();
		//vueJeu.MenuPrincipal();/////////////////////////////////////TODO A ANLEVER PEUT ETRE
		
		game.initGame();
		
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
		
//		
//		
		
		
		
		
		
//		System.out.println("affichage des joueurs dans la partie "+ GameManager.getInstanceUniqueManager().getPlayers());//TODO A ENLEVER
//
//		System.out.println("Instanciation des Humains");//TODO A ENLEVER
//		//Creation et ajout des Humains dans la partie
//		Iterator<String> itNomHumain = listeNomHumain.iterator();
//		Iterator<Integer> itAgeHumain = listeAgeHumain.iterator();
//		while( itNomHumain.hasNext() && itAgeHumain.hasNext() ){
//			this.CreationJoueur(itNomHumain.next(), itAgeHumain.next());//instanciation des humains
//		}
		
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
