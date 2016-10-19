package game;

import java.util.LinkedList;
import java.util.Scanner;
import player.Player;

/*import fr.utt.srt.lo02.projet.chatironlagrange.uno.Game;
import fr.utt.srt.lo02.projet.chatironlagrange.uno.Pioche;
import fr.utt.srt.lo02.projet.chatironlagrange.uno.Talon;
import fr.utt.srt.lo02.projet.chatironlagrange.uno.carte.CarteUno;
import fr.utt.srt.lo02.projet.chatironlagrange.uno.joueur.Bot;
import fr.utt.srt.lo02.projet.chatironlagrange.uno.joueur.Joueur;*/


/* Faire une classe pour chaque type de carte qui herite de Carte:
 * - Classe Croyant
 * - classe Guide
 * 
 * 
	/*attributs:
	 * - une pile de carte
	 * - des joueurs
	 */

	/*methodes:
	 * - Constructeur Game()
	 * - methode static initGame qui fait un scanner qui appele une methode numberPlayer() demande le nombre de joueur, et une autre method DescriptionPlayer 
	 * 			qui demande le nom du joueur
	 * - a voir si on met des noms specifiques au joueur ou on met des numeros
	 * - une méthode ensuite cardsDistribution() qui distribut 7 cartes par joueurs et une carte divinté
	 * - une methode Start_game qui demarre la partie, initialise le nombre de joueurs etc
	 * - une méthode qui demande le nombre de joueurs
	 * - une méthode qui gere les tours 	
	 * - une méthode lancerDé qui retourne de manière random une origin	 	
	 */

	//Arraylist de la class Player
	//avec un getter et un setter 
	//ensuite un autre getter et setter pour le nombre de joueur 

/**Classe qui gere la partie*/
public class Game {

	/**Attribut qui contient la liste des joueurs et des bots dans la partie*/
	public static LinkedList<Player> Players;
	
	/**Attribut qui représente le nombre de joueurs dans la partie*/
	public static int nbJoueur = 0;
	
	/**Attribut qui représente la liste des cartes du jeu*/
	static CardGame cardgame = new CardGame();
	
	/*protected static String joueurEnCours;
	public static boolean presenceBOT = false;*/	
	
	public static String niveauBOT;
	
	/**Méthode qui permet de configurer la partie : (nombre de joueurs et de bots)*/
	
	/////////////////////////////AVANT TOUT DEMANDER COMBIEN DE JOUEURS rééle on veut ajouter ensuite le nombre de bot
	//ensuite on donne les noms des joueurs et des bots 
	//seuleemnt après on peut distribuer les cartes//////////////////////////////////////////////////
	public void initGame() {
		Game.Players = new LinkedList<Player>();
		boolean leave = false;
		System.out.println("Commencer une nouvelle partie \n");

		while (leave == false) {
			System.out.println("Choix 1 : Ajouter Joueur");
			System.out.println("Choix 2 : Ajouter BOT");
			System.out.println("Choix 3 : Commencer partie");
			System.out.println("Votre choix : ");
			Scanner sc = new Scanner(System.in);
			String choix = sc.nextLine();
			int ch = Integer.parseInt(choix);
			
			switch(ch){
			case 1: //ajout d'un joueur
				if(nbJoueur<10){
					ajouterJoueur();
					nbJoueur++;
				}else
					System.out.println("Il faut au maxi 10 joueurs");
				break;

			case 2: //ajout d'un bot
				if(nbJoueur<10){
					ajouterBot();
					nbJoueur++;
				}

				else
					System.out.println("Il faut au maxi 10 joueurs");
				break;

			case 3:

				/*
				 * M�thode qu'on avait dans la class Test.
				 * Dans la class test, il ne reste plus que la m�thode pour cr�e un nouvelle partie, qui appellera la 
				 * methode commencerPartie();
				 */

				if(nbJoueur<2)
					System.out.println("il faut au minimum 2 joueurs");

				else{
					System.out.println("il y a "+nbJoueur+" joueurs dans la partie");
					System.out.println("La partie commence, on peut distribuer les cartes \n");
					Game.startGame(); //démarre la partie une fois que tous les joueueurs sont ajoutés
					leave=true;
				}
				break;

			default:
				/*
				 * Si l'on ne rentre pas un chiffre parmis ceux que l'on
				 * demande. N�anmoins, si l'on rentre une lettre, ou un symbole,
				 * �a g�n�re une erreur, donc faudra trouver le moyen de bloqu�
				 * ca, ou d'y mettre une exception
				 */
				System.out.println("Vous n'avez pas entr� un nombre correct ! ");

				break;
			}
		}
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
					 * Cr�ation d'une carte qui s'appel carteTest, et qui prend les caract�ristiques de la carte que l'on souhaite jouer.
					 * Celle-ci servira donc pour valider le coup
					 */

					/*CarteUno carteTest = new CarteUno(Players.get(i).main.get(carteAPoser).getValeur(), Players.get(i).main.get(carteAPoser).getCouleur(), 0);
					if(Players.get(i).main.get(carteAPoser).getValeur()==11 && carteTest.CoupValide(carteTest, talon) == true ){
						determinerSens();
						talon.afficherCartePremiereTalon(Players.get(i).main.get(carteAPoser));	
						Players.get(i).JouerCarte(carteAPoser);
						System.out.println("Le sens de la partie change \n");

						if(i==0 && nbJoueur==2){
							i=0;
							//afficherJoueurEnCours(i);
							Tour(i);
						}
						else if(i==1 && nbJoueur==2){
							i=1;
							//afficherJoueurEnCours(i);
							Tour(i);
						}
					}
					
					//si la carte est noire et donc soit plus4 ou joker, on ne v�rifie pas, on pose, c'est tout car ces cartes peut aller sur n'importe 
					//quelle couleur et mm valeur
					else if(Players.get(i).main.get(carteAPoser).getEffet()==2 && carteTest.CoupValide(carteTest, talon) == true ){		

						//System.out.println("Carte talon : " +talon.afficherCartePremiereTalon(Joueurs.get(i).main.get(carteAPoser)));
						//on r�actualise la talon � l'affichage pour le prochain joueur
						piocher(i,2);
						talon.afficherCartePremiereTalon(Players.get(i).main.get(carteAPoser));	
						Players.get(i).JouerCarte(carteAPoser);
						System.out.println("Le joueurs suivant a piocher 2 cartes et passe son tour \n");

						if(sens==true){
							if(i==nbJoueur-2 && nbJoueur>2){
								i=0;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i!=nbJoueur-1 && nbJoueur>2){
								i=i+2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i!=nbJoueur-1 && nbJoueur==2){
								i=0;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i==nbJoueur-1 && nbJoueur>2){
								i=1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else{
								i=1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
						}
						else if(sens==false){

							if(i>=2 && nbJoueur>2){
								i=i-2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}else if(i==1){
								i=nbJoueur-1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}else if(i==0 ){
								i=nbJoueur-2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
						}
					}

					//si la carte est noire et donc soit plus4 ou joker, on ne v�rifie pas, on pose, 
					//c'est tout car ces cartes peut aller sur n'importe quelle couleur et mm valeur
					else if(Players.get(i).main.get(carteAPoser).getValeur()==14){		

						//System.out.println("Carte talon : " +talon.afficherCartePremiereTalon(Joueurs.get(i).main.get(carteAPoser)));		
						//on r�actualise la talon � l'affichage pour le prochain joueur

						ChangementCouleur(carteAPoser, i);
						talon.afficherCartePremiereTalon(Players.get(i).main.get(carteAPoser));	
						Players.get(i).JouerCarte(carteAPoser);
						piocher(i,4);
						System.out.println("Le joueurs suivant a piocher 4 cartes et passe son tour \n");

						if(sens==true){
							if(i==nbJoueur-2 && nbJoueur>2){
								i=0;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i!=nbJoueur-1 && nbJoueur>2){
								i=i+2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i!=nbJoueur-1 && nbJoueur==2){
								i=0;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i==nbJoueur-1 && nbJoueur>2){
								i=1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else{
								i=1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
						}
						else if(sens==false){
							if(i>=2 && nbJoueur>2){
								i=i-2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}else if(i==1){
								i=nbJoueur-1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}else if(i==0 ){
								i=nbJoueur-2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
						}
						// System.out.println("CA MARCHE !!!!!!!!!!!!!!!!! Carte sp�ciale !! COUP VALIDE");
					}

					else if(Players.get(i).main.get(carteAPoser).getValeur()==13){		
						//si la carte est noire et donc soit plus4 ou joker, on ne v�rifie pas, on pose, c'est tout car ces cartes peut aller 
						//sur n'importe quelle couleur et mm valeur
						//System.out.println("Carte talon : " +talon.afficherCartePremiereTalon(Joueurs.get(i).main.get(carteAPoser)));		
						//on r�actualise la talon � l'affichage pour le prochain joueur
						ChangementCouleur(carteAPoser, i);
						talon.afficherCartePremiereTalon(Joueurs.get(i).main.get(carteAPoser));	
						Joueurs.get(i).JouerCarte(carteAPoser);
					}else if(Joueurs.get(i).main.get(carteAPoser).getValeur()==10 && carteTest.CoupValide(carteTest, talon) == true ){	
						//Carte passer tour	
						//System.out.println("Carte talon : " +talon.afficherCartePremiereTalon(Joueurs.get(i).main.get(carteAPoser)));		
						//on r�actualise la talon � l'affichage pour le prochain joueur
						talon.afficherCartePremiereTalon(Joueurs.get(i).main.get(carteAPoser));		//on r�actualise la talon � l'affichage pour le prochain joueur
						Joueurs.get(i).JouerCarte(carteAPoser);
						System.out.println("Le joueur suivant ne peut pas jouer \n");

						if(sens==true){
							if(i==nbJoueur-2 && nbJoueur>2){
								i=0;
								//afficherJoueurEnCours(i);
								Tour(i);
							}else if(i!=nbJoueur-1 && nbJoueur>2){
								i=i+2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i!=nbJoueur-1 && nbJoueur==2){
								i=0;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i==nbJoueur-1 && nbJoueur>2){
								i=1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else{
								i=1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
						}
						else if(sens==false){

							if(i>=2 && nbJoueur>2){
								i=i-2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i==1){
								i=nbJoueur-1;
								//afficherJoueurEnCours(i);
								Tour(i);
							}
							else if(i==0 ){
								i=nbJoueur-2;
								//afficherJoueurEnCours(i);
								Tour(i);
							}

						}	

					}else if(carteTest.CoupValide(carteTest, talon) == true){
						//TestCarteAPoser(carteAPoser, i);
						talon.afficherCartePremiereTalon(Joueurs.get(i).main.get(carteAPoser));		//on r�actualise la talon � l'affichage pour le prochain joueur
						Joueurs.get(i).JouerCarte(carteAPoser);
						//System.out.println("CA MARCHE !!!!!!!!!!!!!!!!! COUP VALIDE");

					}else{
						System.out.println("coup non valide");
						//afficherJoueurEnCours(i);
						Tour(i);
					}

				}else{
					if(niveauBOT=="facile")
						Bot.JouerFacile(i);
					else if(niveauBOT == "moyen")
						Bot.JouerMoyen(i);
					else
						Bot.JouerDifficile(i);
				}
			}

			if(sens==true){
				if(i!=nbJoueur-1){
					i++;
					//afficherJoueurEnCours(i);
					Tour(i);
				}
				else{
					i=0;
					//afficherJoueurEnCours(i);
					Tour(i);
				}
			}
			else if(sens==false){
				if(i!=0){
					i--;
					//afficherJoueurEnCours(i);
					Tour(i);
				}
				else if(i==0 && nbJoueur>2){
					i=nbJoueur-1;
					//afficherJoueurEnCours(i);
					Tour(i);
				}
				else if(i==0 && nbJoueur==2){
					i=nbJoueur-1;
					//afficherJoueurEnCours(i);
					Tour(i);
				}
			}
		}
	}*/

	/**Méthode qui permet d'ajouter un joueur à la partie*/
	public void ajouterJoueur(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrez un nom de joueur : ");
		String joueur = scan.nextLine();
		System.out.println(Player.NOM[nbJoueur]+ " : " +joueur+ "\n");
		Player J1 = new Player(joueur);
		Players.add(J1);
	}

	/**Méthode qui permet d'ajouter un bot à la partie*/
	public void ajouterBot(){

		String bot = "BOT";
		System.out.println(Player.NOM[nbJoueur]+ " : " + bot+ "\n");
		Player J2 = new Player(bot);
		Players.add(J2);
		////////////////////presenceBOT = true;
	}

	/**Methode qui permet de choisir la difficulté du bot*/
	public static void choisirDifficulteBot(){
		boolean partieEnCours = false;
		/*
		 * 
		 */
		System.out.println("\nChoississez la difficult� des BOTs \n");

		while(partieEnCours==false){

			System.out.println("Choix 1 : Facile/Easy");
			System.out.println("Choix 2 : Moyen/Medium");
			System.out.println("Choix 3 : Difficile/Hard");

			Scanner sc = new Scanner(System.in);
			String choix = sc.nextLine();
			int ch = Integer.parseInt(choix);

			switch(ch){
			case 1: //bot facile
				niveauBOT = "facile";
				partieEnCours=true;
				break;
			case 2: //bot moyen
				niveauBOT = "moyen";
				partieEnCours=true;
				break;
			case 3: //bot difficile
				niveauBOT = "difficile";
				partieEnCours=true;
				break;

			default: //erreur si le nombre entré n'est pas correcte
				System.out.println("Error ! Vous n'avez pas entré un bon choix \n");
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