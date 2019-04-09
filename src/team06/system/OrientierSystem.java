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
		for (int i = 0; i < 4; i++) {
			System.out.print(Instanzen.iRSensor.distanzlesen(i));
			System.out.print("\t");
		}
		System.out.println();
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

}
