package view.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.game.De;
import model.game.GameManager;
import model.player.Bot;
import model.player.Player;
import model.strategy.MediumStrategy;
import model.cards.*;
import java.awt.Component;

public class PanelTableJeu extends JPanel {
	
	
	public PanelTableJeu(final GameManager gameManager, De de){
		this.setLayout(new BorderLayout());
//		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel panelGauche = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
		JPanel panelDroite = new JPanel();
		panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
		
		
		//Definition du panel des joueurs
		final JPanel panelJoueurs = new JPanel();
		panelJoueurs.setLayout(new BoxLayout(panelJoueurs, BoxLayout.Y_AXIS));
		panelJoueurs.setPreferredSize(new Dimension(800,120));
		
		JPanel panelLabelJoueurJeu = new JPanel();
		JLabel labelJoueurEnJeu = new JLabel("liste des joueurs en jeu:");
		panelLabelJoueurJeu.add(labelJoueurEnJeu);
//		final ArrayList<Player> liste = new ArrayList<Player>();
//		liste.add(new Bot("lala", new MediumStrategy()));
//		liste.add(new Bot("lala", new MediumStrategy()));
		
		panelJoueurs.add(panelLabelJoueurJeu);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panelJoueurs.add(new ScrollerPlayer(gameManager.getPlayers()));
//            	frame.pack();
            }
        });	
		
		
		//Panel de la pile d'appel de carte
		
		final JPanel panelPileAppelCarte = new JPanel();
		panelPileAppelCarte.setLayout(new BoxLayout(panelPileAppelCarte, BoxLayout.Y_AXIS));
		panelPileAppelCarte.setPreferredSize(new Dimension(800,200));
		
		JPanel panelLabelPileCarte = new JPanel();
		JLabel labelPileCarte = new JLabel("liste des cartes lancees par les joueurs:");
		panelLabelPileCarte.add(labelPileCarte);
		
		labelPileCarte.setHorizontalAlignment(SwingConstants.LEFT);
		labelPileCarte.setHorizontalAlignment(SwingConstants.LEFT);
		panelPileAppelCarte.add(panelLabelPileCarte);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panelPileAppelCarte.add(new ScrollerCard(new ArrayList<Card>(gameManager.getPilesCartesTour())));
            }
        });

		
		//d�finition de l'espace r�serv� au d�
		final JPanel panelDeTour = new JPanel();
		panelDeTour.setLayout(new BoxLayout(panelDeTour, BoxLayout.Y_AXIS));
		
		JPanel panelLabelDe = new JPanel();
		panelLabelDe.setLayout(new BorderLayout(0, 0));
		JLabel labelDe = new JLabel("face du de:");
		panelLabelDe.add(labelDe);
		
		panelJoueurs.add(panelLabelJoueurJeu);
		panelDeTour.add(panelLabelDe);
		
		// mettre une image non d�finie lorseque le d� est affich� pour la premiere fois
		ImagePanel panelImageDe = new ImagePanel("./images/OrigineCarte/jour.jpg",800/20,800/20);
		JLabel label = new JLabel(new ImageIcon(panelImageDe.getBufferedImage().getScaledInstance(800/20,800/20, Image.SCALE_SMOOTH)));
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelDeTour.add(label);
		panelDeTour.add(Box.createRigidArea(new Dimension(0,250)));
		
		panelImageDe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelGauche.add(panelJoueurs);
		panelGauche.add(panelPileAppelCarte);
		
		
		panelDroite.add(panelDeTour);
		panelDroite.add(Box.createRigidArea(new Dimension(150,0)));
		
		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
		
//		this.add(panelGauche);
//		this.add(panelDeTour);
		
	}
	
}