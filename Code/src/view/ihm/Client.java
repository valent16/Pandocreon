package view.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.GameController;
import model.game.Game;
import model.game.GameManager;

public class Client extends JFrame{

	private static final long serialVersionUID = 1L;

	private JFrame frame = new JFrame();

	private GameController gc = new GameController();

	private GameManager gm = GameManager.getInstanceUniqueManager();

	public Client(){
		initialize();
		frame.setTitle("Demarrage de Partie Pandocreon");
		frame.setPreferredSize(new Dimension(250, 250));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
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
