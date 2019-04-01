/**
 * @author Phil
 * @version 2019.04.01
*/

package team06.system;

import team06.Instanzen;

public class FahrSystem {

	public FahrSystem() {
		System.out.println("Fahrsystem_gestartet");
	}

	/**
	 * Speed für Fahrmotoren setzen
	 */
	public void setdrehzahl(float drehzahl) {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(drehzahl);
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(drehzahl);
	}

	/**
	 * Fahrmotoren Starten
	 */
	public void motorstarten() {
		Instanzen.sMSC_FahrMotorlinks.motorstarten();
		Instanzen.sMSC_FahrMotorrechts.motorstarten();

	}

}
