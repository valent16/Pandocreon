package model.game;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedList;
import model.cards.ActionCard;
import model.cards.Divinity;
import model.gestionDonnees.DataManager;
import model.gestionDonnees.FakeSaver;
import model.gestionDonnees.ParserXML;
import model.player.Bot;
import model.player.Human;
import model.player.Player;


/**Classe qui gere la partie*/
public class Game {
	
	/**Attribut representant des noms de bots*/
	private final static String[] BOT_NAME = {"Eden", "Alice", "Carla", "Andre", "Eline", "Cleo", "Ava", "Chloe", "Eva"};
	
	/**Attribut representant le nombre de carte maximal qu'un joueur puisse posseder*/
	private final static int NB_CARTE_MAX_MAIN = 7;

	/**Attribut representant le nombre de joueur maximal dans une partie*/
	private int nbJoueurMax;
	
	/**Attribut representant la liste des joueurs et des bots dans la partie*/
	private ArrayList<Player> players = new ArrayList<Player>();

	/**Attribut representant la liste de cartes actions du jeu*/
	public LinkedList<ActionCard> listeCartesAction = new LinkedList<ActionCard>();

	/**Attribut representant la liste de divinités du jeu*/
	private LinkedList<Divinity> listeCartesDivinites = new LinkedList<Divinity>();

	/**Attribut representant la gestionnaire de donnees permettant de recuperer les cartes*/
	DataManager dataManager;
	
	/**Constructeur pour initialiser le DataManager*/
	public Game(){
		dataManager = new DataManager(new FakeSaver(), new ParserXML());
	}
	
	/**Methode permettant d'initialiser la partie: charger les carte, le nombre de joueurs et de bots*/
	public void initGame() {
		this.listeCartesDivinites = dataManager.getGestionnaireChargement().chargerDivinites();
		this.listeCartesAction = dataManager.getGestionnaireChargement().chargerCartes();
		this.calculerNbJoueurMax();
	}
	
	/**Methode permettant de determiner le nombre maximal de joueur pour une partie de jeu*/
	private void calculerNbJoueurMax(){
		if (listeCartesAction.size()/NB_CARTE_MAX_MAIN > listeCartesDivinites.size()){
			this.nbJoueurMax = listeCartesDivinites.size();
		}else{
			this.nbJoueurMax = listeCartesAction.size()/NB_CARTE_MAX_MAIN;
		}
	}
	
	/**Méthode qui permet d'initialiser une nouvelle partie*/
	public void nouvellePartie(){	
		GameManager.getInstanceUniqueManager().initialisationPartie(this.listeCartesAction, this.listeCartesDivinites); //transmission du deck de jeu	
		Iterator<Player> it = players.iterator(); //transmission de la liste de joueurs
		while (it.hasNext()){
			GameManager.getInstanceUniqueManager().ajouterJoueur((Player) it.next());
		}
	}
	

	/**Méthode permettant d'ajouter un joueur à la partie
	 * @param joueur le joueur réél à ajouter
	 */
	public void ajouterJoueurReel(Human joueur){
		this.players.add(joueur);
	}

	/**Méthode permettant d'ajouter un bot à la partie
	 * @param joueur le bot à ajouter
	 */
	public void ajouterBot(Bot joueur){
		this.players.add(joueur);
	}
	
	/**Getter qui permet de recuperer le nombre maximal de joueur
	 * @return le nombre de joueur maximal possible dans une partie
	 */
	public int getNbJoueurMax(){
		return this.nbJoueurMax;
	}
	
	/** Getter qui permet de recuperer le nombre de joueur
	 * @return le nombre de joueur
	 */
	public int getNbJoueur(){
		return players.size();
	}
	
	/**Methode permettant d'attribuer un nom a un bot a partir d'un index
	 * @param index numero ou se trouve le nom
	 * @return le nom du bot
	 */
	public static String getBotName(int index){
		index = index%BOT_NAME.length;
		return BOT_NAME[index];
	}
}