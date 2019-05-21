package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_DIO;

public class Siebenseg {

	TPU_DIO leda, ledb, ledc, ledd, lede, ledf, ledg, leddp;

	int counter = 0;

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

	// 7-Segment aus
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

	// "F" leuchten
	public void leuchtenF() {
		leda.set(false);
		ledb.set(true);
		ledc.set(true);
		ledd.set(true);
		lede.set(false);
		ledf.set(false);
		ledg.set(false);
		leddp.set(true);
	}

	// "F" blinken
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

	// "0" leuchten
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

	// "1" leuchten
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

	// "2" leuchten
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

	// "3" leuchten
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

	// "4" leuchten
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

	// "5" leuchten
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

	// "6" leuchten
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

	// "7" leuchten
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

	// "8" leuchten
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

	// "9" leuchten
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

	public void strichblinken() {
		ledg.set(!ledg.get());
	}
	
//	public void action() {
//		leddp.set(!leddp.get()); // LED “toggeln”
//		// led.set(!led.get()); // LED “toggeln”
//
//		// kleines N (n)
//		if (counter == 0) {
//			leda.set(true); // LED ausschalten
//			ledb.set(true);
//			ledc.set(false);
//			ledd.set(true); // LED ausschalten
//			lede.set(false);
//			ledf.set(true);
//			ledg.set(false); // LED ausschalten
//			leddp.set(true);
//		}
//        
//		// E
//		if (counter == 1) {
//			leda.set(false); // LED ausschalten
//			ledb.set(true);
//			ledc.set(true);
//			ledd.set(false); // LED ausschalten
//			lede.set(false);
//			ledf.set(false);
//			ledg.set(false); // LED ausschalten
//			leddp.set(true);
//		}
//
//		// F
//		if (counter == 2) {
//			leda.set(false); // LED ausschalten
//			ledb.set(true);
//			ledc.set(true);
//			ledd.set(true); // LED ausschalten
//			lede.set(false);
//			ledf.set(false);
//			ledg.set(false); // LED ausschalten
//			leddp.set(true);
//		}
//
////		if (counter > 3) {
////			leddp.set(!leddp.get()); // LED “toggeln”
////
////		}
//		counter++;
//
//		if (counter == 5) {
//			counter = 0;
//		}
//
//	}
//
//	public void rblinken() {
//
////		leda.set(!leda.get());
////		ledb.set(!ledb.get());
////		ledc.set(true);
////		ledd.set(!ledd.get());
//		lede.set(!lede.get());
////		ledf.set(!ledf.get());
//		ledg.set(!ledg.get());
////		leddp.set(!leddp.get());
//	}
//
//	public void dleuchten() {
//		leda.set(false); // LED ausschalten
//		ledb.set(false);
//		ledc.set(false);
//		ledd.set(false); // LED ausschalten
//		lede.set(false);
//		ledf.set(false);
////		ledg.set(!ledg.get()); // LED ausschalten
//		leddp.set(false);
//	}
//
	
//
}
