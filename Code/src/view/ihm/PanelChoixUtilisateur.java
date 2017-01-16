package view.ihm;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class PanelChoixUtilisateur {
	JFrame frame = new JFrame();
	
	public PanelChoixUtilisateur(){
		initialize();
	}
	
	public void initialize(){
		
		JWindow fenetre = new JWindow();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panelDeposerCartes = new JPanel();
		JButton deposerCartes = new JButton("Deposer cartes");
		deposerCartes.setPreferredSize(new Dimension(150,35));
		panelDeposerCartes.add(deposerCartes);

		JPanel panelsupprimerCartes = new JPanel();
		JButton supprimerCartes = new JButton("Supprimer cartes");
		supprimerCartes.setPreferredSize(new Dimension(150, 35));
		panelsupprimerCartes.add(supprimerCartes);

		JPanel panelCompleterMain = new JPanel();
		JButton completerMain = new JButton("Completer main");
		completerMain.setPreferredSize(new Dimension(150,35));
		panelCompleterMain.add(completerMain);
		
		JPanel panelPasserTour = new JPanel();
		JButton passerTour = new JButton("Passer son tour");
		passerTour.setPreferredSize(new Dimension(150,35));
		panelPasserTour.add(passerTour);
		
		panel.add(panelDeposerCartes);
		panel.add(panelsupprimerCartes);
		panel.add(panelCompleterMain);
		panel.add(panelPasserTour);
		
		panel.setAlignmentX(SwingConstants.CENTER);
		
		fenetre.add(panel);
		
		frame.pack();
	}
}
