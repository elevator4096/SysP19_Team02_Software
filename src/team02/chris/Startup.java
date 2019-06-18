package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Systeme;

public class Startup extends Task implements IO, Systeme
{
	private static Task task;
	
	static {
		 new Startup();
	}

    public Startup() {
    	task = new Startup(1);
        task.period = 200;
        Task.install(task);
    }
    
    private Startup(int i) {
    	
    }

    public void action(){
        IO.OUT_LED1.set(!IO.OUT_LED1.get());

        if(!IO.IN_Taster_konf1.get()){
            new Main();
            IO.OUT_LED1.set(false);
            IO.OUT_LED2.set(true);
            Task.remove(task);
            
        }
        
        if(!IO.IN_Taster_konf2.get()) {
        	new FernsteuerungV2();
        	IO.OUT_LED1.set(false);
            IO.OUT_LED3.set(true);
            Task.remove(task);
        }
    }
}
