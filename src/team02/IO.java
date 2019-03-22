/**
 * @author Loris
 * @version 2019.03.05
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.HLC1395Pulsed;
import ch.ntb.inf.deep.runtime.mpc555.driver.MDASM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import team02.vorlagen.*;

public interface IO {
	
	//Input
	MPIOSM_DIO IN_Taster_1 				= new MPIOSM_DIO(10, false);
	MPIOSM_DIO IN_Taster_2 				= new MPIOSM_DIO(9, false);
	MPIOSM_DIO IN_Laser_1 				= new MPIOSM_DIO(6, false);
	MPIOSM_DIO IN_Laser_2				= new MPIOSM_DIO(5,false);
	MDASM_DIO IN_Taster_konf1			= new MDASM_DIO(30,false);
	MDASM_DIO IN_Taster_konf2			= new MDASM_DIO(31,false);


	//Output
	MPIOSM_DIO OUT_Reset_Wlan			= new MPIOSM_DIO(11,true);
	MPIOSM_DIO OUT_Magnet_Ausloeser		= new MPIOSM_DIO(8, true);
	MPIOSM_DIO OUT_Magnet				= new MPIOSM_DIO(7, true);

	MDASM_DIO OUT_FG_Speed				= new MDASM_DIO(12,true);
	MDASM_DIO OUT_DIR_Speed1			= new MDASM_DIO(11,true);
	MDASM_DIO OUT_DIR_Speed2			= new MDASM_DIO(13,true);
	MDASM_DIO OUT_LED1					= new MDASM_DIO(14,true);
	MDASM_DIO OUT_LED2					= new MDASM_DIO(15,true);
	MDASM_DIO OUT_LED3					= new MDASM_DIO(27,true);
	MDASM_DIO OUT_LED4					= new MDASM_DIO(28,true);
	MDASM_DIO OUT_LED5					= new MDASM_DIO(29,true);


	//HLC
	HLC1395Pulsed 	HLC_1395_PULSED		= HLC1395Pulsed.getInstance();
	int 		  	HLC_Anzahl_Sens		= 8;
	int				HLC_Code_Sens		= 0xC0EDF;								//Trig,Adr3,Adr2,Adr1,Adr0 HEX Codiert
	int				HLC_AN_chan			= 0;


	//Sharp
	int 	   PIN_Sharp_Kanal_Links	= 0;									//Sharp1
	int 	   PIN_Sharp_Kanal_Rechts	= 1;									//Sharp2
	int 	   PIN_Sharp_Kanal_Vorne	= 2;									//Sharp3
	QADC_AIN   ADC_B					= new QADC_AIN();
	Sharp	   SHARP_Links				= new Sharp(PIN_Sharp_Kanal_Links);
	Sharp	   SHARP_Rechts				= new Sharp(PIN_Sharp_Kanal_Rechts);
	Sharp	   SHARP_Vorne				= new Sharp(PIN_Sharp_Kanal_Vorne);
	short		AN_Sharp1				= QADC_AIN.read(false,0);
	short		AN_Sharp2				= QADC_AIN.read(false,0);
	short		AN_Sharp3				= QADC_AIN.read(false,0);

	//PWM Periodendauer
	int 	   PERIOD_WurfZyl			= 14_000_000/TPU_PWM.tpuTimeBase; 		// PWM 	   72 Hz
	int 	   PERIOD_Motoren 			=     50_000/TPU_PWM.tpuTimeBase;   	// PWM 20'000 Hz

	//PWM
	TPU_PWM    PWM_WurfZylinder		   	= new TPU_PWM(false, 0, PERIOD_WurfZyl, 0);
	TPU_PWM    PWM_MotorLinksPWM 	   	= new TPU_PWM(false, 1, PERIOD_Motoren, 0);
	TPU_PWM    PWM_MotorRechtsPWM 	   	= new TPU_PWM(false, 2, PERIOD_Motoren, 0);


	//Motoren
	Motor 	   MOTOR_links 				= new Motor(IO.FQD_Links, IO.PWM_MotorLinksPWM, IO.PERIOD_Motoren);
	Motor 	   MOTOR_rechts				= new Motor(IO.FQD_Rechts, IO.PWM_MotorRechtsPWM, IO.PERIOD_Motoren);

	TPU_FQD    FQD_Links 				= new TPU_FQD(false, 3);
	TPU_FQD    FQD_Rechts 				= new TPU_FQD(false, 5);
	
	//LinienSensoren
	LinienSensoren LINES_Sensoren 		= new LinienSensoren();
	
	LinienSensor LINE_Sensor_Vorne		= new LinienSensor(0,1);
	LinienSensor LINE_Sensor_Links		= new LinienSensor(2,3);
	LinienSensor LINE_Sensor_Rechts		= new LinienSensor(4,5);
	LinienSensor LINE_Sensor_Hinten		= new LinienSensor(6,7);
	
	
}
