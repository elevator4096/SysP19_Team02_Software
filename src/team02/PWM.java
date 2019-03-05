/**
*
*
*@author chris
*@version 19.01.13.0
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_PWM;

public class PWM
{
	private final int CHAN_0_MIN = 980_000/TPU_PWM.tpuTimeBase;
	private final int CHAN_0_MAX = 1_980_000/TPU_PWM.tpuTimeBase;
	private final int CHAN_0_x	= 1_300_000/TPU_PWM.tpuTimeBase;
	
	//private TPU_PPWA CHAN_1;
	//private TPU_PPWA CHAN_2;
	
	//private TPU_PWM CHAN_3;
	//private TPU_PPWA CHAN_4;
	
	public PWM()
	{
		//CHAN_1 = new TPU_PPWA(true,1,true);
		//CHAN_2 = new TPU_PPWA(true,2,false);
		
		//CHAN_3 = new TPU_PWM(true, 3, pwmPeriod, pwmPeriod/3);
		//CHAN_4 = new TPU_PPWA(true,4,false);
	}
	
	public void run()
	{
		/*CHAN_0.update(pwmPeriod/3);
		System.out.print("Kanal 1: ");
		System.out.println(CHAN_1.read());
	
		CHAN_3.update(pwmPeriod/4);
		System.out.print("Kanal 4: ");
		System.out.println(CHAN_4.read());
		*/
	}
	
	public void setWurfZylinderPWM(int i)
	{
		double d;
		d = ((double)(i))/100;
		d = d*(CHAN_0_MAX-CHAN_0_MIN)+CHAN_0_MIN;
		IO.wurfZylinderPWM.update((int)d);
	}

	
	public void setMotorLinksPWM(int i)
	{
		double d;
		d = ((double)(i))/100;
		d = d*IO.PWM_Period;
		IO.motorLinksPWM.update((int)d);
	}
	


	public void setMotorRechtsPWM(int i)
	{
		double d;
		d = ((double)(i))/100;
		d = d*IO.PWM_Period;
		IO.motorRechtsPWM.update((int)d);
	}
	
	
}
