package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_DIO;

public class Siebenseg {

	TPU_DIO leda, ledb, ledc, ledd, lede, ledf, ledg, leddp;

	int counter = 0;

	/**
	 * Konstruktor, um das Siebensegment zu initialisieren
	 */
	public Siebenseg() {

		leda = new TPU_DIO(false, 11, true);
		ledb = new TPU_DIO(false, 9, true);
		ledc = new TPU_DIO(false, 5, true);
		ledd = new TPU_DIO(false, 3, true);
		lede = new TPU_DIO(false, 1, true);
		ledf = new TPU_DIO(false, 13, true);
		ledg = new TPU_DIO(false, 15, true);
		leddp = new TPU_DIO(false, 7, true);

		// alle LED ausschalten (true = aus)
		leda.set(true);
		ledb.set(true);
		ledc.set(true);
		ledd.set(true);
		lede.set(true);
		ledf.set(true);
		ledg.set(true);
		leddp.set(true);

	}

	/**
	 * Methode, um das SiebenSegment auszuschalten
	 */
	public void alleLEDAusschalten() {
		leda.set(true);
		ledb.set(true);
		ledc.set(true);
		ledd.set(true);
		lede.set(true);
		ledf.set(true);
		ledg.set(true);
		leddp.set(true);
	}

	/**
	 * Methode, zum den Buchstaben F einzuschalten
	 */
	public void leuchtenF() {
		leda.set(false);
		ledb.set(true);
		ledc.set(true);
		ledd.set(false);
		lede.set(false);
		ledf.set(false);
		ledg.set(false);
		leddp.set(true);
	}

	/**
	 * Methode, zum den Buchstaben F blinken zu lassen
	 */
	public void blinkenF() {
		leda.set(!leda.get());
		ledb.set(true);
		ledc.set(true);
		ledd.set(true);
		lede.set(!leda.get());
		ledf.set(!leda.get());
		ledg.set(!leda.get());
		leddp.set(true);
	}

	/**
	 * Methode, zum den Buchstaben 0 einzuschalten
	 */
	public void leuchten0() {
		leda.set(false);
		ledb.set(false);
		ledc.set(false);
		ledd.set(false);
		lede.set(false);
		ledf.set(false);
		ledg.set(true);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 1 einzuschalten
	 */
	public void leuchten1() {
		leda.set(true);
		ledb.set(false);
		ledc.set(false);
		ledd.set(true);
		lede.set(true);
		ledf.set(true);
		ledg.set(true);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 2 einzuschalten
	 */
	public void leuchten2() {
		leda.set(false);
		ledb.set(false);
		ledc.set(true);
		ledd.set(false);
		lede.set(false);
		ledf.set(true);
		ledg.set(false);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 3 einzuschalten
	 */
	public void leuchten3() {
		leda.set(false);
		ledb.set(false);
		ledc.set(false);
		ledd.set(false);
		lede.set(true);
		ledf.set(true);
		ledg.set(false);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 4 einzuschalten
	 */
	public void leuchten4() {
		leda.set(true);
		ledb.set(false);
		ledc.set(false);
		ledd.set(true);
		lede.set(true);
		ledf.set(false);
		ledg.set(false);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 5 einzuschalten
	 */
	public void leuchten5() {
		leda.set(false);
		ledb.set(true);
		ledc.set(false);
		ledd.set(false);
		lede.set(true);
		ledf.set(false);
		ledg.set(false);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 6 einzuschalten
	 */
	public void leuchten6() {
		leda.set(false);
		ledb.set(true);
		ledc.set(false);
		ledd.set(false);
		lede.set(false);
		ledf.set(false);
		ledg.set(false);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 7 einzuschalten
	 */
	public void leuchten7() {
		leda.set(false);
		ledb.set(false);
		ledc.set(false);
		ledd.set(true);
		lede.set(true);
		ledf.set(true);
		ledg.set(true);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 8 einzuschalten
	 */
	public void leuchten8() {
		leda.set(false);
		ledb.set(false);
		ledc.set(false);
		ledd.set(false);
		lede.set(false);
		ledf.set(false);
		ledg.set(false);
		leddp.set(true);
	}

	/**
	 * Methode, zum die Zahl 9 einzuschalten
	 */
	public void leuchten9() {
		leda.set(false);
		ledb.set(false);
		ledc.set(false);
		ledd.set(false);
		lede.set(true);
		ledf.set(false);
		ledg.set(false);
		leddp.set(true);
	}

	/**
	 * Methode, zum den Bindestrich blinken zu lassen
	 */
	public void strichblinken() {
		ledg.set(!ledg.get());
	}

}
