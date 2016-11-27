package model.game;
/////////////////////////////////////////////////////////////////////////
import java.util.Collections;
import java.util.LinkedList;

import model.cards.Card;

/**Liste des cartes du jeu*/
public class CardGame{
	
	public static LinkedList<Card> JDC;
	
	/**Constructeur qui permet d'ajouter toutes les cartes du jeu*/
	public CardGame() {
		CardGame.JDC = new LinkedList<Card>();
	}
		 /*
		 int i,j,k;
		 
		 for(i=0;i<4;i++){			
			 for(j=1;j<10;j++){ 		// on initialise � 1 car il n'y a qu'une seule carte 0 par couleur
				 CarteChiffre c = new CarteChiffre(j,i,1);
				 JDC.add(c);
				 JDC.add(c);	 
			 }
		 }
		 
		 for (i=0;i<4;i++){
			 j = 0;	// une seule carte 0 par couleur
			 CarteChiffre c0 = new CarteChiffre(j,i,1);
			 JDC.add(c0);
		 }
		 for(i=0;i<4;i++){		 
			 j=10; 		// carte passer tour
			 CartePasserTour ctour = new CartePasserTour(j,i,10);
			 JDC.add(ctour);
			 JDC.add(ctour);
		 }

		 for(i=0;i<4;i++){		
			 j=11; 		// carte inversion
			 CarteInversion cinversion = new CarteInversion(j,i,11);
			 JDC.add(cinversion);
			 JDC.add(cinversion);	 
		 }
		 for(i=0;i<4;i++){		
			 j=12; 		// carte+2
			 CartePlus2 cplus2 = new CartePlus2(j,i,2);
			 JDC.add(cplus2);
			 JDC.add(cplus2);
		 }
		 CarteJoker cjoker = new CarteJoker(13, 4,0);
		 
		 for(k=0;k<4;k++){
			 JDC.add(cjoker);
		 }

		 CartePlus4 cplus4 = new CartePlus4(14,4,4);
		 for(k=0;k<4;k++){
			 JDC.add(cplus4);
		 }*/

	/**Recupere les cartes du jeu*/
	 public LinkedList<Card> getCarte(){
		 System.out.println(JDC.size());
		 return JDC;
	 }
	 
	 /**Méthode qui permet de mélanger les cartes*/
	 public LinkedList<Card> melangerCarte(){
		 Collections.shuffle(JDC);
		 return JDC;
	 }
	 
	 /**Méthode qui permet de tirer une carte*/
	 public Card tirerCarte(){
		 return JDC.pop();
	 }
	 
	 public String toString() {
		 StringBuffer sb = new StringBuffer();
		 sb.append(JDC);
		 sb.append("\n");
		 return sb.toString();
	 }


	


}