/**
 * @author Phil
 * @version 2019.03.22
*/

package team06.system;

import team06.Instanzen;
import team06.Variablen;

public class WurfSystem {

	public WurfSystem() {

	}

	/**
	 * Speed für Wurfmotoren setzen
	 */
	public void setdrehzahl(float drehzahl) {
		Instanzen.sMSC_WurfMotor1.setdrehzahl(drehzahl);
		Instanzen.sMSC_WurfMotor2.setdrehzahl(-drehzahl);
	}

	/**
	 * Wurfmotoren Starten
	 */
	public void motorstarten() {
		Instanzen.sMSC_WurfMotor1.motorstarten();
		Instanzen.sMSC_WurfMotor2.motorstarten();
	}

	/**
	 * Wurfmotoren passgeschwindigkeit
	 */
	public void setspeedpassgegner() {
		setdrehzahl(Variablen.speedpassgegner);
	}
	
	/**
	 * Wurfmotoren Nullspeed
	 */
	public void nullwerfen() {
		setdrehzahl(0);
	}
	
	/**
	 * Ball wird losgelassen
	 */
	public void servooffen() {
		Instanzen.servoMotor.servooffen();
	}

	/**
	 * Ball wird gesperrt
	 */
	public void servogeschlossen() {
		Instanzen.servoMotor.servogeschlossen();
	}


}
