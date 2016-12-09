package view.console;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import controller.JoueurController;
import model.cards.ActionCard;
import model.cards.Card;
import model.player.Human;

public class VueJoueurReel {

	Human joueur;
	
	JoueurController controller;
	
	public VueJoueurReel(JoueurController controller, Human j){
		this.controller = controller;
		this.joueur = j;
	}
	
	//affichage lorsqu'il s'agit du d�but du tour du joueur
	public void passageTour(){
		List<ActionCard> cartes;
		System.out.println("Joueur "+joueur.getNom()+", c'est � votre tour");
		System.out.println("\n");
		cartes = joueur.getHand();
		//liste des cartes
		for (Card c : cartes){
			System.out.println(c.toString());
		}
		System.out.println("\n");
	}
	
	
	public void jouerTour(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle op�ration souhaitez-vous ex�cuter ?");
		System.out.println("1- Vous d�fausser d'une partie de vos cartes");
		System.out.println("2- Compl�ter votre main");
		System.out.println("3- Jouer des cartes");
		System.out.print("votre choix: ");
		String choix = sc.nextLine();
		
		while(!choix.matches("[0-9]+") && Integer.parseInt(choix)>3 && Integer.parseInt(choix)<1){
			System.out.println("le choix de vos op�rations est invalide");
			System.out.print("votre choix: ");
			choix = sc.nextLine();
		}
		
		switch(choix){
		case "1":
			this.defausserCartes();
			break;
		case "2":
			controller.completerMain();
			break;
		case "3":
			this.jouerCartes();
			break;
		default:
			//lancement exception
		}
	}
	
	public void defausserCartes(){
		LinkedList<ActionCard> cartesToDelete = new LinkedList<ActionCard>();
		Scanner sc = new Scanner(System.in);
		System.out.println("liste des cartes pouvant �tre d�fauss�es:");
		List<ActionCard> listeAAfficher = joueur.getHand();;
//		afficherListeCarte(listeAAfficher);
//		System.out.println("veuillez renseigner la carte � d�fausser en renseignant son num�ro:");
//		System.out.println("taper \"end\" si votre s�lection de carte � supprimer est termin�e");
//		System.out.print("votre choix: ");
		String choix = "";
		
		while(!(choix.equals("end") || listeAAfficher.size() == 0)){
			listeAAfficher = joueur.getHand();
			afficherListeCarte(listeAAfficher);
			System.out.println("veuillez renseigner la carte � d�fausser en renseignant son num�ro:");
			System.out.println("taper \"end\" si votre s�lection de carte � supprimer est termin�e");
			System.out.print("votre choix: ");
			choix = sc.nextLine();
			
//			if (!(choix.matches("[0-9]+") && listeAAfficher.size() > Integer.parseInt(choix) && Integer.parseInt(choix) >= 0 || choix.equals("end"))))			
//			while((!choix.matches("[0-9]+") || (listeAAfficher.size() > Integer.parseInt(choix)) || Integer.parseInt(choix) >= 0 ) && !choix.equals("end")){
			while(!(choix.matches("[0-9]+") && listeAAfficher.size() > Integer.parseInt(choix) && Integer.parseInt(choix) >= 0 || choix.equals("end"))){
				System.out.println("\n");
				afficherListeCarte(listeAAfficher);
				System.out.println("votre choix est invalide, veuillez le renseigner � nouveau.");
				System.out.println("votre choix: ");
				choix = sc.nextLine();
			}
			if (!choix.equals("end")){
				controller.supprimerCarte(listeAAfficher.get(Integer.parseInt(choix)));
			}
		}
		
		
		//lister les cartes
		//faire un check si le joueur a bien des cartes
	}
	
	
	public void afficherListeCarte(List<ActionCard> cartes){
//		Iterator<Card> itCarte = cartes.iterator();
//		
//		while(itCarte.hasNext()){
//			Card carte = itCarte.next();
//			System.out.println(" "carte.toString());
//		}
		for (int i=0; i< cartes.size(); i++){
			System.out.println(i+"- "+cartes.get(i).toString());
		}
		
		
	}
	
	public void jouerCartes(){
		//lister les cartes
		//faire un check si le joueur a bien des cartes
	}
}
