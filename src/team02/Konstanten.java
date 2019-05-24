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
	
	boolean DREHE_NUR_MIT_ENCODER	= true;							//Benutze ausschliesslich Encoder fuer 90Grad Drehungen 
	boolean SANFTES_BREMSEN			= true;							//Verlangsame den Roboter kurz vor erreichen der Zielposition(Hoehere Praezision)
	

	//Programm Parameter
	double	TASK_PERIOD 			= 0.2;						//Taktzeit des Haupttasks in s

	double 	GEAR_RATIO 				= 1.0/86;						//Übersetzung des Getriebes
	int 	TICKS_PER_ROUND 		= 512;							//Encoder Impulse pro Umdr.
	double 	WHEEL_DIAMETER 			= 0.0465; 						//in m
	double 	WHEEL_DISTANCE 			= 0.145;						//Horizontaler Abstand der RĂ¤der
	double  DISTANCE_PER_TICK		= WHEEL_DIAMETER*Math.PI*GEAR_RATIO/(TICKS_PER_ROUND);

	double 	MAX_DREHZAHL_MOTOR 		= 7000.0/60;					// Umdrehungen pro Sekunde
	
	//Tuning Parameter
	double	DRIVING_SPEED			= 0.100;							// RichtGeschwindigkeit 	  fuer Fahren in m/s   		( Werte von ca 0.010   - 0.180)
	double	TURNING_SPEED			= 0.020*Math.PI;			 	// RichtWinkelGeschwindigkeit fuer Drehen in rad/s 		( Werte von ca 0.050PI - 1.150PI)
	double	SLOW_TURNING_SPEED		= 0.010*Math.PI;			 	// RichtWinkelGeschwindigkeit fuer langsames Drehen in rad/s 		( Werte von ca 0.050PI - 1.150PI)
	double	SLOW_SPEED				= 0.03;							// RichtGeschwindigkeit fuer langsames fahren in m/s   	( Werte von ca 0.010   - 0.180)
	double	MAX_SPEED				= MAX_DREHZAHL_MOTOR * GEAR_RATIO * WHEEL_DIAMETER * Math.PI;
	
	double  BREMSWEG				= 0.070;						//Bremsweg vor Halt(Hoehere Praezision durch sanftes Bremsen)
	double  BREMSWINKEL				= 0.500;						//BREMSWINKEL vor Halt(Hoehere Praezision durch sanftes Bremsen)
	
	double	LINE_FOLLOWER_RADIUS	= 0.400;						// Radius von KorrekturKurve fuer Linienfolger in m
	double	LINE_KORREKTUR_SPEED	= 0.050;
	
	double 	SPANN_ZEIT 				= 0;
	double 	WURF_ZEIT 				= 1000;
	
	double DREHWINKEL_KORB			= (15/180)*Math.PI;
	
	int		Langer_Wurf				= 0;
	int		Kurzer_Wurf				= 0;
	int		Korb_Wurf				= 0;
	
	//Sensor Konstanten 
	int 	HLC_GRENZWERT_SCHWARZ 	= 300  ;							// schwarze Linie
	double  SHARP_GRENZWERT			= 0.050;							//Gegner erkannt?
	
	
	//Sharp Konstanten fuer Lineare Naeherung siehe Sharp_Distanz_Rechner.xlsx
	double  m_SHARP					= 14249.186;
	double  n_SHARP					= 9.941;
	double  k_SHARP					= 18.030;
	

	//Bezeichner
	int LINIE_VORNE 						= 0;
	int LINIE_MITTE 						= 1;
	int LINIE_HINTEN 						= 2;
	
	

}
