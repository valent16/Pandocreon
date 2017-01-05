package model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import controller.GameManagerController;
import model.cards.ActionCard;
import model.cards.Card;
import model.cards.Divinity;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.player.Player;
import view.IObservateurGameManager;
import view.console.VueGameManager;

/**Classe qui gère tous les elements de la partie*/
public class GameManager implements IObservableGameManager {
	
	private final static int NB_CARTE_MAX_MAIN = 7;
	
	private IObservateurGameManager observateur = new GameManagerController(new VueGameManager());

	private static volatile GameManager managerUnique;

	private LinkedList<ActionCard> pioche = new LinkedList<ActionCard>();

	private LinkedList<ActionCard> defausse = new LinkedList<ActionCard>();

	/**Représente les cartes croyants sur la table*/
	private LinkedList<Believer> croyants = new LinkedList<Believer>();

	private LinkedList<Divinity> listDivinites = new LinkedList<Divinity>();

	private ArrayList<Player> players = new ArrayList<Player>();

	private Player joueurDebutTour;

	private Player joueurActif;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	//Permet de recupeerer toutes les divinites et la pioche
	public void initialisationPartie(LinkedList<ActionCard> cartesAction, LinkedList<Divinity> divinites ){
		this.listDivinites = divinites; 
		this.pioche = cartesAction;
	}
	
	//Methode de demarrage de la partie
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
	
	//permet de piocher une carte
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
	
	//permet de deposer un coroyant sur la table
	public void deposerCroyant(Card carte){
		if(carte instanceof Believer){
			croyants.add((Believer) carte);
		}else{
			//lancement d'un exception si la carte n'est pas un croyant
		}
	}
	
	//permet d'ajouter un joueur ou un bot a la partie
	public void ajouterJoueur(Player joueur){
		players.add(joueur);
	}
	
	public List<Believer> getCroyantCompatibles(SpiritGuide guide){
		ArrayList<Believer> croyantsAPresenter = new ArrayList<Believer>();
		
		Believer croyant;
		Iterator<Believer> itCroyant = GameManager.getInstanceUniqueManager().getCroyants().iterator();
		//TODO Iterator<EnumDogme> itDogme;
		
		while(itCroyant.hasNext()){
			croyant = itCroyant.next();
			if (guide.isCroyantCompatible(croyant)){
				croyantsAPresenter.add(croyant);
			}
		}
		
		return croyantsAPresenter;
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

	//Tour de jeu
	public void deroulementTourJeu(){
		int start = 1;
		int cpt = 0;
		while(players.size()!=0){//TODO A remettre au lieu de 10 tours
		//while(cpt<10){
			System.out.println("TOUR = "+ cpt);
			players.get(start%players.size()).lancerDe();
			//System.out.println("\nTour Numero "+cpt+ " le de est sur la face "+ De.getInstanceDe().getFace());
			
			for (int i = start; i<start+this.getNbJoueur(); i++){
				if (players.size() != 0){
//					System.out.println(players.get(i%players.size()).toString());
					players.get(i%players.size()).jouerTour();
				}
			}
			start = start+1;
			if (players.size() != 0){
				start = start%players.size();
			}
			cpt++;
			System.out.println();
			afficherCroyants();
			System.out.println();
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
	 * Assigne une Divinite a un joueur
	 * @param joueur, Le joueur auquel on assigne la Divinite
	 * @param div, La Divinite a assigner
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

	
	//Permet de determiner l'index du plus jeune joueur
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
	
	/**Methode qui affiche les croyants en commun sur la table a chaque tour*/
	public void afficherCroyants(){
		Iterator<Believer> it = getCroyants().iterator();
		System.out.println("-----------------------------------------------------");
		System.out.println("les croyants sur la table");
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("-----------------------------------------------------");
	}
	
	public void eliminationJoueurFaible(){
		Player p;
		Player joueurElimine = null;
		boolean egalite = false;
		Iterator<Player> itPlayer = players.iterator();
		
		while(itPlayer.hasNext()){
			p = itPlayer.next();
			if (joueurElimine == null){
				joueurElimine =p;
			}else{
				if(joueurElimine.getScore() < p.getScore()){
					p = joueurElimine;
				}
			}
		}
		
		itPlayer = players.iterator();
		while(itPlayer.hasNext()){
			if(joueurElimine.getScore() == itPlayer.next().getScore()){
				egalite = true;
			}
		}
		if(!egalite){
			notifyPlayerDefeat(joueurElimine);
			this.players.remove(joueurElimine);
		}
	}
	
	public void determinerVainqueur(){
		Player p;
		Player joueurGagnant = null;
		int cpt = 0;
		Iterator<Player> itPlayer = players.iterator();
		
		while(itPlayer.hasNext()){
			p = itPlayer.next();
			if (joueurGagnant == null){
				joueurGagnant = p;
			}else{
				if(joueurGagnant.getScore() < p.getScore()){
					joueurGagnant = p;
				}
			}
		}
		System.out.println("joueur Gagnant:"+joueurGagnant.getNom());
		itPlayer = players.iterator();
		while(itPlayer.hasNext()){
			if(joueurGagnant.getScore() == itPlayer.next().getScore()){
				cpt++;
			}
		}
		if(cpt == 1){
			//TODO tester quand il y a un bot d'annoncer la victoire
			notifyPlayerVictory(joueurGagnant);
			this.players.removeAll(this.players);
		}
	}

	public LinkedList<Divinity> getDivinites() {
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
	
	public int getNbJoueur(){
		return players.size();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public LinkedList<Believer> getCroyants() {
		return croyants;
	}

	public void setCroyants(LinkedList<Believer> croyants) {
		this.croyants = croyants;
	}
	
	public int getIndexJoueur(Player p){
		return players.indexOf(p);
	}
	
	public Player getJoueurAtIndex(int index){
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

	@Override
	public void notifyPlayerVictory(Player p) {
		observateur.annoncerVictoireJoueur(p);
	}

	@Override
	public void notifyPlayerDefeat(Player p) {
		observateur.annoncerDefaitJoueur(p);
	}
}
