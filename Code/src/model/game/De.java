package model.game;

import model.EnumType.Cosmogonie;

public class De {
	/*un singleton pour avoir un unique dé*/
	private volatile static De de;
	private Cosmogonie face;
	
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
		int random = (int) (Math.random() * 3 + 1);
		if(random == 1){
			de.face = Cosmogonie.JOUR;
		}
		if(random == 2 ){
			de.face = Cosmogonie.NUIT;
		}
		if(random == 3 ){
			de.face = Cosmogonie.NEANT;
		}
	}

	/**méthode qui permet de récuperer l'origine sur lequel le dé est retombé*/
	public Cosmogonie getFace(){
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
