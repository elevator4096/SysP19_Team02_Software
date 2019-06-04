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
	
	public static void fahrnullspeed() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(0);
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(0);
	}

	public static void fahrviertelspeed() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (0.5 * Math.PI));
	}

	public static void fahrhalbspeed() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-2 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (2 * Math.PI));
	}

	public static void fahrmaxspeed() {

		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-4 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (4 * Math.PI));

	}

	public static void fahrretour() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (-0.5 * Math.PI));
	}

	public static void fahrlinkskurve() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (0.5 * Math.PI));

	}

	public static void fahrrechtskurve() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (-0.5 * Math.PI));

	}

	public static void retourrechtsbogen() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (0.3 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (-0.5 * Math.PI));
	}

	public static void retourlinksbogen() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (-0.3 * Math.PI));
	}
	
	
	
	
	
	

//	/**
//	 * Speed für Fahrmotoren setzen
//	 */
//	public void setdrehzahl(float drehzahl) {
//		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(-drehzahl);
//		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(drehzahl);
//	}
//
//	/**
//	 * Drehung Links
//	 */
//	public void linkskurve() {
//		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(Variablen.speedmotorinnen);
//		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(Variablen.speedmotorausen);
//	}
//
//	/**
//	 * Drehung Rechts
//	 */
//	public void rechtskurve() {
//		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(-Variablen.speedmotorausen);
//		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(-Variablen.speedmotorinnen);
//	}
//	
//	/**
//	 * Rückwärts fahren
//	 */
//	public void retour() {
//		setdrehzahl(Variablen.speedrückwärts);
//	}
//
//	/**
//	 * Geradeaus fahren
//	 */
//	public void geradeaus() {
//		setdrehzahl(Variablen.speedgeradeaus);
//	}
//	
//	/**
//	 * Null fahren
//	 */
//	public void nullfahren() {
//		setdrehzahl(0);
//	}
	
}
