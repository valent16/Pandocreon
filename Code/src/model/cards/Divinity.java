package model.cards;

import java.util.ArrayList;

import model.EnumType.EnumDogme;

//Represente une carte divinité
public class Divinity extends Card{

	private String description;
	
	private ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();

	/**Création d'une carte divinité*/
	public Divinity(String nom, ArrayList<EnumDogme> dogmes) {
		super(nom);
		//faire une deep copy 
		this.dogmes = dogmes;
	}

	@Override
	public void utiliserPouvoir(String commande) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Divinity [description=" + description + ", dogmes=" + dogmes + "]";
	}
	
	
//	public ArrayList<Divinity>instancierDivinites(){/////////////////IL FAUT APPELER CETTE METHDOE DANS LE GAME MANAGER///////////////////////////////////
//		ArrayList<Divinity> listDivinite = new ArrayList<Divinity>();
//		listDivinite.add(new Divinity("Brewalen", Origin.JOUR, "Peut empêcher l'utilisation d'une carte Apocalypse. La carte est défaussée", Dogme.NATURE, Dogme.HUMAIN, Dogme.MYSTIQUE));
//		listDivinite.add(new Divinity("Drinded", Origin.JOUR, "Peut empêcher le sacrifice d'un des guides spirituels de n'importe quel joueur.", Dogme.NATURE, Dogme.HUMAIN, Dogme.SYMBOLE));
//		listDivinite.add(new Divinity("Yarstur", Origin.JOUR, "Peut détruire toutes les cartes de Croyants au centre de la table d'Orignie Néant", Dogme.CHAOS, Dogme.SYMBOLE, Dogme.MYSTIQUE));
//		listDivinite.add(new Divinity("Killinstred", Origin.NUIT, "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une", Dogme.NATURE, Dogme.MYSTIQUE, Dogme.CHAOS));
//		listDivinite.add(new Divinity("Llewella", Origin.NUIT, "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une", Dogme.NATURE, Dogme.MYSTIQUE, Dogme.CHAOS));
//		listDivinite.add(new Divinity("Pui-Tara", Origin.NUIT, "Peut détruire toutes les cartes de Croyants au centre de la table d'Origine Jour", Dogme.NATURE, Dogme.MYSTIQUE, Dogme.SYMBOLE));
//		listDivinite.add(new Divinity("Gwenghelen", Origin.AUBE, "Récupère autant de points d'Action supplémentaires d'Origine Néant que le nombre de guides spirituels que la divinité possède", Dogme.HUMAIN, Dogme.MYSTIQUE, Dogme.SYMBOLE));
//		listDivinite.add(new Divinity("Shingua", Origin.AUBE, "Peut imposer le sacrifice d'un Guide spirituel ayant le dogme Symbole ou Nature", Dogme.NATURE, Dogme.MYSTIQUE, Dogme.CHAOS));
//		listDivinite.add(new Divinity("Gorpa", Origin.AUBE, "Peut récupérer les points d'Action d'une autre Divinité en plus des siens. L'autre divinité ne reçoit aucun point d'Action ce tour-ci", Dogme.HUMAIN, Dogme.SYMBOLE, Dogme.CHAOS));
//		listDivinite.add(new Divinity("Romtec", Origin.AUBE, "Peut empêcher n joueur de créer un guide spirituel. La carte est défaussée", Dogme.NATURE, Dogme.HUMAIN, Dogme.CHAOS));
//		return listDivinite;
//	}
	

//	@Override
//	public String toString() {
//		return "la carte "+ nom + " : dogmes = " + this.dogmes + " | capacite spéciale: " + this.capaciteSpeciale;
//	}
//
//	public static void main(String[] args){
//		//TESTER LE CODE DIVINITE
//		Card carte1 = new Divinity("Brewalen", Origin.JOUR, "Peut empêcher l'utilisation d'une carte Apocalypse. La carte est défaussée", Dogme.NATURE, Dogme.HUMAIN, Dogme.MYSTIQUE);
//		System.out.println(carte1);
//	}

}
