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
		System.out.println("-----Bienvenue sur Pandocr�on-----");
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		System.out.println("\n\n");
		
		do{
			if (!choix.equals("1")){
				System.out.println("vous avez fait une erreur lors de l'entr�e de la valeur");
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
	
	//m�thode permettant de jouer une nouvelle partie
	public void jouerNouvellePartie(){
		Scanner sc = new Scanner(System.in);
		String choix="2";
		System.out.println("Vous d�sirez lancer un nouvelle partie");
		System.out.println("Combien de joueur souhaitez vous initialiser ?");
		System.out.println("Cette partie peut contenir entre 2 et " + game.getNbJoueurMax());
		do{
			if (!choix.equals("2")){
				System.out.println("vous avez fait une erreur lors de l'entr�e de la valeur");
			}
			System.out.println("tapez 0 pour revenir au menu principal");
			System.out.print("votre choix : ");
			choix = sc.nextLine();
			System.out.println(Integer.parseInt(choix));
		}while( !(choix.matches("[0-9]+") && (Integer.parseInt(choix) >= 2 && Integer.parseInt(choix) <= game.getNbJoueurMax())) && Integer.parseInt(choix)!=0 );
		if (choix.equals("0")){
			MenuPrincipal();
		}else{
			System.out.println("vous avez d�cid� de cr�er "+choix+" joueurs");
			this.creationJoueurs(Integer.parseInt(choix));
		}
		sc.close();
	}
	
	//Permet de cr�er un joueur
	public void creationJoueurs(int nbJoueur){
		System.out.println("utilitaire de cr�ation de joueur");
	}
	
	//m�thode permettant le chargement d'une ancienne partie
	public void chargerAnciennePartie(){
		System.out.println("cette partie n'est pas encore impl�ment�e");
		this.MenuPrincipal();
	}
	
}
