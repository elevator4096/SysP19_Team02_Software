/**
*
*
*@author chris
*@version 19.01.13.0
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;

public class Input
{
	public static MPIOSM_DIO IN_1;
	public static MPIOSM_DIO IN_2;
	public static MPIOSM_DIO IN_3;
	public static MPIOSM_DIO IN_4;
	
	public Input()
	{
		IN_1 = new MPIOSM_DIO(12, false);
		IN_2 = new MPIOSM_DIO(13, false);
		IN_3 = new MPIOSM_DIO(14, false);
		IN_4 = new MPIOSM_DIO(15, false);
	}
	
	public boolean get(int i)
	{
		switch(i)
		{
			case 1:
				return IN_1.get();
			case 2:
				return IN_2.get();
			case 3:
				return IN_3.get();
			case 4:
				return IN_4.get();
			default:
				return false;
		}
	}
}

