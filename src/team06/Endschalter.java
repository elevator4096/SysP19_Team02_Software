package team06;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;

public class Endschalter {

	public MPIOSM_DIO schalter;


	public Endschalter(int pin, boolean inout) {
		schalter = new MPIOSM_DIO(pin, inout);
	}

	public boolean schalterzustand() {
		return schalter.get();
	}

}
