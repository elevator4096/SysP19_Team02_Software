/**
*
*
*@author chris
*@version 19.01.13.0
*/
package team02;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;

public class Input implements Konstanten
{
	public static MPIOSM_DIO[] IN;
	
	
	public Input()
	{
		IN = new MPIOSM_DIO[Konstanten.ANZ_IN];
		int x = Konstanten.FIRST_IN;
		for(MPIOSM_DIO m : IN)
		{
			IN[x-Konstanten.FIRST_IN] = new MPIOSM_DIO(x, false);
			x++;
		}
	}
	
	public boolean get(int i)
	{
		if(i-Konstanten.FIRST_IN<Konstanten.ANZ_IN)
			return IN[i-Konstanten.FIRST_IN].get();
		return false;
	}
	
	public void print()
	{
		int x = Konstanten.FIRST_IN;
		for(MPIOSM_DIO m : IN )
		{
			System.out.print("IN ");
			System.out.print(x);
			System.out.print(" ");;
			System.out.println(m.get());
		}
	}
}

