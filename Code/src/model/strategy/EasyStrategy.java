package model.strategy;

import java.util.HashMap;

import model.EnumType.Cosmogonie;
import model.player.Bot;

/**Stratégie de jeu facile pour les bots*/
public class EasyStrategy implements Strategy {

	//FAIRE LA METHODE JOUER 
	//TESTER LES TYPES DE CARTE DANS SA MAIN SI IL A DES CROYANTS

	//- tant qu’il peut jouer, il joue l’ordre des cartes qu’il a 
	//- pour la easyStrat privilégier la pose des croyants, et le récupération avec des guides
	//- regarder le modele strategy
	//- test des exceptions

	/**garde le bot qui joue en memoir pour recuperer ses sdonnees (cartes, score etc..)*/
	private Bot bot;

	/**méthode pour recuperer le bot qui joue
	 * 
	 * @param bot le bot qui joue
	 */
	private void setBot(Bot bot) {
		this.bot = bot;
	}

	@Override
	/**
	 * Methode de jeu facile
	 * @param bot recupere le bot pour avoir ses parametress
	 */
	public void jouer(Bot bot) {
		this.setBot(bot);
		HashMap<Cosmogonie, Integer> pointsAction = bot.getDicoPA();
		//linkedlist getDICOPA avec un iterateur//////////////////////////////////////////////////////////////////////////////////
		System.out.println("Point Action: (Jour: " + pointsAction.get(Cosmogonie.JOUR) + ") " + "(Nuit: " + pointsAction.get(Cosmogonie.NUIT) + ") "
				+ "(Néant: " + pointsAction.get(Cosmogonie.NEANT) + ")");

		//this.seDefausserCartesEtCompleter(partie);
		//this.choisirCarte(partie);
		//if (this.sacrifice && this.laMain.getListeCroyantGuidee().size() != 0) {
		//this.sacrifierCroyant(this.laMain.getListeCroyantGuidee().get(0).get(0).getId(), partie);
		//}
	}

	@Override
	public void choisirCarte() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	/*public void seDefausserCartesEtCompleter() {
		JeuDeCartes jeuDeCartes=partie.getJeuDeCartes();
		// Choisir au hasard le nombre de carte défaussée.
		System.out.println("Les cartes actions tenu dans sa main:");
		System.out.println(this.laMain);

		LinkedList<Integer> ids = new LinkedList<Integer>();
		LinkedList<CarteAction> cartesRecupere =stategie.choisirCartesDefausser(partie);
		for (CarteAction carteA: cartesRecupere) {
			ids.add(carteA.getId());
		}
		System.out.println("Il a défaussé les cartes qui ont les Id en : " + ids);
		// jeuDeCartes recupére les cartes action après le joueur compléte 7
		// cartes.
		this.Compeleter7Carte(jeuDeCartes);
		for (CarteAction carte : cartesRecupere) {
			jeuDeCartes.recupererCarteAction(carte);
		}
	}*/

	//@Override
	/*public void choisirCarte(Bot bot) {
		CarteAction carteChoisi = new CarteAction();
		for (CarteAction carteA : this.laMain.getListeCarteA()) {
			if (carteA.getOrigine().equals("Jour") && this.testEntree(carteA, partie)) {
				if (this.ptAction_Jour > 0) {
					carteChoisi = carteA;
					this.ptAction_Jour--;
				}
			} else if (carteA.getOrigine().equals("Nuit") && this.testEntree(carteA, partie)) {
				if (this.ptAction_Nuit > 0) {
					carteChoisi = carteA;
					this.ptAction_Nuit--;
				}
			} else if (carteA.getOrigine().equals("Néant") && this.testEntree(carteA, partie)) {
				if (this.ptAction_Neant > 0) {
					carteChoisi = carteA;
					this.ptAction_Neant--;
				} else if (this.ptAction_Nuit >= 2) {
					carteChoisi = carteA;
					this.ptAction_Nuit -= 2;
				} else if (this.ptAction_Jour >= 2) {
					carteChoisi = carteA;
					this.ptAction_Jour -= 2;
				}
			}
			if (carteChoisi.estEgal(carteA)) {
				break;
			}
		}
		if (carteChoisi.getId() != 0) {
			System.out.println(this.nom + " a joué la carte: " + carteChoisi);
		}
		switch (carteChoisi.getType()) {
		case "Croyant":
			this.jouerCroyant(carteChoisi, partie.getEspaceCommun());
			break;
		case "GuideSpirituel":
			this.jouerGuideSpirituel(carteChoisi, partie.getEspaceCommun());
			break;
		case "DeusEx":
			this.jouerDeusEx(partie);
			break;
		case "Apocalypse":
			this.jouerApocalypse(carteChoisi, partie);
			break;
		}

	}*/

	/*private void jouerGuideSpirituel(CarteAction carte, EspaceCommun espaceCommun) {
		GuideSpirituel carteG = (GuideSpirituel) carte;
		LinkedList<CarteAction> listeCroyantsGuidee = new LinkedList<CarteAction>();
		int indice = 0;
		for (CarteAction carteA : espaceCommun.getListeCartesPret()) {
			Boolean test = false;
			for (String dogmeA : carteA.getDogme()) {
				for (String dogmeD : carteG.getDogme()) {
					if (dogmeD.equals(dogmeA)) {
						test = true;
						break;
					}
				}
			}
			if (test == true && indice < carteG.getNbGuider()) {
				indice++;
				listeCroyantsGuidee.add(carteA);
			}
		}
		this.laMain.ajouterGuidee(listeCroyantsGuidee, carteG);
	}*/

	/*private Boolean testEntree(CarteAction carte, Partie partie) {
		Boolean test = true;
		if (carte.getType().equals("GuideSpirituel")) {
			test = false;
			if (carte.getType().equals("GuideSpitituel")) {
				for (CarteAction carteA : partie.getEspaceCommun().getListeCartesPret()) {
					for (String dogmeA : carteA.getDogme()) {
						for (String dogmeD : carte.getDogme()) {
							if (dogmeD.equals(dogmeA)) {
								test = true;
								break;
							}
						}
					}
				}
			}
		}
		if (carte.getType().equals("Apocalypse")) {
			if (partie.getEstApocalypseAvant() == 0 || partie.getEstApocalypseAvant() == -1) {
				test = false;
			}
		}
		return test;
	}*/

	/*private void jouerDeusEx(Partie partie) {

	}*/

	/*private void jouerApocalypse(ActionCard carte, GameManager gameManager) {
		partie.setEstApocalypseAvant(-1);
		int[] arPriere = {};
		int indice = -1;
		for (Joueur j : partie.getListeJoueurs()) {
			j.setPtPriere();
			arPriere[indice++] = j.getPtPriere();
		}
		for (int i = 0; i <= indice - 1; i++) {
			for (int j = i + 1; j <= indice; j++) {
				if (arPriere[i] < arPriere[j]) {
					int tg = arPriere[i];
					arPriere[i] = arPriere[j];
					arPriere[j] = tg;
				}
			}
		}
		if (indice + 1 >= 4) {
			if (arPriere[indice] == arPriere[indice - 1]) {
				System.out.println(
						"Il y a 2 joueur ayant le même point prière dernier. Cette carte Apocalypse est défaussé.");
			} else {
				for (Joueur j : partie.getListeJoueurs()) {
					if (j.getPtPriere() == arPriere[indice]) {
						partie.eliminerJoueur(j);
						break;
					}
				}
			}
		} else {
			if (arPriere[0] == arPriere[1]) {
				System.out.println(
						"Il y a 2 joueur ayant le même point prière premier. Cette carte Apocalypse est défaussé.");
			} else {
				for (Joueur j : partie.getListeJoueurs()) {
					if (j.getPtPriere() == arPriere[0]) {
						partie.setJoueurgagnant(j);
						partie.setEstFini(true);
						break;
					}
				}
			}
		}*/
}




