package team06.system;

import team06.Instanzen;
import team06.Variablen;

public class OrientierSystem {
	
	static int[] sensorDistances;	

	public OrientierSystem() {

	}
	
	public void tofupdate() {
		
		sensorDistances = Instanzen.tofSensor.read();
		int val = sensorDistances[0];
		if (val > 20 && val < 80) {
			Variablen.gegnerlinks = true;
		}

		if (val < 20 || val > 80) {
			Variablen.gegnerlinks = false;
		}

		val = sensorDistances[2];
		if (val > 20 && val < 80) {
			Variablen.gegnerrechts = true;
		}

		if (val < 20 || val > 80) {
			Variablen.gegnerrechts = false;
		}

		val = sensorDistances[1];
		if (val > 20 && val < 80) {
			Variablen.gegnervorne = true;
		}

		if (val < 20 || val > 80) {
			Variablen.gegnervorne = false;
		}
		
	}
	
	public void endschalterupdate() {
		if (Instanzen.endSchalterlinks.schalterzustand() == false) {
			Variablen.schalterlinks = true;
		}
		if (Instanzen.endSchalterrechts.schalterzustand() == false) {
			Variablen.schalterrechts = true;
		}
		if (Instanzen.endSchalterlinks.schalterzustand() == true) {
			Variablen.schalterlinks = false;
		}
		if (Instanzen.endSchalterrechts.schalterzustand() == true) {
			Variablen.schalterrechts = false;
		}
	}
	
	

//	/**
//	 * Testen ob Sensor auf Linie oder nicht
//	 */
//	public boolean aufLinie(int pin) {
//		int sensorwert = Instanzen.iRSensor.distanzlesen(pin);
//		if (sensorwert < Variablen.aufliniemax && sensorwert > Variablen.aufliniemin) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * Hat Ball oder nicht
//	 * 
//	 * Achtung Pin anpassen und Wert anpassen
//	 */
//	public void hatBall() {
//		if (Instanzen.iRSensor.distanzlesen(6) < 7) {
//			Variablen.hatball = true;
//		}
//		if (Instanzen.iRSensor.distanzlesen(6) > 12) {
//			Variablen.hatball = false;
//		}
//	}
//
//	/**
//	 * Ist mit einem Schalter an der Wand Ist mit beiden Schaltern an der Wand
//	 * 
//	 * Achtung true/false anpassen
//	 */
//	public void anWand() {
//
//		if (Instanzen.endSchalter1.schalterzustand() == true) {
//			Variablen.schalter1 = true;
//		}
//
//		if (Instanzen.endSchalter1.schalterzustand() == false) {
//			Variablen.schalter1 = false;
//		}
//
//		if (Instanzen.endSchalter2.schalterzustand() == true) {
//			Variablen.schalter2 = true;
//		}
//
//		if (Instanzen.endSchalter2.schalterzustand() == false) {
//			Variablen.schalter2 = false;
//		}
//
//		if (Instanzen.endSchalter1.schalterzustand() == true && Instanzen.endSchalter2.schalterzustand() == true) {
//			Variablen.anWand = true;
//		}
//		if (Instanzen.endSchalter1.schalterzustand() == false && Instanzen.endSchalter2.schalterzustand() == false) {
//			Variablen.anWand = false;
//		}
//	}
//
//	// Testmehode
//	// Alle Sensorwerte ausgeben
//	public void gibsensorwert() {
//		for (int i = 0; i < 7; i++) {
//			System.out.print(Instanzen.iRSensor.distanzlesen(i));
//			System.out.print("\t");
//		}
//		System.out.println();
//	}
//
//	// Testmehode
//	// Schalterzustand ausgeben
//	public void gibschalterzustand() {
//		System.out.print("Schalter 1");
//		System.out.println(Instanzen.endSchalter1.schalterzustand());
//		System.out.print("Schalter 2");
//		System.out.println(Instanzen.endSchalter2.schalterzustand());
//	}

	public void update() {
		tofupdate();
		endschalterupdate();
//		hatBall();
//		anWand();
	}

}
