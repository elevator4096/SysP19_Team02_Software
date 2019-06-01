package team06.phil;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.mpc555.driver.VL6180X;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.WlanSystem;
import exchange.ZustandWifi;
import team06.aurelia.Main;
import team06.aurelia.Zustand;
import team06.testprogramme.ToFSensorDemoParv;
import team06.Endschalter;
import team06.MotorSMSC;
import team06.PWM_Servo;
import team06.Variablen;
import static team06.aurelia.Zustand.*;

public class Test_Main_M3 extends Task {

	static MotorSMSC_M3 wurfmotor3;
	static MotorSMSC_M3 wurfmotor4;

	static MotorSMSC_M3 fahrmotor1;
	static MotorSMSC_M3 fahrmotor2;

	static Endschalter endschalter;

	static PWM_Servo_M3 servo;

	static TPUDIO_M3 sieben;

	static DistSensorTest_M3 dist;

	static Endschalter_M3 schalter1, schalter2;

	static MPIOSM_DIO wlandio;

	static WlanSystem wlanSystem;

//	static ToFSensor_M3 tof;

	VL6180X tofvlx;

//	static ToFSensorDemoParv tof;

	int counter = 0;
	int zähler = 0;

	static Zustand zustand = STARTZUSTAND;
	static int start = 0;

	int linksbogen = 1;
	static int geradeaus = 2;

	static boolean gestartet = false;
	static boolean schritt2 = false;

	static boolean gegnerlinks = false;
	static boolean gegnerrechts = false;
	static boolean gegnervorne = false;

	static boolean zweifelder = false;

	static float umdrehungen;
	static int anzahlgegner = 2;

	static int partnerstate = 0;

	static int countertemporär = 0;

	static boolean schwarz = false;

	static boolean schalterlinks = false;
	static boolean schalterrechts = false;

	static boolean fahrenrechts = false;
	static boolean fahrenlinks = true;

	static int[] sensorDistances;
	final int numberOfSensors = 3;
	final int resetPin = 9;

	static boolean ir_1 = false; // schwarz = true
	static boolean ir_2 = false; // schwarz = true

	public Test_Main_M3() {

		tofvlx = new VL6180X(numberOfSensors, resetPin);

		fahrmotor1 = new MotorSMSC_M3(0.01f, 5, 7, true, 8, true, 256, 12f, 91f / 1f, 1f, 0.008f);
		fahrmotor2 = new MotorSMSC_M3(0.01f, 4, 6, true, 10, true, 256, 12f, 91f / 1f, 1f, 0.008f);

//		wurfmotor3 = new MotorSMSC_M3(0.01f, 1, 3, true, 12, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);
//		wurfmotor4 = new MotorSMSC_M3(0.01f, 0, 2, true, 14, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);

		servo = new PWM_Servo_M3(false, 4, Variablen.pwmPeriod, Variablen.highTime); // anpassen

		sieben = new TPUDIO_M3();

		dist = new DistSensorTest_M3();

		schalter1 = new Endschalter_M3(6, false);
		schalter2 = new Endschalter_M3(7, false);

		wlandio = new MPIOSM_DIO(8, true);

		wlanSystem = WlanSystem.getInstance(wlandio);

//		System.out.println("action anfang");
//		tof = new ToFSensor_M3();
//		System.out.println("action ende");

	}

	public void action() {

		if (nofActivations % 150 == 0) {
			sensorDistances = tofvlx.read();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
			int val = sensorDistances[0];
			System.out.print("RESULT__RANGE_VAL:\tSens0: ");
			System.out.printHex((val & 0xFF));
			System.out.print("\t");
			if (val < 0)
				val += 256;
			System.out.println((val));
			System.out.println("-------------------------------------------");
			val = sensorDistances[1];
			System.out.print("RESULT__RANGE_VAL:\tSens1: ");
			System.out.printHex((val & 0xFF));
			System.out.print("\t");
			if (val < 0)
				val += 256;
			System.out.println((val));
			System.out.println("-------------------------------------------");
			val = sensorDistances[2];
			System.out.print("RESULT__RANGE_VAL:\tSens2: ");
			System.out.printHex((val & 0xFF));
			System.out.print("\t");
			if (val < 0)
				val += 256;
			System.out.println((val));
			System.out.println("-------------------------------------------");
		}

////		if (schalterlinks == true) {
////			fahrnullspeed();
////
////		}
//
		
//		if (partnerstate == 5 && gestartet == false) {
//		zustand = START;
//		gestartet = true;
//	}
		
		
		endschalterupdate();
		servo.update();
////		irupdate();
//
//		if (partnerstate == 5 && gestartet == false) {
//			zustand = START;
//			gestartet = true;
//		}
//
//		if (partnerstate == 5) {
//			fahrenlinks();
//		}
//
//		if (partnerstate == 6 && schritt2 == false) {
//			zustand = START;
//			schritt2 = true;
//		}
//
//		if (partnerstate == 6) {
//			fahrenrechts();
//		}
//
		if (nofActivations % 150 == 0) {
			sieben.strichblinken();
			tofupdate();
			System.out.println(fahrenlinks);
			System.out.println(fahrenrechts);
//			dist.alleirausgeben();
//			tof.tofausgeben();		
//			System.out.print("partnerstate ------>");
//			System.out.println(partnerstate);
//			System.out.print("sensor 1 ------>");
//			System.out.println(dist.gibdist(1));
//			System.out.print("sensor 2 ------>");
//			System.out.println(dist.gibdist(2));
			// System.out.println(anzahlgegner);
		}

		if (nofActivations % 50 == 0) {
//			irsensor();
			tofupdate();
			wlanSystem.update();
			partnerstate = wlanSystem.getPartnerState();

		}

//		wurfmotor3.motorstarten();
//		wurfmotor4.motorstarten();

		fahrmotor1.motorstarten();
		fahrmotor2.motorstarten();

//		schiesenmitwlan();
//		testfahren();
//		neunziggradtest();
//		yachse();
//		fahrenlinks();
//		fahrenrechts();
//		fangundschiess();

		if (fahrenlinks == true) {
			fahrenlinks();
		}

		if (fahrenrechts == true) {
			fahrenrechts();
		}

		if (nofActivations % 150 == 0) {
			System.out.print("Schalter links--->>>>>");
			System.out.print(schalterlinks);
			System.out.print("   Schalter rechts--->>>>>");
			System.out.println(schalterrechts);
		}
//
//		if (nofActivations % 150 == 0) {
//			System.out.print("Fahrmotor 1: Umdrehungen >");
//			System.out.print(fahrmotor1.gibUmdrehungen());
//			System.out.print("        Geschwindigkeit in 1/min  >");
//			System.out.println(fahrmotor1.gibGeschwindigkeit());
//
//			System.out.print("Fahrmotor 2: Umdrehungen >");
//			System.out.print(fahrmotor2.gibUmdrehungen());
//			System.out.print("        Geschwindigkeit in 1/min  >");
//			System.out.println(fahrmotor2.gibGeschwindigkeit());
//		}

//		if (nofActivations % 150 == 0) {
//			System.out.print("Wurfmotor 3: Umdrehungen >");
//			System.out.print(wurfmotor3.gibUmdrehungen());
//			System.out.print("        Geschwindigkeit in 1/min  >");
//			System.out.println(wurfmotor3.gibGeschwindigkeit());
//
//			System.out.print("Wurfmotor 4: Umdrehungen >");
//			System.out.print(wurfmotor4.gibUmdrehungen());
//			System.out.print("        Geschwindigkeit in 1/min  >");
//			System.out.println(wurfmotor4.gibGeschwindigkeit());
//		}

	}

	public void retourmitir() {

		switch (zustand) {

		case START: {
			fahrretour();
			zustand = VORRUECKEN;
			break;
		}

		case VORRUECKEN: {
			if (ir_1 == false) {
				retourrechtsbogen();
			}
			if (ir_2 == false) {
				retourlinksbogen();
			}

			if (ir_1 == true && ir_2 == true) {
				fahrretour();
			}

			if (schalterlinks == true) {
				fahrmotor1.setdrehzahl(0);
			}
			if (schalterrechts == true) {
				fahrmotor2.setdrehzahl(0);
			}

			if (schalterlinks == true && schalterrechts == true) {
				zustand = ENDE;
			}
			break;
		}

		case ENDE: {
			fahrnullspeed();
			zustand = STARTZUSTAND;
			break;
		}
		}
	}

	public void yachse() {

		switch (zustand) {

		case START: {
			fahrviertelspeed();
			zustand = VORRUECKEN;
			umdrehungen = fahrmotor2.gibUmdrehungen();
			break;
		}

		case VORRUECKEN: {
			if (fahrmotor2.gibUmdrehungen() >= (umdrehungen + 9.4)) {
				fahrnullspeed();
				zustand = ENDE;
				break;
			}
			break;
		}

		case ENDE: {
			fahrnullspeed();
			zustand = STARTZUSTAND;
			break;
		}
		}
	}

	public void neunziggradtest() {

		switch (zustand) {

		case START: {
			fahrlinkskurve();
			zustand = VORRUECKEN;
			umdrehungen = fahrmotor1.gibUmdrehungen();
			break;
		}

		case VORRUECKEN: {
			if (fahrmotor1.gibUmdrehungen() >= (umdrehungen + 4.82)) {
				fahrnullspeed();
				zustand = ENDE;
				break;
			}
			break;
		}

		case ENDE: {
			fahrnullspeed();
			zustand = STARTZUSTAND;
			break;
		}
		}
	}

	public void schiesenmitwlan() {

		switch (zustand) {
		case START: {
			if (schwarz == true && partnerstate == 3) {
//			if (dist.gibdist(1) <= 10 && partnerstate == 3) {
				wurfspeedhalb();
				servo.servooffen();
				zustand = WURF_KURZ;
			}
			break;
		}

		case WURF_KURZ: {
			countertemporär++;
			if (countertemporär >= 500) {
				servo.servogeschlossen();
				wurfnullspeed();
				countertemporär = 0;
				zustand = STARTZUSTAND;
			}
			break;
		}
		}
	}

	public void fangundschiess() {

		switch (zustand) {
		case START: {
			fahrretour();
			umdrehungen = fahrmotor2.gibUmdrehungen();
			zustand = VORRUECKEN;
			break;
		}

		case VORRUECKEN: {
			if (fahrmotor2.gibUmdrehungen() <= (umdrehungen - 0.4)) {
				fahrviertelspeed();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = NEUNZIGGRADRECHTS;
			}
			break;
		}

		case NEUNZIGGRADRECHTS: {
			if (fahrmotor2.gibUmdrehungen() >= (umdrehungen + 2.35)) {
				fahrrechtskurve();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = FANGPOSITION;
			}
			break;
		}

		case FANGPOSITION: {
			if (fahrmotor2.gibUmdrehungen() <= (umdrehungen - 4.82)) {
				fahrnullspeed();
				zustand = HUNDERTACHZIGLINKS;
			}
			break;
		}

		case HUNDERTACHZIGLINKS: {
			// if(ir-sensor = true) hat ball
			fahrlinkskurve();
			umdrehungen = fahrmotor1.gibUmdrehungen();
			zustand = SCHIESSPOSITION;
			break;
		}

		case SCHIESSPOSITION: {
			if (fahrmotor1.gibUmdrehungen() >= (umdrehungen + 9.64)) {
				// ++ if wlan signal methode schiessen
				fahrnullspeed();
				zustand = STARTZUSTAND;
			}
			break;
		}
		}
	}

	public void fahrenrechts() {

		switch (zustand) {

		case START: {
			fahrretour();
			umdrehungen = fahrmotor2.gibUmdrehungen();
			zustand = KURZRETOUR;
			break;
		}

		case KURZRETOUR: {
			if (fahrmotor2.gibUmdrehungen() <= (umdrehungen - 0.4)) {
				fahrviertelspeed();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = VORWÄRTS;
			}
			break;
		}

		case VORWÄRTS: {
			if (fahrmotor2.gibUmdrehungen() >= (umdrehungen + 2.35)) {
				if (gegnerrechts == false) {
					zustand = NEUNZIGGRADRECHTS;
				} else if (gegnerrechts == true) {
					fahrviertelspeed();
					umdrehungen = fahrmotor2.gibUmdrehungen();
					zustand = VORRUECKEN;
				}
			}
			break;
		}

		case VORRUECKEN: {
			if (fahrmotor2.gibUmdrehungen() >= (umdrehungen + 7.25)) {
				if (gegnerrechts == false) {
					zustand = NEUNZIGGRADRECHTS;
					System.out.println("case1");
				} else if (gegnerrechts == true) {
					System.out.println("case2");
					anzahlgegner--;
					zustand = WEITERFAHREN;
				}
			}
			break;
		}
		case NEUNZIGGRADRECHTS: {
			fahrrechtskurve();
			umdrehungen = fahrmotor2.gibUmdrehungen();
			zustand = YFAHREN;
			break;
		}

		case YFAHREN: {
			if (fahrmotor2.gibUmdrehungen() <= umdrehungen - 4.82) {
				fahrviertelspeed();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = NEUNZIGGRADRECHTS_2;
			}
			break;
		}

		case NEUNZIGGRADRECHTS_2: {
			if (fahrmotor2.gibUmdrehungen() >= umdrehungen + 11.1) {
				fahrrechtskurve();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = RETOUR;
			}
			break;
		}

		case WEITERFAHREN: {
			fahrviertelspeed();
			umdrehungen = fahrmotor2.gibUmdrehungen();
			zustand = VORRUECKEN;
			break;
		}

		case RETOUR: {
			if (fahrmotor2.gibUmdrehungen() <= umdrehungen - 4.82) {
				fahrretour();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = RUECKWAERTS_AN_WAND;
			}
			break;
		}

//		// Mit inkrement retour
//		case RUECKWAERTS_AN_WAND: {
//			if (fahrmotor2.gibUmdrehungen() <= (umdrehungen - 7.5 * anzahlgegner)) {
//				fahrnullspeed();
//				anzahlgegner = 2;
//				zustand = ENDE;
//			}
//			break;

		// mit taster retour
		case RUECKWAERTS_AN_WAND: {
			if (schalterlinks == true) {
				fahrmotor1.setdrehzahl(0);
			}
			if (schalterrechts == true) {
				fahrmotor2.setdrehzahl(0);
			}

			if (schalterlinks == true && schalterrechts == true) {
				anzahlgegner = 2;
				zustand = ENDE;
				fahrenlinks = true;
				fahrenrechts = false;
			}
			break;

		}

		}
	}

	public void fahrenlinks() {
		switch (zustand) {

		case START: {
			fahrviertelspeed();
			umdrehungen = fahrmotor2.gibUmdrehungen();
			zustand = STARTPOSITION;
			break;
		}

		case STARTPOSITION: {
			if (fahrmotor2.gibUmdrehungen() >= (umdrehungen + 2.35)) {
				fahrviertelspeed();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = VORRUECKEN;
			}
		}

		case VORRUECKEN: {
			if (fahrmotor2.gibUmdrehungen() >= (umdrehungen + 7.25)) {
				if (gegnerlinks == false) {
//					System.out.print("Tof -->");
//					System.out.println(gegner);
					zustand = NEUNZIGGRADLINKS;
				} else if (gegnerlinks == true) {
					anzahlgegner--;
					zustand = WEITERFAHREN;
				}
			}
			break;
		}
		case NEUNZIGGRADLINKS: {
			fahrlinkskurve();
			umdrehungen = fahrmotor1.gibUmdrehungen();
			zustand = YFAHREN;
			break;
		}

		case YFAHREN: {
			if (fahrmotor1.gibUmdrehungen() >= umdrehungen + 4.82) {
				fahrviertelspeed();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = GEGNERVORNE;
			}
			break;
		}

		case GEGNERVORNE: {
			if (fahrmotor2.gibUmdrehungen() >= umdrehungen + 11.35) {
				if (gegnervorne == true) {
					zustand = NEUNZIGGRADLINKS_2;
				} else if (gegnervorne == false) {
					zweifelder = true;
					fahrviertelspeed();
					umdrehungen = fahrmotor2.gibUmdrehungen();
					zustand = YFAHREN_2;
				}
			}
			break;

		}

		case NEUNZIGGRADLINKS_2: {
			if (fahrmotor2.gibUmdrehungen() >= umdrehungen + 11.35) {
				fahrlinkskurve();
				umdrehungen = fahrmotor1.gibUmdrehungen();
				zustand = RETOUR;
			}
			break;
		}

		case WEITERFAHREN: {
			fahrviertelspeed();
			umdrehungen = fahrmotor2.gibUmdrehungen();
			zustand = VORRUECKEN;
			break;
		}

		case YFAHREN_2: {
			fahrviertelspeed();
			umdrehungen = fahrmotor2.gibUmdrehungen();
			if (anzahlgegner == 2) {
				zustand = NEUNZIGGRADRECHTS;
			}

			if (anzahlgegner < 2) {
				zustand = NEUNZIGGRADRECHTS;
			}

			break;
		}

		case NEUNZIGGRADRECHTS: {
			if (fahrmotor2.gibUmdrehungen() >= umdrehungen + 11.35) {
				fahrrechtskurve();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = RETOURRECHTS;
			}
		}

		case RETOUR: {
			if (fahrmotor1.gibUmdrehungen() >= umdrehungen + 4.82) {
				fahrretour();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = RUECKWAERTS_AN_WAND;
			}
			break;
		}

		case RETOURRECHTS: {
			if (fahrmotor2.gibUmdrehungen() <= umdrehungen - 4.82) {
				fahrretour();
				umdrehungen = fahrmotor2.gibUmdrehungen();
				zustand = RUECKWAERTS_AN_WAND;
			}
			break;
		}

		// mit inkrement retour
//		case RUECKWAERTS_AN_WAND: {
//			if (fahrmotor2.gibUmdrehungen() <= (umdrehungen - 7.5 * anzahlgegner)) {
//				fahrnullspeed();
//				anzahlgegner = 2;
//				zustand = ENDE;
//			}
//			break;
//
//		}
//
//		}
//	}

		case RUECKWAERTS_AN_WAND: {
			if (schalterlinks == true) {
				fahrmotor1.setdrehzahl(0);
			}
			if (schalterrechts == true) {
				fahrmotor2.setdrehzahl(0);
			}

			if (schalterlinks == true && schalterrechts == true) {
				anzahlgegner = 2;
				fahrenrechts = true;
				fahrenlinks = false;
				zustand = START;
			}
			break;

		}

		}
	}

	public static void testneunziggrad() {
		zustand = START;
	}

	public static void teststarten() {
		zustand = WURF_KURZ;
	}

	public void endschalterupdate() {
		if (schalter2.schalterzustand() == false) {
			schalterlinks = true;
		}
		if (schalter1.schalterzustand() == false) {
			schalterrechts = true;
		}
		if (schalter2.schalterzustand() == true) {
			schalterlinks = false;
		}
		if (schalter1.schalterzustand() == true) {
			schalterrechts = false;
		}
	}

	/**
	 * i & wert anpassen >= 11 weiss <= 7 schwarz schwarz = true
	 */
	public void irupdate() {
		if (dist.gibdist(1) >= 11) {
			ir_1 = false;
		}
		if (dist.gibdist(1) <= 7) {
			ir_1 = true;
		}
		if (dist.gibdist(2) >= 10) {
			ir_2 = false;
		}
		if (dist.gibdist(2) <= 7) {
			ir_2 = true;
		}
	}

	public void tofupdate() {
//		sensorDistances = tof.read();
//		System.out.println(sensorDistances[0]);
//		System.out.println(sensorDistances[1]);
//		System.out.println(sensorDistances[2]);

		sensorDistances = tofvlx.read();
		int val = sensorDistances[0];
		if (val > 20 && val < 80) {
			gegnerlinks = true;
		}

		if (val < 20 || val > 80) {
			gegnerlinks = false;
		}

		val = sensorDistances[2];
		if (val > 20 && val < 80) {
			gegnerrechts = true;
		}

		if (val < 20 || val > 80) {
			gegnerrechts = false;
		}

		val = sensorDistances[1];
		if (val > 20 && val < 80) {
			gegnervorne = true;
		}

		if (val < 20 || val > 80) {
			gegnervorne = false;
		}
	}

	/**
	 * i & wert anpassen
	 */
	public void irsensor() {
		if (dist.gibdist(1) < 8) {
			schwarz = true;
		}
		if (dist.gibdist(1) > 12) {
			schwarz = false;
		}
	}

	public static void fahrnullspeed() {
		fahrmotor1.setdrehzahl(0);
		fahrmotor2.setdrehzahl(0);
	}

	public static void fahrviertelspeed() {
		fahrmotor1.setdrehzahl((float) (-0.5 * Math.PI));
		fahrmotor2.setdrehzahl((float) (0.5 * Math.PI));
	}

	public static void fahrhalbspeed() {
		fahrmotor1.setdrehzahl((float) (-2 * Math.PI));
		fahrmotor2.setdrehzahl((float) (2 * Math.PI));
	}

	public static void fahrmaxspeed() {

		fahrmotor1.setdrehzahl((float) (-4 * Math.PI));
		fahrmotor2.setdrehzahl((float) (4 * Math.PI));

	}

	public static void fahrretour() {
		fahrmotor1.setdrehzahl((float) (0.5 * Math.PI));
		fahrmotor2.setdrehzahl((float) (-0.5 * Math.PI));
	}

	public static void fahrlinkskurve() {
		fahrmotor1.setdrehzahl((float) (0.5 * Math.PI));
		fahrmotor2.setdrehzahl((float) (0.5 * Math.PI));

	}

	public static void fahrrechtskurve() {
		fahrmotor1.setdrehzahl((float) (-0.5 * Math.PI));
		fahrmotor2.setdrehzahl((float) (-0.5 * Math.PI));

	}

	public static void retourrechtsbogen() {
		fahrmotor1.setdrehzahl((float) (0.3 * Math.PI));
		fahrmotor2.setdrehzahl((float) (-0.5 * Math.PI));
	}

	public static void retourlinksbogen() {
		fahrmotor1.setdrehzahl((float) (0.5 * Math.PI));
		fahrmotor2.setdrehzahl((float) (-0.3 * Math.PI));
	}

	public static void wurfnullspeed() {
		wurfmotor3.setdrehzahl(0);
		wurfmotor4.setdrehzahl(0);
	}

	public static void wurfspeedmax() {
		wurfmotor3.setdrehzahl((float) (93 * Math.PI));
		wurfmotor4.setdrehzahl((float) (-93 * Math.PI));

	}

	public static void wurfspeedhalb() {
		wurfmotor3.setdrehzahl((float) (100 * Math.PI));
		wurfmotor4.setdrehzahl((float) (-100 * Math.PI));

	}

	public static void servooffen() {
		servo.servooffen();
	}

	public static void servogeschlossen() {
		servo.servogeschlossen();
	}

	static {

		System.out.println("test");
		Task task;

		task = new Test_Main_M3();
		task.period = (int) (0.01f * 1000);
		Task.install(task);

		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(19200, SCI.NO_PARITY, (short) 8);
		// Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);
		System.out.println("static start");

		System.out.println("test");

	}

}
