package team06.testprogramme;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.mpc555.driver.VL6180X;
import ch.ntb.inf.deep.runtime.ppc32.Task;

public class ToFSensorDemoParv extends Task{
	VL6180X vs;
	int[] sensorDistances;
	final int numberOfSensors = 3;
	final int resetPin = 9;
	
	public ToFSensorDemoParv(){
//		System.out.println("ToFSensorDemoParv");
		vs = new VL6180X(numberOfSensors, resetPin);
//		System.out.println("End ToFSensorDemoParv");
	}
	
	public void action(){
		sensorDistances = vs.read();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		int val = sensorDistances[0];
		System.out.print("RESULT__RANGE_VAL:\tSens0: ");
		System.out.printHex((val&0xFF));
		System.out.print("\t");
		if(val < 0) val+=256;
		System.out.println((val));
		System.out.println("-------------------------------------------");
		val = sensorDistances[1];
		System.out.print("RESULT__RANGE_VAL:\tSens1: ");
		System.out.printHex((val&0xFF));
		System.out.print("\t");
		if(val < 0) val+=256;
		System.out.println((val));
		System.out.println("-------------------------------------------");
		val = sensorDistances[2];
		System.out.print("RESULT__RANGE_VAL:\tSens2: ");
		System.out.printHex((val&0xFF));
		System.out.print("\t");
		if(val < 0) val+=256;
		System.out.println((val));
		System.out.println("-------------------------------------------");
		
	}
	
	public void tofausgeben() {
		sensorDistances = vs.read();
		System.out.println(sensorDistances[0]);
		System.out.println(sensorDistances[1]);
		System.out.println(sensorDistances[2]);
		
		
	}
	
//	static{
//		SCI sci1 = SCI.getInstance(SCI.pSCI1);
//		sci1.start(19200, SCI.NO_PARITY, (short)8);
//
//		System.out = new PrintStream(sci1.out);
//		
//		System.out.println("static start");
//		
//		Task s = new ToFSensorDemoParv();
//		s.period = 1000;
//		
//		Task.install(s);
//	}
}
