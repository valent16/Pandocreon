package game;

import java.util.Collections;
import java.util.LinkedList;
import cards.ActionCard;
import cards.Believer;
import cards.Divinity;
import player.Player;

public class GameManager {
	private static volatile GameManager managerUnique;
	
	private LinkedList<ActionCard> pioche = new LinkedList<ActionCard>();
	
	private LinkedList<ActionCard> defausse = new LinkedList<ActionCard>();
	
	/**Représente les cartes croyants sur la table*/
	private LinkedList<ActionCard> croyants = new LinkedList<ActionCard>();
	
	private LinkedList<Divinity> divinites = new LinkedList<Divinity>();
	
	private LinkedList<Player> players = new LinkedList<Player>();
	
	private Player joueurDebutTour;
	
	private Player joueurActif;
	
	/**
	 * Constructeur privé du singleton GameManager
	 */
	private GameManager(){
		
	}
	
	public static GameManager getInstanceUniqueManager(){
		if (managerUnique == null){
			synchronized (GameManager.class){ //pour gerer le multi-thread
				if (managerUnique == null){
					managerUnique= new GameManager();
				}
			}
		}
		return managerUnique;
	}
	
	/**
	 * m�thode permettant de m�langer les cartes du jeu
	 */
	public void melangerCartes(){
		Collections.shuffle(pioche);
		Collections.shuffle(defausse);
	}
	
	/**
	 * Permet d'ajouter un joueur au gestionnaire de partie
	 */
	public void ajouterJoueur(Player joueur){
		players.add(joueur);
	}
	

	/**
	 * Permet d'�liminer un joueur de la partie
	 * @param joueur
	 */
	public void eliminerJoueur(Player joueur){
		players.remove(joueur);
	}
	
	/**
	 * Permet d'initialiser la partie de jeu
	 * @param cartesAction
	 * @param divinites
	 */
	public void initialisationPartie(LinkedList<ActionCard> cartesAction, LinkedList<Divinity> divinites ){
		this.divinites = divinites; 
		this.pioche = cartesAction;
	}
	
	/**
	 * Permet d'ajouter un croyant sur la table de jeu
	 * @param carte
	 */
	public void deposerCroyant(Believer carte){
		croyants.add(carte);
	}

	/**
	 * Permet de retirer un croyant de la table de jeu
	 * @param carte
	 */
	public void retirerCroyant(Believer carte){
		croyants.remove(carte);
	}
	
	public ActionCard piocherCarte(){
		if (pioche.size() == 0){
			if (defausse.size() == 0){
				//lancer une exception qui arrete la partie => pas assez de carte pour jouer
			}
			pioche.addAll(defausse);
			melangerCartes();
		}
		return pioche.pop();
	}
	
	public void defausserCarte(ActionCard carte){
		defausse.add(carte);
	}
	
	public void defausserCarte(LinkedList<ActionCard> cartes){
		defausse.addAll(cartes);
	}
	
}
