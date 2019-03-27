/**
 * @author Phil
 * @version 2019.03.22
*/

package team06.system;

import team06.IO;
import team06.Variablen;

public class WurfSystem implements IO, Variablen {

	private static WurfSystem wurfSystem;

	private WurfSystem() {

		System.out.println("Wurfsystem_gestartet");

	}

	public static WurfSystem getInstance() {
		if (wurfSystem == null) {
			wurfSystem = new WurfSystem();
		}
		return wurfSystem;
	}

	/**
	 * Speed für den Pass setzen
	 */
	public void setspeedpassgegner() {
		SMSC_WurfMotor1.wurfgeschwindigkeit(Variablen.speedpassgegner);
		SMSC_WurfMotor2.wurfgeschwindigkeit(Variablen.speedpassgegner);
	}

	/**
	 * Speed für den Korbschuss setzen
	 */
	public void setspeedkorbschuss() {
		SMSC_WurfMotor1.wurfgeschwindigkeit(Variablen.speedkorbschuss);
		SMSC_WurfMotor2.wurfgeschwindigkeit(Variablen.speedkorbschuss);
	}

	/**
	 * Wurfmotoren Starten
	 */
	public void werfen() {
		SMSC_WurfMotor1.schiessen();
		SMSC_WurfMotor2.schiessen();

	}

	static {
	}

}
