package fenetre;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;

public class FenetrePrincipale {

	private JFrame fenetre;

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
	private void initialize() {
		fenetre = new JFrame();
		fenetre.getContentPane().setBackground(Color.GRAY);
		fenetre.setTitle("Pandocreon Divinae");
		fenetre.setBounds(100, 100, 450, 300);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel NorthPanel = new JPanel();
		NorthPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(NorthPanel, BorderLayout.NORTH);
		
		JPanel WestPanel = new JPanel();
		WestPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(WestPanel, BorderLayout.WEST);
		
		JPanel SouthPanel = new JPanel();
		SouthPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(SouthPanel, BorderLayout.SOUTH);
		
		JPanel EastPanel = new JPanel();
		EastPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(EastPanel, BorderLayout.EAST);
		
		JPanel CenterPanel = new JPanel();
		CenterPanel.setBackground(Color.GRAY);
		fenetre.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		
		JLabel label1 = new JLabel("A faire");
		CenterPanel.add(label1);
	}

}
