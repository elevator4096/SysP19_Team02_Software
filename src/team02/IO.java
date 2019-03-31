/*
 * @author Loris
 * @version 2019.03.05
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.HLC1395Pulsed;
import ch.ntb.inf.deep.runtime.mpc555.driver.MDASM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import team02.chris.WlanSystem;
import team02.vorlagen.*;


public interface IO
{
	
	//Input
	MPIOSM_DIO 		IN_Taster_1 				= new MPIOSM_DIO(10, false);
	MPIOSM_DIO 		IN_Taster_2 				= new MPIOSM_DIO(9, false);
	MPIOSM_DIO 		IN_Laser_1 					= new MPIOSM_DIO(6, false);
	MPIOSM_DIO 		IN_Laser_2					= new MPIOSM_DIO(5,false);
	MDASM_DIO 		IN_Taster_konf1				= new MDASM_DIO(30,false);
	MDASM_DIO 		IN_Taster_konf2				= new MDASM_DIO(31,false);

	//Experimental
	MPIOSM_DIO[]    IN_MPIOSM_DIO               = {IN_Taster_1,IN_Taster_2,IN_Laser_1,IN_Laser_2};
	MDASM_DIO[]     IN_MDASM_DIO                = {IN_Taster_konf1, IN_Taster_konf2};


	//Output
	MPIOSM_DIO 		OUT_Reset_Wlan				= new MPIOSM_DIO(11,true);
	MPIOSM_DIO 		OUT_Magnet_Ausloeser		= new MPIOSM_DIO(8, true);
	MPIOSM_DIO 		OUT_Magnet_Wand 			= new MPIOSM_DIO(7, true);

	//Experimental
	MPIOSM_DIO[]    OUT_MPIOSM_DIO              = {OUT_Reset_Wlan, OUT_Magnet_Ausloeser, OUT_Magnet_Wand};

	MDASM_DIO 		OUT_FG_Speed				= new MDASM_DIO(12,true);
	MDASM_DIO 		OUT_DIR_Speed1				= new MDASM_DIO(11,true);
	MDASM_DIO 		OUT_DIR_Speed2				= new MDASM_DIO(13,true);
	MDASM_DIO 		OUT_LED1					= new MDASM_DIO(14,true);		//Takt LED
	MDASM_DIO 		OUT_LED2					= new MDASM_DIO(15,true);
	MDASM_DIO 		OUT_LED3					= new MDASM_DIO(27,true);
	MDASM_DIO 		OUT_LED4					= new MDASM_DIO(28,true);
	MDASM_DIO 		OUT_LED5					= new MDASM_DIO(29,true);

	//Experimental
	MDASM_DIO[]     OUT_MDASM_DIO               = {OUT_FG_Speed, OUT_DIR_Speed1, OUT_DIR_Speed2, OUT_LED1, OUT_LED2, OUT_LED3, OUT_LED4, OUT_LED5};


	//HLC
	HLC1395Pulsed 	HLC_1395_PULSED				= HLC1395Pulsed.getInstance();
	int 		  	HLC_Anzahl_Sens				= 4;
	int				HLC_Code_Sens				= (12 << 16) | (0 << 12) | (14 << 8) | (13 << 4) | (15 << 0) ;//0xC0EDF;	//Trig,Adr3,Adr2,Adr1,Adr0 HEX
	int				HLC_AN_chan					= 0;


	//Sharp Distanz in mm
	Sharp			AN_Sharp1					= new Sharp(false,0,0.0);
	Sharp			AN_Sharp2					= new Sharp(false,1, 0.0);
	Sharp			AN_Sharp3					= new Sharp(false,2, 0.0);

	//PWM Periodendauer
	double 	   		PERIOD_WurfZyl				= 14_000_000.0/TPU_PWM.tpuTimeBase; 	// PWM 	   72 Hz
	double 	   		PERIOD_Motoren 				=     50_000.0/TPU_PWM.tpuTimeBase;   	// PWM 20'000 Hz

	//PWM
	TPU_PWM    		PWM_WurfZylinder		   	= new TPU_PWM(false, 0, (int)PERIOD_WurfZyl, 0);
	TPU_PWM    		PWM_MotorLinksPWM 	   		= new TPU_PWM(false, 1, (int)PERIOD_Motoren, 0);
	TPU_PWM    		PWM_MotorRechtsPWM 	   		= new TPU_PWM(false, 2, (int)PERIOD_Motoren, 0);


	TPU_FQD    		FQD_Links 					= new TPU_FQD(false, 3);
	TPU_FQD   		FQD_Rechts 					= new TPU_FQD(false, 5);


	//Motoren
	Motor 	   		MOTOR_links 				= new Motor(FQD_Links, PWM_MotorLinksPWM, (int)PERIOD_Motoren);
	Motor 	   		MOTOR_rechts				= new Motor(FQD_Rechts, PWM_MotorRechtsPWM, (int)PERIOD_Motoren);


	//LinienSensoren
	LinienSensoren 	LINES_Sensoren 				= new LinienSensoren();
	
	LinienSensor 	LINE_Sensor_Vorne			= new LinienSensor(0,1);
	LinienSensor 	LINE_Sensor_Links			= new LinienSensor(2,3);
	LinienSensor	LINE_Sensor_Rechts			= new LinienSensor(4,5);
	LinienSensor 	LINE_Sensor_Hinten			= new LinienSensor(6,7);


	//Systeme
    WurfSystem		wurfSystem 					= WurfSystem.getInstance();
    BewegungsSystem	bewegungsSystem 			= BewegungsSystem.getInstance();
    GegnerSystem	gegnerSystem 				= GegnerSystem.getInstance();
    WlanSystem		wlanSystem 					= WlanSystem.getInstance();
	DebugSystem 	debugSystem 				= DebugSystem.getInstance();
}
