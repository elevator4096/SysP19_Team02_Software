package team02.loris;

import ch.ntb.inf.deep.runtime.mpc555.driver.TPU_DIO;

public class SiebenSegBsp {
	
	TPU_DIO leda, ledb, ledc, ledd, lede, ledf, ledg, leddp;

	public SiebenSegBsp() {

		// LED ausschalten true = aus
		leda  = new TPU_DIO(false, 11, true);
		ledb  = new TPU_DIO(false, 9, true);
		ledc  = new TPU_DIO(false, 5, true);
		ledd  = new TPU_DIO(false, 3, true);
		lede  = new TPU_DIO(false, 1, true);
		ledf  = new TPU_DIO(false, 13, true);
		ledg  = new TPU_DIO(false, 15, true);
		leddp = new TPU_DIO(false, 7, true);

	}
	
    private final int[] siebenSegCode = 
    {
        0b00111111,     // 0 
        0b00000110,     // 1
        0b01011011,     // 2
        0b01001111,     // 3
        0b01100110,     // 4
        0b01101101,     // 5
        0b01111101,     // 6
        0b00000111,     // 7
        0b01111111,     // 8
        0b01101111,     // 9    
        0b11111001      // E = Error
        
    };   
    
    public void setSiebenSeg(int ziffer)
    {
        //Pruefen ob Ziffer zwischen [0-9] sonst Error ausgeben
        if ((ziffer<0)||(ziffer>9)) ziffer = 10; // Error  
        
        leddp.set((siebenSegCode[ziffer]&0b10000000)>0);
        leda .set((siebenSegCode[ziffer]&0b01000000)>0); 
        ledb .set((siebenSegCode[ziffer]&0b00100000)>0);
        ledc .set((siebenSegCode[ziffer]&0b00010000)>0);
        ledd .set((siebenSegCode[ziffer]&0b00001000)>0); 
        lede .set((siebenSegCode[ziffer]&0b00000100)>0);
        ledf .set((siebenSegCode[ziffer]&0b00000010)>0);
        ledg .set((siebenSegCode[ziffer]&0b00000001)>0);     
    }    
    
	public void rblinken() {
		lede.set(!lede.get());
		ledg.set(!ledg.get());
	}
	
	public void dleuchten() {
		leda.set(false); // LED ausschalten
		ledb.set(false);
		ledc.set(false);
		ledd.set(false); // LED ausschalten
		lede.set(false);
		ledf.set(false);
		leddp.set(false);
	}
	
	public void strichblinken() {
		ledg.set(!ledg.get());
	}
	public void löschen()
	{
		leda.set(true); // LED ausschalten
		ledb.set(true);
		ledc.set(true);
		ledd.set(true); // LED ausschalten
		lede.set(true);
		ledf.set(true);
		ledg.set(true); // LED ausschalten
		leddp.set(true);
	}

}
