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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelConfirmation extends PanelType{

	private static final long serialVersionUID = 1L;

	private JPanel panelConfirmation;
	private JPanel panelNombre;
	private JPanel panelNom;
	private JLabel nombreJoueur;
	private JLabel nombreBot;
	private JLabel nombreHumain;
	private JLabel strategie;
	private JLabel nomBots;
	private JLabel nomHumains;

	/**Constructeur
	 * @param c l'interface client
	 */
	public PanelConfirmation(Client c){
		client = c;
		initialize();
		ajouterListener();
	}

	@Override
	protected void initialize() {
		this.setLayout(new BorderLayout());
		//les labels
		nombreJoueur = new JLabel("joueurs au total : "+client.getNombreJoueurs()+ " | "); 
		nombreBot = new JLabel("Bots : "+client.getNombreBot()+ " | ");
		nombreHumain = new JLabel("Humain : "+client.getNombreHumain()+ " | ");
		strategie = new JLabel("Strategie bot : "+client.getStrategie());

		//panel nombre
		panelNombre = new JPanel();	
		panelNombre.add(nombreJoueur);
		panelNombre.add(nombreBot);
		panelNombre.add(nombreHumain);

		nomHumains = new JLabel("Nom des humains : "+ client.getListeNomHumain().toString());
		nomBots = new JLabel("Nom des bots : "+ client.getListeNomBot().toString());

		//panel noms
		panelNom = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridx = 0;
		gbc.gridy = 0;

		if(client.getListeNomHumain().size() != 0) //si il a au moins 1 humain on affiche sinon on le cache
			panelNom.add(nomHumains, gbc);

		gbc.gridy = 1;
		
		if(client.getListeNomBot().size() != 0) //si il a au moins 1 bot on affiche sinon on le cache
			panelNom.add(nomBots, gbc);

		//pnael confirmation
		panelConfirmation = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelConfirmation.add(panelNombre, gbc);

		gbc.gridy = 2;	
		panelConfirmation.add(strategie, gbc);

		gbc.gridy = 3;
		panelConfirmation.add(panelNom, gbc);

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

		this.add(panelConfirmation, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		client.getFenetre().setTitle("humain numero : "+client.getIndexHumain());
		client.getFenetre().setContentPane(this);
		client.getFenetre().repaint();
		client.getFenetre().validate();
	}

	@Override
	protected void ajouterListener() {
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.retourMenuPrincipale();//on revient au menuPrincipale
			}
		});

		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int nombreTotalJoueur = client.getNombreBot() + client.getNombreHumain();
				if(nombreTotalJoueur < 2){//il faut au moins 2 joueurs pour lancer la partie
					JOptionPane.showMessageDialog(null, "Vous avez besoin d'au moins 2 joueurs pour lancer la partie", "Probleme nombre de joueur", JOptionPane.ERROR_MESSAGE, logo);
					client.getFenetre().setContentPane(client.getPanelMenuPrincipale());
					client.getFenetre().setTitle("Client Pandocreon Divinae");
					client.getFenetre().repaint();
					client.getFenetre().validate();	
					client.resetDonnees();
				}else{
					client.lancerPartie();//on lance la partie
				}
			}
		});
	}
}
