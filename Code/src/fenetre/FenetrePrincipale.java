package fenetre;

import java.awt.EventQueue;
import java.awt.Graphics;
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
	private JLabel label1;

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
		ImageIcon bg = new ImageIcon("images/")
		fenetre.getContentPane().setI
		fenetre.setTitle("Pandocreon Divinae");
		fenetre.setBounds(100, 100, 450, 300);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		NorthPanel = new JPanel();
		NorthPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(NorthPanel, BorderLayout.NORTH);

		WestPanel = new JPanel();
		WestPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(WestPanel, BorderLayout.WEST);

		SouthPanel = new JPanel();
		SouthPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(SouthPanel, BorderLayout.SOUTH);

		EastPanel = new JPanel();
		EastPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(EastPanel, BorderLayout.EAST);

		CenterPanel = new JPanel();

		CenterPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(CenterPanel, BorderLayout.CENTER);

		label1 = new JLabel("A faire");
		CenterPanel.add(label1);


		//Image i = new Image("./Brewalen.png");
		ImageIcon image = new ImageIcon("./images/cartes/Brewalen.png");
		buttonImage = new JButton(image);
		buttonImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action a effectuer dessus");
				SouthPanel.setBackground(Color.black);
				SouthPanel.add
				SouthPanel.repaint(); ///on met a jour le panel avec cette methode

			}
		});
		SouthPanel.add(buttonImage);

		//TODO un label pour le nom du joueur dans le southPanel
		//TODO creer quelqes caret en mode bouton pour voir si ca marche dans le southPanel avec un listenr qui active la carte
	}
}
