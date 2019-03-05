/**
 * @author Loris
 * @version 2019.03.05
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public interface IO {
	
	//Input
	MPIOSM_DIO IN_Taster1 			= new MPIOSM_DIO( 5, false);
	MPIOSM_DIO IN_Taster2			= new MPIOSM_DIO( 6, false);
	
	//Output
	MPIOSM_DIO OUT_Test_Led			= new MPIOSM_DIO(10, true );
	MPIOSM_DIO OUT_Magnet			= new MPIOSM_DIO(11, true );

	//PWM Periodendauer
	int PWM_WurfZyl					= 14_000_000/TPU_PWM.tpuTimeBase; 	// PWM 	   72 Hz
	int PWM_Period 					= 	  50_000/TPU_PWM.tpuTimeBase;   // PWM 20'000 Hz
	
	//PWM
	TPU_PWM    wurfZylinderPWM 	   	= new TPU_PWM(true, 0, PWM_WurfZyl, 0);
	TPU_PWM    motorLinksPWM 	   	= new TPU_PWM(true, 1, PWM_Period, 0);
	TPU_PWM    motorRechtsPWM 	   	= new TPU_PWM(true, 2, PWM_Period, 0);
	


}
