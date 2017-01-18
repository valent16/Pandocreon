package view.ihm.client;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class PanelType extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/**Attribut representant L'interface du jeu*/
	protected Client client;

	/**Bouton permettant de valider un Panel*/
	protected JButton valider;
	
	/**Bouton permettant d'annuler un Panel*/
	protected JButton annuler;
	
	/**Bouton permettant d'incrementer un compteur*/
	protected JButton plus;
	
	/**Bouton permettant de decrementer un compteur*/
	protected JButton moins;

	/**logo du jeu*/
	protected ImageIcon logo = new ImageIcon("images/logo.png");
	
	/**Initialisation du Panel*/
	protected abstract void initialize();
	
	/*Ajout des ecouteur du panel*/
	protected abstract void ajouterListener(); 
	
	

}