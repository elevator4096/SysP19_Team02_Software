/**
 * @author Phil
 * @version 2019.03.22
*/

package team06.system;

import team06.Instanzen;
import team06.Variablen;

public class WurfSystem {
	private static int x =0;

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
		Instanzen.sMSC_WurfMotor2.motorstarten();
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

		if(x%5==0) {
		System.out.print("Motor 1     >");
		System.out.println(Instanzen.sMSC_WurfMotor1.gibInkrement());
		System.out.print("Motor 2     >");
		System.out.println(Instanzen.sMSC_WurfMotor2.gibInkrement());
		}
		if(Instanzen.sMSC_WurfMotor2.gibInkrement() == 5) {
			setdrehzahl(0);
		}
		x++;

	}

	static {
	}

}
