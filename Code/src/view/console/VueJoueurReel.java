package view.console;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.plaf.synth.SynthSpinnerUI;

import controller.JoueurController;
import model.cards.ActionCard;
import model.cards.Card;
import model.game.GameManager;
import model.player.Human;
import model.player.Player;
import model.pouvoir.Pouvoir;

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
		System.out.println("Joueur "+joueur.getNom()+", c'est a votre tour");
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
		System.out.println("Quelle operation souhaitez-vous executer ?");
		System.out.println("1- Vous defausser d'une partie de vos cartes");
		System.out.println("2- Completer votre main");
		System.out.println("3- Jouer des cartes");
		System.out.print("votre choix: ");
		String choix = sc.nextLine();
		
		while(!choix.matches("[0-9]+") && Integer.parseInt(choix)>3 && Integer.parseInt(choix)<1){
			System.out.println("le choix de vos operations est invalide");
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

		System.out.println("liste des cartes pouvant etre d�faussees:");
		List<ActionCard> listeAAfficher = joueur.getHand();

//		afficherListeCarte(listeAAfficher);
//		System.out.println("veuillez renseigner la carte � d�fausser en renseignant son num�ro:");
//		System.out.println("taper \"end\" si votre s�lection de carte � supprimer est termin�e");
//		System.out.print("votre choix: ");

		String choix = "";
		
		while(!(choix.equals("end") || listeAAfficher.size() == 0)){
			listeAAfficher = joueur.getHand();
			afficherListeCarte(listeAAfficher);
			System.out.println("veuillez renseigner la carte a defausser en renseignant son numero:");
			System.out.println("taper \"end\" si votre selection de carte � supprimer est terminee");
			System.out.print("votre choix: ");
			choix = sc.nextLine();
			
//			if (!(choix.matches("[0-9]+") && listeAAfficher.size() > Integer.parseInt(choix) && Integer.parseInt(choix) >= 0 || choix.equals("end"))))			
//			while((!choix.matches("[0-9]+") || (listeAAfficher.size() > Integer.parseInt(choix)) || Integer.parseInt(choix) >= 0 ) && !choix.equals("end")){
			while(!(choix.matches("[0-9]+") && listeAAfficher.size() > Integer.parseInt(choix) && Integer.parseInt(choix) >= 0 || choix.equals("end"))){
				System.out.println("\n");
				afficherListeCarte(listeAAfficher);
				System.out.println("votre choix est invalide, veuillez le renseigner a nouveau.");
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
		for (int i=0; i< cartes.size(); i++){
			System.out.println(i+"- "+cartes.get(i).toString());
		}
	}
	
	public void jouerCartes(){
		//LinkedList<ActionCard> cartesToDelete = new LinkedList<ActionCard>();
		Scanner sc = new Scanner(System.in);
		System.out.println("liste des cartes pouvant etre jouees:");
		List<ActionCard> listeAAfficher = joueur.getHand();
		String choix = "";
		
		while(!(choix.equals("end") || listeAAfficher.size() == 0)){
			listeAAfficher = joueur.getHand();
			afficherListeCarte(listeAAfficher);
			System.out.println("veuillez renseigner la carte � utiliser en renseignant son numero:");
			System.out.println("taper \"end\" si vous avez fini d'utiliser des cartes");
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
				this.choisirPouvoirCarte(listeAAfficher.get(Integer.parseInt(choix)));
				//controller.jouerCarte(listeAAfficher.get(Integer.parseInt(choix)));
				//controller.supprimerCarte(listeAAfficher.get(Integer.parseInt(choix)));
			}
		}
		
		
		//lister les cartes
		//faire un check si le joueur a bien des cartes
	}
	
	//Fonction permettant de d�finir l'action a faire avec une carte Action
	public void choisirPouvoirCarte(ActionCard carte){
		//Set<String> listeCommande =  ( carte.getPouvoirs().keySet();
		Object[] listeCommande = carte.getPouvoirs().keySet().toArray();
		
		Scanner sc = new Scanner(System.in);
		String choix = null;
//		commande
		System.out.println("quelle action souhaitez vous effectuer avec la carte ?");
		
		do{
			if (choix != null){
				System.out.println("choix invalide");
			}
			for (int i = 0; i<listeCommande.length; i++ ){
				System.out.println(i+" :"+listeCommande[i].toString());
			}
			System.out.println("votre choix: ");
			choix = sc.nextLine();
		}while(!controller.checkChoiceAction(carte, choix));
		
//		for (int i = 0; i < listeCommande.length; i++){
//			
//		}
		
	}
	
	//Permet la s�lection d'une cible
	public String selectTarget(){
		Scanner sc = new Scanner(System.in);
		int cpt = 0;
		Player p;
		System.out.println("liste des joueurs pouvant etre selectionnes: ");
		Iterator<Player> itPlayer = GameManager.getInstanceUniqueManager().getPlayers().iterator();
		
		while(itPlayer.hasNext()){
			p = itPlayer.next();
			if (!p.equals(joueur)){
				System.out.println(cpt+"- "+p.getNom());
				cpt++;
			}
		}
		System.out.print("votre choix: ");
		return sc.nextLine();
	}
}
