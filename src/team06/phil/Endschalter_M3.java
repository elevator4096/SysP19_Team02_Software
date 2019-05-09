package team06.phil;

import ch.ntb.inf.deep.runtime.mpc555.driver.MPIOSM_DIO;

public class Endschalter_M3 {

	public MPIOSM_DIO schalter;


	public Endschalter_M3(int pin, boolean inout) {
		schalter = new MPIOSM_DIO(pin, inout);
	}

	public boolean schalterzustand() {
		return schalter.get();
	}

}
