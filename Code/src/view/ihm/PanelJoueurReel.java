package view.ihm;

import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.JoueurController;
import model.player.Human;

import view.IViewJoueurReel;
import model.cards.Card;
import model.cards.OriginCards.*;
import model.enumType.EnumCosmogonie;
/**Panel representant le jeu du joueur reel*/
public class PanelJoueurReel extends JPanel implements IViewJoueurReel {

	/**variable permettant de savoir si le jour a fini de jouer son tour, permet de bloquer le thread principal*/
	private boolean tourFinished = false;

	private static final long serialVersionUID = 1L;

	/**controller du joueur humain*/
	JoueurController controller;

	/**instance du joueur humain*/
	Human joueurReel;

	/**Label qui represente le jour*/
	JLabel labelJour;

	/**Label qui represente la nuit*/
	JLabel labelNuit;

	/**Label qui represente le neant*/
	JLabel labelNeant;

	/**panel representant la main du joueur*/
	ScrollerCard mainJoueur;

	/**panel representant les cartes rattachees au joueur*/
	ScrollerCard cartesRattachees;

	/**panel regroupant toutes les donnees de la divinite*/
	JPanel panelDivinite;
	
	/**panel regroupant d'autres panels*/
	JPanel panelDroite;
	
	/**panel regroupant d'autres panels*/
	JPanel panelFinTour;
	
	/**Constructeur
	 * @param p l'humain 
	 */
	public PanelJoueurReel(Human p){
		this.joueurReel = p;

		this.setLayout( new BorderLayout());

		JPanel panelGauche = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
		panelGauche.setPreferredSize(new Dimension(800,300));

		panelDroite = new JPanel();
		panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));

		//liste des cartes du joueur
		final JPanel panelCartesJoueur = new JPanel();
		panelCartesJoueur.setLayout(new BoxLayout(panelCartesJoueur, BoxLayout.Y_AXIS));

		JPanel panelLabelCartesJoueur = new JPanel();
		JLabel LabelCartesJoueur = new JLabel("liste des cartes de votre jeu:");
		panelLabelCartesJoueur.add(LabelCartesJoueur);

		panelCartesJoueur.add(panelLabelCartesJoueur);
		SwingUtilities.invokeLater(new Runnable() {

			//Affichages des cartes rattachees au joueur
			@Override
			public void run() {
				mainJoueur = new ScrollerCard(new ArrayList<Card>(joueurReel.getHand()));
				panelCartesJoueur.add(mainJoueur);
			}
		});

		//Affichages des cartes rattachees au joueur
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
				cartesRattachees =new ScrollerCard(getCartesRattaches()); 
				panelCartesRattachesJoueur.add(cartesRattachees);
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

		labelJour = new JLabel("jour");
		labelJour.setHorizontalAlignment(SwingConstants.CENTER);
		grillePoints.add(labelJour);
		labelNuit = new JLabel("nuit");
		labelNuit.setHorizontalAlignment(SwingConstants.CENTER);
		grillePoints.add(labelNuit);
		labelNeant = new JLabel("neant");
		labelNeant.setHorizontalAlignment(SwingConstants.CENTER);
		grillePoints.add(labelNeant);

		panelPointsAction.add(panelLabelPA);
		panelPointsAction.add(grillePoints);


		panelFinTour = new JPanel();
		JButton FinTour = new JButton("Fin de tour");
		FinTour.setPreferredSize(new Dimension(150, 35));
		panelFinTour.add(FinTour);

		FinTour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTourFinished(true);
			}
		});
		panelFinTour.setVisible(false);

		panelDroite.add(Box.createRigidArea(new Dimension(0,190)));
		panelDroite.add(panelFinTour);
		panelDroite.add(panelPointsAction);

		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);

		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelDroite, BorderLayout.EAST);
	}
	
	/**Methode permettant d'initialiser le controller de la vue
	 * @param controller le controller du joueur 
	 */
	public void initializeController(JoueurController controller){
		this.controller = controller;
	}

	/**Methode permettant de mettre les cartes converties par les joueurs dans une liste unique (guides et croyants)
	 * @return les croyants convertis
	 */
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

	@Override
	public void majDeckCarte(){
		mainJoueur.majCarte(new ArrayList<Card>(joueurReel.getHand()));
	}

	@Override
	public void majPointsAction() {
		labelJour.setText(String.valueOf(joueurReel.getDicoPA().get(EnumCosmogonie.JOUR))); 
		labelNuit.setText(String.valueOf(joueurReel.getDicoPA().get(EnumCosmogonie.NUIT)));
		labelNeant.setText(String.valueOf(joueurReel.getDicoPA().get(EnumCosmogonie.NEANT)));
	}

	@Override
	public void majCartesRattachees() {
		cartesRattachees.majCarte(getCartesRattaches());
	}

	@Override
	public void majDivinite() {
		panelDivinite = new PanelCarte(joueurReel.getDivinity());
		panelDroite.add(panelDivinite);
	}

	@Override
	public void startTour() {
		this.setTourFinished(false);
		createFrameChoixTour();
	}
	

	/**fenetre permettant d'afficher les choix du joueur durant son tour*/
	public void createFrameChoixTour(){
		final JWindow frameChoix = new JWindow();
		frameChoix.setSize(500, 300);
		frameChoix.setLocationRelativeTo(null);
		frameChoix.setVisible(true);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//Bouton permettant de deposer des cartes du jeu
		JPanel panelDeposerCartes = new JPanel();
		JButton deposerCartes = new JButton("Deposer cartes");
		deposerCartes.setPreferredSize(new Dimension(150,35));
		panelDeposerCartes.add(deposerCartes);
		frameChoix.requestFocus();

		deposerCartes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameChoix.dispose();
			}
		});

		//Bouton permettant de supprimer des cartes du jeu
		JPanel panelsupprimerCartes = new JPanel();
		JButton supprimerCartes = new JButton("Supprimer cartes");
		supprimerCartes.setPreferredSize(new Dimension(150, 35));
		panelsupprimerCartes.add(supprimerCartes);

		supprimerCartes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameChoix.dispose();
			}
		});

		//Bouton permettant de completer la main avec des cartes
		JPanel panelCompleterMain = new JPanel();
		JButton completerMain = new JButton("Completer main");
		completerMain.setPreferredSize(new Dimension(150,35));
		panelCompleterMain.add(completerMain);

		completerMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameChoix.dispose();
				controller.completerMain();
				setTourFinished(true);
			}
		});

		//Bouton permettant de passer son tour
		JPanel panelPasserTour = new JPanel();
		JButton passerTour = new JButton("Passer son tour");
		passerTour.setPreferredSize(new Dimension(150,35));
		panelPasserTour.add(passerTour);

		passerTour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frameChoix.dispose();
				setTourFinished(true);
			}
		});

		panel.add(panelDeposerCartes);
		panel.add(panelsupprimerCartes);
		panel.add(panelCompleterMain);
		panel.add(panelPasserTour);

		panel.setAlignmentX(SwingConstants.CENTER);
		frameChoix.add(panel);
		frameChoix.pack();
	}

	/**Setter pour termine le tour
	 * @param value booleen pour savoir si on fini le tour ou pas
	 */
	private void setTourFinished(boolean value){
		if (value == false)
			panelFinTour.setVisible(true);
		else
			panelFinTour.setVisible(false);
		tourFinished = value;
	}

	/**Variable permettant de savoir si le tour du joueur est termine ou non
	 * @return booleen pour savoir si on finit le tour ou pas
	 */
	public boolean isTourFinished(){
		return tourFinished;
	}
}
