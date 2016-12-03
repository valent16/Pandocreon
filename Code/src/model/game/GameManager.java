package model.game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.cards.ActionCard;
import model.cards.Divinity;
import model.cards.OriginCards.Believer;
import model.player.Player;

public class GameManager {
	private static volatile GameManager managerUnique;

	private LinkedList<ActionCard> pioche = new LinkedList<ActionCard>();

	private List<ActionCard> defausse = new LinkedList<ActionCard>();

	/**Représente les cartes croyants sur la table*/
	private List<ActionCard> croyants = new LinkedList<ActionCard>();

	private List<Divinity> listDivinites = new LinkedList<Divinity>();

	private List<Player> players = new LinkedList<Player>();

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
	
	/**Méthode qui permet de commencer la partie*/
	public static void startGame() {
		/*int i =  0;
		if(presenceBOT == true){
			choisirDifficulteBot();
		}
		//System.out.println(jdc.getCarte());
		jdc.melangerCarte();
		Distribuer();*/
	}

	// a voir si on fait une methode tour ou une classe
	/*public static void Tour(int i){
		//nbCarteValide(i);
	 * Affiche les caract�ristiquent de la carte Talon*/
	// TestTalon();
	/*afficherJoueurEnCours(i);

		if(verificationMainJoueur()==false ){
			//while(verificationMainJoueur()==false){
			if(nbCarteValide(i)==true){
				if(Players.get(i).getNom() != "BOT"){
					tour = new Scanner(System.in);
					System.out.println("Quelle carte jouer ?");
					int carteAPoser = tour.nextInt();

					/*
	 * Création d'une carte qui s'appel carteTest, et qui prend les caract�ristiques de la carte que l'on souhaite jouer.
	 * Celle-ci servira donc pour valider le coup
	 */


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
		this.setDivinites(divinites); 
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
	 * Assigne une Divinité a un joueur
	 * @param joueur, Le joueur auquel on assigne la Divinité
	 * @param div, La Divinité a assigner
	 */
	public void assignerDivinite(Player joueur, Divinity div){
		joueur.setDivinity(div);
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

	public List<Divinity> getDivinites() {
		return listDivinites;
	}

	public void setDivinites(LinkedList<Divinity> divinites) {
		this.listDivinites = divinites;
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
