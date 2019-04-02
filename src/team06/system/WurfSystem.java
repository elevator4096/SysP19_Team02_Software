/**
 * @author Phil
 * @version 2019.03.22
*/

package team06.system;

import team06.Instanzen;
import team06.Variablen;

public class WurfSystem {

	public WurfSystem() {

		
		setdrehzahl(0);
		System.out.println("Wurfsystem_gestartet");

	}

	/**
	 * Speed für Wurfmotoren setzen
	 */
	public void setdrehzahl(float drehzahl) {
		Instanzen.sMSC_WurfMotor1.setdrehzahl(drehzahl);
		//Instanzen.sMSC_WurfMotor2.setdrehzahl(drehzahl);
	}

	/**
	 * Wurfmotoren Starten
	 */
	public void motorstarten() {
		Instanzen.sMSC_WurfMotor1.motorstarten();
		//Instanzen.sMSC_WurfMotor2.motorstarten();
		
		

	}

	static {
	}

}
