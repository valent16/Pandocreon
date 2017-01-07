package view.ihm;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.FlowLayout;

public class TableJeu {
	
	private JFrame frame = new JFrame();
	
	public TableJeu(){
		initialize();
		frame.setVisible(true);
	}
	
	public void initialize(){
//		frame.setTitle("Pandocreon Divinae");
		frame.setPreferredSize(new Dimension(1100,1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel panelDroite = new JPanel();
		panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.Y_AXIS));
		panelDroite.setAlignmentX(SwingConstants.CENTER);
		
		JPanel panelGauche = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
		//panelGauche.setLayout(new BorderLayout());
		
		//D�finition du panel de Gauche//
		
		//Panel d'affichage des joueurs
		final JPanel panelJoueurs = new JPanel();
		panelJoueurs.setLayout(new BoxLayout(panelJoueurs, BoxLayout.Y_AXIS));
		
		JPanel panelLabelJoueurJeu = new JPanel();
		JLabel labelJoueurEnJeu = new JLabel("liste des joueurs en jeu:");
		panelLabelJoueurJeu.add(labelJoueurEnJeu);
		
		panelJoueurs.add(panelLabelJoueurJeu);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panelJoueurs.add(new ScrollerPlayer());
            	frame.pack();
            }
        });
		
		final JPanel panelPileAppelCarte = new JPanel();
		panelPileAppelCarte.setLayout(new BoxLayout(panelPileAppelCarte, BoxLayout.Y_AXIS));
		
		JPanel panelLabelPileCarte = new JPanel();
		JLabel labelPileCarte = new JLabel("liste des cartes lanc�es par les joueurs:");
		panelLabelPileCarte.add(labelPileCarte);
		
		labelPileCarte.setHorizontalAlignment(SwingConstants.LEFT);
		panelPileAppelCarte.add(panelLabelPileCarte);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panelPileAppelCarte.add(new Scroller());
            	frame.pack();
            }
        });
		
		final JPanel panelCartesJoueur = new JPanel();
		panelCartesJoueur.setLayout(new BoxLayout(panelCartesJoueur, BoxLayout.Y_AXIS));
		
		JPanel panelLabelCartesJoueur = new JPanel();
		JLabel LabelCartesJoueur = new JLabel("liste des cartes de votre jeu:");
		panelLabelCartesJoueur.add(LabelCartesJoueur);
		
		labelPileCarte.setHorizontalAlignment(SwingConstants.LEFT);
		panelCartesJoueur.add(panelLabelCartesJoueur);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panelCartesJoueur.add(new Scroller());
            	frame.pack();
            }
        });
		
		
		final JPanel panelCartesRattachesJoueur = new JPanel();
		panelCartesRattachesJoueur.setLayout(new BoxLayout(panelCartesRattachesJoueur, BoxLayout.Y_AXIS));
		
		JPanel panelLabelCartesRattaches = new JPanel();
		JLabel LabelCartesRattaches = new JLabel("Listes des cartes rattach�es au joueur:");
		panelLabelCartesRattaches.add(LabelCartesRattaches);
		
		LabelCartesRattaches.setHorizontalAlignment(SwingConstants.LEFT);
		panelCartesRattachesJoueur.add(panelLabelCartesRattaches);
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	panelCartesRattachesJoueur.add(new Scroller());
            	frame.pack();
            }
        });
	
		//------------------------------//
		
		
		
		
//		final JPanel panelPioche = new JPanel();
//		panelPioche.setLayout(new BoxLayout(panelPioche, BoxLayout.Y_AXIS));
//		
//		JPanel panelDeckPioche = new JPanel();
//		JLabel labelDeckPioche = new JLabel("deck de pioche:");
//		labelDeckPioche.setAlignmentX(SwingConstants.CENTER);
//		panelDeckPioche.add(labelDeckPioche);
//		
////		panelPioche.setAlignmentX(SwingConstants.CENTER);
//		panelPioche.add(panelDeckPioche);
//		JPanel panelImagePioche = new ImagePanel("./images/cartes/dosDeCartes.png",60,80);
//		panelPioche.add(panelImagePioche);
//		panelImagePioche.setAlignmentX(SwingConstants.CENTER);
		
		
		
		
		
		
		
		
		//D�finition du panel de droite//
		
		final JPanel panelDeTour = new JPanel();
		panelDeTour.setLayout(new BoxLayout(panelDeTour, BoxLayout.Y_AXIS));
		
		JPanel panelLabelDe = new JPanel();
		JLabel labelDe = new JLabel("face du d�s:");
		panelLabelDe.add(labelDe);
		
		panelJoueurs.add(panelLabelJoueurJeu);
		panelDeTour.add(panelLabelDe);
		
		ImagePanel panelImageDe = new ImagePanel("./images/OrigineCarte/jour.jpg",800/20,800/20);
		panelDeTour.add(new JLabel(new ImageIcon(panelImageDe.getBufferedImage().getScaledInstance(800/20,800/20, Image.SCALE_SMOOTH))));
		
		
		panelImageDe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JPanel panelPointsAction = new JPanel();
		panelPointsAction.setLayout(new BoxLayout(panelPointsAction, BoxLayout.Y_AXIS));
		
		JPanel panelLabelPA = new JPanel();
		JLabel labelPA = new JLabel("points d'action disponibles:");
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
		
<<<<<<< HEAD
		//-----------------------------//
		

		
//		JPanel panelJoueur = new JPanel();
//		panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));
//		panelJoueur.setPreferredSize(new Dimension(200,200));

		JPanel panelJoueur = new JPanel();
		panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));

		//panelJoueur.setBackground(Color.BLACK);
		
		
//		JTextArea labelArea = new JTextArea("Annonce du jeu\nAnnonce du jeu");
//		labelArea.setEditable(false);
//		labelArea.setLineWrap(true);
		

//		JPanel panelTableJeu = new JPanel();
//		panelTableJeu.setLayout(new BoxLayout(panelTableJeu, BoxLayout.X_AXIS));	
//	
//		JScrollPane jsp = new JScrollPane(panelTableJeu, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		
//		
//		JPanel panelCarteMain = new JPanel();
//		panelCarteMain.setLayout(new BoxLayout(panelCarteMain, BoxLayout.X_AXIS));
//		panelCarteMain.setAutoscrolls(true);
//		
//		panelCarteMain.add(initializeExampleCard());
//		
//		JPanel panelCarterattache = new JPanel();
//		panelCarterattache.setLayout(new BoxLayout(panelCarterattache, BoxLayout.X_AXIS));
//		
//		panelCarterattache.add(initializeExampleCard());
//		panelCarterattache.add(initializeExampleCard());
//		
//		JLabel labelGuidesJoueur = new JLabel("cartes d�pos�es:");

		JPanel panelTableJeu = new JPanel();
		panelTableJeu.setLayout(new BoxLayout(panelTableJeu, BoxLayout.X_AXIS));	
		
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		panelTableJeu.add(Box.createRigidArea(new Dimension(5,0)));
		panelTableJeu.add(initializeExampleCard());
		
		JPanel panelCarteMain = new JPanel();
		panelCarteMain.setLayout(new BoxLayout(panelCarteMain, BoxLayout.X_AXIS));
		panelCarteMain.setAutoscrolls(true);
		
		panelCarteMain.add(initializeExampleCard());
		
		JPanel panelCarterattache = new JPanel();
		panelCarterattache.setLayout(new BoxLayout(panelCarterattache, BoxLayout.X_AXIS));
		
		panelCarterattache.add(initializeExampleCard());
		panelCarterattache.add(initializeExampleCard());
		
		JLabel labelGuidesJoueur = new JLabel("cartes d�pos�es:");

//		labelGuidesJoueur.setVerticalAlignment(SwingConstants.LEFT);
//		labelGuidesJoueur.setAlignmentX(Component.LEFT_ALIGNMENT);
//		labelGuidesJoueur.setVerticalAlignment(SwingConstants.LEFT);
		
		//panel.add(annonceTexte);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//		ImageIcon image = new ImageIcon("./images/fondCarte/fond_apocalypses.jpg");
//		panel.add(new JLabel(image));
		
		
		
		
//		panelGauche.add(new JLabel("coucou"));
		
//		JPanel p = new JPanel();
//		p.add(panelJoueurs);
//		
//		panelGauche.add(p);
		
		//Ajout des panels dans le panel principal gauche
		panelGauche.setPreferredSize(new Dimension(800,400));
		panelGauche.add(panelJoueurs);
		panelGauche.add(Box.createRigidArea(new Dimension(0,10)));
		panelGauche.add(panelPileAppelCarte);
		panelGauche.add(Box.createRigidArea(new Dimension(0,10)));
		panelGauche.add(panelCartesJoueur);
		panelGauche.add(Box.createRigidArea(new Dimension(0,10)));
		panelGauche.add(panelCartesRattachesJoueur);
		
		
		
		//Ajout des panels dans le panel principal droit
		panelDroite.setPreferredSize(new Dimension(300,400));
		panelDroite.add(panelDeTour);
		panelDroite.add(Box.createRigidArea(new Dimension(0,500)));
		panelDroite.add(panelPointsAction);
		
		
		
		
//		frame.getContentPane().add(panelJoueurs);
//		frame.getContentPane().add(panelPileAppelCarte);
		frame.getContentPane().add(panelGauche, BorderLayout.WEST);
		frame.getContentPane().add(panelDroite, BorderLayout.EAST);
//		frame.getContentPane().add(panelPileAppelCarte, BorderLayout.EAST);
//		frame.getContentPane().add(panelDroite, BorderLayout.EAST);
		
		//Ajout des panels dans le panel de Gauche
//		panelGauche.add(panelJoueurs);
		
		
		
		
		
		
//		panelJoueur.add(new JLabel(image));
		
//		panelGauche.add(jsp);
//		
//		jsp.setVisible(true);
//		panelTableJeu.setPreferredSize(new Dimension(200, 200));
//		
		
		
//	panelGauche.add(panelTableJeu);
//		panelJoueur.add(new JLabel(image));
//		panelGauche.add(Box.createRigidArea(new Dimension(0,20)));
//		panelGauche.add(labelArea);
//		panelGauche.add(Box.createRigidArea(new Dimension(0,20)));
//		panelGauche.add(new JLabel(image));
		
//		panelGauche.add(panelCarteMain);
//		panelCarteMain.setAlignmentX(Component.LEFT_ALIGNMENT);
//		panelGauche.add(Box.createRigidArea(new Dimension(0,20)));
//		panelGauche.add(labelGuidesJoueur);
//		panelGauche.add(panelCarterattache);
//		panelCarterattache.setAlignmentX(Component.LEFT_ALIGNMENT);
		frame.pack();
		//panelJoueur.add(scrollPaneJoueur);
	}
	
	public JPanel initializeExampleCard(){
//		frame.setTitle("Pandocreon Divinae");
		//frame.setPreferredSize(new Dimension(800, 600));
//		frame.setBounds(100, 100, 450, 300);
//		frame.setPreferredSize(new Dimension(200, 300));
//		frame.setSize(159, 180);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel panelBox = new JPanel();
		panelBox.setLayout(new BoxLayout(panelBox, BoxLayout.Y_AXIS));
		
//		panelBox.setPreferredSize(new Dimension(150,200));
		panelBox.setMaximumSize(new Dimension(150,200));
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		
		JPanel panelBot = new JPanel();
		panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));
		
		panelTop.add(new ImagePanel("./images/OrigineCarte/jour.jpg",800/12,800/12), BorderLayout.WEST);
		
		panelTop.add(new ImagePanel("./images/nbCroyantsCarte/nbr1.jpg", 800/24, 1600/24), BorderLayout.EAST);
		
		panelBot.add(new ImagePanel("./images/dogmesCarte/chaos.jpg", 800/12, 800/12));
		
		panelBox.add(panelTop);
		
		//frame.getContentPane().add(panelBox, BorderLayout.CENTER);
		
		JLabel nomCarte = new JLabel();
		nomCarte.setText("nom de la carte\n");
		nomCarte.setAlignmentX(Component.CENTER_ALIGNMENT);
		nomCarte.setVerticalAlignment(SwingConstants.CENTER);
		
		JTextArea labelArea = new JTextArea("mon texte\nmon texte\nmon texte");
		labelArea.setEditable(false);
		labelArea.setLineWrap(true);
		
		panelBox.add(nomCarte);
		panelBox.add(labelArea);
		panelBox.add(panelBot);
		
		return panelBox;
//		BufferedImage img = null;
//		try {
//		    img = ImageIO.read(new File("./images/fondCarte/fond_apocalypses.jpg"));
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
		
//		BufferedImage dimg = img.getScaledInstance(label.width, label.height,
//		        Image.SCALE_SMOOTH);
		
//		try{
//			Image image = ImageIO.read(new File("./images/fondCarte/fond_apocalypses.jpg"));
//			Image scaledImage = image.getScaledInstance(frame.getContentPane().getWidth(),frame.getContentPane().getHeight(),Image.SCALE_SMOOTH);
//			
//			
//			panel.add(new ImageLabel(image));
//			
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		JLabel l = new JLabel();
//		JPanel panel = new JPanel();
//		frame.getContentPane().add(panel, BorderLayout.NORTH);
//		
//		panel.add(l);
//		
//		BufferedImage img = null;
//		try {
//		    img = ImageIO.read(new File("./images/fondCarte/fond_apocalypses.jpg"));
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
		
		//BufferedImage dimg = (BufferedImage) img.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
		
//		ImageIcon imageIcon = new ImageIcon(img);
//		l.setIcon(imageIcon);
//		ImageIcon image = new ImageIcon("./images/fondCarte/fond_apocalypses.jpg");
		
//		panel.add(new JLabel(image));
//		frame.getContentPane().add(new JLabel(image));
//		JLabel l = new JLabel(image);
//		l.
		
//		panel.add();
		
//		 BufferedImage image;

	
//		 try {                
//			 image = ImageIO.read(new File("./images/fondCarte/fond_apocalypses.jpg"));
//		 } catch (IOException ex) {
//			 // handle exception...
//		 }
		
//		buttonImage = new JButton(image);
//		buttonImage.addActionListener(new ActionListener() {
	}
	
	public JFrame getFenetre(){
		return frame;
	}
}


