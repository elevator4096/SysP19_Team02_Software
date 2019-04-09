/**
 * @author Phil
 * @version 2019.04.01
*/

package team06.system;

import team06.Instanzen;
import team06.Variablen;

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

	/**
	 * Drehung Links
	 */
	public void linkskurve() {

		while (Instanzen.sMSC_FahrMotorrechts.gibInkrement() <= Variablen.kurve90grad) {
			Instanzen.sMSC_FahrMotorlinks.setdrehzahl(Variablen.speedmotorinnen);
			Instanzen.sMSC_FahrMotorrechts.setdrehzahl(Variablen.speedmotorausen);
		}
		setdrehzahl(0);
	}

	/**
	 * Drehung Rechts
	 */
	public void rechtskurve() {

		while (Instanzen.sMSC_FahrMotorlinks.gibInkrement() <= Variablen.kurve90grad) {
			Instanzen.sMSC_FahrMotorlinks.setdrehzahl(Variablen.speedmotorausen);
			Instanzen.sMSC_FahrMotorrechts.setdrehzahl(Variablen.speedmotorinnen);
		}
		setdrehzahl(0);

	}

	/**
	 * Anzahl Inkrements geraudeausfahren
	 */
	public void geradeaus(int inkrement) {
		while (Instanzen.sMSC_FahrMotorlinks.gibInkrement() <= inkrement) {
			Instanzen.sMSC_FahrMotorlinks.setdrehzahl(Variablen.speedgeradeaus);
			Instanzen.sMSC_FahrMotorrechts.setdrehzahl(Variablen.speedgeradeaus);
		}
		setdrehzahl(0);
	}

	/**
	 * Anzahl Inkrements rückwärts
	 */
	public void rückwärts(int inkrement) {
		while (Instanzen.sMSC_FahrMotorlinks.gibInkrement() <= inkrement) {
			Instanzen.sMSC_FahrMotorlinks.setdrehzahl(Variablen.speedrückwärts);
			Instanzen.sMSC_FahrMotorrechts.setdrehzahl(Variablen.speedrückwärts);
		}
		setdrehzahl(0);
	}

}
