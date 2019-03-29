package team02.testklassen;

import team02.IO;

public class Test_LinienSensoren {
	
	public static void test()
	{
		IO.debugSystem.print(Boolean.toString( IO.LINES_Sensoren.istLinieLinks (IO.LINE_Sensor_Vorne)));IO.debugSystem.print("\t");
		IO.debugSystem.print(Boolean.toString( IO.LINES_Sensoren.istLinieVorne (IO.LINE_Sensor_Vorne)));IO.debugSystem.print("\t");
		IO.debugSystem.print(Boolean.toString( IO.LINES_Sensoren.istLinieRechts(IO.LINE_Sensor_Vorne)));IO.debugSystem.print("\t");
		IO.debugSystem.println("");
	}

}
