/**
 * @author Phil
 * @version 2019.03.22
*/

package team06.system;

import team06.Instanzen;
import team06.Variablen;

public class WurfSystem {

	public WurfSystem() {

		System.out.println("Wurfsystem_gestartet");

	}

	/**
	 * Speed für Wurfmotoren setzen
	 */
	public void setdrehzahl(float drehzahl) {
		Instanzen.sMSC_WurfMotor1.setdrehzahl(drehzahl);
		// Instanzen.sMSC_WurfMotor2.setdrehzahl(drehzahl);
	}

	/**
	 * Wurfmotoren Starten
	 */
	public void motorstarten() {
		Instanzen.sMSC_WurfMotor1.motorstarten();
		// Instanzen.sMSC_WurfMotor2.motorstarten();
	}

	/**
	 * Ball wird losgelassen
	 */
	public void servomotoröffnen() {
		Instanzen.servoMotor.servoupdate(Variablen.hightTimeoffen);
	}

	/**
	 * Ball wird gesperrt
	 */
	public void servomotorschliessen() {
		Instanzen.servoMotor.servoupdate(Variablen.hightTimehalten);
	}

	/**
	 * Methode zum testen, funktioniert nicht
	 */
	public void gibweg() {

		Instanzen.sMSC_WurfMotor1.gibInkrement();

	}

	static {
	}

}
