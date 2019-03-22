/**
 * @author Phil
 * @version 2019.03.22
*/


package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.HLC1395Pulsed;
import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import ch.ntb.sysp.lib.SpeedController4DCMotor;
import team02.vorlagen.*;

public interface IO{	
	
	
// Wurfsystem
	
	MotorSMSC SMSC_WurfMotor1 = new MotorSMSC(0.001f, 0, 1, true, 2, true, 64, 12f, 3249f/196f, 1f, 0.01f);
	MotorSMSC SMSC_WurfMotor2 = new MotorSMSC(0.001f, 0, 1, true, 2, true, 64, 12f, 3249f/196f, 1f, 0.01f);
	
		
	
	
	
//	//Input
//	MPIOSM_DIO IN_Taster1 				= new MPIOSM_DIO( 5, false);
//	MPIOSM_DIO IN_Taster2				= new MPIOSM_DIO( 6, false);
//	MPIOSM_DIO IN_BallDetektor			= new MPIOSM_DIO( 7, false);
//	
//	int 	   PIN_Sharp_Kanal_Links	= 0;
//	int 	   PIN_Sharp_Kanal_Rechts	= 1;
//	int 	   PIN_Sharp_Kanal_Vorne	= 2;
//	QADC_AIN   ADC_B					= new QADC_AIN();
//	Sharp	   SHARP_Links				= new Sharp(PIN_Sharp_Kanal_Links);
//	Sharp	   SHARP_Rechts				= new Sharp(PIN_Sharp_Kanal_Rechts);
//	Sharp	   SHARP_Vorne				= new Sharp(PIN_Sharp_Kanal_Vorne);
//	
//
//	//Output
//	MPIOSM_DIO OUT_Test_Led				= new MPIOSM_DIO(10, true );
//	MPIOSM_DIO OUT_Magnet				= new MPIOSM_DIO(11, true );
//
//	//PWM Periodendauer
//	int 	   PERIOD_WurfZyl			= 14_000_000/TPU_PWM.tpuTimeBase; 		// PWM 	   72 Hz
//	int 	   PERIOD_Motoren 			=     50_000/TPU_PWM.tpuTimeBase;   	// PWM 20'000 Hz
//
//	//PWM
//	TPU_PWM    PWM_WurfZylinder		   	= new TPU_PWM(true, 0, PERIOD_WurfZyl, 0);
//	TPU_PWM    PWM_MotorLinksPWM 	   	= new TPU_PWM(true, 1, PERIOD_Motoren, 0);
//	TPU_PWM    PWM_MotorRechtsPWM 	   	= new TPU_PWM(true, 2, PERIOD_Motoren, 0);
//
//
//	//Motoren
//	Motor 	   MOTOR_links = new Motor(IO.FQD_Links, IO.PWM_MotorLinksPWM, IO.PERIOD_Motoren);
//	Motor 	   MOTOR_rechts = new Motor(IO.FQD_Rechts, IO.PWM_MotorRechtsPWM, IO.PERIOD_Motoren);
//
//	TPU_FQD    FQD_Links 				= new TPU_FQD(true, 3);
//	TPU_FQD    FQD_Rechts 				= new TPU_FQD(true, 5);
//	
//	//HLC
//	static HLC1395Pulsed HLC_1395_PULSED 			= HLC1395Pulsed.getInstance();
//	
//	//LinienSensoren
//	LinienSensoren LINES_Sensoren 		= new LinienSensoren();
//	
//	LinienSensor LINE_Sensor_Vorne		= new LinienSensor(0,1);
//	LinienSensor LINE_Sensor_Links		= new LinienSensor(2,3);
//	LinienSensor LINE_Sensor_Rechts		= new LinienSensor(4,5);
//	LinienSensor LINE_Sensor_Hinten		= new LinienSensor(6,7);
	
	
}
