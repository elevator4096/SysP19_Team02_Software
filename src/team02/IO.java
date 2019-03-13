/**
 * @author Loris
 * @version 2019.03.05
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;
import ch.ntb.inf.deep.runtime.mpc555.driver.QADC_AIN;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_FQD;
import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;
import team02.chris.Motor;

public interface IO {
	
	//Input
	MPIOSM_DIO IN_Taster1 				= new MPIOSM_DIO( 5, false);
	MPIOSM_DIO IN_Taster2				= new MPIOSM_DIO( 6, false);
	MPIOSM_DIO IN_BallDetektor			= new MPIOSM_DIO( 7, false);

	QADC_AIN   ADC_B					= new QADC_AIN();
	int 	   PIN_LeftSharpChannel		= 0;

	//Output
	MPIOSM_DIO OUT_Test_Led				= new MPIOSM_DIO(10, true );
	MPIOSM_DIO OUT_Magnet				= new MPIOSM_DIO(11, true );

	//PWM Periodendauer
	int 	   PERIOD_WurfZyl			= 14_000_000/TPU_PWM.tpuTimeBase; 		// PWM 	   72 Hz
	int 	   PERIOD_Motoren 			=     50_000/TPU_PWM.tpuTimeBase;   	// PWM 20'000 Hz

	//PWM
	TPU_PWM    PWM_WurfZylinder		   	= new TPU_PWM(true, 0, PERIOD_WurfZyl, 0);
	TPU_PWM    PWM_MotorLinksPWM 	   	= new TPU_PWM(true, 1, PERIOD_Motoren, 0);
	TPU_PWM    PWM_MotorRechtsPWM 	   	= new TPU_PWM(true, 2, PERIOD_Motoren, 0);


	//Motoren
	Motor 		MOTOR_links = new Motor(IO.FQD_Links, IO.PWM_MotorLinksPWM, IO.PERIOD_Motoren);
	Motor 		MOTOR_rechts = new Motor(IO.FQD_Rechts, IO.PWM_MotorRechtsPWM, IO.PERIOD_Motoren);

	TPU_FQD    FQD_Links 				= new TPU_FQD(true, 3);
	TPU_FQD    FQD_Rechts 				= new TPU_FQD(true, 5);
}
