/*
 * @author Loris
 * @version 2019.03.05
*/
package team02;

import team02.chris.WlanSystem;
import team02.vorlagen.*;


public interface Systeme
{
	//Systeme
    WurfSystem		wurfSystem 					= WurfSystem.getInstance();
    BewegungsSystem	bewegungsSystem 			= BewegungsSystem.getInstance();
    GegnerSystem	gegnerSystem 				= GegnerSystem.getInstance();
    WlanSystem		wlanSystem 					= WlanSystem.getInstance();
}
