package team02.testklassen;

import team02.IO;
import team02.Konstanten;
import team02.Systeme;

public class Test_Sharp implements IO, Konstanten, Systeme
{
	public static void test()
	{
		//09.04.19 -> Funktioniert!
		debug.println((int)(IO.AN_Sharp1.getDistanz()*1000));
		debug.println((int)(IO.AN_Sharp2.getDistanz()*1000));
		debug.println((int)(IO.AN_Sharp3.getDistanz()*1000));
	}
}
