package model.game;

import model.EnumType.EnumOrigineCA;

public class De {
	/*un singleton pour avoir un unique dé*/
	private volatile static De de;
	private EnumOrigineCA face;
	
	private De(){

	}

	/**Méthode qui permet d'avoir qu'une seule instance de Dé
	 * @return de*/
	public static synchronized De getInstanceDe(){
		if (de == null){
			synchronized (De.class){ //pour gerer le multi-thread
				if (de == null) 
					de = new De();
			}
		}
		return de;
	}

	/**méthode qui lance de maniere random un Dé pour obtenir une face
	 * @return */
	public void lancerDe(){
		//genere un nombre random entre 1 et 6
		int random = (int) (Math.random() * 6 + 1);
		if(random == 1 || random == 4)
			de.face = EnumOrigineCA.JOUR;

		if(random == 2 || random == 5)
			de.face = EnumOrigineCA.NUIT;

		if(random == 3 || random == 6)
			de.face = EnumOrigineCA.NEANT;
	}

	/**méthode qui permet de récuperer l'origine sur lequel le dé est retombé*/
	public EnumOrigineCA getFace(){
		return face;
	}

	//TEST
	public static void main(String[] args){
		//test du singleton
		getInstanceDe();
		System.out.println(de.hashCode());

		//test d'un lancer de dé
		for (int i = 0; i < 5; i++) {
			de.lancerDe();
			System.out.println(de.getFace());
		}
	}
}
