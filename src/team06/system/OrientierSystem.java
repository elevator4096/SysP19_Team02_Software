package team06.system;

import team06.Instanzen;
import team06.Variablen;

public class OrientierSystem {

	static int[] sensorDistances;

	public OrientierSystem() {

	}

	/**
	 * Methode, um die Variablen Gegnererkennung zu aktualisieren
	 */
	public void tofupdate() {
		sensorDistances = Instanzen.tofSensor.read();
		int val = sensorDistances[2];
		if (val > 20 && val < 80) {
			Variablen.gegnerlinks = true;
		}

		if (val < 20 || val > 80) {
			Variablen.gegnerlinks = false;
		}

		val = sensorDistances[0];
		if (val > 20 && val < 80) {
			Variablen.gegnerrechts = true;
		}

		if (val < 20 || val > 80) {
			Variablen.gegnerrechts = false;
		}

		val = sensorDistances[1];
		if (val > 20 && val < 80) {
			Variablen.gegnervorne = true;
		}

		if (val < 20 || val > 80) {
			Variablen.gegnervorne = false;
		}

	}

	/**
	 * Methode, um die Variablen der Endschalter zu aktualisieren
	 */
	public void endschalterupdate() {
		if (Instanzen.endSchalterlinks.schalterzustand() == false) {
			Variablen.schalterlinks = true;
		}
		if (Instanzen.endSchalterrechts.schalterzustand() == false) {
			Variablen.schalterrechts = true;
		}
		if (Instanzen.endSchalterlinks.schalterzustand() == true) {
			Variablen.schalterlinks = false;
		}
		if (Instanzen.endSchalterrechts.schalterzustand() == true) {
			Variablen.schalterrechts = false;
		}
	}

	
	/**
	 * Methode, um den Ballsensor zu aktualisieren
	 */
	public void ballupdate() {
		if (Instanzen.iRSensor.distanzlesen(2) >= 300) {
			Variablen.hatball = true;
		}

		if (Instanzen.iRSensor.distanzlesen(2) <= 200) {
			Variablen.hatball = false;
		}
	}

	/**
	 * Methode, um die Variablen der Bodensensoren zu aktualisieren
	 */
	public void irupdate() {
		if (Instanzen.iRSensor.distanzlesen(1) <= 50) {
			Variablen.ir_vl = true;
		}
		if (Instanzen.iRSensor.distanzlesen(1) >= 100) {
			Variablen.ir_vl = false;
		}

		if (Instanzen.iRSensor.distanzlesen(3) <= 50) {
			Variablen.ir_vr = true;
		}
		if (Instanzen.iRSensor.distanzlesen(3) >= 100) {
			Variablen.ir_vr = false;
		}
		
		if (Instanzen.iRSensor.distanzlesen(5) <= 100) {
			Variablen.ir_hl = true;
		}
		if (Instanzen.iRSensor.distanzlesen(5) >= 150) {
			Variablen.ir_hl = false;
		}

		if (Instanzen.iRSensor.distanzlesen(6) <= 100) {
			Variablen.ir_hr = true;
		}
		if (Instanzen.iRSensor.distanzlesen(6) >= 150) {
			Variablen.ir_hr = false;
		}
	}

	
	/**
	 * Methode, welche von der Main zyklisch aufgerufen wird
	 */
	public void update() {
		tofupdate();
		endschalterupdate();
		ballupdate();
		irupdate();

	}

}
