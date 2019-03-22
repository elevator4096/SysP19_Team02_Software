/**
 * @author Phil
 * @version 2019.03.22
*/



package team06;

public class WurfSystem implements IO, Variablen{
	
	
	
	
	public void passgegner() {
		IO.SMSC_WurfMotor1.wurfgeschwindigkeit(Variablen.speedpassgegner);
		IO.SMSC_WurfMotor2.wurfgeschwindigkeit(Variablen.speedpassgegner);
	}
	
	public void korbschuss() {
		IO.SMSC_WurfMotor1.wurfgeschwindigkeit(Variablen.speedkorbschuss);
		IO.SMSC_WurfMotor2.wurfgeschwindigkeit(Variablen.speedkorbschuss);
	}
	
		
}
