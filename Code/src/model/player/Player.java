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

/**Classe qui représente un joueur*/
public abstract class Player extends Observer{
	//A voir si chaque joueur a un tableau de Points d'action
	// ou si on fait un tableau pour chaque type de point d'action
	//ou un seul tableau de taille 3 avec en indice 0 les points de jour, indice 1 les points de nuit, indice 2 les points de neant, 

	public static final int NB_CARTE_MAX = 7;

	/**chaine de caractere representant le nom du joueur*/
	private String pseudo;

	private int age;

	/**Liste representant la main d'un joueur*/ 
	protected LinkedList<ActionCard> hand = new LinkedList<ActionCard>();

	/**Dictionnaire contenant les points d'action du joueur*/
	private HashMap<EnumCosmogonie, Integer> dicoPA = new HashMap<EnumCosmogonie, Integer>();

	private LinkedList<Believer> croyantDeposesPendantTour = new LinkedList<Believer>();

	private LinkedList<SpiritGuide> guidesRattaches = new LinkedList<SpiritGuide>();

	/**Carte divinte du joueur*/
	private Divinity divinity;

	/**Constructeur public*/
	public Player(String pseudo, int age){
		this.setNom(pseudo);
		this.setAge(age);

		//Permet l'initialisation du dictionnaire de points d'action du joueur
		EnumCosmogonie valuesEnumPointAction[] = EnumCosmogonie.values();
		for (int i = 0; i< valuesEnumPointAction.length; i++) {
			dicoPA.put(valuesEnumPointAction[i], 0);
		}
	}

	//Methode de jeu de tour qui se fait red�finir pour le joueur et le bot
	public abstract void jouerTour();

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
	
	
	public List<Believer> getCroyantDeposesPendantTour(){
		return Collections.unmodifiableList(croyantDeposesPendantTour);
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


	//Permet de renvoyer une liste 
	public LinkedList<ActionCard> getHand(){ 
		//		return Collections.unmodifiableList((LinkedList<ActionCard>) deepClone(hand));
		return hand;
	}

	/**Getter du pseudo*/
	public String getNom(){
		return pseudo;
	}
	/**Setter du pseudo*/
	public void setNom(String pseudo){
		this.pseudo = pseudo;
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
		int score = 0	;
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
		}else{
			//Lancement d'un exception
		}
	}

	public void rattacherGuide(Card carte){
		if(carte instanceof SpiritGuide){
			this.guidesRattaches.add((SpiritGuide) carte);
		}else{
			//Lancement d'une exception
		}
	}

	public void viderCroyantPendantTour(){
		//this.croyantDeposesPendantTour.re
	}

	public void retirerCarte(Card carte){
		if (carte instanceof ActionCard){
			hand.remove((ActionCard)carte);
		}else{
			//lancement Exception
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

	//Fonction permettant de compl�ter la main du joueur
	public void piocherCartes(){
		while (hand.size() < NB_CARTE_MAX){
			hand.push(GameManager.getInstanceUniqueManager().piocherCarte());
		}
	}

	public int getNbCartes(){
		return hand.size();
	}

	//Methode qui permet d'incrementer les pointsd'action du joueur du nombre de points indiqué
	public void incrementerPointAction(EnumCosmogonie typePA, int nbPA){
		dicoPA.put(typePA, dicoPA.get(typePA) + nbPA);
//		dicoPA.replace(typePA, dicoPA.get(typePA), dicoPA.get(typePA) + nbPA);
	}

	public void decrementerPointAction(EnumCosmogonie typePA, int nbPA) throws PAInsuffisantException{
		if ((dicoPA.get(typePA) - nbPA) < 0){
			throw new PAInsuffisantException("Pas assez de point d'action "+dicoPA.get(typePA));
		}
		dicoPA.replace(typePA, dicoPA.get(typePA), dicoPA.get(typePA) - nbPA);
	}

	//Methode appelee a chaque debut de tour pour attribuer les PA au joueur
	protected void incrementerPointActionWithDe(){
		if (divinity.getOrigine() == EnumOrigineDivinite.AUBE || divinity.getOrigine() == EnumOrigineDivinite.CREPUSCULE){
			if(De.getInstanceDe().getFace() == EnumCosmogonie.NEANT){
				incrementerPointAction(EnumCosmogonie.NEANT, 1);
			}else{
				incrementerPointAction(De.getInstanceDe().getFace(),1);
			}
		}else{
			if (De.getInstanceDe().getFace() == EnumCosmogonie.JOUR || De.getInstanceDe().getFace() == EnumCosmogonie.NUIT){
				incrementerPointAction(De.getInstanceDe().getFace(), 2);
			}
		}
	}	

	/**
	 * Methode qui permet de recuperer les points d'cations d'un joueur
	 * @return dicoPA, les points d'action JOUR, NUIT, NEANT du joueur
	 */
	public HashMap<EnumCosmogonie, Integer> getDicoPA(){
		return dicoPA;
	}

	public LinkedList<SpiritGuide> getGuides() {
		return guidesRattaches;
	}

	@Override
	public String toString() {
		return "Player [pseudo=" + pseudo +", dicoPA="+ dicoPA;
	}
}


