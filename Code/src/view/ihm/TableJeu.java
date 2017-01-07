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
import javax.swing.SwingConstants;
import java.awt.Component;

public class TableJeu {
	
	private JFrame frame = new JFrame();
	
	public TableJeu(){
		initialize();
		frame.setVisible(true);
	}
	
	public void initialize(){
		
		frame.setTitle("Pandocreon Divinae");
		//frame.setPreferredSize(new Dimension(800, 600));
		frame.setPreferredSize(new Dimension(200, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		
		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.EAST);
		
		
		JPanel panelGauche = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
		
		
		JPanel panelJoueur = new JPanel();
		panelJoueur.setLayout(new BoxLayout(panelJoueur, BoxLayout.X_AXIS));
		//panelJoueur.setBackground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(panelJoueur);
		
		
		JTextArea labelArea = new JTextArea("Annonce du jeu\nAnnonce du jeu");
		labelArea.setEditable(false);
		labelArea.setLineWrap(true);
		
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
		labelGuidesJoueur.setAlignmentX(Component.LEFT_ALIGNMENT);
//		labelGuidesJoueur.setVerticalAlignment(SwingConstants.LEFT);
		
		//panel.add(annonceTexte);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//		ImageIcon image = new ImageIcon("./images/fondCarte/fond_apocalypses.jpg");
//		panel.add(new JLabel(image));
		panel.add(panelGauche);
		
		panelGauche.add(scrollPane);
//		panelJoueur.add(new JLabel(image));
		
		panelGauche.add(panelTableJeu);
//		panelJoueur.add(new JLabel(image));
		panelGauche.add(Box.createRigidArea(new Dimension(0,20)));
		panelGauche.add(labelArea);
		panelGauche.add(Box.createRigidArea(new Dimension(0,20)));
//		panelGauche.add(new JLabel(image));
		
		panelGauche.add(panelCarteMain);
		panelCarteMain.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelGauche.add(Box.createRigidArea(new Dimension(0,20)));
		panelGauche.add(labelGuidesJoueur);
		panelGauche.add(panelCarterattache);
		panelCarterattache.setAlignmentX(Component.LEFT_ALIGNMENT);
		
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
		
		frame.getContentPane().add(panelBox, BorderLayout.CENTER);
		
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


