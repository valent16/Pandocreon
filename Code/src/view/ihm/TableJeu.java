package view.ihm;
import javax.swing.BoxLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import model.game.De;
import model.game.GameManager;
import model.player.Human;

/**Table de jeu de la partie, frame englobant tous les autres composants*/
public class TableJeu{
	
	/**frame de la partie*/
	JFrame frame = new JFrame();
	
	/**panel contenant les donnees du joueur reel*/
	PanelJoueurReel panelJoueur;
	
	/** panel representant le plateau de jeu commun*/
	PanelTableJeu panelTableJeu;
	
	/**
	 * Constructeur de la classe TableJeu
	 * @param joueur joueur humain dans la partie
	 */
	public TableJeu(Human joueur){
		frame.setTitle("Pandocreon Divinae");
		frame.setPreferredSize(new Dimension(1000,1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize(joueur);
		frame.setVisible(true);
	}
	
	/**
	 * Initialisation du plateau de jeu
	 * @param joueur joueur humain dans la partie
	 */
	public void initialize(Human joueur){
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel panelGlobal = new JPanel();
		panelGlobal.setLayout(new BoxLayout(panelGlobal, BoxLayout.Y_AXIS));
		
		panelTableJeu = new PanelTableJeu(GameManager.getInstanceUniqueManager(), De.getInstanceDe());
		panelGlobal.add(panelTableJeu);
		
		if (joueur != null ){
			panelJoueur = new PanelJoueurReel(joueur);
			panelGlobal.add(panelJoueur);
		}
		frame.getContentPane().add(panelGlobal, BorderLayout.CENTER);
		frame.pack();
	}
	
	/**
	 * Getter sur le panel du joueur humain
	 * @return panel du joueur humain
	 */
	public PanelJoueurReel getPanelJoueur(){
		return this.panelJoueur;
	}
	
	/**
	 * Getter sur le panel du plateau de jeu commun
	 * @return panel contenant le plateau de jeu commun
	 */
	public PanelTableJeu getPanelTableJeu(){
		return this.panelTableJeu;
	}

}
