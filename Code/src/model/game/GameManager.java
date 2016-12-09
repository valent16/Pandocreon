package model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import model.cards.ActionCard;
import model.cards.Divinity;
import model.cards.OriginCards.Believer;
import model.player.Player;

/**Classe qui gère tous les elements de la partie*/
public class GameManager {

	private final static int NB_CARTE_MAX_MAIN = 7;

	private static volatile GameManager managerUnique;

	private LinkedList<ActionCard> pioche = new LinkedList<ActionCard>();

	private LinkedList<ActionCard> defausse = new LinkedList<ActionCard>();

	/**Représente les cartes croyants sur la table*/
	private LinkedList<ActionCard> croyants = new LinkedList<ActionCard>();

	private LinkedList<Divinity> listDivinites = new LinkedList<Divinity>();

	private ArrayList<Player> players = new ArrayList<Player>();

	private Player joueurDebutTour;

	private Player joueurActif;


	/**
	 * Méthode qui permet d'avoir une seule insatance du gestionnaire de partie
	 * @return le gestionnaire de partie
	 */
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

	/**Méthode permettant de mélanger les cartes divintés*/	
	public void melangerDivinites(){
		Collections.shuffle(listDivinites);
	}

	/**Méthode permettant de mélanger les cartes de la pioche*/
	public void melangerPioche(){
		Collections.shuffle(pioche);
	}

	/**Méthode qui permet de mélanger les cartes de la pile de défausse*/
	public void melangerDefausse(){
		Collections.shuffle(defausse);
	}

	/**
	 * Méthode qui défausse une carte action
	 * @param carte la carte action a defausser qui sera ajoute dans la liste defausse
	 */
	public void defausserCarte(ActionCard carte){
		defausse.add(carte);
	}

	/**
	 * Méthode qui permet de défausser une liste de carte action
	 * @param cartes listes de cartes action à défausser
	 */
	public void defausserCarte(LinkedList<ActionCard> cartes){
		defausse.addAll(cartes);
	}

	/**
	 * Méthode qui permet de défausser une carte divinite
	 * @param divinite la carte divinite à défausser
	 */
	public void defausserDivinite(Divinity divinite){
		listDivinites.add(divinite);
	}

	/**
	 * Méthode qui permet de récuperer une divinité
	 * @return la divinité a l'index 0 de la liste
	 */
	public Divinity piocherDivinite(){
		return listDivinites.pop();
	}


	/**
	 * Permet d'ajouter un joueur au gestionnaire de partie
	 */
	public void ajouterJoueur(Player joueur){
		players.add(joueur);
	}

	//Permet de réaliser l'initialisation des jeux
	public void intialisationDesJeux(){
		Iterator<Player> itJoueur = players.iterator();
		//Permet l'initialisation des divinites des joueurs
		while(itJoueur.hasNext()){
			Player joueur = (Player) itJoueur.next();
			joueur.piocherDivinite();
		}

		//Permet l'intialisation des cartes action des joueurs
		for (int i=0; i<NB_CARTE_MAX_MAIN; i++){
			itJoueur = players.iterator();
			while(itJoueur.hasNext()){
				Player joueur = (Player) itJoueur.next();
				joueur.piocher();;
			}
		}
	}


	/**Méthode qui permet de commencer la partie*/
	public void startGame() {
		this.melangerDivinites();
		this.melangerPioche();
		this.intialisationDesJeux();
	}

	/**
	 * Permet d'éliminer un joueur de la partie
	 * @param joueur
	 */
	public void eliminerJoueur(Player joueur){
		players.remove(joueur);
	}

	/**
	 * Permet d'initialiser la partie de jeu
	 * @param listeCartesAction
	 * @param listeCartesDivinites
	 */
	public void initialisationPartie(LinkedList<ActionCard> listeCartesAction, LinkedList<Divinity> listeCartesDivinites ){
		this.setDivinites(listeCartesDivinites); 
		this.pioche = listeCartesAction;
	}

	/**
	 * Permet d'ajouter un croyant sur la table de jeu
	 * @param carte
	 */
	public void deposerCroyant(Believer carte){
		croyants.add(carte);
	}

	/**
	 * Assigne une Divinité a un joueur
	 * @param joueur, Le joueur auquel on assigne la Divinité
	 * @param div, La Divinité a assigner
	 */
	public void assignerDivinite(Player joueur, Divinity div){
		joueur.setDivinity(div);
	}


	/**
	 * Permet de retirer un croyant de la table de jeu
	 * @param carte le croyant à retirer
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
			//melangerPioche();
		}
		return pioche.pop();
	}



	public LinkedList<Divinity> getDivinites() {
		return listDivinites;
	}

	public void setDivinites(LinkedList<Divinity> divinites) {
		this.listDivinites = divinites;
	}

	public Player getPlayer(int index){
		return players.get(index);
	}

	public Player getJoueurDebutTour() {
		return joueurDebutTour;
	}

	public void setJoueurDebutTour(Player joueurDebutTour) {
		this.joueurDebutTour = joueurDebutTour;
	}

	public Player getJoueurActif() {
		return joueurActif;
	}

	public void setJoueurActif(Player joueurActif) {
		this.joueurActif = joueurActif;
	}

}
