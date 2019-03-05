package team02;

public interface Konstanten
{
	//Debug Konstanten
	final boolean DEBUG 	= true;
	
	//Programm Parameter
	final int TASK_PERIOD 	= 200;
	final int TIME_1S		= 1000;
	
	//Eingaenge
	final int FIRST_IN		= 15;
	final int ANZ_IN		= 4;
	
	//Ausgaenge
	final int FIRST_OUT		= 19;
	final int ANZ_OUT		= 4;
	
	//Wifi Parameter
	//Befehle
	final int BEW_ZYL 		= 0b1101;
	final int WERFE 		= 0b1110;
	
	//Feineinstellungen
	final int SPANN_ZEIT	= 15_000; // Spannzeit in ms
	final int WURF_ZEIT		= 1_000 ; // wurfzeit  in ms
	
}
