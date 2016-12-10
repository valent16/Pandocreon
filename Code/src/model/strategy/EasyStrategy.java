package model.strategy;

import java.util.HashMap;

import model.EnumType.Cosmogonie;
import model.player.Bot;

/**Stratégie de jeu facile pour les bots
 * Les choix se font au hasard*/
public class EasyStrategy implements Strategy {

	//FAIRE LA METHODE JOUER 
			//TESTER LES TYPES DE CARTE DANS SA MAIN SI IL A DES CROYANTS

			//- tant qu’il peut jouer, il joue l’ordre des cartes qu’il a 
			//- pour la easyStrat privilégier la pose des croyants, et le récupération avec des guides
			//- regarder le modele strategy
			//- test des exceptions
	
	//POUR LA EASY STRATEGIE FAIRE UN CHOIX RANDOM parmi les choix suivants:
	//Suivre le code sur le site
	
	
	
    
	/**garde le bot qui joue en memoir pour recuperer ses sdonnees (cartes, score etc..)*/
	private Bot bot;

	/**
	 * Methode pour recuperer le bot qui joue
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
		System.out.println("les points du BOT "+ bot.getNom() +": "  
			+ pointsAction.get(Cosmogonie.JOUR) +" Point Jour | "
			+ pointsAction.get(Cosmogonie.NUIT) +" Point Nuit | " + 
			+ pointsAction.get(Cosmogonie.NEANT) +" Point Néant"); 

		
		/*voir dans la classe Player les possibilités du joueur
		faire un switchcase 
		qui recupere un choix et qui effectue un test si le choix est possible exemple poser un croyant 
		necessite d'en avoir donc si le test est a true on appelle la boonne fonction, sinon on rappelele la methode jouer*/
		
		//-defausserCartes
		//-Sacrifier une carte
		
		//depart
	    int action = (int) (Math.random() * 5) + 1;
		System.out.println("action "+action);
        switch (action){
            case 0:
                break;
            case 1:	//jouer une carte
            	System.out.println("action1");
                //int cardInt = this.choisirCarte(bot.getHand().size());
                //System.out.println("Carte a jouer : " + bot.getHand().get(cardInt));
                //this.playCard(partie, bot.getHand().get(cardInt));
                break;
            
            case 2: //poser un croyant
            	System.out.println("action2");
                break;
            
            case 3: //recuperer des croyants avec le guide spirituel
            	
            	System.out.println("action3");
            	break;
            
            case 4: //defausser une ou plusieurs carte
            	//si sa main est vide on rappelle la methode jouer
                //this.defausserCartes(partie.getCartesDefaussees());
            	System.out.println("action4");
                break;
            
            case 5: //completer sa main jusqu'a 7
                if(bot.getHand().size() < 7){
                	//this.completerMain(partie.getPioche());
                    //this.jouer(partie);
                	System.out.println("action5");
                }else{
                    this.jouer(bot);
                }
                break;
            default:
                break;
        }

		
		//this.seDefausserCartesEtCompleter(partie);
		//this.choisirCarte(partie);
		//if (this.sacrifice && this.laMain.getListeCroyantGuidee().size() != 0) {
		//this.sacrifierCroyant(this.laMain.getListeCroyantGuidee().get(0).get(0).getId(), partie);
		//}
	}

	private void choisirCarte() {
		// TODO Auto-generated method stub	
		System.out.println("chirsier une carte");
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	@Override
    public int choixDefausserCartes(JoueurVirtuel joueur, Defausse defausse) {
        int choix = ((int) (Math.random()*2+1));
        return choix;
    }

    // Strategie : les choix se font au hasard

    @Override
    public int[] choixCartesADefausser(JoueurVirtuel joueur) {
        int nbCartesADefausser = ((int) (Math.random()*joueur.getMain().getMainCartesActions().size()+1));
        int[] choixCartes = new int[nbCartesADefausser];
        for (int i=0; i<choixCartes.length; i++) {
            choixCartes[i] = ((int) (Math.random()*joueur.getMain().getMainCartesActions().size()+1));
        }
        return choixCartes;
    }

    @Override
    public int choixAction() {
        int choix = ((int) (Math.random()*4+1));
        return choix;
    }

    public int choixCartesAction(JoueurVirtuel joueur) {
        int choix = ((int) (Math.random()*joueur.getMain().getMainCartesActions().size()+1));
        return choix;
    }

    @Override
    public int choixPointAction() {
        int choix = ((int) (Math.random()*3+1));
        return choix;
    }

    @Override
    public int choixContinuerJouerCarte(JoueurVirtuel joueur) {
        int choix = ((int) (Math.random()*2+1));
        return choix;
    }

    @Override
    public int choixCarteChamp(JoueurVirtuel joueur) {
        int nbCartesChamp = joueur.getChamp().getListeCartes().size();
        int choixCarteSacrifice = ((int) (Math.random()*nbCartesChamp+1));
        return choixCarteSacrifice;
    }

    @Override
    public int choixJoueur(JoueurVirtuel joueur, Partie partie) {
        int choixJoueur;
        do {
            choixJoueur = (int) (Math.random() * partie.getNbJoueur() + 1);
        } while ((choixJoueur - 1) != partie.getListJoueur().indexOf(joueur));
        return choixJoueur;
    }
    
    @Override
    public int choixGuide(JoueurVirtuel joueur) {
        int choixGuide;
        do {
            choixGuide = (int) (Math.random() * joueur.getChamp().getListeCartes().size() + 1);
        } while (!(joueur.getChamp().getListeCartes().get(choixGuide - 1) instanceof GuideSpirituel));
        return choixGuide;
    }
    
    @Override
    public int choixCroyant(JoueurVirtuel joueur) {
        int choixCroyant;
        do {
            choixCroyant = (int) (Math.random() * joueur.getChamp().getListeCartes().size() + 1);
        } while (!(joueur.getChamp().getListeCartes().get(choixCroyant - 1) instanceof Croyant));
        return choixCroyant;
    }

    @Override
    public int choixOrigine() {
        int choixOrigine;
        choixOrigine = (int)(Math.random()*3)+1;
        return choixOrigine;
}
    */
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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




