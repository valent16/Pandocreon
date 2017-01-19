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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**Classe qui gere la vue de la partie en mode console*/
public class PanelAjoutInfoHumain extends PanelType{

	private static final long serialVersionUID = 1L;

	/**liste des strategie*/
	private JComboBox<String> listeAges;

	private JTextField tfNom;

	private int compteurValider = 0;


	/**Constructeur
	 * @param c l'interface client
	 */
	public PanelAjoutInfoHumain(Client c){
		client = c;
		initialize();
		ajouterListener();
		compteurValider = client.getNombreHumain(); //recupere le nombre d'humain que veut l'utilisateur
	}

	@Override
	protected void initialize(){
		//pour chaque joueur
		this.setLayout(new BorderLayout());

		//ajout des champs de text
		JLabel nomHumain = new JLabel("Nom : ");
		JLabel ageHumain = new JLabel("Age : ");
		tfNom = new JTextField();
		tfNom.setColumns(10);

		//Ajout du comboBox
		String[] ages = new String[60];
		for(int i = 0; i < ages.length; i++){
			ages[i] = ""+i;
		}

		listeAges = new JComboBox<>(ages);

		JPanel infoHumainPanel = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);

		gbc.gridx = 0;
		gbc.gridy = 0;
		infoHumainPanel.add(nomHumain, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		infoHumainPanel.add(tfNom, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		infoHumainPanel.add(ageHumain, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		infoHumainPanel.add(listeAges, gbc);

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

		this.add(infoHumainPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		client.getFenetre().setTitle("humain numero : "+client.getIndexHumain()+"/"+client.getNombreHumain());
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
				if (compteurValider == 1){
					if(testerDonnees()){
						ajouterJoueur();
						client.confirmerPartie();
					}
				}else if(compteurValider > 1 ){
					if(testerDonnees()){
						ajouterJoueur();
						client.setIndexHumain(client.getIndexHumain()+1);
						compteurValider--;
					}
				}	
			}
		});
	}

	/**Methode permettant de tester les donnees saisies par l'utilisateur
	 * @return true si les données sont valides sinon false
	 */
	private boolean testerDonnees(){
		if(tfNom.getText().equals("")){//si le nom est vide
			JOptionPane.showMessageDialog(null, "Vous n'avez pas donnée de nom au joueur", "Probleme nom joueur", JOptionPane.ERROR_MESSAGE, logo);
			return false;
		}else if(client.getListeNomHumain().contains(tfNom.getText())){//si ce nom a deja été donée par un joueur
			JOptionPane.showMessageDialog(null, "Ce nom a déja éte donnée pour un autre joueur.", "Probleme nom joueur", JOptionPane.ERROR_MESSAGE, logo);
			return false;
		}else if(client.getListeNomBot().contains(tfNom.getText())){//Si ce nom a été donnée par un bot
			JOptionPane.showMessageDialog(null, "Ce nom a déja éte donnée pour un bot", "Probleme nom joueurr", JOptionPane.ERROR_MESSAGE, logo);
			return false;
		}else if(Integer.parseInt(listeAges.getSelectedItem().toString()) < 12){//si le joueur a moins de 12 ans il ne peut pas jouer
			JOptionPane.showMessageDialog(null, "Le joueur est trop jeune pour jouer\nIl doit avoir plus de 12 ans.", "Probleme age joueur", JOptionPane.ERROR_MESSAGE, logo);
			return false;
		}		
		return true; //sinon c'est bon on peut ajouter les infos
	}

	/**methode qui ajoute les données du joueur au client*/
	private void ajouterJoueur(){
		client.getListeNomHumain().add(tfNom.getText());
		client.getListeAgeHumain().add(Integer.parseInt(listeAges.getSelectedItem().toString()));
		client.getFenetre().setTitle("humain numero : "+client.getIndexHumain()+"/"+client.getNombreHumain());
	}
}
