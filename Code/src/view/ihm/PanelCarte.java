package view.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import model.cards.Card;
import model.cards.Divinity;
import model.cards.OriginCards.ActionCardWithOrigin;
import model.cards.OriginCards.ApocalypseWithOrigin;
import model.cards.OriginCards.Believer;
import model.cards.OriginCards.CarteDogmatique;
import model.cards.OriginCards.DeusExWithOrigin;
import model.cards.OriginCards.SpiritGuide;
import model.cards.withoutOriginCards.Apocalypse;
import model.cards.withoutOriginCards.DeusEx;
import model.enumType.EnumDogme;

/**Panel represeantant une carte*/
public class PanelCarte extends JPanel{

	private static final long serialVersionUID = 1L;

	/**contenant le nombre de croyant a droite*/
	private JPanel panelTop;

	/**panel contenant les dogmes de la carte*/
	private JPanel panelBot;

	/**Panel contenant le titre de la carte*/
	private JLabel titre;

	/**label contenant le nom de la carte*/
	private JLabel nomCarte;

	/**description de la carte*/
	private JTextArea labelArea;

	/**la carte represente dans le panel*/
	private Card carte;

	private JButton bouton;

	/**Constructeur
	 * @param carte la carte a afficher dans le panel
	 */
	public PanelCarte(Card carte){
		this.carte = carte;
		bouton = new JButton();

		JPanel panelBox = new JPanel();
		panelBox.setLayout(new BoxLayout(panelBox, BoxLayout.Y_AXIS));
		panelBox.setPreferredSize(new Dimension(150,200));

		panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());

		panelBot = new JPanel();
		panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));

		titre = new JLabel();
		JPanel panelTitre = new JPanel();
		panelTitre.add(titre);

		nomCarte = new JLabel();
		nomCarte.setAlignmentX(Component.CENTER_ALIGNMENT);
		nomCarte.setVerticalAlignment(SwingConstants.CENTER);

		labelArea = new JTextArea();
		labelArea.setEditable(false);
		labelArea.setLineWrap(true);

		labelArea.setOpaque(false);

		bouton.add(panelBox);
		panelBox.add(panelTop);
		panelBox.add(panelTitre);
		panelBox.add(labelArea);
		panelBox.add(panelBot);
		this.add(bouton);
		bouton.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.black));

		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelFactory();
		bouton.setEnabled(false);
	}

	/**Getter de la carte 
	 * @return la carte
	 */
	public Card getCarte(){
		return carte;
	}

	/**Methode de creation du panel en fonction de la carte*/ 
	public void panelFactory(){
		ajoutTitre();
		if (carte instanceof Divinity){
			ajoutDogmes();
			ajoutOrigineDivinite();
			ajoutDescription();
		}else{
			if (carte instanceof ActionCardWithOrigin){
				ajoutOrigine();
				if (carte instanceof CarteDogmatique){
					ajoutDogmes();
					ajoutDescription();
				}
				else if (carte instanceof DeusExWithOrigin)
					ajoutDescription();
			}else{
				if (carte instanceof DeusEx)
					ajoutDescription();
			}
		}
	}

	/**Methode permettant d'ajouter les dogmes a la carte*/ 
	public void ajoutDogmes(){
		List<EnumDogme> listeDogmes;
		if (carte instanceof Divinity)
			listeDogmes = ((Divinity)carte).getDogmes();
		else
			listeDogmes = ((CarteDogmatique)carte).getDogmes();
		Iterator<EnumDogme> it = listeDogmes.iterator();
		while (it.hasNext()){
			switch (it.next()){
			case NATURE:
				panelBot.add(new ImagePanel("./images/dogmesCarte/nature.jpg", 800/20, 800/20));
				break;
			case HUMAIN:
				panelBot.add(new ImagePanel("./images/dogmesCarte/humain.jpg", 800/20, 800/20));
				break;
			case SYMBOLE:
				panelBot.add(new ImagePanel("./images/dogmesCarte/symboles.jpg", 800/20, 800/20));
				break;
			case MYSTIQUE:
				panelBot.add(new ImagePanel("./images/dogmesCarte/mystique.jpg", 800/20, 800/20));
				break;
			case CHAOS:
				panelBot.add(new ImagePanel("./images/dogmesCarte/chaos.jpg", 800/20, 800/20));
				break;
			case NOTREFERENCED:
				break;
			}
		}
	}

	/**Methode permettant d'ajouter dans le panel l'origine de la carte*/
	public void ajoutOrigine(){
		String path="";
		switch(((ActionCardWithOrigin) carte).getOrigine()){
		case JOUR:
			path = "./images/OrigineCarte/jour.jpg";
			break;
		case NEANT:
			path = "./images/OrigineCarte/neant.jpg";
			break;
		case NUIT:
			path = "./images/OrigineCarte/nuit.jpg";
			break;
		case NOTREFERENCED:
			break;
		}
		panelTop.add(new ImagePanel(path, 800/20, 800/20), BorderLayout.WEST);
	}

	/**Methode permettant d'ajouter dans le panel l'origine de la divinite*/
	public void ajoutOrigineDivinite(){
		String path="";
		switch(((Divinity) carte).getOrigine()){
		case JOUR:
			path = "./images/OrigineDivinite/jour.jpg";
			break;
		case AUBE:
			path = "./images/OrigineDivinite/aube.jpg";
			break;
		case CREPUSCULE:
			path = "./images/OrigineDivinite/crepuscule.jpg";
			break;
		case NUIT:
			path = "./images/OrigineDivinite/nuit.jpg";
		case NOTREFERENCED:
			break;
		}
		panelTop.add(new ImagePanel(path, 800/20, 800/20), BorderLayout.WEST);
	}

	/**Methode permettant d'ajouter dans le type de la carte*/
	public void ajoutTitre(){
		String nom = "";
		if (carte instanceof Divinity)
			nom = "Divinite";
		if(carte instanceof Believer)
			nom = "Croyant";
		if (carte instanceof SpiritGuide)
			nom= "Guide spirituel";
		if (carte instanceof ApocalypseWithOrigin || carte instanceof Apocalypse)
			nom = "Apocalypse";
		if (carte instanceof DeusEx || carte instanceof DeusExWithOrigin)
			nom = "Deus Ex";
		titre.setText(nom);
	}

	/**Methode permettant d'ajouter dans le panel la description de la carte*/
	public void ajoutDescription(){
		String description="";
		if (carte instanceof Divinity)
			description = ((Divinity)carte).getDescription();
		if (carte instanceof CarteDogmatique)
			description = ((CarteDogmatique)carte).getTextSacrifice();
		if (carte instanceof DeusExWithOrigin)
			description = ((DeusExWithOrigin)carte).getDescription();
		if (carte instanceof DeusEx)
			description = ((DeusEx)carte).getDescription();
		labelArea.setText(description);
	}
}
