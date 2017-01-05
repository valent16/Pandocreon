package fenetre;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

public class FenetrePrincipale {

	private JFrame fenetre;
	private JButton buttonImage;
	private JPanel EastPanel, SouthPanel, WestPanel, NorthPanel, CenterPanel;
	private JLabel label1, labelCentrale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale window = new FenetrePrincipale();
					window.fenetre.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetrePrincipale() {
		initialize();
	}

	public JFrame getFenetre(){
		return fenetre;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
	 */
	private void initialize() {
		fenetre = new JFrame();
		fenetre.setContentPane(new ImagePanel(new ImageIcon("images/background2.jpg").getImage()));
		fenetre.getContentPane().setLayout(new BorderLayout());
			
		//Image bg = new Image();
		//fenetre.setIconImage(bg);
		fenetre.setTitle("Pandocreon Divinae");
		fenetre.setBounds(100, 100, 450, 300);
		fenetre.setSize(800, 600);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		NorthPanel = new JPanel(new BorderLayout());
		fenetre.getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		SouthPanel = new JPanel(new BorderLayout());
		fenetre.getContentPane().add(SouthPanel, BorderLayout.SOUTH);

		WestPanel = new JPanel(new BorderLayout());
		fenetre.getContentPane().add(WestPanel, BorderLayout.WEST);
	
		EastPanel = new JPanel(new BorderLayout());
		fenetre.getContentPane().add(EastPanel, BorderLayout.EAST);

		CenterPanel = new JPanel(new BorderLayout());
		fenetre.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		
		labelCentrale = new JLabel("Table de JEU");
		CenterPanel.add(labelCentrale, BorderLayout.CENTER);

		label1 = new JLabel("A faire");
		NorthPanel.add(label1);


		//Image i = new Image("./Brewalen.png");
		ImageIcon image = new ImageIcon("./images/cartes/Brewalen.png");
		buttonImage = new JButton(image);
		buttonImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action a effectuer dessus");
				SouthPanel.setBackground(Color.black);
				//SouthPanel.add
				SouthPanel.repaint(); ///on met a jour le panel avec cette methode
				SouthPanel.revalidate();//il faut le revalider pour refaire des actions dessus

			}
		});

		SouthPanel.add(buttonImage);
		//TODO un label pour le nom du joueur dans le southPanel
		//TODO creer quelques carte en mode bouton pour voir si ca marche dans le southPanel avec un listenr qui active la carte
	}
	
	//Class qui permet d'ajouter le background
	public class ImagePanel extends JPanel {
		 
	    private static final long serialVersionUID = 1L;
	 
	    private Image img;
	     
	    public ImagePanel(Image img){
	        this.img = img;
	    }
	     
	    public void paintComponent(Graphics g) {
	        g.drawImage(img, 0, 0, null);
	    }
	}
}
