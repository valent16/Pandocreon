package model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import controller.IObserverGameManager;
import model.cards.ActionCard;
import model.cards.Card;
import model.cards.Divinity;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.player.Player;

/**Classe qui gère tous les elements de la partie*/
public class GameManager implements IObservableGameManager {

	private final static int NB_CARTE_MAX_MAIN = 7;

	/**Attribut representant l'observateur de la classe GameManager*/
	private IObserverGameManager observateur ;

	/**Attribut representant le gestionnaire de partie*/
	private static volatile GameManager managerUnique;

	/**Attribut representant la pioche de la partie*/
	private LinkedList<ActionCard> pioche = new LinkedList<ActionCard>();

	/**Attribut representant les cartes defaussees de la partie*/
	private LinkedList<ActionCard> defausse = new LinkedList<ActionCard>();

	/** /**Attribut representant les cartes croyants sur la table*/
	private LinkedList<Believer> croyants = new LinkedList<Believer>();

	/**Attribut representant les cartes divinites de la partie*/
	private LinkedList<Divinity> listDivinites = new LinkedList<Divinity>();

	/**Attribut representant la pile de cartes appelees pendant le tour*/
	private LinkedList<Card> pilesCartesTour = new LinkedList<Card>();

	/**Attribut representant les joueurs de la partie*/
	private ArrayList<Player> players = new ArrayList<Player>();

	/**Attribut representant le joueur qui comence le tour*/
	private Player joueurDebutTour;

	/** Methode permettant d'avoir une seule instance du gestionnaire de partie
	 * @return le gestionnaire de partie
	 */
	public static GameManager getInstanceUniqueManager(){
		if (managerUnique == null){
			synchronized (GameManager.class){ //pour gerer le multi-thread
				if (managerUnique == null){
					managerUnique = new GameManager();
				}
			}
		}
		return managerUnique;
	}

	/**Methode permettant d'initialiser la partie en recuperant toutes les divinites et les cartes actions existantes
	 * @param cartesAction les cartes actions a creer
	 * @param divinites les divinites a creer
	 */
	public void initialisationPartie(LinkedList<ActionCard> cartesAction, LinkedList<Divinity> divinites ){
		this.listDivinites = divinites; 
		this.pioche = cartesAction;
	}

	/**Methode permettant l'initialisation de l'observateur de la partie
	 * @param controller l'observateur du gestionnaire de partie
	 */
	public void initialisationController(IObserverGameManager controller){
		observateur = controller;
	}

	/**Methode permettant de demarrer la partie*/
	public void startGame(){
		this.melangerDivinites();
		this.melangerPioche();
		this.intialisationDesJeux();
		Tour.deroulementTourJeu();	//lancement des tours de jeu
	}

	/**Methode permettant de melanger les cartes divinites*/	
	public void melangerDivinites(){
		Collections.shuffle(listDivinites);
	}

	/**Methode permettant de melanger les cartes de la pioche*/
	public void melangerPioche(){
		Collections.shuffle(pioche);
	}

	/**Methode permettant de melanger les cartes de la pile de defausses*/
	public void melangerDefausse(){
		Collections.shuffle(defausse);
	}

	/**Methode permettant de defausser une carte action
	 * @param carte la carte action a defausser qui sera ajoutee dans la liste de defausses
	 */
	public void defausserCarte(ActionCard carte){
		defausse.add(carte);
	}

	/**Methode permettant de defausser une liste de cartes actions
	 * @param cartes liste de cartes actions a defausser
	 */
	public void defausserCarte(LinkedList<ActionCard> cartes){
		defausse.addAll(cartes);
	}

	/**Methode permettant de defausser une carte divinite
	 * @param divinite la carte divinite a defausser
	 */
	public void defausserDivinite(Divinity divinite){
		listDivinites.add(divinite);
	}

	/**Methode permettant de recuperer une divinite
	 * @return la divinite au debut de la liste
	 */
	public Divinity piocherDivinite(){
		return listDivinites.pop();
	}

	/**Methode permettant de piocher une carte
	 * @return la carte au debut de la pioche
	 */
	public ActionCard piocherCarte(){
		if (pioche.size() == 0){
			if (defausse.size() == 0){
				//TODO A developper lancer une exception qui arrete la partie => pas assez de carte pour jouer
			}
			pioche.addAll(defausse);
			melangerPioche();
		}
		return pioche.pop();
	}

	/**Methode permettant de deposer un croyant sur la table
	 * @param carte le croyant a deposer
	 */
	public void deposerCroyant(Card carte){
		if(carte instanceof Believer){
			croyants.add((Believer) carte);
			notifyChangementCroyants();
		}
	}

	/**Methode permettant d'ajouter un joueur ou un bot a la partie
	 * @param joueur le joueur a ajouter a la partie
	 */
	public void ajouterJoueur(Player joueur){
		players.add(joueur);
		notifyChangementJoueurs();
	}

	/**Methode permettant de recuperer les croyants compatibles au guide
	 * @param guide le guide a tester
	 * @return les croyants potentiellement convertibles par ce guide
	 */
	public List<Believer> getCroyantCompatibles(SpiritGuide guide){
		ArrayList<Believer> croyantsAPresenter = new ArrayList<Believer>();

		Believer croyant;
		Iterator<Believer> itCroyant = GameManager.getInstanceUniqueManager().getCroyants().iterator();	
		while(itCroyant.hasNext()){
			croyant = itCroyant.next();
			if (guide.isCroyantCompatible(croyant)){
				croyantsAPresenter.add(croyant);
			}
		}
		return croyantsAPresenter;
	}

	/**Methode permettant de realiser l'initialisation des jeux*/
	public void intialisationDesJeux(){
		Iterator<Player> itJoueur = players.iterator();
		while(itJoueur.hasNext()){  //Permet l'initialisation des divinites des joueurs
			Player joueur = (Player) itJoueur.next();
			joueur.piocherDivinite();
		}
		for(int i=0; i<NB_CARTE_MAX_MAIN; i++){ //Permet l'intialisation des cartes action des joueurs
			itJoueur = players.iterator();
			while(itJoueur.hasNext()){
				Player joueur = (Player) itJoueur.next();
				joueur.piocher();;
			}
		}
	}

	/**Methode permettant d'eliminer un joueur de la partie
	 * @param joueur le joueur a eliminer
	 */
	public void eliminerJoueur(Player joueur){
		players.remove(joueur);
	}

	/**Methode permettant d'assigner une divinite a un joueur
	 * @param joueur, Le joueur auquel on assigne la Divinite
	 * @param div, La Divinite a assigner
	 */
	public void assignerDivinite(Player joueur, Divinity div){
		joueur.setDivinity(div);
	}

	/**Methode permettant de retirer un croyant de la table de jeu
	 * @param carte le croyant a retirer
	 */
	public void retirerCroyant(Believer carte){
		croyants.remove(carte);
		notifyChangementCroyants();
	}

	/**Methode permettant de recuperer l'index du plus jeune joueur
	 * @return l'index de la liste des joueurs du joueur le plus jeune
	 */
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

	/**Methode permettant d'eliminer le joueur ayant le plus petit score*/
	public void eliminationJoueurFaible(){
		Player p;
		Player joueurElimine = null;
		boolean egalite = false;
		int cpt = 0;

		Iterator<Player> itPlayer = players.iterator();

		while(itPlayer.hasNext()){
			p = itPlayer.next();

			if (joueurElimine == null){
				joueurElimine = p;
			}else{
				if(joueurElimine.getScore() > p.getScore()){
					joueurElimine = p;
				}
			}
		}

		Iterator<Player> it2=  players.iterator();
		while(it2.hasNext()){
			Player i = it2.next();
			if (joueurElimine.getScore() == i.getScore()){
				cpt++;
			}
		}

		if (cpt >= 2){
			egalite = true;
		}

		if(!egalite){			
			System.out.println("le joueur "+ joueurElimine.getNom()+" est elimine");
			this.players.remove(joueurElimine);
			notifyPlayerDefeat(joueurElimine);
		}
		System.out.println(egalite);

		try{
			Thread.sleep(10000);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}

	/**Methode permettant d'arreter la partie et d'afficher le joueur ayant le plus grand score*/
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
		System.out.println("joueur Gagnant: "+joueurGagnant.getNom());
		itPlayer = players.iterator();
		while(itPlayer.hasNext()){
			if(joueurGagnant.getScore() == itPlayer.next().getScore()){
				cpt++;
			}
		}
		if(cpt == 1){
			notifyPlayerVictory(joueurGagnant);
			this.players.removeAll(this.players);
		}
	}

	/**Getter liste de divinites
	 * @return les divintes du jeu
	 */
	public LinkedList<Divinity> getDivinites() {
		return listDivinites;
	}

	/**Getter un joueur
	 * @return le joueur du debut du tour
	 */
	public Player getJoueurDebutTour() {
		return joueurDebutTour;
	}

	/**Getter un entier
	 * @return le nombre de joueurs presents dans la partie
	 */
	public int getNbJoueur(){
		return players.size();
	}

	/**Getter liste de joueurs
	 * @return la liste des joueurs de cette partie
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**Getter liste de croyant
	 * @return l'ensemble des croyants mis sur la table
	 */
	public LinkedList<Believer> getCroyants() {
		return croyants;
	}

	/**Getter index du joueur
	 * @param p le joueur a tester
	 * @return l'index du joueur dans la liste des joueurs
	 */
	public int getIndexJoueur(Player p){
		return players.indexOf(p);
	}

	/**Getter un joueur
	 * @param index l'index de la liste de joueurs
	 * @return le joueur a l'index demande
	 */
	public Player getJoueurAtIndex(int index){
		return players.get(index);
	}

	/**Getter de la pile de cartes utilisees pendant le tour
	 * @return liste de cartes jouees pendant le tour
	 */
	public LinkedList<Card> getPilesCartesTour(){
		return pilesCartesTour;
	}

	@Override
	public void notifyPlayerVictory(Player p) {
		observateur.annoncerVictoireJoueur(p);
		observateur.miseAJourJoueurs();
	}

	@Override
	public void notifyPlayerDefeat(Player p) {
		observateur.annoncerDefaitJoueur(p);
		observateur.miseAJourJoueurs();
	}

	@Override
	public void notifyChangementCroyants() {
		observateur.miseAJourCroyants();
	}

	@Override
	public void notifyChangementTour() {
		observateur.miseAJourNbTour();
	}

	@Override
	public void notifyChangementJoueurs(){
		observateur.miseAJourJoueurs();
	}

	public void notifyJoueurActif(){
		observateur.miseAJourJoueurActif();
	}

}
