package model.player;

public class Human extends Player{
	
	/**Constructeur de joueur qui est appelé pour créer un joueur humain*/
	public Human(String pseudo, int age) {
		super(pseudo, age);
	}
	
	@Override
	public void jouerTour() {
		incrementerPointActionWithDe();
		notifier();
		// TODO Auto-generated method stub
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
