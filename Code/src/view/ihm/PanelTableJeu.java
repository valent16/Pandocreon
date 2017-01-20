package view.ihm;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.game.De;
import model.game.GameManager;
import model.player.Player;
import view.IViewGame;
import model.cards.*;

import java.awt.Component;
/**Panel representant le panel de la table de jeu*/
public class PanelTableJeu extends JPanel implements IViewGame {
	
	private static final long serialVersionUID = 1L;

	/**Panel contenant la liste des joueur en jeu*/
	JPanel panelJoueurs;
	
	/**scroller de la liste des joueurs en jeu*/
	ScrollerPlayer scrollJoueur;
	
	/**panel contenant l'image de la face de de du tour*/
	ImagePanel panelImageDe;
	
	/**label*/
	JLabel label;
	
	/**panel englobant le de*/
	JPanel panelDeTour;
	
	/**scroll des croyants poses sur la table*/
	ScrollerCard scrollCroyant;
	
	/**Constructeur
	 * @param gameManager le gestionnaire de partie
	 * @param de le De
	 */
	public PanelTableJeu(final GameManager gameManager, De de){
		this.setLayout(new BorderLayout());
		JPanel panelGauche = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
		JPanel panelDroite = new JPanel();
		panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
		
		//Definition du panel des joueurs
		panelJoueurs = new JPanel();
		panelJoueurs.setLayout(new BoxLayout(panelJoueurs, BoxLayout.Y_AXIS));
		panelJoueurs.setPreferredSize(new Dimension(800,120));
		
		JPanel panelLabelJoueurJeu = new JPanel();
		JLabel labelJoueurEnJeu = new JLabel("liste des joueurs en jeu:");
		panelLabelJoueurJeu.add(labelJoueurEnJeu);
		
		panelJoueurs.add(panelLabelJoueurJeu);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	scrollJoueur = new ScrollerPlayer(gameManager.getPlayers());
            	panelJoueurs.add(scrollJoueur);
            }
        });	
		
		
		//Panel de la pile d'appel de carte
		final JPanel panelCroyant = new JPanel();
		panelCroyant.setLayout(new BoxLayout(panelCroyant, BoxLayout.Y_AXIS));
		panelCroyant.setPreferredSize(new Dimension(800,200));
		
		JPanel panelLabelCroyants = new JPanel();
		JLabel labelCroyant = new JLabel("liste des croyants sur la table de jeu:");
		panelLabelCroyants.add(labelCroyant);
		
		labelCroyant.setHorizontalAlignment(SwingConstants.LEFT);
		labelCroyant.setHorizontalAlignment(SwingConstants.LEFT);
		panelCroyant.add(panelLabelCroyants);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	scrollCroyant = new ScrollerCard(new ArrayList<Card>(gameManager.getPilesCartesTour()));
            	panelCroyant.add(scrollCroyant);
            }
        });

		//definition de l'espace reserve au de
		panelDeTour = new JPanel();
		panelDeTour.setLayout(new BoxLayout(panelDeTour, BoxLayout.Y_AXIS));
		
		JPanel panelLabelDe = new JPanel();
		panelLabelDe.setLayout(new BorderLayout(0, 0));
		JLabel labelDe = new JLabel("face du de:");
		panelLabelDe.add(labelDe);
		
		panelJoueurs.add(panelLabelJoueurJeu);
		panelDeTour.add(panelLabelDe);
		
		//mettre une image non definie lorseque le d� est affich� pour la premiere fois
		label = new JLabel();
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelDeTour.add(label);
		panelDeTour.add(Box.createRigidArea(new Dimension(0,250)));
		
		
		panelGauche.add(panelJoueurs);
		panelGauche.add(panelCroyant);
		
		panelDroite.add(panelDeTour);
		panelDroite.add(Box.createRigidArea(new Dimension(150,0)));
		
		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
	}

	@Override
	public void majJoueurs() {
		scrollJoueur.majPlayer();
	}

	@Override
	public void majFaceDe() {
		switch(De.getInstanceDe().getFace()){
		case JOUR:
			panelImageDe = new ImagePanel("./images/OrigineCarte/jour.jpg",800/20,800/20);
			label.setIcon(new ImageIcon(panelImageDe.getBufferedImage().getScaledInstance(800/20,800/20, Image.SCALE_SMOOTH)));
			break;
		case NUIT:
			panelImageDe = new ImagePanel("./images/OrigineCarte/nuit.jpg",800/20,800/20);
			label.setIcon(new ImageIcon(panelImageDe.getBufferedImage().getScaledInstance(800/20,800/20, Image.SCALE_SMOOTH)));
			break;
			
		case NEANT:
			panelImageDe = new ImagePanel("./images/OrigineCarte/neant.jpg",800/20,800/20);
			label.setIcon(new ImageIcon(panelImageDe.getBufferedImage().getScaledInstance(800/20,800/20, Image.SCALE_SMOOTH)));
			break;
			
		case NOTREFERENCED:
			break;
		}
		panelDeTour.revalidate();
	}

	@Override
	public void majTableCroyant() {
		scrollCroyant.majCarte(new ArrayList<Card>(GameManager.getInstanceUniqueManager().getCroyants()));
	}

	@Override
	public void majNbTours() {
		//TODO A developper pour afficher le tour de jeu
	}

	@Override
	public void afficherVainqueur(Player p) {
		JOptionPane.showMessageDialog(null, "le vainqueur de la partie est: "+p.getNom(),"Affichage du vainqueur",JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void afficherDefaite(Player p) {
		JOptionPane.showMessageDialog(null, "le joueur: "+p.getNom()+" a perdu","Affichage d'un perdant", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void changementJoueur() {}

	@Override
	public void majJoueurActif() {
		scrollJoueur.surlignerJoueurActif();
	}
}
