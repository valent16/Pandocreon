package player;

import java.util.ArrayList;
import java.util.LinkedList;

import cards.Card;

//import uno.carte.CarteUno;

/**Classe qui représente un joueur*/
public class Player {
	
	
	/////////////////IL Faut utiliser des linkedlist plutot que des arraylist
	
	/*A voir a quoi cela sert
	public final static int Joueur1 = 0;
	public final static int Joueur2 = 1;
	public final static int Joueur3 = 2;
	public final static int Joueur4 = 3;
	public final static int Joueur5 = 4;
	public final static int Joueur6 = 5;
	public final static int Joueur7 = 6;
	public final static int Joueur8 = 7;	
	public final static int Joueur9 = 8;
	public final static int Joueur10 = 9;*/
	
	/**chaîne de caractère représebtant le nom du joueur*/
	private String pseudo;
	
	/**Entier représentant le nombre de croyant que possede le joueur*/
	private int score;
	
	/**Liste représentant la main d'un joueur*/ 
	private LinkedList<Card> hand; 
	/////////////////////////////////////////// A VOIR SI ON FAIT UNE CLASSE HAND ou on MET JUSTE UN ATTRIBUT
	//si on dit que la main du joueur est un attribut savoir si c'est un likedlist ou une arraylist
	/////////////////////////faire un getter et un setter de Hand
	
	/**Attribut représentant la liste des joueurs*/
	/////////////////FAire en sorte que cette liste soit créer en fonction du nombre de joueur définie par l'utilisateur*/
	public final static String[] NOM = {"Joueur1","Joueur2","Joueur3","Joueur4","Joueur5","Joueur6","Joueur7","Joueur8","Joueur9","Joueur10"};

	/**Constructeur public*/
	public Player(String pseudo){
		this.setNom(pseudo);
		//main = new LinkedList<CarteUno>();
		}
	
	/**Getter du pseudo*/
	public String getNom(){
		return pseudo;
	}
	
	/**Setter du pseudo*/
	private void setNom(String pseudo){
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
	
	/*public static LinkedList<Player> afficherJoueur(){
		return Game.players;
		}*/

	/** Le joueur joue une carte et donc on l'enleve de sa main*/
	public void JouerCarte(int carte) {
		hand.remove(carte);
	}
	
	/**Methode piocher*/
	public void piocher(Card carte){
		hand.add(carte);
    }
	
	

	/*attributs:
	 * -  Possede un objet de la classe Deck qui represtente les 7 cartes
	 * - Possede un attribut de la classe divinité
	 * 
	 */
	
	/*methodes:
	 * - un methode piocher() qui recupere une carte de la pile de carte
	 */
	

	//A voir si chaque joueur a un tableau de Points d'action
	// ou si on fait un tableau pour chaque type

}


