package team02.testklassen;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;
import team02.Zustand;

public class test_Ablauf  implements IO, Konstanten, Systeme
{
	private static long lastTime = Task.time();
	private static Zustand zustand = Zustand.WARTEN1;
	private static boolean richtung = true;

public static void main()
{
	
	
	if (Task.time() > lastTime+2000)
	{
		switch(zustand)
		{
		
		case WARTEN1:
			debug.println("Warten");
			zustand = Zustand.SETUP_ZYLINDER_HOCH;
			break;

		case SETUP_ZYLINDER_HOCH:
			debug.println("Zylinder Hoch");
			Systeme.wurfSystem.zylinderSpannen(90);
			
			zustand = Zustand.SETUP_ZYLINDER_TIEF;
			break;
		case SETUP_ZYLINDER_TIEF:
			debug.println("Zylinder Spannen");
			Systeme.wurfSystem.zylinderSpannen(10);
			zustand = Zustand.WARTEN_START;
			break;
		case WARTEN_START: 
			debug.println("Warten");
			zustand = Zustand.SPIELBEGINN;
			break;
		case SPIELBEGINN:
			debug.println("Spielbeginn");
		//	IO.OUT_Magnet_Wand.set(true); 
			Systeme.wurfSystem.ballWerfen();
			Systeme.gegnerSystem.resetGegnerErkennung();
			zustand = Zustand.Bewegen1;
			break;
		case Bewegen1:
			debug.println("Fahren1");
			Systeme.bewegungsSystem.fahreFreiBisKreuzung(true, Konstanten.LINIE_VORNE);
			zustand = Zustand.Bewegen2;
			break;
		case Bewegen2:
			if (Systeme.gegnerSystem.warGegnerRechts())
			{
				Systeme.bewegungsSystem.fahreFreiBisKreuzung(true,Konstanten.LINIE_HINTEN);
				Systeme.gegnerSystem.resetGegnerErkennung();
				zustand = Zustand.Bewegen3;
			}else {
				Systeme.bewegungsSystem.dreheUngenauUZ(0.5*Math.PI);	
				zustand = Zustand.FAHRE_EBENE2;
			}
			break;
		case Bewegen3:
			if(Systeme.bewegungsSystem.istInBewegung() == false)
			{
				debug.println("Bewegen 3");
				if(Systeme.gegnerSystem.warGegnerRechts())
				{
				 Systeme.bewegungsSystem.fahreFreiBisKreuzung(true, Konstanten.LINIE_MITTE);
				 Systeme.gegnerSystem.resetGegnerErkennung();
				 zustand = Zustand.Bewegen4;
				}
				else
				{
					Systeme.bewegungsSystem.dreheUngenauUZ(0.5*Math.PI);
					zustand = Zustand.FAHRE_EBENE2;
				}
				
				
			}
				
			
			
			
			
			
			
			
		
		
		lastTime = Task.time();
	}
	
}



}
