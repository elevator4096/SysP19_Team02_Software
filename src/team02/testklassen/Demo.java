package team02.testklassen;

import exchange.ZustandWifi;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;

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
	            case ZustandWifi.DEMO_ZYLINDER_SPANNEN:
	            {
	            	Systeme.wurfSystem.zylinderSpannen(50);
	                break;
	            }
	            case ZustandWifi.DEMO_FAHREN_WEIT:
	            {
	            	Systeme.bewegungsSystem.fahreFreiBisDistanz(false, 0.3);
	                break;
	            }
	            case ZustandWifi.DEMO_DREHEN_90_GUZ:
	            {
	            	Systeme.bewegungsSystem.dreheUngenauGUZ(Math.PI/2);
	                break;
	            }
	            case ZustandWifi.DEMO_FAHREN_KURZ:
	            {
	            	Systeme.bewegungsSystem.fahreFreiBisDistanz(false, 0.1);
	                break;
	            }
	            case ZustandWifi.DEMO_DREHEN_90_UZ:
	            {
	            	Systeme.bewegungsSystem.dreheUngenauUZ(Math.PI/2);	            	
	                break;
	            }
	            case ZustandWifi.DEMO_FAHREN_WAND:
	            {
	            	Systeme.bewegungsSystem.folgeLinieBisWandRueckwaerts();
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
	            default:
	            {
	            	demoZustand = ZustandWifi.DEMO_WARTEN;
	                break;
	            }
	        }
		 
		 //Gegner erkannt Signalisieren
		 IO.OUT_LED5.set(Systeme.gegnerSystem.istGegnerRechts());
		
		
	}

}