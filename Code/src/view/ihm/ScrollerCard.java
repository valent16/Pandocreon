package view.ihm;

import java.awt.*;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import java.util.List;

import model.cards.Card;
/**Panel de scroll pour la representation des cartes dans la partie, classe utilisee plusieurs fois pour la cration du jeu du joueur et la creation de la liste de carte sur table de jeu*/
public class ScrollerCard extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**Panel englobant tous les items*/
	JPanel panel;
	
	/**scroll du panel*/
	private final JScrollPane scroll;
	
	/**Liste des cartes a afficher dans le panel*/
	private ArrayList<PanelCarte> listeCartesGraphiques = new ArrayList<PanelCarte>();

	/**
	 * Constructeur de la classe ScrollerCard
	 * @param listeCartes liste des cartes a afficher dans le scroll
	 */
	public ScrollerCard(List<Card> listeCartes) throws HeadlessException {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createLineBorder(Color.red));
		scroll = new JScrollPane(panel);
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);

		Iterator<Card> it = listeCartes.iterator();

		while (it.hasNext()){
			PanelCarte panelCarte = new PanelCarte(it.next());
			panel.add(panelCarte);
			listeCartesGraphiques.add(panelCarte);
		}
		panel.revalidate();
		scroll.revalidate();
	}

	/**Methode permettant de mettre a jour le panel de cartes en fonction de la liste de cartes passee en parametre
	 * @param cartes les cartes a supprimer ou a ajouter dans le panel
	 */
	public void majCarte(List<Card> cartes){
		ArrayList<Card> cartesASupprimer = new ArrayList<Card>();
		Iterator<PanelCarte> it = listeCartesGraphiques.iterator();
		while(it.hasNext()){
			cartesASupprimer.add(it.next().getCarte());
		}

		Iterator<PanelCarte> itPanelCarte = listeCartesGraphiques.iterator();
		while(itPanelCarte.hasNext()){
			PanelCarte panelCarte = itPanelCarte.next();
			if (cartes.contains(panelCarte.getCarte())){
				cartesASupprimer.remove(panelCarte.getCarte());
				cartes.remove(panelCarte.getCarte());
			}
		}

		//Ajout des cartes qui n'etaient pas presentes
		Iterator<Card> itCarteAjout = cartes.iterator();
		while (itCarteAjout.hasNext()){
			PanelCarte panelCarte = new PanelCarte(itCarteAjout.next());

			panel.add(panelCarte);
			listeCartesGraphiques.add(panelCarte);
		}

		Iterator<Card> itSup = cartesASupprimer.iterator();
		while(itSup.hasNext()){
			Card ca = itSup.next();
			for (int i=0;i<listeCartesGraphiques.size();i++){
				if (listeCartesGraphiques.get(i).getCarte() == ca){
					panel.remove(listeCartesGraphiques.get(i));
				}
			}
		}
		panel.revalidate();
		scroll.revalidate();
	}
}