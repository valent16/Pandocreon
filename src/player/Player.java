package player;

import java.util.LinkedList;
import cards.Card;
import cards.Divinity;

/**Classe qui représente un joueur*/
public abstract class Player {

	//A voir si chaque joueur a un tableau de Points d'action
	// ou si on fait un tableau pour chaque type de point d'action
	//ou un seul tableau de taille 3 avec en indice 0 les points de jour, indice 1 les points de nuit, indice 2 les points de neant, 
	
	/**chaîne de caractère représebtant le nom du joueur*/
	protected String pseudo;
	
	/**Entier représentant le nombre de croyant que possede le joueur*/
	protected int score;
	
	/**Liste représentant la main d'un joueur*/ 
	protected LinkedList<Card> hand; 
	
	/**Carte divinté du joueur*/
	private Divinity divinity;
	
	/**Attribut représentant la liste des joueurs*/
	////////////////////////////////////////////Faire en sorte que cette liste soit créer en fonction du nombre de joueur définie par l'utilisateur*/
	public final static String[] NOM = {"Joueur1","Joueur2","Joueur3","Joueur4","Joueur5","Joueur6","Joueur7","Joueur8","Joueur9","Joueur10"};

	/**Constructeur public*/
	public Player(String pseudo){
		this.setNom(pseudo);	
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
}


