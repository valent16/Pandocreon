package model.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import model.cards.ActionCard;
import model.cards.Divinity;
import model.gestionDonnees.DataManager;
import model.gestionDonnees.FakeSaver;
import model.gestionDonnees.ParserXML;
import model.player.Bot;
import model.player.Human;
import model.player.Player;


/**Classe qui gere la partie*/
public class Game {
	
	private final static int NB_CARTE_MAX_MAIN = 7;

	private int nbJoueurMax;
	
	/**Attribut qui contient la liste des joueurs et des bots dans la partie*/
	private ArrayList<Player> players = new ArrayList<Player>();

	/**Attribut qui représente la liste de cartes actions*/
	private LinkedList<ActionCard> listeCartesAction = new LinkedList<ActionCard>();

	/**Attribut  qui représente la liste de divinités*/
	private LinkedList<Divinity> listeCartesDivinites = new LinkedList<Divinity>();

	DataManager dataManager;
	
	public Game(){
		//Permet l'intialisation du dataManager
		dataManager = new DataManager(new FakeSaver(), new ParserXML());
		
	}
	
	//Permet de d�terminer le nombre maximal de joueur pour une partie de jeu
	private void calculerNbJoueurMax(){
		System.out.println(listeCartesAction.size());
		System.out.println(listeCartesDivinites.size());
		if (listeCartesAction.size()/NB_CARTE_MAX_MAIN > listeCartesDivinites.size()){
			this.nbJoueurMax = listeCartesDivinites.size();
		}else{
			this.nbJoueurMax = listeCartesAction.size()/NB_CARTE_MAX_MAIN;
		}
	}
	
	/** Getter qui permet de recuperer le nombre maximal de joueur
	 * @return le nombre de joueur maximal possible dans une partie
	 */
	public int getNbJoueurMax(){
		return this.nbJoueurMax;
	}
	
	/** Getter qui permet de recuperer le nombre de joueur
	 * @return le nombre de joueur
	 */
	public int getNbJoueur(){
		return players.size();
	}
	
	
	/**Methode pour initialiser la partie, le nombre de joueurs et de bots*/
	public void initGame() {
		//Fonctions permettant le chargement des cartes
		this.listeCartesDivinites = dataManager.getGestionnaireChargement().chargerDivinites();
		this.listeCartesAction = dataManager.getGestionnaireChargement().chargerCartes();
		this.calculerNbJoueurMax();
	}
	
	/**Méthode qui permet d'initialiser une nouvelle partie*/
	public void nouvellePartie(){	
		GameManager.getInstanceUniqueManager().initialisationPartie(this.listeCartesAction, this.listeCartesDivinites); //transmission du deck de jeu	
		Iterator<Player> it = players.iterator(); //transmission de la liste de joueurs
		while (it.hasNext()){
			GameManager.getInstanceUniqueManager().ajouterJoueur((Player) it.next());
		}
	}
	

	/**Méthode qui permet d'ajouter un joueur à la partie
	 *
	 * @param joueur le joueur réél à ajouter
	 */
	public void ajouterJoueurReel(Human joueur){
		//Check � voir si on peut mettre plusieurs joueur r�els 
		this.players.add(joueur);
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Entrez un nom de joueur : ");
//		String joueur = sc.nextLine();
//		System.out.println(Player.NOM[nbJoueur]+ " : " +joueur+ "\n");
//		Player J1 = new Human(joueur,10);
		//Players.;
//		sc.close();
	}

	/**Méthode qui permet d'ajouter un bot à la partie
	 * 
	 * @param joueur le bot à ajouter
	 */
	public void ajouterBot(Bot joueur){
		this.players.add(joueur);
		/*
		String bot = "BOT";
		System.out.println(Player.NOM[nbJoueur]+ " : " + bot+ "\n");
		Player J2 = new Bot(bot, new HardStrategy()); ///////////////////Ce sera new Strategie voirs le design pattern Strategie en exemple
		*/
		//Players.add(J2);
	}

	/**Méthode qui permet de choisir la difficulté du bot*/
	public static void choisirDifficulteBot(){
		System.out.println("\nChoississez la difficulté des BOTs \n");

		System.out.println("Choix 1 : Facile/Easy");
		System.out.println("Choix 2 : Moyen/Medium");
		System.out.println("Choix 3 : Difficile/Hard");

		Scanner sc = new Scanner(System.in);
		String choix = sc.nextLine();
		int strategy = Integer.parseInt(choix);
		sc.close();

		switch(strategy){
		case 1: //bot facile
			//Bot.setStrategy(new EasyStrategy());
			System.out.println("Vous avez choisi la difficulté EASY pour les bots\n");
			break;
		case 2: //bot moyen
			//Bot.setStrategy(new MediumStrategy());
			System.out.println("Vous avez choisi la difficulté MEDIUM pour les bots\n");
			break;
		case 3: //bot difficile
			//Bot.setStrategy(new HardStrategy());
			System.out.println("Vous avez choisi la difficulté HARD pour les bots\n");
			break;

		default: //erreur si le nombre entré n'est pas correcte
			System.out.println("Erreur ! Vous n'avez pas entré un bon choix \n");
			break;
		}
	}



	//Calculer le score de chqaue joueur 
	//pour cela il aut calculer le nombre de croyant
	//A RAJOUTER DANS LE GAMEMANGER
	//public static void Score(int k){}

	///////////A VOIR SI C'est Utile de regenerer la pioche
	//A RAJOUTER DANS LE GAMEMANAGER
//	public static void regenererPioche(){}

	////////////A VOIR SI le code en dessous C'est Valentin qui a fait ça ou si c'ets moi/////////////////////////////////////////////////////////////////////////////////

	/*Afficher le joueur qui joue
	private static void afficherJoueurEnCours(int i) {
		System.out.println(Players.get(i).getNom()+ " doit jouer \n");
		System.out.println("Main de " +Players.get(i).getNom()+ " :  " );
		System.out.println(Players.get(i));
	}*/

	////////////////////////////////////////////////////*TEST*///////////////////////////////////////////////////////////////////////
	/*
	public void  carteTest(int valeur, int couleur, int i, int carteAPoser){
		this.valeur=Players.get(i).main.get(carteAPoser).getValeur();
		this.couleur=Joueurs.get(i).main.get(carteAPoser).getCouleur();
	}

	public static void TestCarteAPoser(int carteAPoser, int i){
		System.out.println("TestCarteAPoser: ");
		System.out.println("valeur carte jouer: "+Players.get(i).main.get(carteAPoser).getValeur());
		System.out.println("couleur carte jouer: "+Players.get(i).main.get(carteAPoser).getCouleur());
	}

	public static boolean verificationMainJoueur(){
		for(int i=0;i<nbJoueur-1;i++){
			if(Players.get(i).main.size()==0){
				System.out.println("******* Le gagnant est : " +Players.get(i).getNom()+" *******");
				Score(i);
				return true;
			}
		}
		return false;

	}


	/*public static boolean nbCarteValide(int i){
		int nombreCarteValide=0;

		for(int o=0; o<Joueurs.get(i).main.size(); o++){

			CarteUno u = new CarteUno(Joueurs.get(i).main.get(o).getValeur(), Joueurs.get(i).main.get(o).getCouleur(), 0);

			if(u.CoupValide(u, talon)==true){
				nombreCarteValide++;

			}else if(Joueurs.get(i).main.get(o).getValeur()== 13){
				Joueurs.get(i).main.get(0);
				nombreCarteValide++;
			}
			else if(Joueurs.get(i).main.get(o).getValeur()==14){
				Joueurs.get(i).main.get(0);
				nombreCarteValide++;
			}
//			else if(Joueurs.get(i).main.get(o).getEffet()== 1){
//				Joueurs.get(i).main.get(0);
//				nombreCarteValide++;
//			}

		}

		if(nombreCarteValide==0){

			System.out.println("Vous avez "+nombreCarteValide+" carte pouvant �tre jou�");
			if(Pioche.pioche.JDC.size()==0){
				regenererPioche();
			}

			Joueurs.get(i).prendreCarte(Pioche.tirerCarte());

			if(Joueurs.get(i).main.getLast().CoupValide(Joueurs.get(i).main.getLast(), talon)==true){
				System.out.println("\n ************ Bonne Pioche ************ \n A vous de jouer la carte n�"+(Joueurs.get(i).main.size()-1)+"\n");
				Tour(i);
			}
			else if(Joueurs.get(i).main.getLast().getValeur()==talon.getValeur() || Joueurs.get(i).main.getLast().getCouleur()==talon.getCouleur()){
				System.out.println("\n ************ Bonne Pioche ************ \n A vous de jouer la carte n�"+(Joueurs.get(i).main.size()-1)+"\n");
//				talon.afficherCartePremiereTalon(Joueurs.get(i).main.get(Joueurs.get(i).main.size()-1));
//				Joueurs.get(i).JouerCarte(Joueurs.get(i).main.size()-1);
				Tour(i);
			}
			else if(Joueurs.get(i).main.getLast().getValeur()==13 || Joueurs.get(i).main.getLast().getValeur()==14){
				System.out.println("\n ************ Bonne Pioche ************ \n A vous de jouer la carte n�"+(Joueurs.get(i).main.size()-1)+"\n");
				//ChangementCouleur(Joueurs.get(i).main.size()-1,i);
				Tour(i);
			}


			return false;

		}else if(nombreCarteValide!=0){
			System.out.println("Vous avez "+nombreCarteValide+" cartes pouvant �tre jou�s");
			return true;
		}

		return false;	
	}	*/

}