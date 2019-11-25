package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.ZustandWifi;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;

public class FernsteuerungV2 extends Task implements IO, Systeme
{
	private static int cache = 0;
	
	private static Task task;
	
	private int r,l;

	private static byte[] transmit;
	
	static
    {
            
    }
	
	public FernsteuerungV2() {
		task = new FernsteuerungV2(1);
        task.period = 50;
        Task.install(task);
        transmit = new byte[4];
	}
	
	private FernsteuerungV2(int i) {
		
	}
	
	public void action() {
		IO.OUT_LED4.set(!IO.OUT_LED4.get());
		
		if(!(IO.IN_Taster_konf1.get() || IO.IN_Taster_konf2.get())) {
            IO.OUT_LED3.set(false);
			IO.OUT_LED4.set(false);
			Task.remove(task);
			task = null;
			new Startup();
		}

		IO.MOTOR_links.update();
		IO.MOTOR_rechts.update();
			
		cache = wlanSystem.getPartnerState();
		debug.println(cache);

		if(!(cache == -1 || cache ==0)){
			//Decrypt Data
			transmit[0] = (byte) ((cache >> 24) & 0xff);
			transmit[1] = (byte) ((cache >> 16) & 0xff);
			transmit[2] = (byte) ((cache >> 8) & 0xff);
			transmit[3] = (byte) ((cache >> 0) & 0xff);

			int r = (transmit[2]);
			int l = (transmit[3]);

			boolean buttonA = ((transmit[1]&0x01) !=0);
			boolean buttonB = ((transmit[1]&0x02) !=0);
			boolean buttonX = ((transmit[1]&0x04) !=0);
			boolean buttonY = ((transmit[1]&0x08) !=0);
			boolean buttonRZ= ((transmit[1]&0x10) !=0);
			boolean buttonLT3 = ((transmit[1]&0x20)!=0);

			int zylValue = transmit[0]&0x0F;


			if(!buttonLT3) {
                IO.MOTOR_links.updateSpeed(Konstanten.MAX_SPEED * l / 127.0);
                IO.MOTOR_rechts.updateSpeed(Konstanten.MAX_SPEED * r / 127.0);
            }else{
                IO.MOTOR_links.updateSpeed(Konstanten.MAX_SPEED * l / 255.0);
                IO.MOTOR_rechts.updateSpeed(Konstanten.MAX_SPEED * r / 255.0);
            }
			Systeme.wurfSystem.zylinderSpannen((int)(zylValue*(87.0/15.0)+8));

			if(buttonRZ){
				Systeme.wurfSystem.ballWerfen();
			}
			if(buttonY){
				Systeme.wurfSystem.Wandauf();
			}


		}else {
			IO.MOTOR_links.updateSpeed(0);
			IO.MOTOR_rechts.updateSpeed(0);
		}

		wlanSystem.update();
		Systeme.wurfSystem.update();
		 	

	}

}
