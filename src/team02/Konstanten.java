/**
 * @Author Chris
 * @version 2019.03.15
 */
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public interface Konstanten
{
	//Debug Konstanten
	final boolean DEBUG 	= true;							//True, Debug aktiviert
	final boolean TEST		= true;							//True, Testfunktionen aktiviert

	//Programm Parameter
	final double TASK_PERIOD 	= 0.2;							//Taktzeit des Haupttasks

	//Wifi Parameter
	//Befehle
	final int CATCH_READY = 0;								//Wifi Befehl Fangbereit
	final int THROW_READY = 0;								//wifi Befehl Wurfbereit
	final int BALL_CATCHED = 0;								//wifi Befehl Ball gefangen


	final double GEAR_RATIO = 1.0/86;						//Übersetzung des Getriebes
	final int TICKS_PER_ROUND = 128;						//Encoder Impulse pro Umdr.
	final double WHEEL_DIAMETER = 0.0455; 					//in mm
	final double WHEEL_DISTANCE = 0.100;						//Horizontaler Abstand der RĂ¤der

	final double MAX_ROUNDS = 7000.0/60;					//1/s


	final double SPANN_ZEIT = 0;
	final double WURF_ZEIT = 0;
	
	//Sensor Konstanten 
	
	final int HLC_GRENZWERT_SCHWARZ = 100;							// schwarze Linie 
	final int HLC_GRENZWERT_WEISS = 100;							// keine Linie 


}
