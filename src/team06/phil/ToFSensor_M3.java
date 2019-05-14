package team06.phil;

import java.lang.Object;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.mpc555.driver.VL6180XAddr;
import ch.ntb.inf.deep.runtime.mpc555.driver.VL6180X_SC18IS600;

import java.io.PrintStream;

public class ToFSensor_M3 extends Task {

	static VL6180X_SC18IS600 tofSensor;

	public ToFSensor_M3() {

	}

	public void action() {

		//System.out.println("action");

		//System.out.println(tofSensor.getSingleRangeValue(1));

		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		byte val = tofSensor.getSingleRangeValue(1);
		System.out.print("RESULT__RANGE_VAL:\tSens0: ");
		System.out.printHex((val & 0xFF));
		System.out.print("\t");
		if (val < 0)
			val += 256;
		System.out.println((val));
		System.out.println("-------------------------------------------");
		val = tofSensor.getSingleRangeValue(2);
		System.out.print("RESULT__RANGE_VAL:\tSens1: ");
		System.out.printHex((val & 0xFF));
		System.out.print("\t");
		if (val < 0)
			val += 256;
		System.out.println((val));
		/*System.out.println("-------------------------------------------");
		val = tofSensor.getSingleRangeValue(3);
		System.out.print("RESULT__RANGE_VAL:\tSens2: ");
		System.out.printHex((val & 0xFF));
		System.out.print("\t");
		if (val < 0)
			val += 256;
		System.out.println((val));
		System.out.println("-------------------------------------------");
		*/

	}

	public static void startCont() { // für Beispiel 2
		tofSensor.startRangeContMode(1);
		tofSensor.startRangeContMode(2);
		tofSensor.startRangeContMode(3);
		tofSensor.startRangeContMode(4);
		tofSensor.startRangeContMode(5);
		tofSensor.startRangeContMode(6);
	}

	public static void getCont() { // für Beispiel 2
		System.out.print("Sensor0: ");
		System.out.printHex(tofSensor.getRangeContValue(1));
		System.out.print(" mm\nSensor1: ");
		System.out.printHex(tofSensor.getRangeContValue(2));
		System.out.print(" mm\nSensor2: ");
		System.out.printHex(tofSensor.getRangeContValue(3));
		System.out.print(" mm\nSensor3: ");
	}

	static {
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(19200, SCI.NO_PARITY, (short) 8);
		// Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);
		System.out.println("static start");

		tofSensor = new VL6180X_SC18IS600(3);
		System.out.println("Static load complete");

		Task s = new ToFSensor_M3();
		s.period = 500;
		Task.install(s);
		
		
		
		
	}
}
