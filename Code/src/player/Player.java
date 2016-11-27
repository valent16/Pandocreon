package player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import cards.ActionCard;
import cards.Card;
import cards.Divinity;
import game.De;
import game.De.Face;
import game.GameManager;

/**Classe qui représente un joueur*/
public abstract class Player {
	//A voir si chaque joueur a un tableau de Points d'action
	// ou si on fait un tableau pour chaque type de point d'action
	//ou un seul tableau de taille 3 avec en indice 0 les points de jour, indice 1 les points de nuit, indice 2 les points de neant, 

	public static final int NB_CARTE_MAX = 7;

	/**chaîne de caractère représebtant le nom du joueur*/
	private String pseudo;

	/**Entier représentant le nombre de croyant que possede le joueur*/
	protected int score;

	private int age;

	/**Liste représentant la main d'un joueur*/ 
	protected LinkedList<Card> hand = new LinkedList<Card>();

	/**Dictionnaire contenant les points d'action du joueur*/
	private HashMap<Face, Integer> dicoPA = new HashMap<Face, Integer>();

	/**Carte divinté du joueur*/
	private Divinity divinity;

	/**Attribut représentant la liste des joueurs
	 * Faire en sorte que cette liste soit créer en fonction du 
	 * nombre de joueur définie par l'utilisateur
	 */
	public final static String[] NOM = {"Joueur1","Joueur2","Joueur3","Joueur4","Joueur5","Joueur6","Joueur7","Joueur8","Joueur9","Joueur10"};

	/**Constructeur public*/
	public Player(String pseudo, int age){
		this.setNom(pseudo);
		this.setAge(age);

		//Permet l'initialisation du dictionnaire de points d'action du joueur
		Face valuesEnumPointAction[] = Face.values();
		for (int i = 0; i< valuesEnumPointAction.length; i++) {
			dicoPA.put(valuesEnumPointAction[i], 0);
		}

	}

	/** Le joueur joue une carte et donc on l'enleve de sa main*/
	public void JouerCarte(int carte) {
		hand.remove(carte);
	}

	/**Methode piocher*/
	public void piocher(Card carte){
		hand.add(carte);
	}

	//////////////////////////////// GETTERS & SETTERS////////////////////////////////////////////////

	/**Getter du pseudo*/
	public String getNom(){
		return pseudo;
	}
	/**Setter du pseudo*/
	public void setNom(String pseudo){
		this.pseudo = pseudo;
	}
	/**Getter du score du joueur*/
	public int getScore() {
		return score;
	}
	/**Setter du score du joueur*/
	public void setScore(int score) {
		this.score = score;
	}
	/**Getter de la main du joueur*/
	public LinkedList<Card> getHand() {
		return hand;
	}
	/**Setter de la main du joueur*/
	public void setHand(LinkedList<Card> hand) {
		this.hand = hand;
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

	public void defausserCartes(LinkedList<ActionCard> cartes){
		hand.removeAll(cartes);
		GameManager.getInstanceUniqueManager().defausserCarte(cartes);
	}

	public void piocherCartes(){
		while (hand.size() <= NB_CARTE_MAX){
			GameManager.getInstanceUniqueManager().piocherCarte();
		}
	}

	public void decrementerPointAction(Face typePA, int nbPA){
		if ((dicoPA.get(typePA) - nbPA) < 0){
			//lancer Exception, le joueur ne peut pas jouer la carte
		}
		dicoPA.replace(typePA, dicoPA.get(typePA), dicoPA.get(typePA) - nbPA);
	}

	public void incrementerPointAction(Face typePA, int nbPA){
		dicoPA.replace(typePA, dicoPA.get(typePA), dicoPA.get(typePA) + nbPA);
	}

	@Override
	public String toString() {
		return "Player [pseudo=" + pseudo + ", score=" + score + ", age=" + age + ", hand=" + hand + ", dicoPA="
				+ dicoPA + ", divinity=" + divinity + "]";
	}
}


