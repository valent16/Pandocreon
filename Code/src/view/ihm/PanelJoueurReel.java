package view.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.player.Human;
import model.player.Player;
import model.cards.Card;
import model.cards.OriginCards.*;
public class PanelJoueurReel extends JPanel {
	
	Human joueurReel;
	
	public PanelJoueurReel(Human p){
		
		this.joueurReel = p;

		this.setLayout( new BorderLayout());
		
//		this.setLayout( new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panelGauche = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
		panelGauche.setPreferredSize(new Dimension(800,300));
		
		JPanel panelDroite = new JPanel();
		panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
		
		//liste des cartes du joueur
		final JPanel panelCartesJoueur = new JPanel();
		panelCartesJoueur.setLayout(new BoxLayout(panelCartesJoueur, BoxLayout.Y_AXIS));
		
		JPanel panelLabelCartesJoueur = new JPanel();
		JLabel LabelCartesJoueur = new JLabel("liste des cartes de votre jeu:");
		panelLabelCartesJoueur.add(LabelCartesJoueur);
		
		panelCartesJoueur.add(panelLabelCartesJoueur);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panelCartesJoueur.add(new ScrollerCard(new ArrayList<Card>(joueurReel.getHand())));
//            	frame.pack();
            }
        });
		
		//Affichages des cartes rattach�es au joueur
		final JPanel panelCartesRattachesJoueur = new JPanel();
		panelCartesRattachesJoueur.setLayout(new BoxLayout(panelCartesRattachesJoueur, BoxLayout.Y_AXIS));
		panelCartesRattachesJoueur.setPreferredSize(new Dimension(800,200));
		
		JPanel panelLabelCartesRattaches = new JPanel();
		JLabel LabelCartesRattaches = new JLabel("Listes des cartes rattachees au joueur:");
		panelLabelCartesRattaches.add(LabelCartesRattaches);
		
		LabelCartesRattaches.setHorizontalAlignment(SwingConstants.LEFT);
		panelCartesRattachesJoueur.add(panelLabelCartesRattaches);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panelCartesRattachesJoueur.add(new ScrollerCard(getCartesRattaches()));
//            	frame.pack();
            }
        });
		
		panelGauche.add(panelCartesJoueur);
		panelGauche.add(panelCartesRattachesJoueur);
		
		
		final JPanel panelPointsAction = new JPanel();
		panelPointsAction.setLayout(new BoxLayout(panelPointsAction, BoxLayout.Y_AXIS));
		
		JPanel panelLabelPA = new JPanel();
		JLabel labelPA = new JLabel("points d'action disponibles:");
		labelPA.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelPA.add(labelPA);
		
		JPanel grillePoints = new JPanel();
		grillePoints.setLayout(new GridLayout(2, 3));
		
		ImagePanel imageJour = new ImagePanel("./images/OrigineCarte/jour.jpg",800/20,800/20);
		grillePoints.add(new JLabel(new ImageIcon(imageJour.getBufferedImage().getScaledInstance(800/20,800/20, Image.SCALE_SMOOTH))));
		
		ImagePanel imageNuit = new ImagePanel("./images/OrigineCarte/nuit.jpg",800/20,800/20);
		grillePoints.add(new JLabel(new ImageIcon(imageNuit.getBufferedImage().getScaledInstance(800/20,800/20, Image.SCALE_SMOOTH))));
		
		ImagePanel imageNeant = new ImagePanel("./images/OrigineCarte/neant.jpg",800/20,800/20);
		grillePoints.add(new JLabel(new ImageIcon(imageNeant.getBufferedImage().getScaledInstance(800/20,800/20, Image.SCALE_SMOOTH))));
		
		JLabel label = new JLabel("jour");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		grillePoints.add(label);
		JLabel label_1 = new JLabel("nuit");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		grillePoints.add(label_1);
		JLabel label_2 = new JLabel("neant");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		grillePoints.add(label_2);
		
		panelPointsAction.add(panelLabelPA);
		panelPointsAction.add(grillePoints);
		
		panelDroite.add(Box.createRigidArea(new Dimension(0,230)));
		panelDroite.add(panelPointsAction);
		panelDroite.add(new PanelCarte(joueurReel.getDivinity()));
		
		
		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
//		this.add(panelGauche);
//		this.add(Box.createRigidArea(new Dimension(15,0)));
//		this.add(panelDroite);
		
	}
	
	//M�thode permettant de mettre les cartes converties par les joueurs dans une liste unique (guides et croyants)
	private List<Card> getCartesRattaches(){
		final ArrayList<Card> cartesConverties = new ArrayList<Card>();
		Iterator<SpiritGuide> it = joueurReel.getGuides().iterator();
		
		while(it.hasNext()){
			SpiritGuide guide = it.next();
			Iterator<Believer> itCroyants = guide.getCroyantsConvertis().iterator();
			while (itCroyants.hasNext()){
				cartesConverties.add(itCroyants.next());
			}
			cartesConverties.add(guide);
		}
		return cartesConverties; 
	}
}