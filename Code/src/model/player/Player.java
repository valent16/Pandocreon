package model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.Observer;
import model.cards.ActionCard;
import model.cards.Card;
import model.cards.Divinity;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.enumType.EnumOrigineDivinite;
import model.exception.PAInsuffisantException;
import model.exception.TargetSelectionException;
import model.game.De;
import model.game.GameManager;

/**Classe qui représente un joueur ou un bot*/
public abstract class Player extends Observer{

	/**Attribut representant le nombre maximal de carte au debut de la partie que le joueur possede*/ 
	public static final int NB_CARTE_MAX = 7;

	/**Attribut representant le nom du joueur*/
	private String pseudo;

	/**Attribut representant l'age du joueur*/
	private int age;

	/**Attribut representant la main d'un joueur*/ 
	protected LinkedList<ActionCard> hand = new LinkedList<ActionCard>();

	/**Attribut representant les points d'action du joueur*/
	protected HashMap<EnumCosmogonie, Integer> dicoPA = new HashMap<EnumCosmogonie, Integer>();

	/**Attribut representant les croyants deposes pendant le tour de jeu*/
	private LinkedList<Believer> croyantDeposesPendantTour = new LinkedList<Believer>();

	/**Attribut representant les guides du joueur*/
	protected LinkedList<SpiritGuide> guidesRattaches = new LinkedList<SpiritGuide>();

	/**Attribut representant la divinite du joueur*/
	protected Divinity divinity;

	/**Constructeur
	 * @param pseudo le nom du joueur 
	 * @param age l'age du joueur
	 */
	public Player(String pseudo, int age){
		this.setNom(pseudo);
		this.setAge(age);
		EnumCosmogonie valuesEnumPointAction[] = EnumCosmogonie.values();//Permet l'initialisation du dictionnaire de points d'action du joueur
		for (int i = 0; i< valuesEnumPointAction.length; i++) {
			dicoPA.put(valuesEnumPointAction[i], 0);
		}
	}

	/**Methode permettant au joueur de jouer son tour*/
	public abstract void jouerTour();

	/**Methode permettant de recuperer un guide possedant un croyant specifique
	 * @param croyant le croyant a tester
	 * @return le guide possedant de ce croyant
	 */
	public SpiritGuide getGuideWithCroyant(Believer croyant){
		Iterator<SpiritGuide> itGuide = guidesRattaches.iterator();
		SpiritGuide guide;
		while(itGuide.hasNext()){
			guide = itGuide.next();
			if (guide.hasCroyant(croyant)){
				return guide;
			}
		}
		return null;
	}

	/**Methode permettant au joueur de piocher une carte*/
	public abstract void piocher();

	/**Methode permettant de piocher une divinite*/
	public void piocherDivinite(){
		this.divinity = GameManager.getInstanceUniqueManager().piocherDivinite();
	}

	/**Methode permettant de recuperer les croyants convertis par le joueur
	 * @return la liste des croyants convertis
	 */
	public List<ActionCard> getListCardGuide(){
		ArrayList<ActionCard> liste =  new ArrayList<ActionCard>();
		Iterator<SpiritGuide> itGuide = guidesRattaches.iterator();
		SpiritGuide guide;
		while(itGuide.hasNext()){
			guide = itGuide.next();
			liste.add(guide);
			Iterator<Believer> itCroyant = guide.getCroyantsConvertis().iterator();
			while(itCroyant.hasNext()){
				liste.add(itCroyant.next());
			}
		}
		return liste;
	}

	/**Getter calcul du score du joueur
	 * @return le score du joueur
	 */
	public int getScore() {
		int score = 0;
		Iterator<SpiritGuide> itGuides = guidesRattaches.iterator();
		SpiritGuide guide;
		while(itGuides.hasNext()){
			guide = itGuides.next();
			Iterator<Believer> itCroyant = guide.getCroyantsConvertis().iterator();
			while(itCroyant.hasNext()){
				score = score + itCroyant.next().getNbPrieres();
			}
		}
		return score;
	}

	/**Methode permettant au joueur de lancer le de*/
	public void lancerDe(){
		De.getInstanceDe().lancerDe();
	}

	/**Methode permettant au joueur d'ajouter une carte action dans sa main
	 * @param card la carte a ajouter dans sa main
	 */
	public abstract void ajouterMain(ActionCard card);

	/**Methode permettant d'afficher la main du joueur*/
	public void afficherHand(){
		Iterator<ActionCard> it = hand.iterator();
		while(it.hasNext()){
			ActionCard card = (ActionCard) it.next();
			System.out.println("Carte "+ (hand.indexOf(card)+1) +": "+ card);
		}
	}

	/**Methode permettant de choisir une cible (joueur)
	 * @return le joueur cible
	 * @throws TargetSelectionException exception sur le joueur selectionne
	 */
	public abstract Player pickTarget() throws TargetSelectionException;

	/**Methode permettant de choisir le type de point d'action
	 * @param carte la carte qui va etre utiliser
	 * @return l'origine choisi
	 */
	public abstract EnumCosmogonie pickOrigine(ActionCardWithOrigin carte);

	/**Methode permettant de choisir les croyants que le joueur peut convertir avec son guide
	 * @param carte le guide qui va convertir
	 * @return la liste des croyants potentiellemnt convertibles
	 */
	public abstract List<Believer> pickCroyant(SpiritGuide carte);

	/**Methode permettant de deposer un croyant sur la table
	 * @param carte le croyant a deposer
	 */
	public void ajouterCroyant(Card carte){
		if (carte instanceof Believer){
			this.croyantDeposesPendantTour.add((Believer)carte);
		}
	}

	/**Methode permettant de rattacher un guide au joueur
	 * @param carte le guide a rattacher
	 */
	public abstract void rattacherGuide(Card carte);

	/**Methode permettant de retirer une carte de la main du joueur
	 * @param carte la carte a retirer
	 */
	public void retirerCarte(Card carte){
		if (carte instanceof ActionCard)
			hand.remove((ActionCard)carte);
	}

	/**Methode permettant de se defausser d'une carte
	 * @param carte la carte a se defausser
	 */
	public abstract void defausserCarte(ActionCard carte);

	/**Methode permettant de se defausser de plusieurs cartes
	 * @param cartes la liste des cartes a se defausser
	 */
	public abstract void defausserCartes(LinkedList<ActionCard> cartes);

	/**Methode permettant de se defausser d'un guide
	 * @param guide le guide a se defausser
	 */
	public abstract void defausserGuideRattache(SpiritGuide guide);

	/**Methode permettant de completer la main du joueur*/
	public void piocherCartes(){
		while (hand.size() < NB_CARTE_MAX){
			piocher();
		}
	}

	/**Methode permettant d'ajouter des points d'action du joueur du nombre de points indiques
	 * @param typePA le type de PA (nuit, jour ou neant)
	 * @param nbPA le nombre de points a donner
	 */
	public abstract void incrementerPointAction(EnumCosmogonie typePA, int nbPA);

	/**Methode permettant de retirer des points d'action du joueur du nombre de points indiques
	 * @param typePA le type de PA (nuit, jour ou neant)
	 *@param nbPA le nombre de points a retirer
	 * @throws PAInsuffisantException exceprion si le joueur manque de point d'action
	 */
	public abstract void decrementerPointAction(EnumCosmogonie typePA, int nbPA) throws PAInsuffisantException;

	/**Methode permettant d'ajouter les points d'action au joueur a chaque tour grace au De*/
	protected void incrementerPointActionWithDe(){
		//si le de a une face jour
		if (De.getInstanceDe().getFace() == EnumCosmogonie.JOUR){
			if(divinity.getOrigine() == EnumOrigineDivinite.JOUR)
				incrementerPointAction(De.getInstanceDe().getFace(), 2);//on ajoute 2 point pour une orgine Jour
			else if(divinity.getOrigine() == EnumOrigineDivinite.AUBE)
				incrementerPointAction(De.getInstanceDe().getFace(), 1);//on ajoute 1 point pour une origine Aube
		}
		//si le de a une face nuit
		else if (De.getInstanceDe().getFace() == EnumCosmogonie.NUIT){
			if(divinity.getOrigine() == EnumOrigineDivinite.NUIT)
				incrementerPointAction(De.getInstanceDe().getFace(), 2);//on ajoute 2 point pour une orgine Nuit
			if(divinity.getOrigine() == EnumOrigineDivinite.CREPUSCULE)
				incrementerPointAction(De.getInstanceDe().getFace(), 1);//on ajoute 1 point pour une origine Crepuscule
		}
		//si le de a une face neant
		else if(De.getInstanceDe().getFace() == EnumCosmogonie.NEANT){
			if(divinity.getOrigine() == EnumOrigineDivinite.AUBE)
				incrementerPointAction(De.getInstanceDe().getFace(), 1);//on ajoute 1 point pour une orgine Aube
			if(divinity.getOrigine() == EnumOrigineDivinite.CREPUSCULE)
				incrementerPointAction(De.getInstanceDe().getFace(), 1);//on ajoute 1 point pour une origine Crepuscule
		}
	}

	/**Getter de la divinite du joueur
	 * @return la divinite du joueur
	 */
	public Divinity getDivinity() {
		return divinity;
	}
	/**Setter de la divinite du joueur
	 * @param divinity la divinite attribuer au joueur
	 */
	public void setDivinity(Divinity divinity) {
		this.divinity = divinity;
	}

	/**Getter de l'age du joueur
	 * @return l'age du joueur
	 */
	public int getAge() {
		return age;
	}

	/**Setter de l'age du joueur
	 * @param age l'age du joueur
	 */
	private void setAge(int age) {
		this.age = age;
	}

	/**Getter points d'action d'un joueur
	 * @return les points d'action JOUR, NUIT, NEANT du joueur
	 */
	public HashMap<EnumCosmogonie, Integer> getDicoPA(){
		return dicoPA;
	}

	/**Getter liste des guides
	 * @return les guides qui sont rattaché a ce joueur
	 */
	public LinkedList<SpiritGuide> getGuides() {
		return guidesRattaches;
	}

	/**Getter liste de croyants
	 * @return les croyants que le joueur a depose pendant son tour de jeu
	 */
	public List<Believer> getCroyantDeposesPendantTour(){
		return Collections.unmodifiableList(croyantDeposesPendantTour);
	}

	/**Getter liste de cartes actions
	 * @return la main du joueur
	 */ 
	public LinkedList<ActionCard> getHand(){ 
		return hand;
	}

	/**Getter nombre de carte
	 * @return le nombre de carte que le joueur possede dans sa main
	 */
	public int getNbCartes(){
		return hand.size();
	}

	/**Getter du pseudo
	 * @return le nom du joueur
	 */
	public String getNom(){
		return pseudo;
	}

	/**Setter du pseudo
	 * @param pseudo le nom du joueur
	 */
	public void setNom(String pseudo){
		this.pseudo = pseudo;
	}

	@Override
	public String toString() {
		return "Player [pseudo=" + pseudo +", dicoPA="+ dicoPA;
	}
}
