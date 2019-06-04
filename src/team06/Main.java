/**
 * @Author Phil
 * @version 2019.03.26
 */
package team06;

import static team06.Zustand.*;

import java.io.PrintStream;

import com.sun.org.apache.xpath.internal.operations.Variable;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.ZustandWifi;
import team06.system.OrientierSystem;

public class Main extends Task {

	private static Main task;
	public Instanzen instanz;
	private Zustand zustand = STARTZUSTAND;
	private Zustand vorruecken = START_1;
	private Zustand fangposition = START_1;
	private Zustand wurf_kurz = START_1;
	private Zustand fangstart = START_1;
	private Zustand bereit = START_1;

	private float umdrehungen = 0; // Temporär für Encoder
	private int countertemporär = 0;

	private boolean gestartet = false;

	/**
	 * Konstruktor der Klasse Main
	 * 
	 * Instanz erzeugen
	 */
	public Main() {
		instanz = new Instanzen();
//		zustand = BEREIT;
	}

	/**
	 * Methode, die zyklisch aufgerufen wird
	 */
	public void action() {
//		if (instanz.wlanSystem.getPartnerState() == 10 && gestartet == false) {
//			zustand = VORRUECKEN;
//			gestartet = true;
//		}

		update();
		spielablauf();

		if (nofActivations % 10 == 0) {
			testsystemout();
		}
	}

	public void testsystemout() {
		System.out.println("test action");
//		System.out.println(Variablen.gegnerlinks);
//		System.out.println(Variablen.gegnerrechts);
//		System.out.println(Variablen.gegnervorne);
//		System.out.println(Variablen.schalterlinks);
//		System.out.println(Variablen.schalterrechts);
		instanz.siebensegment.strichblinken();
//		Instanzen.iRSensor.alleirausgeben();
//		System.out.println(Variablen.schalterrechts);
		System.out.print("gefangen  ---->>");
		System.out.println(Variablen.gefangen);
		System.out.print("geworfen  ---->>");
		System.out.println(Variablen.geworfen);

//		System.out.println(instanz.sMSC_FahrMotorlinks.gibUmdrehungen());
//		System.out.println(instanz.sMSC_FahrMotorrechts.gibUmdrehungen());
	}

	/**
	 * Spielablauf mit Switch/Case
	 */
	public void spielablauf() {

		switch (zustand) {

		case STARTZUSTAND: // Startzustand herstellen
		{
			startzustand();
			break;
		}

		case BEREIT: // Robi ist bereit
		{
			bereit();
			break;
		}

		case WURF_KURZ: // kurzen Pass an Partner vorbereiten
		{
			wurf_kurz();
			break;
		}

		case VORRUECKEN: // in naechsten Spielfeldabschnitt vorruecken
		{
			vorruecken();
			break;
		}

		case FANGPOSITION: // Bereit zum Fangen
		{
			fangposition();
			break;
		}

		case ENDE: // Spiel beendet
		{
			ende();
			break;
		}

		case FEHLER: // Spielabbruch durch Fehler
		{
			fehler();
			break;
		}

//		case POSITION_WECHSELN: // Positionswechsel einleiten
//		{
//			position_wechseln();
//			break;
//		}

//		case RUECKWAERTS_AN_WAND_1: // rueckwaerts an Wand fahren, um neu auszurichten und Fangen vorzubereiten
//		{
//			rueckwaerts_an_wand();
//			break;
//		}

		}
	}

	/**
	 * Methode Startzustand
	 */
	public void startzustand() {
		if (instanz.wlanSystem.getPartnerState() == ZustandWifi.START) {
			zustand = BEREIT;
		}
	}

	public static void geworfen_1() {
		Variablen.gefangen = 1;
	}

	public static void gefangen_1() {
		Variablen.gefangen = 1;
	}

	public static void hatballtrue() {
		Variablen.hatball = true;
	}

	public static void hatballfalse() {
		Variablen.hatball = false;
	}

	/**
	 * Robi ist bereit
	 */
	public void bereit() {

		switch (bereit) {

		case START_1: {
			// Start: hat Ball nicht, geworfen = 0, gefangen = 0 -> fangbereit
			if (Variablen.hatball == false && Variablen.geworfen == 0 && Variablen.gefangen == 0) {
				instanz.wlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);
				bereit = FANGSTART;
			}

			// Start: hat Ball, gefangen = 0 und geworfen = 0 -> schiessen
			if (Variablen.hatball == true && Variablen.geworfen == 0 && Variablen.gefangen == 0) {
				instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
				zustand = WURF_KURZ;
			}

			// Hat Ball gefangen (1) und geworfen = 0, geworfen = 0
			if (Variablen.hatball == true && Variablen.geworfen == 0 && Variablen.gefangen == 1) {
				// instanz.wlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);
				zustand = WURF_KURZ;
			}

			// Hat Ball nicht, gefangen (0), geworfen (1)
			if (Variablen.hatball == false && Variablen.geworfen == 1 && Variablen.gefangen == 0) {
				instanz.wlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);
				bereit = AUFBALLWARTEN;
			}

			// geworfen = 1 und gefangen = 1
			if (Variablen.gefangen == 1 && Variablen.geworfen == 1) {
				bereit = STARTZUSTAND;
			}
			break;
		}

		case FANGSTART: {
			if (Variablen.hatball == true) {
				Variablen.gefangen++;
				instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
				bereit = START_1;
			}
			break;
		}

		case AUFBALLWARTEN: {
			if (Variablen.hatball == true) {
				Variablen.gefangen++;
				bereit = STARTZUSTAND;
			}
			break;
		}

		case STARTZUSTAND: {
			instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
			instanz.fahrSystem.fahrrechtskurve();
			umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
			bereit = NEUNZIGGRADRECHTS;
			break;
		}

		case NEUNZIGGRADRECHTS: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= (umdrehungen - 4.82)) {
				instanz.fahrSystem.fahrretour();
				bereit = RETOUR;
			}
			break;
		}

		case RETOUR: {
			if (Variablen.schalterlinks == true) {
				instanz.sMSC_FahrMotorlinks.setdrehzahl(0);
			}
			if (Variablen.schalterrechts == true) {
				instanz.sMSC_FahrMotorrechts.setdrehzahl(0);
			}

			if (Variablen.schalterlinks == true && Variablen.schalterrechts == true) {
				zustand = VORRUECKEN;
			}
			break;
		}

		}

	}

	/**
	 * Methode, für kurzen Pass an Partner
	 */
	public void wurf_kurz() {
		switch (wurf_kurz) {

		case START_1: {
			instanz.fahrSystem.fahrlinkskurve();
			umdrehungen = instanz.sMSC_FahrMotorlinks.gibUmdrehungen();
			wurf_kurz = NEUNZIGGRADLINKS;
			break;
		}

		case NEUNZIGGRADLINKS: {
			if (instanz.sMSC_FahrMotorlinks.gibUmdrehungen() >= umdrehungen + 4.82) {
				instanz.fahrSystem.fahrnullspeed();
				instanz.wlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);
				countertemporär = 0;
				wurf_kurz = SCHIESSENVORBEREITEN;
			}
			break;
		}

		case SCHIESSENVORBEREITEN: {
			if (instanz.wlanSystem.getPartnerState() == ZustandWifi.FANG_BEREIT) {
				// wurfmotoren einschalten
				wurf_kurz = SCHIESSEN;
			}
			break;
		}

		case SCHIESSEN: {
			countertemporär++;
			if (countertemporär == 20) {
				instanz.servoMotor.servooffen();
			} else if (countertemporär == 80) {
				// wurfmotoren ausschalten
				instanz.servoMotor.servogeschlossen();
				Variablen.geworfen++;
				zustand = BEREIT;
			}
			break;
		}

		}

		// Methode formulieren
	}

	/**
	 * Methode, für langen Pass an Partner
	 */
	public void wurf_lang() {
		// Methode formulieren
	}

	/**
	 * Methode, um Positionswechsel einzuleiten
	 */
	public void position_wechseln() {
		// Methode formulieren
	}

	/**
	 * Methode, um in den naechsten Feldabschnitt vorzuruecken
	 */
	public void vorruecken() {

		switch (vorruecken) {

		case START_1: {
			instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
			instanz.fahrSystem.fahrviertelspeed();
			umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
			vorruecken = POS_1;
			break;
		}

		case POS_1: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= (umdrehungen + 2.35)) {
				instanz.fahrSystem.fahrviertelspeed();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = GEGNER_1;
			}
			break;
		}

		case GEGNER_1: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= (umdrehungen + 6.95)) {
				if (Variablen.gegnerlinks == false) {
					instanz.fahrSystem.fahrlinkskurve();
					umdrehungen = instanz.sMSC_FahrMotorlinks.gibUmdrehungen();
					vorruecken = YFAHREN;
				} else if (Variablen.gegnerlinks == true) {
					vorruecken = WEITERFAHREN;
				}
			}
			break;
		}

		case YFAHREN: {
			if (instanz.sMSC_FahrMotorlinks.gibUmdrehungen() >= umdrehungen + 4.82) {
				instanz.fahrSystem.fahrviertelspeed();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = GEGNERVORNE;
			}
			break;
		}

		case GEGNERVORNE: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 11.35) {
				if (Variablen.gegnervorne == true) {
					vorruecken = NEUNZIGGRADLINKS;
				} else if (Variablen.gegnervorne == false) {
					instanz.fahrSystem.fahrviertelspeed();
					umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
					vorruecken = YFAHREN_2;
				}
			}
			break;
		}

		case NEUNZIGGRADLINKS: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 11.35) {
				instanz.fahrSystem.fahrlinkskurve();
				umdrehungen = instanz.sMSC_FahrMotorlinks.gibUmdrehungen();
				vorruecken = RETOUR_1;
			}
			break;
		}

		case WEITERFAHREN: {
			instanz.fahrSystem.fahrviertelspeed();
			umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
			vorruecken = GEGNER_1;
			break;
		}

		case YFAHREN_2: {
			instanz.fahrSystem.fahrviertelspeed();
			umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
			vorruecken = NEUNZIGGRADRECHTS;
			break;
		}

		case NEUNZIGGRADRECHTS: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 11.35) {
				instanz.fahrSystem.fahrrechtskurve();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = RETOURRECHTS;
			}
		}

		case RETOUR_1: {
			if (instanz.sMSC_FahrMotorlinks.gibUmdrehungen() >= umdrehungen + 4.82) {
				instanz.fahrSystem.fahrretour();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = RUECKWAERTS_AN_WAND_1;
			}
			break;
		}

		case RETOURRECHTS: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= umdrehungen - 4.82) {
				instanz.fahrSystem.fahrretour();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = RUECKWAERTS_AN_WAND_2;
			}
			break;
		}

		case RUECKWAERTS_AN_WAND_1: {
			if (Variablen.schalterlinks == true) {
				instanz.sMSC_FahrMotorlinks.setdrehzahl(0);
			}
			if (Variablen.schalterrechts == true) {
				instanz.sMSC_FahrMotorrechts.setdrehzahl(0);
			}

			if (Variablen.schalterlinks == true && Variablen.schalterrechts == true) {
				vorruecken = START_2;
			}
			break;
		}

		// letzter case
		case RUECKWAERTS_AN_WAND_2: {
			if (Variablen.schalterlinks == true) {
				instanz.sMSC_FahrMotorlinks.setdrehzahl(0);
			}
			if (Variablen.schalterrechts == true) {
				instanz.sMSC_FahrMotorrechts.setdrehzahl(0);
			}

			if (Variablen.schalterlinks == true && Variablen.schalterrechts == true) {
				vorruecken = ENDE;
				zustand = FANGPOSITION;
			}
			break;
		}

		// Schritt 2 ( wenn beim zweiten feld am rand )

		case START_2: {
			instanz.fahrSystem.fahrretour();
			umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
			vorruecken = KURZRETOUR;
			break;
		}

		case KURZRETOUR: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= (umdrehungen - 0.4)) {
				instanz.fahrSystem.fahrviertelspeed();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = KURZVORWÄRTS;
			}
			break;
		}

		case KURZVORWÄRTS: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= (umdrehungen + 2.35)) {
				if (Variablen.gegnerrechts == false) {
					instanz.fahrSystem.fahrrechtskurve();
					umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
					vorruecken = YFAHREN_4;
				} else if (Variablen.gegnerrechts == true) {
					instanz.fahrSystem.fahrviertelspeed();
					umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
					vorruecken = GEGNER_2;
				}
			}
			break;
		}

		case YFAHREN_4: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= (umdrehungen - 4.82)) {
				instanz.fahrSystem.fahrviertelspeed();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = NEUNZIGGRADRECHTS;
			}
			break;
		}

		case GEGNER_2: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= (umdrehungen + 6.95)) {
				if (Variablen.gegnerrechts == false) {
					instanz.fahrSystem.fahrrechtskurve();
					umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
					vorruecken = YFAHREN_3;
				} else if (Variablen.gegnerrechts == true) {
					vorruecken = WEITERFAHREN_2;
				}
			}
			break;
		}

		case YFAHREN_3: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= umdrehungen - 4.82) {
				instanz.fahrSystem.fahrviertelspeed();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = NEUNZIGGRADRECHTS_2;
			}
			break;
		}

		case NEUNZIGGRADRECHTS_2: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 11.1) {
				instanz.fahrSystem.fahrrechtskurve();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				vorruecken = RETOURRECHTS;
			}
			break;
		}

		case WEITERFAHREN_2: {
			instanz.fahrSystem.fahrviertelspeed();
			umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
			vorruecken = GEGNER_2;
			break;
		}
		}
	}

	// Methode formulieren

	/**
	 * Methode, um rueckwaerts an Wand zu fahren und neue Position einzunehmen
	 */
	public void rueckwaerts_an_wand() {
		// Methode formulieren
	}

	/**
	 * Methode, um Ball zu fangen
	 */
	public void fangposition() {

		switch (fangposition) {
		case START_1: {
			instanz.fahrSystem.fahrretour();
			umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
			fangposition = KURZRETOUR;
			break;
		}

		case KURZRETOUR: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= (umdrehungen - 0.4)) {
				instanz.fahrSystem.fahrviertelspeed();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				fangposition = KURZVORWÄRTS;
			}
			break;
		}

		case KURZVORWÄRTS: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= (umdrehungen + 2.35)) {
				instanz.fahrSystem.fahrrechtskurve();
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				fangposition = FANGPOSITION;
			}
			break;
		}

		case FANGPOSITION: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= umdrehungen - 4.82) {
				instanz.fahrSystem.fahrnullspeed();
				instanz.wlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);

				// if irsensor hat ball
				// (zustand drehen)
				umdrehungen = instanz.sMSC_FahrMotorlinks.gibUmdrehungen();
				instanz.fahrSystem.fahrlinkskurve();
				fangposition = SCHIESSPOSITION;
			}
			break;
		}

		case SCHIESSPOSITION: {
			if (instanz.sMSC_FahrMotorlinks.gibUmdrehungen() >= umdrehungen + 9.64) {
				instanz.fahrSystem.fahrnullspeed();
				instanz.wlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);
				fangposition = SCHIESSENVORBEREITEN;
			}
			break;
		}

		case SCHIESSENVORBEREITEN: {
			if (instanz.wlanSystem.getPartnerState() == ZustandWifi.FANG_BEREIT) {
				// wurfmotoren starten
				instanz.servoMotor.servooffen();
				countertemporär = 0;
				fangposition = SCHIESSEN;
			}
			break;
		}

		case SCHIESSEN: {
			countertemporär++;
			if (countertemporär == 80) {
				instanz.servoMotor.servogeschlossen();
				fangposition = ENDE;
				zustand = ENDE;
			}
			break;
		}
		}

		// Methode formulieren
	}

	/**
	 * Methode, Spiel zu beenden
	 */
	public void ende() {
		// Methode formulieren
	}

	/**
	 * Methode, um Fehler zu melden/Spiel abzubrechen
	 */
	public void fehler() {
		// Methode formulieren
	}

	/**
	 * Methode, welche alle Update-Methoden der anderen Klassen aufruft
	 */
	public void update() {
		instanz.orientierSystem.update();
		instanz.wlanSystem.update();
	}

	/**
	 * Task initialisieren/ SCI_OUT
	 */
	static {

		task = new Main();
		task.period = Variablen.TASK_PERIOD;
		Task.install(task);

		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(19200, SCI.NO_PARITY, (short) 8);
		// Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);

		System.out.println("Main Static gestartet");

	}

}
