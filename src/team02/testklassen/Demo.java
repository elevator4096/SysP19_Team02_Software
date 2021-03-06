package team02.testklassen;

import exchange.ZustandWifi;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;
import team02.vorlagen.Pos_Wechsel_V2;

/**
 * 
 * @author Loris
 *
 *	Testklasse zur Demonstration der implementierten Teilfunktionen des Roboters
 *	Die Teilfunktionen lassen sich direkt per WLan Befehl ausfuehren!  
 */

public class Demo implements IO, Konstanten, Systeme {
	
	private static int demoZustand = ZustandWifi.DEMO_WARTEN;
	
	public static void run()
	{
		demoZustand = Systeme.wlanSystem.getPartnerState();
		 switch (demoZustand)
	        {
	            case ZustandWifi.DEMO_WARTEN:
	            {
	                break;
	            }
	            case ZustandWifi.DEMO_WAND:
	            {
	            	IO.OUT_Magnet_Wand.set(true);
	            	break;
	            }
	            case ZustandWifi.DEMO_WAND_AUS:
	            {
	            	IO.OUT_Magnet_Wand.set(false);
	            	break;
	            }
	            case ZustandWifi.DEMO_DREHEN_90_UZ:
	            {
	            	IO.OUT_Magnet_Wand.set(false);
	            	Systeme.bewegungsSystem.dreheUngenauUZ(Math.PI/2);	            	
	                break;
	            }
	            case ZustandWifi.DEMO_FAHREN_WEIT:
	            {
	            	Systeme.bewegungsSystem.fahreFreiBisDistanz(true, 0.3);
	                break;
	            }
	            case ZustandWifi.DEMO_DREHEN_90_GUZ:
	            {
	            	Systeme.bewegungsSystem.dreheUngenauGUZ(Math.PI/2);
	                break;
	            }
	            case ZustandWifi.DEMO_ZYLINDER_HOCH:
	            {
	            	//Wand-Magnet wieder aus
	            	IO.OUT_Magnet_Wand.set(false);
	            	
	            	Systeme.wurfSystem.zylinderSpannen(90);
	                break;
	            }
	            case ZustandWifi.DEMO_ZYLINDER_RUNTER:
	            {
	            	//Wand-Magnet wieder aus
	            	IO.OUT_Magnet_Wand.set(false);
	            	
	            	Systeme.wurfSystem.zylinderSpannen(10);
	                break;
	            }
	            case ZustandWifi.DEMO_WERFEN:
	            {
	            	Systeme.wurfSystem.ballWerfen();
	                break;
	            }
	            case ZustandWifi.DEMO_HALT:
	            {
	            	Systeme.bewegungsSystem.fahreFreiBisDistanz(false, 0);
	                break;
	            }
	            case ZustandWifi.DEMO_POS1:
	            {
	            	debug.println("DEMO_POS1");
	            	Pos_Wechsel_V2.fahre_zu_Pos1();
	                break;
	            }
	            case ZustandWifi.DEMO_POS2:
	            {
	            	debug.println("DEMO_POS2");
	            	Pos_Wechsel_V2.fahre_zu_Pos2();
	                break;
	            }
	            case ZustandWifi.DEMO_NORM_DIST:
	            {
	            	debug.println("DEMO_NORM_DIST");
	            	Systeme.gegnerSystem.resetGegnerErkennung();
	            	Systeme.bewegungsSystem.fahreFreiBisDistanz(true, 0.168);
	                break;
	            }
	            
	            
	            
	            default:
	            {
	            	//Bereich 1000-1100 spannt zylinder auf demoZustand-1000
	            	if (demoZustand >= 1000 && demoZustand <= 1100)
	            	{
	            		Systeme.wurfSystem.zylinderSpannen(demoZustand-1000);
	            	}
	            	
	            	demoZustand = ZustandWifi.DEMO_WARTEN;
	                break;
	            }
	        }
		 
		 Systeme.wlanSystem.setPartnerState(ZustandWifi.DEMO_WARTEN);
		 
		 //Gegner erkannt Signalisieren
		 IO.OUT_LED2.set(Systeme.gegnerSystem.istGegnerRechts());
		
		
	}

}
