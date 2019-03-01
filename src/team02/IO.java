/*
Input/Output Pinbelegung

*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public interface IO {
	
	//Input
	MPIOSM_DIO taster1 				= new MPIOSM_DIO( 5, false);
	MPIOSM_DIO taster2 				= new MPIOSM_DIO( 6, false);
	
	//Output
	MPIOSM_DIO testLed 				= new MPIOSM_DIO(10, true );
	MPIOSM_DIO wurfMagnet			= new MPIOSM_DIO(11, true );

	//PWM Periodendauer
	int wurfZylinderPWMPeriod 		= 14_000_000/TPU_PWM.tpuTimeBase; 	// PWM 	   72 Hz
	int motorenPWMPeriod			= 	  50_000/TPU_PWM.tpuTimeBase;   // PWM 20'000 Hz
	
	//PWM
	TPU_PWM    wurfZylinderPWM 	   	= new TPU_PWM(true, 0, wurfZylinderPWMPeriod , 0); 
	TPU_PWM    motorLinksPWM 	   	= new TPU_PWM(true, 1, motorenPWMPeriod		 , 0); 
	TPU_PWM    motorRechtsPWM 	   	= new TPU_PWM(true, 2, motorenPWMPeriod		 , 0);
	


}
