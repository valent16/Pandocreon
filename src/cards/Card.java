/* @author SMIMITE Badr-Eddine
 * @version 1.00 
 * mettre la date aussi
 */

package cards;

////////////A VOIR SI CELA PEUT ETRE UNE INTERFACE///////////////////
/**Classe abstraite qui represente n'importe quel carte*/
public class Card {
	
	/**nom du fichier de l'image de la carte*/
	 private String img;
	
	/*attributs:
	 * - une Origin
	 * - Un tableau ou une liste de Dogmes
	 * - Un Sacrifice
	 * - un entier pour le nombre de croyants 
	 */
	
	/*methodes:
	 * 	
	 */
	 
	 /**renvoie img (le nom du fichier qui contiens l'image de la carte)*/
	 public String getImg(){
		 return this.img;
	 }

}
