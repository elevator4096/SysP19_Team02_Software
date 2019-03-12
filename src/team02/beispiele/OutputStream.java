/**
*
*
*@author chris
*@version 19.01.13.0
*/
package team02.beispiele;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;

public class OutputStream 
{

	public OutputStream()
	{
		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short)8);
		//Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);	
	}
}
