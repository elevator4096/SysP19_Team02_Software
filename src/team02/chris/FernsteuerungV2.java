package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.ZustandWifi;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;

public class FernsteuerungV2 extends Task implements IO, Systeme
{
	private static int cache = 0;
	
	private int r,l;
	
	static
    {
            Task task = new FernsteuerungV2();
            task.period = 50;
            Task.install(task);
    }
	
	public void action() {
		
			
		cache = wlanSystem.getPartnerState();

		if(cache!=0){
			int r = (cache>>4 & 0x0F)-7;
			int l = (cache & 0x0F)-7;
			IO.MOTOR_links.updateSpeed(Konstanten.MAX_SPEED*l/7.0);
			IO.MOTOR_rechts.updateSpeed(Konstanten.MAX_SPEED*r/7.0);
		}

		wlanSystem.update();
		Systeme.wurfSystem.update();
		 	
		IO.MOTOR_links.update();
		IO.MOTOR_rechts.update();
	}

}
