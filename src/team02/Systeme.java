/*
 * @author Loris
 * @version 2019.03.05
*/
package team02;

import exchange.WlanSystem;
import team02.IO;
import team02.vorlagen.*;


public interface Systeme
{
	//Systeme
    WurfSystem		wurfSystem 					= WurfSystem.getInstance();
    GegnerSystem	gegnerSystem 				= GegnerSystem.getInstance();
    WlanSystem		wlanSystem 					= WlanSystem.getInstance(IO.OUT_Reset_Wlan);
    BewegungsSystem	bewegungsSystem 			= BewegungsSystem.getInstance();
}
