package view.ihm.client;

import java.util.LinkedList;

import javax.swing.*;
import javax.swing.JOptionPane;

import controller.GameController;

/**Classe qui represente le menu de l'IHM*/
public class Client{

	/**Attribut represennat les composants de l'interface graphique*/
	private JFrame frame = new JFrame();	
	private PanelMenuPrincipale panelMenuPrincipale;
	private PanelAjoutBot panelAjoutBot;
	private PanelAjoutDifficulte panelAjoutDifficulte;
	private PanelAjoutHumain panelAjoutHumain;
	private PanelAjoutInfoHumain panelAjoutInfoHumain;
	private PanelConfirmation panelConfirmation;

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

	/**Attribut representant le numero du joueur*/
	private int indexHumain = 1;
	
	/**Attribut permettant de démarrer la partie*/
	private boolean initialize = false;
	
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

	/**methode permettant d'initialiser les composants de la fenetre*/
	public void initialize(){
		setPanelMenuPrincipale(new PanelMenuPrincipale(this));
		//panelAjoutInfoHumain = new PanelAjoutInfoHumain(this);	
	}

	//methode permettant d'ajouter les bots
	public void ajouterBots() {
		setPanelAjoutBot(new PanelAjoutBot(this));
	}

	/**Methode permettant d'ajouter la difficulté des bots*/
	public void ajouterDifficulte(){
		setPanelAjoutDifficulte(new PanelAjoutDifficulte(this));
	}

	/**Methode permettant d'ajouter les joueurs humains en fonction du nombre de joueur saisi*/
	public void ajouterJoueurHumain() {
		setPanelAjoutHumain(new PanelAjoutHumain(this));
	}

	/**Methode permettant d'ajouter le nom et l'age a un humain*/
	public void ajouterInfoJoueurHumain() {
		setPanelAjoutInfoHumain(new PanelAjoutInfoHumain(this));		
	}

	/**Methode permettant de verifier les donnees saisies et de les confirmer*/
	public void confirmerPartie() {
		setPanelConfirmation(new PanelConfirmation(this));
	}

	/**Methode permettant de lancer la partie*/
	public void lancerPartie(){
		creationPartie();
	}

	/**Methode permettant d'instancier les joueurs et les ajouter dans le gameController*/
	public void creationPartie(){
		initialize = true;
		frame.dispose();//permet de fermer la fenetre
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
			resetDonnees();
		}
	}
	
	/**Methode permettant de reinitialiser les données*/
	public void resetDonnees(){
		setNombreHumain(0);
		setNombreBot(0);
		setStrategie("");
		setListeNomBot(new LinkedList<String>());
		setListeNomHumain(new LinkedList<String>());
		setListeAgeHumain(new LinkedList<Integer>());
		setIndexHumain(1);
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
		return this.getNombreBot()+this.getNombreHumain();
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
	/**Getter pour le nombre de bot
	 * @return le nombre de bot
	 */
	public int getNombreBot(){
		return nombreBot;
	}
	/**Setter pour le nombre de bot
	 * @param nb le nombre de bot
	 */
	public void setNombreBot(int nb){
		nombreBot = nb;
	}
	/**Getter pour la strategie des bots
	 * @return la strategie des bots
	 */
	public String getStrategie(){
		return strategie;
	}
	/**Setter pour la strategie des bots
	 * @param strat la strategie des bots
	 */
	public void setStrategie(String strat) {
		strategie = strat;
	}
	/**Getter pour le nombre d'humain
	 * @return le nombre d'humain
	 */
	public int getNombreHumain() {
		return nombreHumain;
	}
	/**Setter pour le nombre d'humain
	 * @param nb le nombre d'humain
	 */
	public void setNombreHumain(int nb){
		nombreHumain = nb;
	}
	/**Getter pour les noms des humains
	 * @return les noms des humains
	 */
	public LinkedList<String> getListeNomHumain() {
		return listeNomHumain;
	}
	/**Setter pour les noms des humains
	 * @param liste, les noms des humains
	 */
	public void setListeNomHumain(LinkedList<String> liste) {
		listeNomHumain = liste;
	}
	/**Getter pour les ages des humains
	 * @return les ages des humains
	 */
	public LinkedList<Integer> getListeAgeHumain(){
		return listeAgeHumain;
	}
	/**Setter pour les ages des humains
	 * @param liste les ages des humains
	 */
	public void setListeAgeHumain(LinkedList<Integer> liste) {
		listeAgeHumain = liste;
	}
	/**Getter pour la position de l'humain
	 * @return la position de l'humain
	 */
	public int getIndexHumain() {
		return indexHumain;
	}
	/**Setter pour la position de l'humain
	 * @param index la position de l'humain
	 */
	public void setIndexHumain(int index) {
		indexHumain = index;
	}
	/**Getter pour les noms des bots
	 * @return les noms des bots
	 */
	public LinkedList<String> getListeNomBot() {
		return listeNomBot;
	}
	/**Setter pour les noms des bots
	 * @param liste les noms des bots
	 */
	public void setListeNomBot(LinkedList<String> liste) {
		this.listeNomBot = liste;
	}
	/**Getter pour la confirmation de la partie
	 * @return la confirmation de la partie
	 */
	public boolean getInitialize(){
		return initialize;
	}
	/**Getter pour le menu principale
	 * @return le menu principal
	 */
	public PanelMenuPrincipale getPanelMenuPrincipale() {
		return panelMenuPrincipale;
	}
	/**Setter pour le menu principale
	 * @param panelMenuPrincipale le menu principal
	 */
	public void setPanelMenuPrincipale(PanelMenuPrincipale panelMenuPrincipale) {
		this.panelMenuPrincipale = panelMenuPrincipale;
	}
	/**Getter pour le panel d'ajout des bots
	 * @return le panel d'ajout des bots
	 */
	public PanelAjoutBot getPanelAjoutBot() {
		return panelAjoutBot;
	}
	/**Setter pour le panel d'ajout des bots
	 * @param panelAjoutBot le panel d'ajout des bots
	 */
	public void setPanelAjoutBot(PanelAjoutBot panelAjoutBot) {
		this.panelAjoutBot = panelAjoutBot;
	}
	/**Getter pour le panel de la difficulte
	 * @return le panel de la difficulte
	 */
	public PanelAjoutDifficulte getPanelAjoutDifficulte() {
		return panelAjoutDifficulte;
	}
	/**Setter pour le panel de la difficulte
	 * @param panelAjoutDifficulte de la difficulte
	 */
	public void setPanelAjoutDifficulte(PanelAjoutDifficulte panelAjoutDifficulte) {
		this.panelAjoutDifficulte = panelAjoutDifficulte;
	}
	/**Getter pour le panel d'ajout des humains
	 * @return le panel d'ajout des humains
	 */
	public PanelAjoutHumain getPanelAjoutHumain() {
		return panelAjoutHumain;
	}
	/**Setter pour le panel d'ajout des humains
	 * @param panelAjoutHumain le panel d'ajout des humains
	 */
	public void setPanelAjoutHumain(PanelAjoutHumain panelAjoutHumain) {
		this.panelAjoutHumain = panelAjoutHumain;
	}
	/**Getter pour le panel d'ajout des informations sur les humains
	 * @return le panel d'ajout des informations sur les humains
	 */
	public PanelAjoutInfoHumain getPanelAjoutInfoHumain() {
		return panelAjoutInfoHumain;
	}
	/**Setter pour le panel d'ajout des informations sur les humains
	 * @param panelAjoutInfoHumain le panel d'ajout des informations sur les humains
	 */
	public void setPanelAjoutInfoHumain(PanelAjoutInfoHumain panelAjoutInfoHumain) {
		this.panelAjoutInfoHumain = panelAjoutInfoHumain;
	}
	/**Getter pour le panel de confirmation
	 * @return le panel de confirmation
	 */
	public PanelConfirmation getPanelConfirmation() {
		return panelConfirmation;
	}
	/**Setter pour le panel de confirmation
	 * @param panelConfirmation le panel de confirmation
	 */
	public void setPanelConfirmation(PanelConfirmation panelConfirmation) {
		this.panelConfirmation = panelConfirmation;
	}
}
