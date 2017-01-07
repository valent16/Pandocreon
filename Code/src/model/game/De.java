package model.game;

import model.EnumType.EnumCosmogonie;

public class De {
	
	/*Attribut representant un singleton pour avoir un unique dé*/
	private volatile static De de;
	
	/**Attribut representant la face du de*/
	private EnumCosmogonie face;
	
	/**Constructeur*/
	private De(){}

	/**Méthode permetttant d'avoir qu'une seule instance de Dé
	 * @return de le De de la classe
	 */
	public static synchronized De getInstanceDe(){
		if (de == null){
			synchronized (De.class){ //pour gerer le multi-thread
				if (de == null) 
					de = new De();
			}
		}
		return de;
	}

	/**Méthode permettant de lancer de maniere random un Dé pour obtenir une face*/
	public void lancerDe(){
		int random = (int) (Math.random() * 3 + 1);
		if(random == 1){
			de.face = EnumCosmogonie.JOUR;
		}
		if(random == 2 ){
			de.face = EnumCosmogonie.NUIT;
		}
		if(random == 3 ){
			de.face = EnumCosmogonie.NEANT;
		}
	}

	/**Getter origine sur lequel le dé est retombé
	 * @return la face du De
	 */
	public EnumCosmogonie getFace(){
		return face;
	}
	
	/**Setter origne du de
	 * @param face la nouvelle face du De
	 */
	public void setFace(EnumCosmogonie face){
		this.face = face;
	}
}
