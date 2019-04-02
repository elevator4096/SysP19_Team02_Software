/*
 * @author Loris
 */ 
package team02.testklassen;

import team02.IO;

public class Test_LinienSensoren {
	
	public static void test()
	{
		//02.04.19 -> Funktioniert!
		IO.debug.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieLinks ()));IO.debug.print("\t");
		IO.debug.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieVorne ()));IO.debug.print("\t");
		IO.debug.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieRechts()));IO.debug.print("\t");
		
		IO.debug.println("");
		
	}

}
