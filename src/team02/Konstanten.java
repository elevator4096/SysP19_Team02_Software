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
	double	TASK_PERIOD 			= 2.000;						//Taktzeit des Haupttasks in s

	double 	GEAR_RATIO 				= 1.0/86;						//Übersetzung des Getriebes
	int 	TICKS_PER_ROUND 		= 128;							//Encoder Impulse pro Umdr.
	double 	WHEEL_DIAMETER 			= 0.0455; 						//in m
	double 	WHEEL_DISTANCE 			= 0.100;						//Horizontaler Abstand der RĂ¤der

	double 	MAX_DREHZAHL_MOTOR 		= 7000.0/60;					// Umdrehungen pro Sekunde
	
	//Tuning Parameter
	double	DRIVING_SPEED			= 0.018;						// RichtGeschwindigkeit 	  fuer Fahren in m/s   ( Werte von ca 0.010   - 0.180)
	double	TURNING_SPEED			= 0.100*Math.PI;			 	// RichtWinkelGeschwindigkeit fuer Drehen in rad/s ( Werte von ca 0.050PI - 1.150PI)
	double	MAX_SPEED				= MAX_DREHZAHL_MOTOR * GEAR_RATIO * WHEEL_DIAMETER * Math.PI;
	
	double	LINE_FOLLOWER_RADIUS	= 0.500;						// Radius von KorrekturKurve fuer Linienfolger in m
	
	double 	SPANN_ZEIT 				= 0;
	double 	WURF_ZEIT 				= 1000;
	
	
	
	//Sensor Konstanten 
	int 	HLC_GRENZWERT_SCHWARZ 	= 100;							// schwarze Linie
	int 	HLC_GRENZWERT_WEISS 	= 100;							// keine Linie
	
	//Sharp Konstanten fuer Lineare Naeherung siehe Sharp_Distanz_Rechner.xlsx
	double  m_SHARP					= 14249.186;
	double  n_SHARP					= 9.941;
	double  k_SHARP					= 18.030;
	

	//Bezeichner
	int LINIE_VORNE 						= 0;
	int LINIE_MITTE 						= 1;
	int LINIE_HINTEN 						= 2;
	
	

}
