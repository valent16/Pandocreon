package view.ihm.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

/**Classe qui gere la vue de la partie en mode console*/
public class PanelAjoutHumain extends PanelType{

	private static final long serialVersionUID = 1L;

	private JLabel nombreHumain;

	/**Constructeur
	 * @param c l'interface client
	 */
	public PanelAjoutHumain(Client c){
		client = c;
		initialize();
		ajouterListener();
	}

	@Override
	protected void initialize() {
		this.setLayout(new BorderLayout());

		//label nombre de joueur
		nombreHumain = new JLabel(""+Client.getNombreMinimalJoueur());
		nombreHumain.setHorizontalAlignment(JLabel.CENTER); //permet de centrer le contenu du label

		//bordure sur le label nombre de joueur
		Border line = BorderFactory.createLineBorder(Color.GRAY, 2);
		Border panelBorder = BorderFactory.createTitledBorder(line, "");
		nombreHumain.setBorder(panelBorder);
		nombreHumain.setPreferredSize(new Dimension(40, 40));

		//bouton plus
		plus = new JButton("+");
		plus.setPreferredSize(new Dimension(45, 45));

		//bouton moins
		moins = new JButton("-");
		moins.setPreferredSize(new Dimension(45, 45));

		JPanel nombreHumainPanel = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		JLabel labelNombreHumain = new JLabel("Nombre d'humains à ajouter : ("+ Client.getNombreMinimalJoueur() + "-" + client.getNombreHumain() +")");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridx = 0;
		gbc.gridy = 0;
		nombreHumainPanel.add(labelNombreHumain, gbc);

		gbc.gridx = 1;
		nombreHumainPanel.add(moins, gbc);

		gbc.gridx = 2;
		nombreHumainPanel.add(nombreHumain, gbc);

		gbc.gridx = 3;
		nombreHumainPanel.add(plus, gbc);

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

		this.add(nombreHumainPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		client.getFenetre().setTitle("Nombres d'humains");
		client.getFenetre().setContentPane(this);
		client.getFenetre().repaint();
		client.getFenetre().validate();

	}

	@Override
	protected void ajouterListener() {
		moins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String d = nombreHumain.getText();
				int c = Integer.parseInt(d);
				if(c == Client.getNombreMinimalJoueur())
					c = Client.getNombreMinimalJoueur();
				else
					c--;
				nombreHumain.setText(""+c);
				client.getFenetre().requestFocus();				
			}
		});

		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String d = nombreHumain.getText();
				int c = Integer.parseInt(d);
				if(c == client.getNombreHumain())
					c = client.getNombreHumain();
				else
					c++;
				nombreHumain.setText(""+c);
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
				client.setNombreHumain(Integer.parseInt(nombreHumain.getText()));
				
				//si on a des joueurs humains on ajoute leurs info sinon
				if(client.getNombreHumain() != 0)
					client.ajouterInfoJoueurHumain();//on ajoute les noms et age des joueurs
				
				//sinon on passe a la confirmation
				else
					client.confirmerPartie();
			}
		});
	}
}
