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
	final int TASK_PERIOD 	= 200;							//Taktzeit des Haupttasks
	final int TIME_1S		= 1000;							//1s

	//Wifi Parameter
	//Befehle
	final int CATCH_READY = 0;								//Wifi Befehl Fangbereit
	final int THROW_READY = 0;								//wifi Befehl Wurfbereit
	final int BALL_CATCHED = 0;								//wifi Befehl Ball gefangen


	final float GEAR_RATIO = 1.0f/86f;						//Übersetzung des Getriebes
	final int TICKS_PER_ROUND = 128;						//Encoder Impulse pro Umdr.
	final float WHEEL_DIAMETER = 45.5f; 					//in mm
	final float WHEEL_DISTANCE = 100f;						//Horizontaler Abstand der RĂ¤der

	final float MAX_ROUNDS = 7_000f/60f;					//1/s


	final double SPANN_ZEIT = 0;
	final double WURF_ZEIT = 0;


}
