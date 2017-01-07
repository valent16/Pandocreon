package view.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.GameController;
import model.game.GameManager;

public class Client extends JFrame{

	private static final long serialVersionUID = 1L;

	private JFrame frame = new JFrame();

	private GameController gc = new GameController();

	private GameManager gm = GameManager.getInstanceUniqueManager();

	public Client(){
		initialize();
		frame.setTitle("Demarrage de Partie Pandocreon");
		frame.setSize(250, 250);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public void initialize(){
		frame.getContentPane().setLayout(new BorderLayout());
		//Faire un panel centrale en grid et ajouter des boutons nouvelle partie, charger partie, afficher les regles
		//Lors de la nouvelle partie on change le panel et on demande le nombre de joueurs et la difficulte

	}




	public JFrame getFenetre(){
		return frame;
	}
}
