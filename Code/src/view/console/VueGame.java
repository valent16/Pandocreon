package view.console;
import java.util.Scanner;
import controller.GameController;
import model.game.Game;

public class VueGame {
	
	GameController controllerJeu;
	
	Game game;
	
	public VueGame(GameController gameController, Game jeu){
		this.controllerJeu = gameController;
		this.game = jeu;
	}

	public void MenuPrincipal(){
		Scanner sc = new Scanner(System.in);
		String choix="1";
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		System.out.println("-----Bienvenue sur Pandocrï¿½on-----");
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		System.out.println("\n\n");
		
		do{
			if (!choix.equals("1")){
				System.out.println("vous avez fait une erreur lors de l'entrï¿½e de la valeur");
			}
			System.out.println("Vous avez le choix entre les diverses options suivantes:");
			System.out.println("1- Jouer nouvelle partie");
			System.out.println("2- Charger ancienne partie");
			System.out.println("3- Quitter le jeu");
			System.out.print("votre choix : ");
			choix = sc.nextLine();
		}while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3"));
		
		switch(choix){
		case "1":
			this.jouerNouvellePartie();
			break;
		case "2":
			this.chargerAnciennePartie();
			break;
		default:
			break;
		}
		sc.close();
	}
	
	//mï¿½thode permettant de jouer une nouvelle partie
	public void jouerNouvellePartie(){
		Scanner sc = new Scanner(System.in);
		String choix="2";
		System.out.println("Vous dï¿½sirez lancer un nouvelle partie");
		System.out.println("Combien de joueur souhaitez vous initialiser ?");
		System.out.println("Cette partie peut contenir entre 2 et " + game.getNbJoueurMax());
		do{
			if (!choix.equals("2")){
				System.out.println("vous avez fait une erreur lors de l'entrï¿½e de la valeur");
			}
			System.out.println("tapez 0 pour revenir au menu principal");
			System.out.print("votre choix : ");
			choix = sc.nextLine();
			System.out.println(Integer.parseInt(choix));
		}while( !(choix.matches("[0-9]+") && (Integer.parseInt(choix) >= 2 && Integer.parseInt(choix) <= game.getNbJoueurMax())) && Integer.parseInt(choix)!=0 );
		if (choix.equals("0")){
			MenuPrincipal();
		}else{
			System.out.println("vous avez dï¿½cidï¿½ de crï¿½er "+choix+" joueurs");
			this.creationJoueurs(Integer.parseInt(choix));
		}
		sc.close();
	}
	
	//Permet de crï¿½er un joueur
	public void creationJoueurs(int nbJoueur){
		//System.out.println("utilitaire de création de joueur");
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("Creation des joueurs:");
		for(int i = 0; i<nbJoueur; i++){
			String choix="3";
			do{
				if (!choix.equals("3")){
					System.out.println("Vous avez fait un erreur lors de l'entrée de la valeur");
				}
				System.out.println("Quel type de joueur souhaitez-vous ajouter ?");
				System.out.println("1- IA");
				System.out.println("2- JoueurReel");
				System.out.println("3- Quitter");
				choix = sc.nextLine();
			}while(!choix.equals("3") && !choix.equals("2") && !choix.equals("1") );
			
			if (choix.equals("3")){
				MenuPrincipal();
			}else if (choix.equals("2")){
				creationHumain();
			}else{
				creationBot(i);
			}
		}
	}
	
	//Permet de créer un joueur humain
	public void creationHumain(){
		String nom;
		String age;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n");
		System.out.println("veuillez renseigner le nom de la personne : ");
		nom = sc.nextLine();
		System.out.println("veuillez renseigner l'age de la personne : ");
		age = sc.nextLine();
		while(!age.matches("[0-9]+")){
			System.out.println("l'age entré est invalide");
			age = sc.nextLine();
		}
		controllerJeu.CreationJoueur(nom, Integer.parseInt(age));
	}
	
	//Permet de créer un bot
	public void creationBot(int index){
		String nomStrat;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez renseigner le niveau de difficulté de ce bot : ");
		System.out.println("1- facile");
		System.out.println("2- medium");
		System.out.println("3- difficile");
		String choix = sc.nextLine();
		while(!choix.matches("[0-9]+") && Integer.parseInt(choix)>3 && Integer.parseInt(choix)<1){
			System.out.println("le choix de difficulté est invalide");
		}
		
		switch(choix){
		case "1":
			nomStrat = "facile";
			break;
		case "2": 
			nomStrat = "medium";
			break;
		case "3":
			nomStrat = "difficile";
			break;
		default:
			//Lancement Exception
			nomStrat = "";
		}
		
		
		controllerJeu.CreationBot(game.getBotName(index), nomStrat);
		System.out.println("utilitaire de crï¿½ation de joueur");
	}
	
	//mï¿½thode permettant le chargement d'une ancienne partie
	public void chargerAnciennePartie(){
		System.out.println("cette partie n'est pas encore implï¿½mentï¿½e");
		this.MenuPrincipal();
	}
	
}
