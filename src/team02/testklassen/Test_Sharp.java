package team02.testklassen;

import team02.IO;
import team02.Konstanten;
import team02.Systeme;

public class Test_Sharp implements IO, Konstanten, Systeme
{
	public static void test()
	{
		//09.04.19 -> Funktioniert!
		debug.print("Sharp1: ");
		debug.print((int)(IO.AN_Sharp1.getDistanz()*1000));
		debug.print("   Sharp2: ");
		debug.print((int)(IO.AN_Sharp2.getDistanz()*1000));
		debug.print("   Sharp3: ");
		debug.println((int)(IO.AN_Sharp3.getDistanz()*1000));
	}
}
