package view.ihm;

import java.util.LinkedList;

import javax.swing.*;
import javax.swing.JOptionPane;

import controller.GameController;
import model.game.Game;
import model.game.GameManager;
import view.ihm.client.PanelAjoutBot;
import view.ihm.client.PanelAjoutDifficulte;
import view.ihm.client.PanelAjoutHumain;
import view.ihm.client.PanelAjoutInfoHumain;
import view.ihm.client.PanelConfirmation;
import view.ihm.client.PanelMenuPrincipale;

/**Classe qui represente le menu de l'IHM*/
public class Client{

	/**Attribut represennat les composants de l'interface graphique*/
	private JFrame frame = new JFrame();

	/**logo du jeu*/
	private ImageIcon logo = new ImageIcon("images/logo.png");

	//model
	private final static int NOMBRE_MINIMAL_JOUEUR = 0;
	private final static int NOMBRE_MAXIMAL_JOUEUR = 9;
	private final static int AGE_JOUEUR = 20;

	/**Attribut representant le controlleur de la partie*/
	private GameController gc;

	/**Attribut representant le nombre total de humain dans la partie*/
	private int nombreHumain = 0;

	/**Attribut representant le nombre total de bot dans la partie*/
	private int nombreBot = 0;

	/**Attribut representant la strategie choisi par le joueur*/
	private String strategie;
	
	/**Attribut representant les noms des bots*/
	private LinkedList<String> listeNomBot = new LinkedList<String>();

	/**Attribut representant les noms des humains*/
	private LinkedList<String> listeNomHumain = new LinkedList<String>();

	/*Attribut representant les ages de humain*/
	private LinkedList<Integer> listeAgeHumain = new LinkedList<Integer>();

	/**attribut representant le numero du joueur*/
	private int indexHumain = 1;

	private PanelMenuPrincipale panelMenuPrincipale;

	private PanelAjoutBot panelAjoutBot;

	private PanelAjoutDifficulte panelAjoutDifficulte;

	private PanelAjoutHumain panelAjoutHumain;

	private PanelAjoutInfoHumain panelAjoutInfoHumain;

	private PanelConfirmation panelConfirmation;

	/**Constructeur du client*/
	public Client(){
		gc = new GameController();
		initialize();
		frame.setTitle("Client Pandocreon Divinae");
		frame.setSize(500, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
	}

	//methode pour initialiser les composants de la fenetre
	public void initialize(){
		setPanelMenuPrincipale(new PanelMenuPrincipale(this));
		//panelAjoutInfoHumain = new PanelAjoutInfoHumain(this);	
	}

	//methode qui permet d'ajouter les bots
	public void ajouterBots() {
		setPanelAjoutBot(new PanelAjoutBot(this));
	}

	/**Methode permettant d'ajouter la difficulté des bots*/
	public void ajouterDifficulte(){
		setPanelAjoutDifficulte(new PanelAjoutDifficulte(this));
	}

	/**Methode qui permet d'ajouter les joueurs humains en fonction du nombre de joueur saisi*/
	public void ajouterJoueurHumain() {
		setPanelAjoutHumain(new PanelAjoutHumain(this));
	}

	/**Methode Permettant d'ajouter le nom et l'age a un humain*/
	public void ajouterInfoJoueurHumain() {
		setPanelAjoutInfoHumain(new PanelAjoutInfoHumain(this));		
	}


	public void confirmerPartie() {
		setPanelConfirmation(new PanelConfirmation(this));
	}

	/**Methode pour instancier les joueurs et les ajouters dans le gameController*/
	public void CreationJoueurs(){
		int nombreTotalJoueur = nombreBot+nombreHumain;
		if(nombreTotalJoueur == 0){
			JOptionPane.showConfirmDialog(null, "Vous n'avez saisis aucun joueur", "probleme nombre de joueur", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logo);
			retourMenuPrincipale();
		}
		else{//sinon il y au moins un joueur
			
			//Creation et ajout des bots dans la partie
			for(int i = 0; i < nombreBot; i++){
				gc.CreationBot("Bot "+Game.getBotName(i), strategie);//ajout des bots
			}
			System.out.println("affichage des bots "+ GameManager.getInstanceUniqueManager().getPlayers());//TODO A ENLEVER

			//Creation et ajout des humains dans la partie
			for(int i = 0; i < nombreHumain; i++){
				gc.CreationJoueur("Humain "+Game.getBotName(i), AGE_JOUEUR);
			}
			System.out.println("affichage des joueur "+ GameManager.getInstanceUniqueManager().getPlayers());//TODO A ENLEVER
		}
	}

	/**Methode pour lancer la partie*/
	public void lancerPartie(){
		///////////////////////////////////////////////////////////////////////TODO ICI QU'on lance LA PARTIE
		System.out.println("Lancement de la partie");
		GameController gc = new GameController();
		gc.startGame();

		//TODO ON peut recuperer toutes les information (noms, age et strategie dans cette classe
		gc.CreationJoueur("valentin", 18);
		gc.CreationJoueur("David", 20);

		gc.lancerPartie();
	}

		/**Methode pour revenir au panel MenuPrincipale*/
		public void retourMenuPrincipale() {
			int option = JOptionPane.showConfirmDialog(null, "Voulez vous revenir au menu principal ?", "Revenir au Menu Principal", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logo);
			if(option == JOptionPane.OK_OPTION){
				frame.setTitle("Client Pandocreon Divinae");
				frame.setContentPane(panelMenuPrincipale);
				frame.repaint();
				frame.validate();
			}
		}

		/**Getter Jframe
		 * @return la fenetre graphique
		 */
		public JFrame getFenetre(){
			return frame;
		}

		/**Getter nombre de joueur
		 * @return le nombre de joueur choisi pour demarrer la partie
		 */
		public int getNombreJoueurs(){
			return this.getNombreBot()+this.getIndexHumain();
		}

		/**Getter pour le controller de la partie
		 * @return le GameControler
		 */
		public GameController getGc() {
			return gc;
		}

		/**Getter pour le nombre minimal de joueur
		 * @return le nombre minimal des joueurs
		 */
		public static int getNombreMinimalJoueur() {
			return NOMBRE_MINIMAL_JOUEUR;
		}
		/**Getter pour le nombre maximal des joueurs
		 * @return le nombre maximal des joueurs
		 */
		public static int getNombreMaximalJoueur() {
			return NOMBRE_MAXIMAL_JOUEUR;
		}
		/**Getter pour l'age des joueurs
		 * @return l'age du joueur
		 */
		public static int getAgeJoueur() {
			return AGE_JOUEUR;
		}

		public int getNombreBot(){
			return nombreBot;
		}

		public void setNombreBot(int nb){
			nombreBot = nb;
		}
		
		public String getStrategie(){
			return strategie;
		}

		public void setStrategie(String strat) {
			strategie = strat;
		}

		public int getNombreHumain() {
			return nombreHumain;
		}

		public void setNombreHumain(int nb){
			nombreHumain = nb;
		}

		public LinkedList<String> getListeNomHumain() {
			return listeNomHumain;
		}

		public LinkedList<Integer> getListeAgeHumain(){
			return listeAgeHumain;
		}

		public int getIndexHumain() {
			return indexHumain;
		}

		public void setIndexHumain(int index) {
			indexHumain = index;
		}
		
		public PanelMenuPrincipale getPanelMenuPrincipale() {
			return panelMenuPrincipale;
		}

		public void setPanelMenuPrincipale(PanelMenuPrincipale panelMenuPrincipale) {
			this.panelMenuPrincipale = panelMenuPrincipale;
		}

		public PanelAjoutBot getPanelAjoutBot() {
			return panelAjoutBot;
		}

		public void setPanelAjoutBot(PanelAjoutBot panelAjoutBot) {
			this.panelAjoutBot = panelAjoutBot;
		}

		public PanelAjoutDifficulte getPanelAjoutDifficulte() {
			return panelAjoutDifficulte;
		}

		public void setPanelAjoutDifficulte(PanelAjoutDifficulte panelAjoutDifficulte) {
			this.panelAjoutDifficulte = panelAjoutDifficulte;
		}

		public PanelAjoutHumain getPanelAjoutHumain() {
			return panelAjoutHumain;
		}

		public void setPanelAjoutHumain(PanelAjoutHumain panelAjoutHumain) {
			this.panelAjoutHumain = panelAjoutHumain;
		}

		public PanelAjoutInfoHumain getPanelAjoutInfoHumain() {
			return panelAjoutInfoHumain;
		}

		public void setPanelAjoutInfoHumain(PanelAjoutInfoHumain panelAjoutInfoHumain) {
			this.panelAjoutInfoHumain = panelAjoutInfoHumain;
		}

		public PanelConfirmation getPanelConfirmation() {
			return panelConfirmation;
		}

		public void setPanelConfirmation(PanelConfirmation panelConfirmation) {
			this.panelConfirmation = panelConfirmation;
		}

		public LinkedList<String> getListeNomBot() {
			return listeNomBot;
		}

		public void setListeNomBot(LinkedList<String> listeNomBots) {
			this.listeNomBot = listeNomBots;
		}
	}
