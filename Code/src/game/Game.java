package game;

import java.util.LinkedList;
import java.util.Scanner;

import cards.ActionCard;
import cards.Divinity;
import player.Bot;
import player.Human;
import player.Player;
import strategy.EasyStrategy;
import strategy.HardStrategy;
import strategy.MediumStrategy;


/**Classe qui gere la partie*/
public class Game {

	/**Attribut qui contient la liste des joueurs et des bots dans la partie*/
	public static LinkedList<Player> Players;
	
	/**Attribut qui représente le nombre de joueurs dans la partie*/
	public static int nbJoueur = 0;

	/**Attribut qui représente la liste des cartes du jeu*/
	static CardGame cardgame = new CardGame();
	
	/**Attribut qui représente la liste de cartes actions*/
	private LinkedList<ActionCard> listeCartesAction = new LinkedList<ActionCard>();
	
	/**Attribut  qui représente la liste de divinités*/
	private LinkedList<Divinity> listeCartesDivinites = new LinkedList<Divinity>();

	/*protected static String joueurEnCours;*/

	/**Methpde pour initialiser la partie, le nombre de joueurs et de bots*/
	public static void initGame() {
		Game.Players = new LinkedList<Player>();
		System.out.println("Commencer une nouvelle partie\n");
		
		Scanner sc;
		String choix;
		int boucle = 0;
		boolean presenceBot = false;
		do{
			System.out.println("Combien de joueurs voulez ajouter:");
			try{
				sc = new Scanner(System.in);
				choix = sc.nextLine();
				boucle = Integer.parseInt(choix);
				if(boucle<2)
					System.out.println("il faut au minimum 2 joueurs");
			}catch(Exception e){
				e.getMessage();
				System.out.println("Vous n'avez pas entré un nombre correct ! ");
				
			}
		}while(boucle<2);

		for (int i = 0; i < boucle; i++) {//boucle qui continu tant qu'on a pas atteint le nombre de joueur demandé

			System.out.println("Choix 1 : Ajouter Joueur");
			System.out.println("Choix 2 : Ajouter BOT");
			System.out.println("Votre choix : ");
			int ch;

			try{
				sc = new Scanner(System.in);
				choix = sc.nextLine();
				ch = Integer.parseInt(choix);
			}catch(Exception e){
				e.getMessage();
				System.out.println("Vous n'avez pas entré un nombre correct ! ");
				break;
			}

			switch(ch){
			case 1: //ajout d'un joueur
				ajouterJoueur();
				nbJoueur++;
				break;

			case 2: //ajout d'un bot
				ajouterBot();
				presenceBot=true;
				nbJoueur++;
			default:
				break;
			}
		}
		if(presenceBot)
			choisirDifficulteBot();
		System.out.println("il y a "+nbJoueur+" joueurs dans la partie");
		System.out.println("La partie commence, on peut distribuer les cartes \n");
		Game.startGame(); //démarre la partie une fois que tous les joueueurs sont ajoutés
	}


	/**Méthode qui permet de commencer la partie*/
	private static void startGame() {
		/*int i =  0;
		if(presenceBOT == true){
			choisirDifficulteBot();
		}
		//System.out.println(jdc.getCarte());
		jdc.melangerCarte();
		Distribuer();*/
	}

	// a voir si on fait une methode tour ou une classe
	/*public static void Tour(int i){
		//nbCarteValide(i);
	 * Affiche les caract�ristiquent de la carte Talon*/
	// TestTalon();
	/*afficherJoueurEnCours(i);

		if(verificationMainJoueur()==false ){
			//while(verificationMainJoueur()==false){
			if(nbCarteValide(i)==true){
				if(Players.get(i).getNom() != "BOT"){
					tour = new Scanner(System.in);
					System.out.println("Quelle carte jouer ?");
					int carteAPoser = tour.nextInt();

					/*
	 * Création d'une carte qui s'appel carteTest, et qui prend les caract�ristiques de la carte que l'on souhaite jouer.
	 * Celle-ci servira donc pour valider le coup
	 */

	/**Méthode qui permet d'ajouter un joueur à la partie*/
	public static void ajouterJoueur(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez un nom de joueur : ");
		String joueur = scan.nextLine();
		System.out.println(Player.NOM[nbJoueur]+ " : " +joueur+ "\n");
		Player J1 = new Human(joueur,10);
		Players.add(J1);
	}

	/**Méthode qui permet d'ajouter un bot à la partie*/
	public static void ajouterBot(){
		String bot = "BOT";
		System.out.println(Player.NOM[nbJoueur]+ " : " + bot+ "\n");
		Player J2 = new Bot(bot, new HardStrategy());
		Players.add(J2);
	}

	/**Methode qui permet de choisir la difficulté du bot*/
	public static void choisirDifficulteBot(){
		boolean partieEnCours = false;
		System.out.println("\nChoississez la difficulté des BOTs \n");

		while(partieEnCours==false){

			System.out.println("Choix 1 : Facile/Easy");
			System.out.println("Choix 2 : Moyen/Medium");
			System.out.println("Choix 3 : Difficile/Hard");

			Scanner sc = new Scanner(System.in);
			String choix = sc.nextLine();
			int strategy = Integer.parseInt(choix);

			switch(strategy){
			case 1: //bot facile
				//Bot.setStrategy(new EasyStrategy());
				System.out.println("Vous avez choisi la difficulté facile pour les bots\n");
				partieEnCours=true;
				break;
			case 2: //bot moyen
				//Bot.setStrategy(new MediumStrategy());
				System.out.println("Vous avez choisi la difficulté moyen pour les bots\n");
				partieEnCours=true;
				break;
			case 3: //bot difficile
				//Bot.setStrategy(new HardStrategy());
				System.out.println("Vous avez choisi la difficulté difficile pour les bots\n");
				partieEnCours=true;
				break;

			default: //erreur si le nombre entré n'est pas correcte
				System.out.println("Erreur ! Vous n'avez pas entré un bon choix \n");
				break;
			}
		}
	}



	//Calculer le score de chqaue joueur 
	//pour cela il aut calculer le nombre de croyant
	public static void Score(int k){}

	///////////A VOIR SI C'est Utile de regenerer la pioche
	public static void regenererPioche(){}

	/*piocher une carte
	public static boolean piocher(int i, int n){
		if(sens==true){
			for(int k =0; k<n;k++){
				if(i!=nbJoueur-1){
					if(Pioche.pioche.JDC.size()==0)
						regenererPioche();
					Players.get(i+1).prendreCarte(Pioche.tirerCarte());
				}
				else{
					if(Pioche.pioche.JDC.size()==0)
						regenererPioche();
					Players.getFirst().prendreCarte(Pioche.tirerCarte());
				}
			}
		}
		else if(sens==false){
			for(int k =0; k<n;k++){
				if(i!=0){
					if(Pioche.pioche.JDC.size()==0){
						regenererPioche();
					}
					Players.get(i-1).prendreCarte(Pioche.tirerCarte());
				}
				else{
					if(Pioche.pioche.JDC.size()==0){
						regenererPioche();
					}
					Players.getLast().prendreCarte(Pioche.tirerCarte());
				}
			}
		}
		return false;
	}	*/

	/*Afficher le joueur qui joue
	private static void afficherJoueurEnCours(int i) {
		System.out.println(Players.get(i).getNom()+ " doit jouer \n");
		System.out.println("Main de " +Players.get(i).getNom()+ " :  " );
		System.out.println(Players.get(i));
	}*/

	//Si on a besoin d'un sens
	public static void determinerSens() {}

	/*Methode qui permet de distribuer des cartes
	public static void Distribuer() {
		int i;	
		Iterator<Player> it = Players.iterator();
		while(it.hasNext()){
			Player player = it.next();
			for(i=0;i<7;i++){
				player.prendreCarte(jdc.tirerCarte());
			}
		}						
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