/**
*
*
*@author chris
*@version 19.01.13.0
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;

public class Output
{

	public static MPIOSM_DIO[] OUT;
	
	public Output()
	{

		
		OUT = new MPIOSM_DIO[5];
		
		OUT[1] 	= new MPIOSM_DIO(5, true);
		OUT[2]	= new MPIOSM_DIO(6, true);
		OUT[3]	= new MPIOSM_DIO(6, true);
		OUT[4]	= new MPIOSM_DIO(8, true);
				}
	
	public void set(int i, boolean b)
	{
		OUT[i].set(b);
	}
	public boolean get(int i)
	{
		return OUT[i].get();
	}
}

