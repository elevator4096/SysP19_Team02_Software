/**
 * @Author Phil
 * @version 2019.03.26
 */
package team06;

import static team06.Zustand.*;
import java.io.PrintStream;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.ZustandWifi;

//import com.sun.org.apache.xpath.internal.operations.Variable;

public class Main extends Task {

	private static Main task;
	public Instanzen instanz;

	private Zustand zustand = STARTZUSTAND;
	private Zustand vorruecken = START_1;
	private Zustand fangposition = START_1;
	private Zustand wurf_kurz = START_1;
//	private Zustand wurf_kurz_2 = START_1;
	private Zustand bereit = START_1;
	private Zustand wurf_lang = START_1;

	private float umdrehungen = 0; // Temporär für Encoder
	private int countertemporär = 0; // Temporär für allerlei

	/**
	 * Konstruktor der Klasse Main
	 * 
	 * Instanz erzeugen
	 */
	public Main() {
		instanz = new Instanzen();
//		zustand = VORRUECKEN; // zum testen
	}

	/**
	 * Methode, die zyklisch aufgerufen wird
	 */
	public void action() {
		update();
		spielablauf();

		if (nofActivations % 10 == 0) {
			testsystemout();
		}
	}

	/**
	 * Testmethoden zum löschen
	 */
	public void testsystemout() {
//		instanz.siebensegment.strichblinken();
//		System.out.println(Variablen.gegnerlinks);
//		System.out.println(Variablen.gegnerrechts);
//		System.out.println(Variablen.gegnervorne);
//		System.out.println(Variablen.schalterlinks);
//		System.out.println(Variablen.schalterrechts);
//		Instanzen.iRSensor.alleirausgeben();
//		System.out.println(Variablen.schalterrechts);
//		System.out.print("gefangen  ---->>");
//		System.out.println(Variablen.gefangen);
//		System.out.print("geworfen  ---->>");
//		System.out.println(Variablen.geworfen);
//		System.out.print("hat ball  ---->>");
//		System.out.println(Variablen.hatball);
//		System.out.print("ir links  ---->>");
//		System.out.println(Variablen.ir_hl);
//		System.out.print("ir rechts  ---->>");
//		System.out.println(Variablen.ir_hr);
//		System.out.println(instanz.iRSensor.distanzlesen(2));
//		System.out.println(Variablen.gegnerlinks);
//		System.out.println(instanz.sMSC_FahrMotorlinks.gibUmdrehungen());
//		System.out.println(instanz.sMSC_FahrMotorrechts.gibUmdrehungen());
	}

	/**
	 * Testmethoden zum löschen
	 */
	public static void geworfen_1() {
		Variablen.gefangen = 1;
	}

	/**
	 * Testmethoden zum löschen
	 */
	public static void gefangen_1() {
		Variablen.gefangen = 1;
	}

	/**
	 * Testmethoden zum löschen
	 */
	public static void hatballtrue() {
		Variablen.hatball = true;
	}

	/**
	 * Testmethoden zum löschen
	 */
	public static void hatballfalse() {
		Variablen.hatball = false;
	}

	/**
	 * Spielablauf mit Switch/Case
	 */
	public void spielablauf() {

		switch (zustand) {

		case STARTZUSTAND: // Startzustand herstellen / auf WlanSignal warten
		{
			startzustand();
			break;
		}

		case BEREIT: // Robi ist bereit, entscheided ob er den Ball am Anfang hat oder nicht, führt
						// den langen und/oder den kurzen pass aus
		{
			bereit();
			break;
		}

		case WURF_KURZ: // kurzen Pass ausführen
		{
			wurf_kurz();
			break;
		}

		case WURF_LANG: // langen Pass ausführen
		{
			wurf_lang();
			break;
		}

		case VORRUECKEN: // 2 Felder Richtung Korb fahren
		{
			vorruecken();
			break;
		}

		case FANGPOSITION: // im 3. Feld den Ball fangen, 180 grad drehen und wieder zum Partner passen
		{
			fangposition();
			break;
		}

		case ENDE: // Spiel beendet
		{
			ende();
			break;
		}

		// für Variante Spielablauf 2
//		case WURF_KURZ_2: // kurzen Pass an Partner vorbereiten
//		{
//			wurf_kurz_2();
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

	/**
	 * Robi ist bereit entscheided ob er am Anfang den Ball hat oder nicht
	 * führt die nötigen Pässe aus
	 * fährt zur Ausgansposition (an der Wand)
	 */
	public void bereit() {

		switch (bereit) {

		case START_1: {
			// Start ohne Ball // oder Hat Ball nicht, geworfen (1), gefangen(1)
			if (Variablen.hatball == false && Variablen.geworfen >= 0 && Variablen.gefangen == 0) {
				instanz.wlanSystem.setOwnState(ZustandWifi.FANG_BEREIT);
				instanz.siebensegment.leuchten1();
				bereit = FANGSTART;
			}

			// Start mit Ball --> Wurf lang
			else if (Variablen.hatball == true && Variablen.geworfen == 0 && Variablen.gefangen == 0) {
				instanz.wlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);
				instanz.siebensegment.leuchten4();
				zustand = WURF_LANG;
			}

			// Hat Ball, geworfen (0), gefangen(1)
			else if (Variablen.hatball == true && Variablen.geworfen >= 0 && Variablen.gefangen == 1) {
				zustand = WURF_KURZ;
			}

			// Hat Ball nicht, geworfen (>=1), gefangen(1)
			else if (Variablen.hatball == false && Variablen.geworfen >= 1 && Variablen.gefangen == 1) {
				bereit = STARTZUSTAND;
			}
			break;
		}

		case FANGSTART: {
			if (Variablen.hatball == true) {
				Variablen.gefangen++;
				instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
				instanz.siebensegment.leuchten2();
				bereit = START_1;
			}
			break;
		}

		case STARTZUSTAND: {
			instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
			instanz.siebensegment.leuchten2();
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
			if (Variablen.ir_hl == false && Variablen.ir_hr == true) {
				instanz.fahrSystem.retourrechtsbogen();
			}

			if (Variablen.ir_hl == true && Variablen.ir_hr == false) {
				instanz.fahrSystem.retourlinksbogen();
			}

			if (Variablen.ir_hl == true && Variablen.ir_hr == true) {
				instanz.fahrSystem.fahrretour();
			}

			if (Variablen.ir_hl == false && Variablen.ir_hr == false) {
				instanz.fahrSystem.fahrretour();
			}

			if (Variablen.schalterlinks == true) {
				instanz.sMSC_FahrMotorlinks.setdrehzahl(0);
			}
			if (Variablen.schalterrechts == true) {
				instanz.sMSC_FahrMotorrechts.setdrehzahl(0);
			}
			if (Variablen.schalterlinks == true && Variablen.schalterrechts == true) {
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				instanz.fahrSystem.fahrretour();
				bereit = KURZRETOUR;

			}
			break;
		}

		case KURZRETOUR: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= (umdrehungen - 0.4)) {
				instanz.fahrSystem.fahrnullspeed();
				zustand = VORRUECKEN;
			}
			break;
		}
		}

	}

	/*
	 * Ablauf für Variante Spielablauf 2
	 * 
	 * switch (bereit) {
	 * 
	 * case START_1: { // Start: hat Ball nicht, geworfen = 0, gefangen = 0 ->
	 * fangbereit if (Variablen.hatball == false && Variablen.geworfen == 0 &&
	 * Variablen.gefangen == 0) {
	 * instanz.wlanSystem.setOwnState(ZustandWifi.FANG_BEREIT); bereit = FANGSTART;
	 * }
	 * 
	 * // Start: hat Ball, gefangen = 0 und geworfen = 0 -> schiessen if
	 * (Variablen.hatball == true && Variablen.geworfen == 0 && Variablen.gefangen
	 * == 0) { instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN); zustand =
	 * WURF_KURZ; }
	 * 
	 * // Hat Ball gefangen (1) und geworfen = 0, geworfen = 0 if (Variablen.hatball
	 * == true && Variablen.geworfen == 0 && Variablen.gefangen == 1) { zustand =
	 * WURF_KURZ; }
	 * 
	 * // Hat Ball nicht, gefangen (0), geworfen (1) if (Variablen.hatball == false
	 * && Variablen.geworfen == 1 && Variablen.gefangen == 0) {
	 * instanz.wlanSystem.setOwnState(ZustandWifi.FANG_BEREIT); bereit =
	 * AUFBALLWARTEN; }
	 * 
	 * // geworfen = 1 und gefangen = 1 if (Variablen.hatball == false &&
	 * Variablen.gefangen == 1 && Variablen.geworfen >= 1) { bereit = STARTZUSTAND;
	 * } break; }
	 * 
	 * case FANGSTART: { if (Variablen.hatball == true) { Variablen.gefangen++;
	 * instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN); bereit = START_1; }
	 * break; }
	 * 
	 * case AUFBALLWARTEN: { if (Variablen.hatball == true) { Variablen.gefangen++;
	 * zustand = WURF_KURZ_2; } break; }
	 * 
	 * case STARTZUSTAND: { instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
	 * instanz.fahrSystem.fahrrechtskurve(); umdrehungen =
	 * instanz.sMSC_FahrMotorrechts.gibUmdrehungen(); bereit = NEUNZIGGRADRECHTS;
	 * break; }
	 * 
	 * case NEUNZIGGRADRECHTS: { if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen()
	 * <= (umdrehungen - 4.82)) { instanz.fahrSystem.fahrretour(); bereit = RETOUR;
	 * } break; }
	 * 
	 * case RETOUR: { if (Variablen.schalterlinks == true) {
	 * instanz.sMSC_FahrMotorlinks.setdrehzahl(0); } if (Variablen.schalterrechts ==
	 * true) { instanz.sMSC_FahrMotorrechts.setdrehzahl(0); }
	 * 
	 * if (Variablen.schalterlinks == true && Variablen.schalterrechts == true) {
	 * zustand = VORRUECKEN; } break; }
	 * 
	 * }
	 */

	/**
	 * Methode, für kurzen Pass an Partner (im Startfeld)
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
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				instanz.fahrSystem.fahrretour();
				wurf_kurz = KURZRETOUR;
			}
			break;
		}

		case KURZRETOUR: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= umdrehungen - 0.9) {
				instanz.fahrSystem.fahrnullspeed();
				instanz.wlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);
				instanz.siebensegment.leuchten4();
				wurf_kurz = SCHIESSENVORBEREITEN;
			}
			break;
		}

		case SCHIESSENVORBEREITEN: {
			if (instanz.wlanSystem.getPartnerState() == ZustandWifi.FANG_BEREIT) {
				instanz.wurfSystem.setspeedpassgegnerkurz();
				countertemporär = 0;
				wurf_kurz = SCHIESSEN;
			}
			break;
		}

		case SCHIESSEN: {
			countertemporär++;
			if (countertemporär == 20) {
				instanz.servoMotor.servooffen();
			} else if (countertemporär == 60) {
				instanz.wurfSystem.setnullspeed();
				instanz.servoMotor.servogeschlossen();
				Variablen.geworfen++;
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				instanz.fahrSystem.fahrkleinspeed();
				wurf_kurz = KURZVORWÄRTS;
			}
			break;
		}

		case KURZVORWÄRTS: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 0.9) {
				instanz.fahrSystem.fahrnullspeed();
				zustand = BEREIT;
			}
			break;
		}

		}

	}

	/**
	 * Methode, für kurzen Pass (Variante Spielablauf 2)
	 */
//	public void wurf_kurz_2() {
//
//		switch (wurf_kurz_2) {
//
//		case START_1: {
//			if (instanz.wlanSystem.getPartnerState() == ZustandWifi.FANG_BEREIT) {
//				instanz.wlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);
//				instanz.wurfSystem.setspeedpassgegner();
//				countertemporär = 0;
//				wurf_kurz_2 = SCHIESSEN;
//			}
//			break;
//		}
//
//		case SCHIESSEN: {
//			countertemporär++;
//			if (countertemporär == 20) {
//				instanz.servoMotor.servooffen();
//			} else if (countertemporär == 80) {
//				instanz.wurfSystem.setnullspeed();
//				instanz.servoMotor.servogeschlossen();
//				Variablen.geworfen++;
//				bereit = START_1;
//				zustand = BEREIT;
//			}
//			break;
//		}
//
//		}
//
//	}

	/**
	 * Methode, für langen Pass an Partner (im Startfeld)
	 */
	public void wurf_lang() {

		switch (wurf_lang) {

		case START_1: {
			if (instanz.wlanSystem.getPartnerState() == ZustandWifi.FANG_BEREIT) {
				countertemporär = 0;
				instanz.wurfSystem.setspeedpassgegnerlang();
				wurf_lang = SCHIESSEN;
			}
			break;
		}

		case SCHIESSEN: {
			countertemporär++;
			if (countertemporär == 20) {
				instanz.servoMotor.servooffen();
			} else if (countertemporär == 80) {
				instanz.wurfSystem.setnullspeed();
				instanz.servoMotor.servogeschlossen();
				Variablen.geworfen++;
				bereit = START_1;
				zustand = BEREIT;
			}
			break;
		}

		}
	}

	/**
	 * Methode, um 2 Felder Richtung Korb zu fahren
	 * Variante 1: Robi kann direkt zwei Felder quer fahren
	 * Variange 2: Robi kann nur ein Feld quer fahren, anschliessend nullt er sich an der gegenüberliegenden Wand
	 * und fähr dann wieder weiter zum 3. Feld
	 * Variante 1+2: Robi fährt bis zur Ausgangsposition (Wand)
	 */
	public void vorruecken() {

		switch (vorruecken) {

		case START_1: {
			instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
			instanz.siebensegment.leuchten2();
			instanz.fahrSystem.fahrviertelspeed();
			umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
			vorruecken = POS_1;
			break;
		}

		case POS_1: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= (umdrehungen + 2.3)) {
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
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 10.9) {
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
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 10.9) {
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
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 10.9) {
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
			if (Variablen.ir_hl == false && Variablen.ir_hr == true) {
				instanz.fahrSystem.retourrechtsbogen();
			}

			if (Variablen.ir_hl == true && Variablen.ir_hr == false) {
				instanz.fahrSystem.retourlinksbogen();
			}

			if (Variablen.ir_hl == true && Variablen.ir_hr == true) {
				instanz.fahrSystem.fahrretour();
			}

			if (Variablen.ir_hl == false && Variablen.ir_hr == false) {
				instanz.fahrSystem.fahrretour();
			}

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
			if (Variablen.ir_hl == false && Variablen.ir_hr == true) {
				instanz.fahrSystem.retourrechtsbogen();
			}

			if (Variablen.ir_hl == true && Variablen.ir_hr == false) {
				instanz.fahrSystem.retourlinksbogen();
			}

			if (Variablen.ir_hl == true && Variablen.ir_hr == true) {
				instanz.fahrSystem.fahrretour();
			}

			if (Variablen.ir_hl == false && Variablen.ir_hr == false) {
				instanz.fahrSystem.fahrretour();
			}

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

		// Schritt 2 ( wenn der Roboter beim zweiten feld an der Wand ist)
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
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= (umdrehungen + 2.3)) {
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
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= umdrehungen + 10.9) {
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

	/**
	 * Robi richtet sich zum Fangen aus
	 * Robi dreht sich 180 grad und fährt ein kleines Stück retour
	 * Robi passt den Ball
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
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() >= (umdrehungen + 2.3)) {
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
				instanz.siebensegment.leuchten1();
			}

			if (Variablen.hatball == true) {
				instanz.wlanSystem.setOwnState(ZustandWifi.FAHREN);
				instanz.siebensegment.leuchten2();
				umdrehungen = instanz.sMSC_FahrMotorlinks.gibUmdrehungen();
				instanz.fahrSystem.fahrlinkskurve();
				fangposition = RETOURGEGNER;
			}
			break;
		}

		case RETOURGEGNER: {
			if (instanz.sMSC_FahrMotorlinks.gibUmdrehungen() >= umdrehungen + 9.64) {
				umdrehungen = instanz.sMSC_FahrMotorrechts.gibUmdrehungen();
				instanz.fahrSystem.fahrretour();
				fangposition = SCHIESSPOSITION;
			}
			break;
		}

		case SCHIESSPOSITION: {
			if (instanz.sMSC_FahrMotorrechts.gibUmdrehungen() <= umdrehungen - 0.9) {
				instanz.fahrSystem.fahrnullspeed();
				instanz.wlanSystem.setOwnState(ZustandWifi.WURF_BEREIT);
				instanz.siebensegment.leuchten4();
				fangposition = SCHIESSENVORBEREITEN;
			}
			break;
		}

		case SCHIESSENVORBEREITEN: {
			if (instanz.wlanSystem.getPartnerState() == ZustandWifi.FANG_BEREIT) {
				instanz.wurfSystem.setspeedpassgegnerkurz();
				countertemporär = 0;
				fangposition = SCHIESSEN;
			}
			break;
		}

		case SCHIESSEN: {
			countertemporär++;
			if (countertemporär == 20) {
				instanz.servoMotor.servooffen();
			}
			if (countertemporär == 60) {
				instanz.servoMotor.servogeschlossen();
				instanz.wurfSystem.setnullspeed();
				fangposition = ENDE;
				instanz.wlanSystem.setOwnState(ZustandWifi.ERROR);
				instanz.siebensegment.leuchtenF();
				zustand = ENDE;
			}
			break;
		}
		}

	}

	/**
	 * Methode, Spiel zu beenden
	 */
	public void ende() {
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
