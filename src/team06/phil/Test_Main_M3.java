package team06.phil;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import team06.aurelia.Main;
import team06.Endschalter;
import team06.MotorSMSC;
import team06.Variablen;

public class Test_Main_M3 extends Task {


	static MotorSMSC_M3 wurfmotor3;
	static MotorSMSC_M3 wurfmotor4;
	
	static MotorSMSC_M3 fahrmotor1;
	static MotorSMSC_M3 fahrmotor2;
	
	static Endschalter endschalter;
	
	int counter = 0;

	public Test_Main_M3() {	
		
//		// Testboard
//		wurfmotor3 = new MotorSMSC_M3(0.001f, 5, 7, true, 8, true, 64, 5f, 3249f / 196f, 1f, 0.01f);
//		wurfmotor4 = new MotorSMSC_M3(0.01f, 4, 6, true, 10, true, 256, 12f, 91f/1f, 1f, 0.008f);
	
		fahrmotor1 = new MotorSMSC_M3(0.01f, 5, 7, true, 8, true, 256, 12f, 91f/1f, 1f, 0.008f);
		fahrmotor2 = new MotorSMSC_M3(0.01f, 4, 6, true, 10, true, 256, 12f, 91f/1f, 1f, 0.008f);

		
//		wurfmotor3 = new MotorSMSC_M3(0.01f, 1, 3, true, 12, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);
//		wurfmotor4 = new MotorSMSC_M3(0.01f, 0, 2, true, 14, true, 32, 12f, 1f / 1f, 0.001f, 0.0051f);
		
	
		System.out.println("Main Konstruktor gestartet");
	}

	public void action() {
		
//		wurfmotor3.motorstarten();
//		wurfmotor4.motorstarten();
//		
//		if(nofActivations%200==0) {
//		System.out.print("Wurfmotor 3: Umdrehungen >");
//		System.out.print(wurfmotor3.gibUmdrehungen());
//		System.out.print("        Geschwindigkeit in 1/min  >");
//		System.out.println(wurfmotor3.gibGeschwindigkeit());
//		
//		System.out.print("Wurfmotor 4: Umdrehungen >");
//		System.out.print(wurfmotor4.gibUmdrehungen());
//		System.out.print("        Geschwindigkeit in 1/min  >");
//		System.out.println(wurfmotor4.gibGeschwindigkeit());
//		}	
		
		fahrmotor1.motorstarten();
		fahrmotor2.motorstarten();
		
		if(nofActivations%150==0) {
			System.out.println("test");
		System.out.print("Fahrmotor 1: Umdrehungen >");
		System.out.print(fahrmotor1.gibUmdrehungen());
		System.out.print("        Geschwindigkeit in 1/min  >");
		System.out.println(fahrmotor1.gibGeschwindigkeit());
		
		System.out.print("Fahrmotor 2: Umdrehungen >");
		System.out.print(fahrmotor2.gibUmdrehungen());
		System.out.print("        Geschwindigkeit in 1/min  >");
		System.out.println(fahrmotor2.gibGeschwindigkeit());
		}	
		
		
		if(fahrmotor1.gibUmdrehungen() == -3 && counter == 0) {
			
			fahrmotor1.setdrehzahl(0);
			fahrmotor2.setdrehzahl(0);
			
			counter ++;
			
		}	
		
		
		if(fahrmotor1.gibUmdrehungen() == -3 && counter == 1) {
			
			retour();
			
			counter ++;
			
		}	
	
	}
	
	public static void nullspeed() {
//		wurfmotor3.setdrehzahl(0);
//		wurfmotor4.setdrehzahl(0);
		
		fahrmotor1.setdrehzahl(0);
		fahrmotor2.setdrehzahl(0);
	}
	
	public static void viertelspeed() {
//		wurfmotor3.setdrehzahl((float)(50 * Math.PI));
//		wurfmotor4.setdrehzahl((float)(50 * Math.PI));
		
		fahrmotor1.setdrehzahl((float)(-1 * Math.PI));
		fahrmotor2.setdrehzahl((float)(1 * Math.PI));
	}
	
	public static void halbspeed() {
//		wurfmotor3.setdrehzahl((float)(100 * Math.PI));
//		wurfmotor4.setdrehzahl((float)(100 * Math.PI));
		
		fahrmotor1.setdrehzahl((float)(-2 * Math.PI));
		fahrmotor2.setdrehzahl((float)(2 * Math.PI));
	}
	
	public static void maxspeed() {
//		wurfmotor3.setdrehzahl((float)(200 * Math.PI));
//		wurfmotor4.setdrehzahl((float)(200 * Math.PI));
		
		fahrmotor1.setdrehzahl((float)(-4 * Math.PI));
		fahrmotor2.setdrehzahl((float)(4 * Math.PI));	
			
		}
	
	public static void retour() {
		fahrmotor1.setdrehzahl((float)(0.5 * Math.PI));
		fahrmotor2.setdrehzahl((float)(-0.5 * Math.PI));
	}
	
	public static void linkskurve() {
		fahrmotor1.setdrehzahl((float)(0.5 * Math.PI));
		fahrmotor2.setdrehzahl((float)(0.5 * Math.PI));
		
	}
	
	public static void rechtskurve() {
		fahrmotor1.setdrehzahl((float)(-0.5 * Math.PI));
		fahrmotor2.setdrehzahl((float)(-0.5 * Math.PI));
		
	}
	
	
	
	
	static {

		Task task = new Test_Main_M3();
		task.period = (int)(0.01f * 1000);
		Task.install(task);

		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(19200, SCI.NO_PARITY, (short) 8);
		// Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);
		System.out.println("static start");

	}

}
