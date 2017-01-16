package view.ihm;

import java.awt.*;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import controller.JoueurController;

import java.util.List;

import model.cards.Card;

public class ScrollerCard extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel panel;

	private final JScrollPane scroll;

	private ArrayList<PanelCarte> listeCartesGraphiques = new ArrayList<PanelCarte>();

	private PanelJoueurReel father;

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

	//TODO A commenter
	public void ajouterPanelJoueurReel(PanelJoueurReel father){
		this.setFather(father);
	}

	//TODO A commenter//Permet de mettre a jour le panel de carte en fonction de la liste de carte passe en parametre
	public void majCarte(List<Card> cartes){
		ArrayList<Card> cartesASupprimer = new ArrayList<Card>();
		//Permet de faire une deep copy de la liste de cartes
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

	//TODO A commenter
	public void activateSelection(JoueurController controller){
		Iterator<PanelCarte> it = listeCartesGraphiques.iterator();
		while(it.hasNext()){
			it.next().activateSelection();
		}
	}

	//TODO A commenter
	public void desactivateSelection(){
		Iterator<PanelCarte> it = listeCartesGraphiques.iterator();
		while(it.hasNext()){
			it.next().desactivateSelection();
		}
	}

	//TODO A commenter
	public PanelJoueurReel getFather() {
		return father;
	}

	//TODO A commenter
	public void setFather(PanelJoueurReel father) {
		this.father = father;
	}
}