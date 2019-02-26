/**
*
*@author chris
*@version 19.01.13.0
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;

public class Output implements Konstanten
{

	public static MPIOSM_DIO[] OUT;
	
	public Output()
	{
		OUT = new MPIOSM_DIO[Konstanten.ANZ_OUT];
		
		OUT[0] 	= new MPIOSM_DIO(5, true);
		OUT[1]	= new MPIOSM_DIO(6, true);
		OUT[2]	= new MPIOSM_DIO(6, true);
		OUT[3]	= new MPIOSM_DIO(8, true);
	}
	
	public void set(int i, boolean b)
	{
		if(i-Konstanten.FIRST_OUT<Konstanten.ANZ_OUT)
			OUT[i-Konstanten.FIRST_OUT].set(b);
	}
	public boolean get(int i)
	{
		if(i-Konstanten.FIRST_OUT<Konstanten.ANZ_OUT)
			return OUT[i-Konstanten.FIRST_OUT].get();
		return false;
	}
	
	public void print()
	{
		int x = Konstanten.FIRST_OUT;
		for(MPIOSM_DIO m : OUT )
		{
			System.out.print("OUT ");
			System.out.print(x);
			System.out.print(" ");;
			System.out.println(m.get());
		}
	}
}

