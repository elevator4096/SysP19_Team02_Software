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
	 * Wurfmotoren passgeschwindigkeit wurf kurz
	 */
	public void setspeedpassgegnerkurz() {
		Instanzen.sMSC_WurfMotor1.setdrehzahl(Variablen.speedpassgegnerkurz);
		Instanzen.sMSC_WurfMotor2.setdrehzahl(-Variablen.speedpassgegnerkurz);
	}
	
	/**
	 * Wurfmotoren passgeschwindigkeit wurf lang
	 */
	public void setspeedpassgegnerlang() {
		Instanzen.sMSC_WurfMotor1.setdrehzahl(Variablen.speedpassgegnerlang);
		Instanzen.sMSC_WurfMotor2.setdrehzahl(-Variablen.speedpassgegnerlang);
	}
	
	/**
	 * Wurfmotoren Nullspeed
	 */
	public void setnullspeed() {
		Instanzen.sMSC_WurfMotor1.setdrehzahl((float) (0));
		Instanzen.sMSC_WurfMotor2.setdrehzahl((float) (0));
	}
	
	/**
	 * Ball wird losgelassen
	 */
	public void servooffen() {
		Instanzen.servoMotor.servooffen();
		Variablen.hatball = false;
	}

	/**
	 * Ball wird gesperrt
	 */
	public void servogeschlossen() {
		Instanzen.servoMotor.servogeschlossen();
	}


}
