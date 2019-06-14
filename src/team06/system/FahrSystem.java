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
	 * Fahrmotoren Starten (wird zyklisch vom Motor-Task aufgerufen
	 */
	public void motorstarten() {
		Instanzen.sMSC_FahrMotorlinks.motorstarten();
		Instanzen.sMSC_FahrMotorrechts.motorstarten();

	}

	/**
	 * Fahrmotoren nullspeed
	 */
	public void fahrnullspeed() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl(0);
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl(0);
	}

	/**
	 * Fahrmotoren viertelspeed
	 */
	public void fahrviertelspeed() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-0.7 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (0.7 * Math.PI));
	}

	/**
	 * Fahrmotoren kleinspeed
	 */
	public void fahrkleinspeed() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (0.5 * Math.PI));
	}

	/**
	 * Fahrmotoren retourspeed
	 */
	public void fahrretour() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (0.6 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (-0.6 * Math.PI));
	}

	/**
	 * Fahrmotoren linkskurve
	 */
	public void fahrlinkskurve() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (0.5 * Math.PI));

	}

	/**
	 * Fahrmotoren rechtskurve
	 */
	public void fahrrechtskurve() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (-0.5 * Math.PI));

	}

	/**
	 * Fahrmotoren rechtsbogen
	 */
	public void fahrrechtsbogen() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (0.3 * Math.PI));
	}

	/**
	 * Fahrmotoren linksbogen
	 */
	public void fahrlinksbogen() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (-0.3 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (0.5 * Math.PI));
	}

	/**
	 * Fahrmotoren retourrechtsbogen
	 */
	public void retourrechtsbogen() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (0.5 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (-0.3 * Math.PI));
	}

	/**
	 * Fahrmotoren retourlinksbogen
	 */
	public void retourlinksbogen() {
		Instanzen.sMSC_FahrMotorlinks.setdrehzahl((float) (0.3 * Math.PI));
		Instanzen.sMSC_FahrMotorrechts.setdrehzahl((float) (-0.5 * Math.PI));
	}

}
