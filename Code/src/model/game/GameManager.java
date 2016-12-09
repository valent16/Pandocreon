package model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.cards.ActionCard;
import model.cards.Divinity;
import model.cards.OriginCards.Believer;
import model.player.Player;



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
	public void melangerDivinites(){
		Collections.shuffle(listDivinites);
	}
	
	//Permet de les cartes de la pioche
	public void melangerPioche(){
		Collections.shuffle(pioche);
	}
	
	//Permet de m�langer les cartes de la pile de d�fausse
	public void melangerDefausse(){
		Collections.shuffle(defausse);
	}
	
	//Permet de d�fausser une carte action
	public void defausserCarte(ActionCard carte){
		defausse.add(carte);
	}

	//Permet de d�fausser une liste de carte action
	public void defausserCarte(LinkedList<ActionCard> cartes){
		defausse.addAll(cartes);
	}
	
	//Permet de d�fausser une divinite
	public void defausserDivinite(Divinity divinite){
		listDivinites.add(divinite);
	}
	
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
	
	public void deposerCroyant(Believer carte){
		croyants.add(carte);
	}
	
	public void retirerCroyant(Believer carte){
		croyants.remove(carte);
	}
	
	public void ajouterJoueur(Player joueur){
		players.add(joueur);
	}
	
	public void eliminerJoueur(Player joueur){
		players.remove(joueur);
	}
	
	//Permet de r�aliser l'initialisation des jeux
	public void intialisationDesJeux(){
		Iterator itJoueur = players.iterator();
		
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
			System.out.println("Tour N�"+cpt);
			players.get(start%players.size()).lancerDe();
			
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


	
	
	private Player getPlayer(int index){
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
