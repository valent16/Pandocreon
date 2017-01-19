package view.console;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controller.JoueurController;
import model.cards.ActionCard;
import model.cards.Card;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.SpiritGuide;
import model.enumType.EnumCosmogonie;
import model.game.GameManager;
import model.player.Human;
import model.player.Player;

public class VueJoueurReel {
	Human joueur;
	
	JoueurController controller;
	
	public VueJoueurReel(JoueurController controller, Human j){
		this.controller = controller;
		this.joueur = j;
	}
	
	//affichage lorsqu'il s'agit du debut du tour du joueur
	public void passageTour(){
		List<ActionCard> cartes;
		Iterator<Believer> itCroyant = GameManager.getInstanceUniqueManager().getCroyants().iterator();
		Iterator<SpiritGuide> itGuide = joueur.getGuides().iterator();
		System.out.println("\n");
		System.out.println("Joueur "+joueur.getNom()+", c'est a votre tour  --  score: "+joueur.getScore() );
		System.out.println("PA: "+joueur.getDicoPA().toString());
		System.out.println("liste des croyants sur la table");
		while(itCroyant.hasNext()){
			System.out.println(itCroyant.next().toString());
		}
		System.out.println("\n");
		System.out.println("liste des guides sur possedes par le joueur");
		while(itGuide.hasNext()){
			System.out.println(itGuide.next().toString());
		}
		System.out.println("\n");
		
		cartes = joueur.getHand();
		//liste des cartes
		for (Card c : cartes){
			System.out.println(c.toString());
		}
	}
	
	
	public void jouerTour(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle operation souhaitez-vous executer ?");
		System.out.println("1- Vous defausser d'une partie de vos cartes");
		System.out.println("2- Completer votre main");
		System.out.println("3- Jouer des cartes");
		System.out.println("4- Passer mon tour");
		System.out.print("votre choix: ");
		String choix = sc.nextLine();
		
		while(!choix.matches("[0-9]+") && Integer.parseInt(choix)>4 && Integer.parseInt(choix)<1){
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
		Scanner sc = new Scanner(System.in);
		System.out.println("liste des cartes pouvant etre defaussees:");
		List<ActionCard> listeAAfficher = joueur.getHand();
	
		String choix = "";
		
		while(!(choix.equals("end") || listeAAfficher.size() == 0 || GameManager.getInstanceUniqueManager().getNbJoueur() == 0 )){
			listeAAfficher = joueur.getHand();
			afficherListeCarte(listeAAfficher);
			System.out.println("veuillez renseigner la carte a defausser en renseignant son numero:");
			System.out.println("taper \"end\" si votre selection de carte a supprimer est terminee");
			System.out.print("votre choix: ");
			choix = sc.nextLine();
			
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
	}
	
	public void afficherListeCarte(List<ActionCard> cartes){
		for (int i=0; i< cartes.size(); i++){
			System.out.println(i+"- "+cartes.get(i).toString());
		}
	}
	
	public void jouerCartes(){
		Scanner sc = new Scanner(System.in);
		System.out.println("liste des cartes pouvant etre jouees:");
		List<ActionCard> listeAAfficher = joueur.getHand();
		List<ActionCard> listeCarteRattaches = joueur.getListCardGuide();
		String choix = "";
		
		while(!(choix.equals("end") || (listeAAfficher.size() == 0 && listeCarteRattaches.size() == 0) || GameManager.getInstanceUniqueManager().getNbJoueur() == 0)){
			listeAAfficher = joueur.getHand();
			listeCarteRattaches = joueur.getListCardGuide();
			for (int i=0; i< listeAAfficher.size(); i++){
				System.out.println(i+"- "+listeAAfficher.get(i).toString());
			}
			System.out.println("\n");
			System.out.println("liste des guides et cartes rattach�es � vos guides, vous ne pouvez que sacrifier ces cartes");
			for (int i=0; i< listeCarteRattaches.size(); i++){
				System.out.println(i+listeAAfficher.size()+"- "+listeCarteRattaches.get(i).toString());
			}
			
			System.out.println("veuillez renseigner la carte a utiliser en renseignant son numero:");
			System.out.println("taper \"end\" si vous avez fini d'utiliser des cartes");
			System.out.print("votre choix: ");
			choix = sc.nextLine();
			
			while(!(choix.matches("[0-9]+") && listeAAfficher.size()+listeCarteRattaches.size() > Integer.parseInt(choix) && Integer.parseInt(choix) >= 0 || choix.equals("end"))){
				System.out.println("\n");
				afficherListeCarte(listeAAfficher);
				System.out.println("votre choix est invalide, veuillez le renseigner a nouveau.");
				System.out.println("votre choix: ");
				choix = sc.nextLine();
			}
			if (!choix.equals("end")){
				if (Integer.parseInt(choix) >= listeAAfficher.size()){
					controller.utiliserCarteRattachee(listeCarteRattaches.get((Integer.parseInt(choix)-listeAAfficher.size())));
				}else{
					this.choisirPouvoirCarteMain(listeAAfficher.get(Integer.parseInt(choix)));
				}
			}
		}
	}
	
	//Fonction permettant de definir l'action a faire avec une carte Action
	public void choisirPouvoirCarteMain(ActionCard carte){
		//Set<String> listeCommande =  ( carte.getPouvoirs().keySet();
		Object[] listeCommande = carte.getPouvoirs().keySet().toArray();
		
		Scanner sc = new Scanner(System.in);
		String choix = null;
		System.out.println("quelle action souhaitez vous effectuer avec la carte ?");
		do{
			if (choix != null){
				System.out.println("choix invalide");
			}
			for (int i = 0; i<listeCommande.length; i++ ){
				System.out.println(i+" :"+listeCommande[i].toString());
			}
			
			System.out.print("votre choix: ");
			choix = sc.nextLine();
		}while(!controller.checkChoiceAction(carte, choix));
		try{
			System.out.println("utilisation du pouvoir: "+listeCommande[Integer.parseInt(choix)].toString());
			carte.utiliserPouvoir(listeCommande[Integer.parseInt(choix)].toString(), joueur);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//Permet la selection d'une cible
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
		String choix = sc.nextLine();
		return choix;
	}
	
	
	public EnumCosmogonie selectOrigine(List<EnumCosmogonie> listeOrigine){
		Scanner sc = new Scanner(System.in);
		String choix = null;
		
		do{
			if (choix != null){
				System.out.println("erreur lors de votre choix");
			}
			System.out.println("liste des origines pouvant �tre s�lectionn�es: ");
			for(int i = 0; i<listeOrigine.size(); i++){
				System.out.println(i+": "+listeOrigine.get(i));
			}
			System.out.print("votre choix: ");
			choix = sc.nextLine();
		}while(!controller.checkChoiceOrigine(listeOrigine, choix));

		return listeOrigine.get(Integer.parseInt(choix));
	}

	public ArrayList<Believer> selectCroyant(SpiritGuide guide) {
		List<Believer> croyantsAPresenter = GameManager.getInstanceUniqueManager().getCroyantCompatibles(guide);
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Believer> croyantSelected = new ArrayList<>();
		String choix="0";
		
		
		while(guide.getNbMaxCroyant()>croyantSelected.size() && Integer.parseInt(choix) != croyantsAPresenter.size()){
			System.out.println("liste des croyants pouvant �tre ajout�s au guide:");
			for (int i = 0; i<croyantsAPresenter.size(); i++){
				System.out.println(i+" :"+croyantsAPresenter.get(i).toString());
			}
			System.out.println(croyantsAPresenter.size()+": arreter conversions");
		    choix=null;
			
			do{
				if (choix != null){
					System.out.println("erreur lors de votre choix");
				}
				System.out.print("votre choix: ");
				choix = sc.nextLine();
			}while(!controller.checkGeneralChoice(choix, croyantsAPresenter.size()));
			
			if (Integer.parseInt(choix)!=croyantsAPresenter.size()){
				croyantSelected.add(croyantsAPresenter.get(Integer.parseInt(choix)));
				croyantsAPresenter.remove(croyantsAPresenter.get(Integer.parseInt(choix)));
			}
		}
		return croyantSelected;	
	}
}
