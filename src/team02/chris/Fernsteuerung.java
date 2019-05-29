package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import exchange.WlanSystem;
import exchange.ZustandWifi;
import team02.IO;
import team02.Konstanten;
import team02.Systeme;

public class Fernsteuerung extends Task implements IO, Systeme
{
	private static int demoZustand = ZustandWifi.DEMO_WARTEN;
	
	private double speed = 0.15;
	
	static
    {
            Task task = new Fernsteuerung();
            task.period = 200;
            Task.install(task);
    }
	
	public void action() {
		
			
		demoZustand = wlanSystem.getPartnerState();

		 switch (demoZustand)
	        {
	            case ZustandWifi.VORWAERTS:
	            {
	            	IO.MOTOR_links.updateSpeed(speed);
	            	IO.MOTOR_rechts.updateSpeed(speed);
	                break;
	            }
	            case ZustandWifi.RUECKWAERTS:
	            {
	            	IO.MOTOR_links.updateSpeed(-speed);
	            	IO.MOTOR_rechts.updateSpeed(-speed);
	            	break;
	            }
	            case ZustandWifi.DREHE_GUZ:
	            {
	            	IO.MOTOR_links.updateSpeed(-speed/2);
	            	IO.MOTOR_rechts.updateSpeed(speed/2);
	            	break;
	            }
	            case ZustandWifi.DREHE_UZ:
	            {
	            	IO.MOTOR_links.updateSpeed(speed/2);
	            	IO.MOTOR_rechts.updateSpeed(-speed/2);
	                break;
	            }
	            case ZustandWifi.KURVEV_GUZ:
	            {
	            	IO.MOTOR_links.updateSpeed(speed/2);
	            	IO.MOTOR_rechts.updateSpeed(speed);
	            	break;
	            }
	            case ZustandWifi.KURVEV_UZ:
	            {
	            	IO.MOTOR_links.updateSpeed(speed);
	            	IO.MOTOR_rechts.updateSpeed(speed/2);
	            	break;
	            }
	            case ZustandWifi.KURVER_GUZ:
	            {
	            	IO.MOTOR_links.updateSpeed(-speed/2);
	            	IO.MOTOR_rechts.updateSpeed(-speed);
	            	break;
	            }
	            case ZustandWifi.KURVER_UZ:
	            {
	            	IO.MOTOR_links.updateSpeed(-speed);
	            	IO.MOTOR_rechts.updateSpeed(-speed/2);
	            	break;
	            }
	            
	            
	            default:
	            {
	            	IO.MOTOR_links.updateSpeed(0.00);
	            	IO.MOTOR_rechts.updateSpeed(0.00);
	                break;
	            }
	        }
		 	wlanSystem.update();
		 	
		 	IO.MOTOR_links.update();
		 	IO.MOTOR_rechts.update();
		 	demoZustand = 0;
	}

}
