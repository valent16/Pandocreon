package view.ihm.client;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import view.ihm.Client;

/**Classe qui gere la vue de la partie en mode console*/
public class PanelAjoutBot extends PanelType{

	private static final long serialVersionUID = 1L;

	private JLabel nombreBot;

	private JButton annuler;

	private JButton valider;

	private JButton moins;

	private JButton plus;

	/**Constructeur
	 * @param c le Client du jeu
	 */
	public PanelAjoutBot(Client c){
		client = c;
		initialize();
		ajouterListener();
	}
	
	@Override
	protected void initialize(){
		this.setLayout(new BorderLayout());

		//label nombre de joueur
		nombreBot = new JLabel(""+Client.getNombreMinimalJoueur());
		nombreBot.setHorizontalAlignment(JLabel.CENTER); //permet de centrer le contenu du label

		//bordure sur le label nombre de joueur
		Border line = BorderFactory.createLineBorder(Color.GRAY, 2);
		Border panelBorder = BorderFactory.createTitledBorder(line, "");
		nombreBot.setBorder(panelBorder);
		nombreBot.setPreferredSize(new Dimension(40, 40));

		//bouton plus
		plus = new JButton("+");
		plus.setPreferredSize(new Dimension(45, 45));

		//bouton moins
		moins = new JButton("-");
		moins.setPreferredSize(new Dimension(45, 45));

		JPanel nombreBotPanel = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		JLabel labelNombreBot = new JLabel("Nombre de bots a ajouter : ("+Client.getNombreMinimalJoueur()+"-"+Client.getNombreMaximalJoueur()+")");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridx = 0;
		gbc.gridy = 0;
		nombreBotPanel.add(labelNombreBot, gbc);

		gbc.gridx = 1;
		nombreBotPanel.add(moins, gbc);

		gbc.gridx = 2;
		nombreBotPanel.add(nombreBot, gbc);

		gbc.gridx = 3;
		nombreBotPanel.add(plus, gbc);

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

		this.add(nombreBotPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		client.getFenetre().setTitle("Ajout des Bots");
		client.getFenetre().setContentPane(this);
		client.getFenetre().repaint();
		client.getFenetre().validate();
	}
	
	
	@Override
	protected void ajouterListener() {
		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String d = nombreBot.getText();
				int c = Integer.parseInt(d);
				if(c == Client.getNombreMaximalJoueur())
					c = Client.getNombreMaximalJoueur();
				else
					c++;
				nombreBot.setText(""+c);
				client.getFenetre().requestFocus();				
			}
		});
		
		moins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String d = nombreBot.getText();
				int c = Integer.parseInt(d);
				if(c == Client.getNombreMinimalJoueur())
					c = Client.getNombreMinimalJoueur();
				else
					c--;
				nombreBot.setText(""+c);
				client.getFenetre().requestFocus();				
			}
		});
		
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.retourMenuPrincipale();
			}
		});
		
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Vous avez ajouté "+ nombreBot.getText() +" bots. Voulez-vous continuer ?", "bots ajoutés", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logo);
				if(option == JOptionPane.OK_OPTION){
					client.setNombreBot(Integer.parseInt(nombreBot.getText()));
					client.setNombreHumain(Client.getNombreMaximalJoueur() - client.getNombreBot());
					client.ajouterDifficulte();//on ajoute les joueurs humain
				}else{
					JOptionPane.showMessageDialog(null, "Veuillez ajouté des bots pour pouvoir lancé une partie", "Probleme ajout de bot", JOptionPane.INFORMATION_MESSAGE, logo);
				}
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
