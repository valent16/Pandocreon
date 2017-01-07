package model.player;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.Observer;
import model.EnumType.EnumCosmogonie;
import model.EnumType.EnumOrigineDivinite;
import model.cards.ActionCard;
import model.cards.Card;
import model.cards.Divinity;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.exception.PAInsuffisantException;
import model.exception.TargetSelectionException;
import model.game.De;
import model.game.GameManager;

/**Classe qui représente un joueur ou un bot*/
public abstract class Player extends Observer{

	/**Attribut representant le nombre de carte maximal au depart que le joueur possede*/ 
	public static final int NB_CARTE_MAX = 7;

	/**Attribut representant le nom du joueur*/
	private String pseudo;

	/**Attribut representant l'age du joueur*/
	private int age;

	/**Attribut representant la main d'un joueur*/ 
	protected LinkedList<ActionCard> hand = new LinkedList<ActionCard>();

	/**Attribut representant les points d'actions du joueur*/
	private HashMap<EnumCosmogonie, Integer> dicoPA = new HashMap<EnumCosmogonie, Integer>();

	/**Attribut representant les croyants deposes pendant le tour de jeu*/
	private LinkedList<Believer> croyantDeposesPendantTour = new LinkedList<Believer>();

	/**Attribut representant les guides du joueur*/
	protected LinkedList<SpiritGuide> guidesRattaches = new LinkedList<SpiritGuide>();

	/**Attribut representant la divinite du joueur*/
	private Divinity divinity;

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

	/**Methode permettant au joueur de joueur son tour*/
	public abstract void jouerTour();

	/**Methode permettant de recuperer un guide possdant un croyant specifique
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




	/** Le joueur joue une carte et donc on l'enleve de sa main*/
	public void JouerCarte(ActionCard carte) {
		//carte.utiliserPouvoir(commande, this);
		//hand.remove(carte);
	}

	public void jouerCarteRattachee(ActionCard carte){
		if(carte instanceof SpiritGuide){

		}else{

		}
	}

	/**Methode piocher*/
	public void piocher(){
		hand.add(GameManager.getInstanceUniqueManager().piocherCarte());
	}

	//Permet au joueur de piocher sa divinite
	public void piocherDivinite(){
		this.divinity = GameManager.getInstanceUniqueManager().piocherDivinite();
	}

	public static Object deepClone(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

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

	/**Getter du score du joueur*/
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

	/**Getter de la divinité du joueur*/
	public Divinity getDivinity() {
		return divinity;
	}
	/**Setter de la divinité du joueur*/
	public void setDivinity(Divinity divinity) {
		this.divinity = divinity;
	}

	public int getAge() {
		return age;
	}

	private void setAge(int age) {
		this.age = age;
	}

	public void lancerDe(){
		De.getInstanceDe().lancerDe();
	}

	public void ajouterMain(ActionCard card){
		this.hand.add(card);
	}

	/**Methode qui permet d'afficher la main du joueur*/
	public void afficherHand(){
		Iterator<ActionCard> it = hand.iterator();
		while(it.hasNext()){
			ActionCard card = (ActionCard) it.next();
			System.out.println("Carte "+hand.indexOf(card) +": "+ card);
		}
	}

	public abstract Player pickTarget() throws TargetSelectionException;

	public abstract EnumCosmogonie pickOrigine(ActionCardWithOrigin carte);

	public abstract List<Believer> pickCroyant(SpiritGuide carte);

	public void ajouterCroyant(Card carte){
		if (carte instanceof Believer){
			this.croyantDeposesPendantTour.add((Believer)carte);
		}
	}

	public void rattacherGuide(Card carte){
		if(carte instanceof SpiritGuide){
			this.guidesRattaches.add((SpiritGuide) carte);
		}
	}

	public void viderCroyantPendantTour(){
		//this.croyantDeposesPendantTour.re
	}

	public void retirerCarte(Card carte){
		if (carte instanceof ActionCard){
			hand.remove((ActionCard)carte);
		}
	}

	public void defausserCartes(LinkedList<ActionCard> cartes){
		hand.removeAll(cartes);
		GameManager.getInstanceUniqueManager().defausserCarte(cartes);
	}

	public void defausserCarte(ActionCard carte){
		hand.remove(carte);
		GameManager.getInstanceUniqueManager().defausserCarte(carte);
	}

	public void defausserGuideRattache(SpiritGuide guide){
		guidesRattaches.remove(guide);
		GameManager.getInstanceUniqueManager().defausserCarte(guide);
	}

	//Fonction permettant de completer la main du joueur
	public void piocherCartes(){
		while (hand.size() < NB_CARTE_MAX){
			hand.push(GameManager.getInstanceUniqueManager().piocherCarte());
		}
	}

	/**Methode permettant d'ajouter des points d'action du joueur du nombre de points indiqué
	 * @param typePA le type de PA (nuit, jour ou neant
	 * @param nbPA le nombre de points a donner
	 */
	public void incrementerPointAction(EnumCosmogonie typePA, int nbPA){
		dicoPA.put(typePA, dicoPA.get(typePA) + nbPA);
	}

	/**Methode permettant de retirer des points d'action du joueur du nombre de points indiqué
	 * @param typePA le type de PA (nuit, jour ou neant
	 * @param nbPA le nombre de points a retirer
	 */
	public void decrementerPointAction(EnumCosmogonie typePA, int nbPA) throws PAInsuffisantException{
		if ((dicoPA.get(typePA) - nbPA) < 0){
			throw new PAInsuffisantException("Pas assez de point d'action "+dicoPA.get(typePA));
		}
		dicoPA.replace(typePA, dicoPA.get(typePA), dicoPA.get(typePA) - nbPA);
	}

	//Methode appelee a chaque debut de tour pour attribuer les PA au joueur
	/**Methode permettant d'ajoueter les points d'action au joueur a chaque tour grace au Dé*/
	protected void incrementerPointActionWithDe(){
		if (divinity.getOrigine() == EnumOrigineDivinite.AUBE || divinity.getOrigine() == EnumOrigineDivinite.CREPUSCULE){
			incrementerPointAction(De.getInstanceDe().getFace(),1);
		}else{
			if (De.getInstanceDe().getFace() == EnumCosmogonie.JOUR || De.getInstanceDe().getFace() == EnumCosmogonie.NUIT){
				incrementerPointAction(De.getInstanceDe().getFace(), 2);
			}
		}
	}	

	/**Getter points d'cations d'un joueur
	 * @return les points d'action JOUR, NUIT, NEANT du joueur
	 */
	public HashMap<EnumCosmogonie, Integer> getDicoPA(){
		return dicoPA;
	}

	/**Getter lliste de guides
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

	/**Getter du pseudo*/
	public String getNom(){
		return pseudo;
	}

	/**Setter du pseudo*/
	public void setNom(String pseudo){
		this.pseudo = pseudo;
	}

	@Override
	public String toString() {
		return "Player [pseudo=" + pseudo +", dicoPA="+ dicoPA;
	}
}


