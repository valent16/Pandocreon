package view.ihm.client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**Classe qui gere la vue de la partie en mode console*/
public class PanelMenuPrincipale extends JPanel{

	private static final long serialVersionUID = 1L;

	/**panel du menu principale*/
	private JPanel menuPrincipal;

	/**bouton pour lancer une nouvelle partie*/
	private JButton newGame;

	/**bouton pour le chargement d'une partie*/
	private JButton loadGame;

	/**button pour les regles*/
	private JButton rules;

	/**Constructeur*/
	public PanelMenuPrincipale(){
		initialize();
	}

	private void initialize(){
		menuPrincipal = new JPanel(new GridBagLayout());

		menuPrincipal.setPreferredSize(new Dimension(250,250));

		newGame = new JButton("Nouvelle partie");
		newGame.setPreferredSize(new Dimension(200,50));

		loadGame = new JButton("Charger Partie");
		loadGame.setPreferredSize(new Dimension(200, 50));

		rules = new JButton("Afficher les regles");
		rules.setPreferredSize(new Dimension(200,50));

		//ajout des composants
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		menuPrincipal.add(newGame, gbc);

		gbc.gridy = 1;
		menuPrincipal.add(loadGame, gbc);

		gbc.gridy = 2;
		menuPrincipal.add(rules, gbc);


		//Methode permettant d'ajouter les listener au boutons
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lancerIHM();
			}
		});

		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chargerPartie();
			}
		});

		rules.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				afficherRegles();
			}
		});

	}

	/**Getter
	 * @return le panel MenuPrincipale
	 */
	public JPanel getPanel(){
		return this;
	}
}
