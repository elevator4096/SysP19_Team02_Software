/*
 * @author Loris
 */ 
package team02.testklassen;

import team02.IO;
import team02.Konstanten;
import team02.Systeme;

public class Test_LinienSensoren implements IO, Konstanten, Systeme
{
	
	public static void test()
	{
		//02.04.19 -> Funktioniert!
		IO.debug.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieLinks ()));IO.debug.print("\t");
		IO.debug.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieVorne ()));IO.debug.print("\t");
		IO.debug.print(Boolean.toString( IO.LINE_Sensor_Vorne.istLinieRechts()));IO.debug.print("\t");
		
		IO.debug.println("");
		
	}

}
