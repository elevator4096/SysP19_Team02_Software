package team06.phil;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.WlanSystem;
import team06.aurelia.Main;
import team06.aurelia.Zustand;
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

	static ToFSensor_M3 tof;

	static WlanSystem wlan;

	int counter = 0;
	int zähler = 0;

	static Zustand zustand = STARTZUSTAND;
	static int start = 0;

	int linksbogen = 1;
	static int geradeaus = 2;
	
	static boolean gestartet = false;

	public Test_Main_M3() {

		fahrmotor1 = new MotorSMSC_M3(0.01f, 5, 7, true, 8, true, 256, 12f, 91f / 1f, 1f, 0.008f);
		fahrmotor2 = new MotorSMSC_M3(0.01f, 4, 6, true, 10, true, 256, 12f, 91f / 1f, 1f, 0.008f);

		wurfmotor3 = new MotorSMSC_M3(0.01f, 1, 3, true, 12, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);
		wurfmotor4 = new MotorSMSC_M3(0.01f, 0, 2, true, 14, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);

		servo = new PWM_Servo_M3(false, 4, Variablen.pwmPeriod, Variablen.highTime); // anpassen

		sieben = new TPUDIO_M3();

		dist = new DistSensorTest_M3();

		schalter1 = new Endschalter_M3(6, false);
		schalter2 = new Endschalter_M3(7, false);

//		tof = new ToFSensor_M3();

//		wlan.getInstance(new MPIOSM_DIO(9, true));

	}

	public void action() {
		
		
//		if (nofActivations % 1000 == 0 && gestartet == false) {
//			teststarten();
//			gestartet = true;
//		}

		if (nofActivations % 100 == 0) {
			sieben.strichblinken();
			// dist.alleirausgeben();
		}

		wurfmotor3.motorstarten();
		wurfmotor4.motorstarten();

		fahrmotor1.motorstarten();
		fahrmotor2.motorstarten();

		servo.update();

		testfahren();

		neunziggrad();

//		if (irsensor() == true && nofActivations % 10 == 0) {
//			sieben.dleuchten();
//		}
//
//		if (irsensor() == false && nofActivations % 10 == 0) {
//			sieben.löschen();
//
//		}

//		if (nofActivations % 150 == 0) {
//////			System.out.print("IR Sensor 1    -->");
//////			System.out.println(dist.gibdist(1));
//			System.out.print("zustand    --->>>>>");
//			System.out.println(start);
//////			System.out.print("Schalter 1--->>>>>");
//////			System.out.print(schalter1.schalterzustand());
//////			System.out.print("   Schalter 2--->>>>>");
//////			System.out.println(schalter2.schalterzustand());
////			System.out.println(zähler);
////
//		}
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

//		
//		
//		if(fahrmotor1.gibUmdrehungen() == -3 && counter == 0) {
//			
//			fahrmotor1.setdrehzahl(0);
//			fahrmotor2.setdrehzahl(0);
//			
//			counter ++;
//			
//		}	
//		
//		
//		if(fahrmotor1.gibUmdrehungen() == -3 && counter == 1) {
//			
//			fahrretour();
//			
//			counter ++;
//			
//		}	

	}

	public void neunziggrad() {

		switch (zustand) {

		case BEREIT: {
			fahrlinkskurve();
			zustand = VORRUECKEN;
			break;
		}

		case VORRUECKEN: {
			if (fahrmotor1.gibUmdrehungen() >= 2) {
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

	public void testfahren() {

		switch (start) {

		case 1: // linkskurve
		{
			fahrlinkskurve();
			start = 2;
			break;
		}

		case 2: // anzahl inkrements fahren
		{
			if (fahrmotor1.gibUmdrehungen() == 1) {
				fahrviertelspeed();
				start = 3;
			}
			break;
		}

		case 3: // 180 grad drehen rechtskurve
		{
			if (fahrmotor1.gibUmdrehungen() == -3) {
				fahrrechtskurve();
				start = 4;
			}
			break;
		}

		case 4: {
			if (fahrmotor2.gibUmdrehungen() == 3) {
				fahrnullspeed();
				start = 5;
			}
			break;
		}

		case 5: {
			wurfspeedhalb();
			servo.servooffen();
			start = 6;
			break;

		}

		case 6: {
			zähler++;
			if (zähler == 400) {
				servo.servogeschlossen();
				wurfnullspeed();
				start = 7;
			}
			break;
		}

		case 7: {
			start = 0;
			zähler = 0;
			break;
		}

		}

	}

	public static void testneunziggrad() {
		zustand = BEREIT;
	}

	public static void teststarten() {
		start = 1;
	}

	public boolean irsensor() {
		boolean schwarz = false;
		if (dist.gibdist(1) < 10) {
			schwarz = true;
		}
		if (dist.gibdist(1) > 10) {
			schwarz = false;
		}
		return schwarz;
	}

	public static void fahrnullspeed() {
		fahrmotor1.setdrehzahl(0);
		fahrmotor2.setdrehzahl(0);
	}

	public static void fahrviertelspeed() {
		fahrmotor1.setdrehzahl((float) (-1 * Math.PI));
		fahrmotor2.setdrehzahl((float) (1 * Math.PI));
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

	public static void wurfnullspeed() {
		wurfmotor3.setdrehzahl(0);
		wurfmotor4.setdrehzahl(0);
	}

	public static void wurfspeedmax() {
		wurfmotor3.setdrehzahl((float) (200 * Math.PI));
		wurfmotor4.setdrehzahl((float) (-200 * Math.PI));

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

		Task task;

		task = new Test_Main_M3();
		task.period = (int) (0.01f * 1000);
		Task.install(task);

		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(19200, SCI.NO_PARITY, (short) 8);
		// Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);
		System.out.println("static start");

	}

}
