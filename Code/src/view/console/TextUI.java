package view.console;

import java.util.Scanner;

public class TextUI {
	private Scanner scan = null;
	private static TextUI INSTANCE = new TextUI();

	private TextUI() {}

	public static TextUI getInstance() {
		return INSTANCE;
	}

	protected void openScan() {
		this.scan = new Scanner(System.in);
	}

	public Integer readInt(String msg, int start, int end) {
		int input;

		System.out.print(msg);
		this.openScan();

		do {
			System.out.println("Choose a valid value between " + start + " and " + end);
			while (!scan.hasNextInt()) {
				scan.next();
			}
			input = this.scan.nextInt();
		} while (input < start || input > end);
		return input;
	}

	public String readString(String msg) {
		System.out.print(msg);
		this.openScan();
		while (!scan.hasNextLine()) {
			scan.next();
		}
		String s = this.scan.nextLine();

		return s;
	}

	public Float readFloat(String msg) {
		System.out.print(msg);
		this.openScan();
		while (!scan.hasNextFloat()) {
			scan.next();
		}
		return this.scan.nextFloat();
	}

	/**
	 * Wait until the user presses any key to continue
	 * @return
	 */
	public String readContinue(String msg) {      
		System.out.print(msg);
		this.openScan();
		while (!scan.hasNextLine()) {
			scan.nextLine();
		}
		return this.scan.nextLine();
	}

	public void showMessage(String msg) {
		System.out.println(msg);
	}

	public void showMessage(Object o) {
		System.out.println(o);
	}
}
