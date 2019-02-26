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
	MPIOSM_DIO WurfMagnet			= new MPIOSM_DIO(11, true );
	
	//PWM
	TPU_PWM    WurfZylinderPWM 	   	= new TPU_PWM(true, 0, 14_000_000 /TPU_PWM.tpuTimeBase, 0); // PWM 72 Hz
	


}
