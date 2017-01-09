package view.ihm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import java.util.List;

import model.EnumType.EnumDogme;
import model.EnumType.EnumOrigineDivinite;
import model.cards.Divinity;
import model.cards.Card;

public class ScrollerCard extends JPanel {

	JPanel panel;
	
	private final JScrollPane scroll;
	
	private ArrayList<PanelCarte> listeCartesGraphiques = new ArrayList<PanelCarte>();
	
    public ScrollerCard(List<Card> listeCartes) throws HeadlessException {
    	
    	panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//        panel.setPreferredSize(new Dimension(800,150));
        panel.setBorder(BorderFactory.createLineBorder(Color.red));
        scroll = new JScrollPane(panel);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
//        setSize(300, 300);
//        setVisible(true);
        
        Iterator<Card> it = listeCartes.iterator();
        
        while (it.hasNext()){
        	PanelCarte panelCarte = new PanelCarte(it.next());
        	panel.add(panelCarte);
        	listeCartesGraphiques.add(panelCarte);
        	panel.add(Box.createRigidArea(new Dimension(5,0)));
        }
        panel.revalidate();
        scroll.revalidate();
    }
    
    //Permet de mettre � jour le panel de carte en fonction de la liste de carte pass� en param�tre
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
    	
    	//Ajout des cartes qui n'�taient pas pr�sentes
    	Iterator<Card> itCarteAjout = cartes.iterator();
    	while (itCarteAjout.hasNext()){
    		PanelCarte panelCarte = new PanelCarte(itCarteAjout.next());
        	panel.add(panelCarte);
        	listeCartesGraphiques.add(panelCarte);
        	panel.add(Box.createRigidArea(new Dimension(5,0)));
    	}
    	
    	panel.revalidate();
        scroll.revalidate();
    }
}