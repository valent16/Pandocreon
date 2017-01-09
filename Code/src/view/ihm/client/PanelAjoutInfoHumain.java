package view.ihm.client;

import javax.swing.JPanel;

/**Classe qui gere la vue de la partie en mode console*/
public class PanelAjoutInfoHumain extends JPanel{

	private static final long serialVersionUID = 1L;


	/**Constructeur*/
	public PanelAjoutInfoHumain(){
		initialize();
	}

	private void initialize(){
		/*//pour chaque joueur
		JPanel framePanel = new JPanel(new BorderLayout());

		//ajout des champs de text
		JLabel nomHumain = new JLabel("Nom : ");
		tfNom = new JTextField();
		tfNom.setColumns(10);

		JPanel[] infos = new JPanel[nombreTotalHumain];

		JPanel infoHumainPanel = new JPanel(new GridBagLayout()); //Panel pour le nombre de joueur
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);

		gbc.gridx = 0;
		gbc.gridy = 0;
		//infoHumainPanel.add(nomHumain, gbc);

		gbc.gridx = 1;
		infoHumainPanel.add(tfNom, gbc);

		//bouton annuler
		JButton annuler = new JButton("Annuler");
		annuler.setPreferredSize(new Dimension(120, 50));
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retourMenuPrincipale();
			}
		});

		//bouton valider
		JButton valider = new JButton("Valider");
		valider.setPreferredSize(new Dimension(120, 50));
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				//TODO faire des test sur le nom et l'age récuperé

				do{
					System.out.println(indexHumain);
					listeNomHumains.add(tfNom.getText());
					listeAgeHumains.add(20);
					//ajouterInfoJoueurHumain();
					indexHumain++;
					//liste des joueurs
					System.out.println("liste nom " + listeNomHumains);//////////////////////////////////////////////////////////////////////////////
					System.out.println("liste age "+ listeAgeHumains);//////////////////////////////////////////////////////////////////////////////
				}while(indexHumain <= nombreTotalHumain);//on le fait pour le nombre d'humain demander
			}
		});

		//Panel sud
		JPanel southPanel = new JPanel (new FlowLayout());
		southPanel.add(annuler);
		southPanel.add(valider);

		frame.setTitle("humain numero : "+indexHumain);
		frame.setContentPane(framePanel);
		frame.repaint();
		frame.validate();*/

	}

	/**Getter
	 * @return le panel MenuPrincipale
	 */
	public JPanel getPanel(){
		return this;
	}
}
