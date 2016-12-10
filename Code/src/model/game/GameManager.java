package model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import model.cards.ActionCard;
import model.cards.Card;
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


	public Player getJoueurDebutTour() {
		return joueurDebutTour;
	}

	private void setJoueurDebutTour(Player joueurDebutTour) {
		this.joueurDebutTour = joueurDebutTour;
	}

	public Player getJoueurActif() {
		return joueurActif;
	}

	private void setJoueurActif(Player joueurActif) {
		this.joueurActif = joueurActif;
	}
	
	public int getNbJoueur(){
		return players.size();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public LinkedList<ActionCard> getCroyants() {
		return croyants;
	}

	public void setCroyants(LinkedList<ActionCard> croyants) {
		this.croyants = croyants;
	}

	
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
	
	public void initialisationPartie(LinkedList<ActionCard> cartesAction, LinkedList<Divinity> divinites ){
		this.listDivinites = divinites; 
		this.pioche = cartesAction;
	}
	
	public void startGame() {
		this.melangerDivinites();
		this.melangerPioche();
		this.intialisationDesJeux();
		this.deroulementTourJeu();
	}
	
	//M�thode permettant de m�langer les cartes divinit�s


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
	
	public ActionCard piocherCarte(){
		if (pioche.size() == 0){
			if (defausse.size() == 0){
				//lancer une exception qui arrete la partie => pas assez de carte pour jouer
			}
			pioche.addAll(defausse);
			melangerPioche();
		}
		return pioche.pop();
	}
	
	public void deposerCroyant(Card carte){
		if(carte instanceof Believer){
			croyants.add((Believer) carte);
		}else{
			//lancement d'un exception si la carte n'est pas un croyant
		}
	}
	
	
	public void ajouterJoueur(Player joueur){
		players.add(joueur);
	}

	
	//Permet de r�aliser l'initialisation des jeux

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

	
	public void deroulementTourJeu(){
		int start = 5;
		int cpt = 0;
		boolean b = true;
		
		//players.size()>2
		while(b){
			players.get(start%players.size()).lancerDe();
			System.out.println("\nTour Numero "+cpt+ " le de est sur la face "+ De.getInstanceDe().getFace());
			
			for (int i = start; i<start+this.getNbJoueur(); i++){
				players.get(i%players.size()).jouerTour();	
				System.out.println(players.get(i%players.size()).toString());
			}
			start = start+1;
			start = start%players.size();
			
			cpt++;
			if (cpt == 4){
				b = false;
			}
		}
	}

	/**
	 * Permet d'éliminer un joueur de la partie
	 * @param joueur
	 */
	public void eliminerJoueur(Player joueur){
		players.remove(joueur);
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

	
	//Permet de d�terminer l'index du plus jeune joueur
	public int getIndexJoueurPlusJeune(){
		Player joueurJeune;
		int index = 0;
		joueurJeune = players.get(index);
		for (int i = 1; i<players.size(); i++){
			if(joueurJeune.getAge() > players.get(i).getAge()){
				joueurJeune = players.get(i);
				index = i;
			}
		}
		return index;
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
	
	
	/*
	 * M�thodes de test d'affichage, ces m�thodes seront supprim�es par la suite
	 */
	public void afficherJoueur(){
		for (Player p : players){
			System.out.println(p.toString());
		}
	}
}
