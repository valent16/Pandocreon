package view.ihm.client;

import java.awt.BorderLayout;

import view.ihm.Client;

public class PanelConfirmation extends PanelType{

	private static final long serialVersionUID = 1L;
	
	public PanelConfirmation(Client c){
		client = c;
		initialize();
		ajouterListener();
	}

	@Override
	protected void initialize() {
		//TODO PANEL QUI RECAPITULE TOUTES LES ACTIONS DE l'IHM A SAVOIR LES BOTS, LEUR NOMS, LA STRATEGIE IMPLEMENTER ET LES HUMAINS recuperer tous via le client
		this.setLayout(new BorderLayout());
		
	}

	@Override
	protected void ajouterListener() {
		// TODO Auto-generated method stub
		
	}

}
