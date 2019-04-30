package team06.system;

import team06.Instanzen;
import team06.Variablen;

public class OrientierSystem {

	public OrientierSystem() {

	}

	/**
	 * Testen ob Sensor auf Linie oder nicht
	 */
	public boolean aufLinie(int pin) {
		int sensorwert = Instanzen.iRSensor.distanzlesen(pin);
		if (sensorwert < Variablen.aufliniemax && sensorwert > Variablen.aufliniemin) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Alle Sensorwerte ausgegben
	 * 
	 * Achtung Anzahl anpassen
	 */
	public void gibsensorwert() {
		for (int i = 0; i < 7; i++) {
			System.out.print(Instanzen.iRSensor.distanzlesen(i));
			System.out.print("\t");
		}
		System.out.println();
	}

	/**
	 * Hat Ball oder nicht
	 * 
	 * Achtung Pin anpassen
	 */
	public void hatBall() {
		if (Instanzen.iRSensor.distanzlesen(6) < 100) {
			Variablen.hatball = true;
		}
		if (Instanzen.iRSensor.distanzlesen(6) > 150) {
			Variablen.hatball = false;
		}
	}
	
	
	/**
	 * Ist mit beiden Schaltern an der Wand
	 * 
	 * Achtung true/false anpassen
	 */
	public void anWand() {
		if (Instanzen.endSchalter1.schalterzustand() && Instanzen.endSchalter2.schalterzustand()) {
			Variablen.anWand = true;
		}
		if (Instanzen.endSchalter1.schalterzustand() == false && Instanzen.endSchalter2.schalterzustand() == false) {
			Variablen.hatball = false;
		}
	}
	

	/**
	 * Schalterzustand ausgeben
	 */
	public void gibschalterzustand() {
		System.out.print("Schalter 1");
		System.out.println(Instanzen.endSchalter1.schalterzustand());
		System.out.print("Schalter 2");
		System.out.println(Instanzen.endSchalter2.schalterzustand());
	}

	public void update() {
		hatBall();
		anWand();
	}

}
