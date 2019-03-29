/*
 * @Author Chris
 * @version 2019.03.15
 */
package team02;

public interface Konstanten
{
	//Debug Konstanten
	boolean DEBUG 					= true;							//True, Debug aktiviert
	boolean TEST					= true;							//True, Testfunktionen aktiviert

	//Programm Parameter
	int		TASK_PERIOD 			= 200;							//Taktzeit des Haupttasks in ms (muss int sein)

	double 	GEAR_RATIO 				= 1.0/86;						//Übersetzung des Getriebes
	int 	TICKS_PER_ROUND 		= 128;							//Encoder Impulse pro Umdr.
	double 	WHEEL_DIAMETER 			= 0.0455; 						//in mm
	double 	WHEEL_DISTANCE 			= 0.100;						//Horizontaler Abstand der RĂ¤der

	double 	MAX_ROUNDS 				= 7000.0/60;					//1/s


	double 	SPANN_ZEIT 				= 0;
	double 	WURF_ZEIT 				= 0;
	
	//Sensor Konstanten 
	int 	HLC_GRENZWERT_SCHWARZ 	= 100;							// schwarze Linie
	int 	HLC_GRENZWERT_WEISS 	= 100;							// keine Linie


}
