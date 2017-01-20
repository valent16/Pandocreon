package model.gestionDonnees;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.cards.ActionCard;
import model.cards.Divinity;
import model.cards.OriginCards.*;
import model.cards.withoutOriginCards.Apocalypse;
import model.cards.withoutOriginCards.DeusEx;
import model.enumType.*;
import model.pouvoir.Pouvoir;
import model.pouvoir.pouvoirCarte.*;
import model.pouvoir.sacrificeCarte.*;

/**Classe qui permet de charger a partir d'un fichier CML les cartes du jeu*/
public class ParserXML implements IDataLoad {

	/**Attribut representant la liste des sacrifices possibles*/
	private static HashMap<String, Pouvoir> listeSacrifice = new HashMap<String, Pouvoir>();

	/**Attribut representant la liste des cartes actions chargees grace au parser*/
	private LinkedList<ActionCard> cartesAction = new LinkedList<ActionCard>();

	/**Attribut representant la liste des divinites chargees grace au parser*/
	private LinkedList<Divinity> divinites = new LinkedList<Divinity>();

	/**Constructeur qui ajoute les sacrifices et parse le fichier cartes.xml*/
	public ParserXML(){
		listeSacrifice.put("SacrificeAjouterPointJour", new SacrificeAjouterPointJour() );
		listeSacrifice.put("SacrificeAjouterPointNuit", new SacrificeAjouterPointNuit());
		listeSacrifice.put("SacrificeAjouterPointNeant", new SacrificeAjouterPointNeant());
		listeSacrifice.put("SacrificeApocalypse", new SacrificeApocalypse());
		listeSacrifice.put("SacrificeDefausserCroyantsMystique", new SacrificeDefausserCroyantsMystique());
		listeSacrifice.put("SacrificeDefausserCroyantsNuitNeantNature", new SacrificeDefausserCroyantsNuitNeantNature());
		listeSacrifice.put("SacrificeEchangerGuides", new SacrificeEchangerGuides());
		listeSacrifice.put("SacrificeGorpa", new SacrificeGorpa());
		listeSacrifice.put("SacrificeGwenghelen", new SacrificeGwenghelen());
		listeSacrifice.put("SacrificeKillinstred", new SacrificeKillinstred());
		listeSacrifice.put("SacrificeLlewella", new SacrificeLlewella());
		listeSacrifice.put("SacrificePiocherDeuxCartes", new SacrificePiocherDeuxCartes());
		listeSacrifice.put("SacrificePoserDeFace", new SacrificePoserDeFace());
		listeSacrifice.put("SacrificePrendreTroisCartes", new SacrificePrendreTroisCartes());
		listeSacrifice.put("SacrificePuiTara", new SacrificePuiTara());
		listeSacrifice.put("SacrificeRelanceDeCosmogonie", new SacrificeRelanceDeCosmogonie());
		listeSacrifice.put("SacrificeRetourGuideMainDivinite", new SacrificeRetourGuideMainDivinite());
		listeSacrifice.put("SacrificeYarstur", new SacrificeYarstur());		
		parserFichier();
	}

	@Override
	public LinkedList<ActionCard> chargerCartes() {
		return this.cartesAction;
	}

	@Override
	public LinkedList<Divinity> chargerDivinites() {
		return this.divinites;
	}

	@Override
	public void chargerPartie() {
		//TODO A developper le fait de pouvoir charger une partie, en utilisant la serialisation des classe Game et GameManager 
		//on les stockent dans des fichiers txt que l'on deserialiserai pour pouvoir les charger
	}

	/**Methode permettant au parser de recuperer le type de la carte
	 * @param type le type recherche
	 * @return le type dans le parser
	 */
	private String recupTypeCarte(String type){
		Pattern p=Pattern.compile("carte-([a-zA-Z]*)((,.*)||$)");
		Matcher m=p.matcher(type);
		while(m.find()){
			return m.group(1);
		}	
		return null;
	}

	/**Methode permettant au parser de recuperer l'origine de la carte
	 * @param template l'origine de la carte
	 * @return l'origine de la carte dans le parser
	 */
	private EnumCosmogonie recupOrigineCarte(String template){
		Pattern p=Pattern.compile(".*origine-([a-zA-Z]*).*");
		Matcher m=p.matcher(template);
		while(m.find()){
			return convertOrigineCAFromString(m.group(1));
		}	
		return null;
	}

	/**Methode permettant au parser de recuperer l'origine de la divinite
	 * @param template l'origine de la divinite
	 * @return l'origine de la divinite dans le parser
	 */
	private EnumOrigineDivinite recupOrigineCarteDiv(String template){
		Pattern p=Pattern.compile(".*origine-divinites-([a-zA-Z]*).*");
		Matcher m=p.matcher(template);
		while(m.find()){
			return convertOrigineDiviniteFromString(m.group(1));
		}	
		return null;
	}

	/**Methode permettant au parser de recuperer la liste des dogmes de la carte
	 * @param template le dogme recherche
	 * @return les dogmes de la carte
	 */
	private ArrayList<EnumDogme> getDogmeDiv(String template){
		ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();

		Pattern p=Pattern.compile(".*dogmes-divinites-(.*)");
		Matcher m=p.matcher(template);
		while(m.find()){
			String[] rsltt = m.group(1).split("_");
			for(int i=0; i<rsltt.length;i++){
				dogmes.add(convertDogmeFromString(rsltt[i]));
			}
			return dogmes;
		}	
		return null;
	}

	/**Methode permettant au parser de recuperer les dogmes d'une carte Action
	 * @param template le dogme recherche
	 * @return les dogmes de la carte action
	 */
	private ArrayList<EnumDogme> getDogmeCA(String template){
		ArrayList<EnumDogme> dogmes = new ArrayList<EnumDogme>();	
		Pattern p=Pattern.compile(".*dogmes-(.*)");
		Matcher m=p.matcher(template);
		while(m.find()){
			String[] rsltt = m.group(1).split("_");
			for (int i= 0; i<rsltt.length; i++){
				dogmes.add(convertDogmeFromString(rsltt[i]));
			}
			return dogmes;
		}
		return null;
	}

	/**Methode permettant de convertir une chaine de carateres en EnumDogme
	 * @param dogme la chaine a convertir
	 * @return le dogme converti
	 */
	private EnumDogme convertDogmeFromString(String dogme){
		switch(dogme){
		case "nature":
			return EnumDogme.NATURE;
		case "mystique":
			return EnumDogme.MYSTIQUE;
		case "chaos":
			return EnumDogme.CHAOS;
		case "symboles":
			return EnumDogme.SYMBOLE;
		case "humain":
			return EnumDogme.HUMAIN;
		default:
			return EnumDogme.NOTREFERENCED;
		}
	}

	/**Methode permettant de convertir une chaine de carateres en EnumOrigneDivinite
	 * @param origine la chaine a convertir
	 * @return l'origine convertie
	 */
	private EnumOrigineDivinite convertOrigineDiviniteFromString(String origine){
		switch(origine){
		case "jour":
			return EnumOrigineDivinite.JOUR;
		case "aube":
			return EnumOrigineDivinite.AUBE;
		case "crepuscule":
			return EnumOrigineDivinite.CREPUSCULE;
		case "nuit":
			return EnumOrigineDivinite.NUIT;
		default:
			return EnumOrigineDivinite.NOTREFERENCED;
		}
	}

	/**Methode permettant de convertir une chaine de carateres en EnumOrigneCA (Carte Actions)
	 * @param origine la chaine a convertir
	 * @return l'origine convertie
	 */
	private EnumCosmogonie convertOrigineCAFromString(String origine){
		switch(origine){
		case "jour":
			return EnumCosmogonie.JOUR;
		case "nuit":
			return EnumCosmogonie.NUIT;
		case "neant":
			return EnumCosmogonie.NEANT;
		default:
			return EnumCosmogonie.NOTREFERENCED;
		}
	}

	/**Methode permettant de parser le fichier XML*/
	public void parserFichier(){
		String pouvoir;
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		HashMap<String,Integer > mapNbCroyants = new HashMap<String,Integer >();
		mapNbCroyants.put("nbr_un", 1);
		mapNbCroyants.put("nbr_deux", 2);
		mapNbCroyants.put("nbr_trois", 3);
		mapNbCroyants.put("nbr_quatre", 4);

		try{
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document= (Document) builder.parse(new File("cartes.xml"));
			final Element racine = document.getDocumentElement();
			final NodeList racineNoeuds = racine.getChildNodes();
			final int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) { //parcours toutes les cartes
				if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element deck =  (Element) racineNoeuds.item(i);
					final NodeList cartes = deck.getElementsByTagName("card");
					final int nbCartes = cartes.getLength();

					Pouvoir pvCroyant = new DepotCroyant();
					Pouvoir pvGuide = new ConversionCroyant();
					Pouvoir pvApocalypse = new PouvoirApocalypse();


					if (nbCartes != 0){ // cas ou la balise carte est trouvee
						for(int j = 0; j<nbCartes; j++) { //Pour chaque carte
							final Element carte = (Element) cartes.item(j);
							String[] lstr =  carte.getTextContent().trim().split("\n");
							try{
								String type = recupTypeCarte(carte.getAttribute("template"));
								ArrayList<EnumDogme> dogmesCarte;

								//si la carte est une divinite
								if (type.equals("divinites")){ 
									EnumOrigineDivinite orDIv = recupOrigineCarteDiv(carte.getAttribute("template"));
									dogmesCarte = getDogmeDiv(carte.getAttribute("template".trim()));
									Divinity divinite = new Divinity(lstr[0].trim(), dogmesCarte, lstr[3].trim(), orDIv);
									pouvoir = carte.getAttribute("sacrifice");
									if (pouvoir == ""){
										pouvoir = "NotImplemented";
									}
									divinite.ajouterPouvoir(pouvoir, listeSacrifice.get(pouvoir));
									divinites.push(divinite);

									//si la carte est un croyant ou un guide
								}else if(type.equals("croyants") || type.equals("guides")){
									dogmesCarte = getDogmeCA(carte.getAttribute("template".trim()));
									pouvoir = carte.getAttribute("sacrifice");
									if (pouvoir == ""){
										pouvoir = "NotImplemented";
									}

									EnumCosmogonie orCA = recupOrigineCarte(carte.getAttribute("template"));
									NodeList image = carte.getElementsByTagName("image");
									int nbImage = image.getLength();
									int valeurCroyant =0;
									if (nbImage == 1){
										Element croyants = (Element) image.item(0);
										valeurCroyant = mapNbCroyants.get(croyants.getAttribute("id"));
									}

									//si la carte est un croyant
									if (type.equals("croyants")){
										Believer croyant = new Believer(lstr[0].trim(), orCA, dogmesCarte, valeurCroyant, lstr[3].trim());
										croyant.ajouterPouvoir("deposer Croyant", pvCroyant);
										croyant.ajouterPouvoir(pouvoir, listeSacrifice.get(pouvoir));
										cartesAction.push(croyant);

										//sinon c'est un guide
									}else{
										SpiritGuide guide = new SpiritGuide(lstr[0].trim(), orCA, dogmesCarte, valeurCroyant, lstr[3].trim());
										guide.ajouterPouvoir("convertir Croyant", pvGuide);
										guide.ajouterPouvoir(pouvoir, listeSacrifice.get(pouvoir));
										cartesAction.push(guide);
									}
									
								//si la carte est une DeusEx
								}else if (type.equals("deusex")){
									pouvoir = carte.getAttribute("sacrifice");
									if (pouvoir == ""){
										pouvoir = "NotImplemented";
									}
									EnumCosmogonie orCA = recupOrigineCarte(carte.getAttribute("template"));
									if (orCA == null){
										DeusEx deusEx = new DeusEx(lstr[0].trim(), lstr[3].trim());
										cartesAction.push(deusEx);
										deusEx.ajouterPouvoir(pouvoir, listeSacrifice.get(pouvoir));
									}else{
										DeusExWithOrigin deusExOrigine = new DeusExWithOrigin(lstr[0].trim(), orCA, lstr[3].trim());
										cartesAction.push(deusExOrigine);
										deusExOrigine.ajouterPouvoir(pouvoir, listeSacrifice.get(pouvoir));
										//System.out.println(deusExOrigine.toString());
									}
								}else if (type.equals("apocalypses")){
									EnumCosmogonie orCA = recupOrigineCarte(carte.getAttribute("template"));
									if (orCA == null){
										Apocalypse apocalypse = new Apocalypse();
										apocalypse.ajouterPouvoir("declencher apocalypse", pvApocalypse);
										//System.out.println(apocalypse.toString());
									}else{
										ApocalypseWithOrigin apocalypse = new ApocalypseWithOrigin(orCA); 
										apocalypse.ajouterPouvoir("declencher apocalypse", pvApocalypse);
										cartesAction.push(apocalypse);
									}
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
