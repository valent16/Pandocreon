package view.console;

import java.util.List;
import java.util.Scanner;

import controller.JoueurController;
import model.cards.Card;
import model.player.Human;

public class VueJoueurReel {

	Human joueur;
	
	JoueurController controller;
	
	public VueJoueurReel(JoueurController controller, Human j){
		this.controller = controller;
		this.joueur = j;
	}
	
	//affichage lorsqu'il s'agit du début du tour du joueur
	public void passageTour(){
		List<Card> cartes;
		System.out.println("Joueur "+joueur.getNom()+", c'est à votre tour");
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
		System.out.println("Quelle opération souhaitez-vous exécuter ?");
		System.out.println("1- Vous défausser d'une partie de vos cartes");
		System.out.println("2- Compléter votre main");
		System.out.println("3- Jouer des cartes");
		System.out.print("votre choix: ");
		String choix = sc.nextLine();
		
		while(!choix.matches("[0-9]+") && Integer.parseInt(choix)>3 && Integer.parseInt(choix)<1){
			System.out.println("le choix de vos opérations est invalide");
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
		//lister les cartes
		//faire un check si le joueur a bien des cartes
	}
	
	public void jouerCartes(){
		//lister les cartes
		//faire un check si le joueur a bien des cartes
	}
}
