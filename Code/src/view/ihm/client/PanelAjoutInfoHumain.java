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
	
	private int compteurValider = 1;


	/**Constructeur
	 * @param c l'interface client
	 */
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
				//TODO A developper faire des test sur le nom et l'age récuperé
				if(compteurValider == client.getNombreHumain()){
					System.out.println("stop");
					client.confirmerPartie();//on affiche le panel de confirmation
				}
				else{
					client.getListeNomHumain().add(tfNom.getText());
					client.getListeAgeHumain().add(20);
					client.setIndexHumain(client.getIndexHumain()+1);
					client.getFenetre().setTitle("humain numero : "+client.getIndexHumain()+"/"+client.getNombreHumain());
					compteurValider++;
				}
			}
		});
	}
}
