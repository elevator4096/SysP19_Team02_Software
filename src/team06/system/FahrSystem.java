/**
 * @author Phil
 * @version 2019.04.01
*/

package team06.system;

import team06.Instanzen;
import team06.Variablen;

public class FahrSystem {

	public FahrSystem() {

	}

	/**
	 * Fahrmotoren Starten (action methode)
	 */
	public void motorstarten() {
		Instanzen.sMSC_FahrMotorlinks.motorstarten();
		Instanzen.sMSC_FahrMotorrechts.motorstarten();

	}

	/**
	 * Speed f�r Fahrmotoren setzen
	 */
	public void setdrehzahl(float drehzahl) {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(-drehzahl);
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(drehzahl);
	}

	/**
	 * Drehung Links
	 */
	public void linkskurve() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(Variablen.speedmotorinnen);
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(Variablen.speedmotorausen);
	}

	/**
	 * Drehung Rechts
	 */
	public void rechtskurve() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(-Variablen.speedmotorausen);
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(-Variablen.speedmotorinnen);
	}
	
	/**
	 * R�ckw�rts fahren
	 */
	public void retour() {
		setdrehzahl(Variablen.speedr�ckw�rts);
	}

	/**
	 * Geradeaus fahren
	 */
	public void geradeaus() {
		setdrehzahl(Variablen.speedgeradeaus);
	}
	
	/**
	 * Null fahren
	 */
	public void nullfahren() {
		setdrehzahl(0);
	}
	
}
