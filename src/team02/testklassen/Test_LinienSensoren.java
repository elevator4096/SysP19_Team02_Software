package team02.testklassen;

import team02.IO;

public class Test_LinienSensoren {
	
	public static void test()
	{
		IO.debugSystem.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieLinks ()));IO.debugSystem.print("\t");
		IO.debugSystem.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieVorne ()));IO.debugSystem.print("\t");
		IO.debugSystem.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieRechts()));IO.debugSystem.print("\t");
		IO.debugSystem.println("");
	}

}
