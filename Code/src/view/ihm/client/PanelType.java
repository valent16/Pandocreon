package view.ihm.client;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import view.ihm.Client;

public abstract class PanelType extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	protected Client client;
	
	/**logo du jeu*/
	protected ImageIcon logo = new ImageIcon("images/logo.png");
	
	/**Initialisation du Panel*/
	protected abstract void initialize();
	
	/*Ajout des ecouteur du panel*/
	protected abstract void ajouterListener(); 
	
	

}
