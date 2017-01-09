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
import javax.swing.JTextField;

import view.ihm.Client;

/**Classe qui gere la vue de la partie en mode console*/
public class PanelAjoutInfoHumain extends PanelType{

	private static final long serialVersionUID = 1L;

	/**liste des strategie*/
	private JComboBox<String> listeAges;

	private JTextField tfNom;

	private JButton valider;

	private JButton annuler;

	/**Constructeur*/
	public PanelAjoutInfoHumain(Client c){
		client = c;
		initialize();
		ajouterListener();
	}

	@Override
	protected void initialize(){
		//pour chaque joueur
		this.setLayout(new BorderLayout());

		//ajout des champs de text
		JLabel nomHumain = new JLabel("Nom : ");
		tfNom = new JTextField();
		tfNom.setColumns(10);

		//JPanel[] infos = new JPanel[client.get];
		
		//Ajout du comboBox
		String[] ages = new String[39];
		for(int i = 0; i< ages.length; i++){
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
		infoHumainPanel.add(tfNom, gbc);
		
		gbc.gridy = 2;
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
				client.retourMenuPrincipale();
			}
		});

		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				//TODO faire des test sur le nom et l'age récuperé
				//TODO Faire marcher cette methode
				//TODO faire une boucle for qui parcours nombreTotalJOueur
				//on afiche Faire un panel avec deux champs de test et un label avec joueur+"i"
				//on recupere dans l'arraylist nomHumains le premier champs de texte
				//on recupere dans l'arraylist ageHumains le second champs de texte

				do{
					System.out.println(client.getIndexHumain());
					client.getListeNomHumain().add(tfNom.getText());
					client.getListeAgeHumain().add(20);
					
					//ajouterInfoJoueurHumain();
					client.setIndexHumain(client.getIndexHumain()+1);
					
					//liste des joueurs
					System.out.println("liste nom " + client.getListeNomHumain());//////////////////////////////////////////////////////////////////////////////
					System.out.println("liste age "+ client.getListeAgeHumain());//////////////////////////////////////////////////////////////////////////////
				}while(client.getIndexHumain() <= client.getNombreHumain());//on le fait pour le nombre d'humain demander
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
