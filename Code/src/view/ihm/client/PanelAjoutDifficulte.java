package view.ihm.client;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAjoutDifficulte extends PanelType{

	private static final long serialVersionUID = 1L;

	JComboBox<String> listeStrategie;

	private JButton annuler;

	private JButton valider;

	/**Constructeur
	 * @param c l'interface client
	 */
	public PanelAjoutDifficulte(Client c){
		client = c;
		initialize();
		ajouterListener();
	}

	@Override
	protected void initialize() {
		this.setLayout(new BorderLayout());

		String[] strats = {"Facile", "Moyen", "Difficile"};//les differentes Strategies

		listeStrategie = new JComboBox<String>(strats);
		listeStrategie.setSelectedItem("Moyen");

		JPanel strategieBotPanel = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		JLabel labelStrategieBot = new JLabel("Choississez la strategie des bots (seul la difficulte Moyen fonctionne)");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridx = 0;
		gbc.gridy = 0;
		strategieBotPanel.add(labelStrategieBot, gbc);

		gbc.gridy = 1;
		strategieBotPanel.add(listeStrategie, gbc);

		//bouton annuler
		annuler = new JButton("Annuler");
		annuler.setPreferredSize(new Dimension(120, 50));

		//bouton valider
		valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(120, 50));

		//Panel sud
		JPanel southPanel = new JPanel (new FlowLayout());
		southPanel.add(annuler);
		southPanel.add(valider);

		this.add(strategieBotPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		client.getFenetre().setTitle("Ajout de la strategie");
		client.getFenetre().setContentPane(this);
		client.getFenetre().repaint();
		client.getFenetre().validate();

	}

	@Override
	protected void ajouterListener() {
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.retourMenuPrincipale();
			}
		});

		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] values = {"facile", "medium", "difficile"};//les noms des strategies
				client.setStrategie(values[listeStrategie.getSelectedIndex()]);
				client.ajouterJoueurHumain();//on appelle le panel suivant
			}
		});
	}
}
