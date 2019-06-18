package team02.chris;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team02.IO;
import team02.Systeme;

public class Startup extends Task implements IO, Systeme
{

    static {
        Task task = new Startup();
        task.period = 200;
        Task.install(task);
    }

    public Startup() {

    }

    public void action(){
        IO.OUT_LED1.set(!IO.OUT_LED1.get());

        if(IO.IN_Taster_konf1.get()){
            new Main();
            IO.OUT_LED1.set(false);
            IO.OUT_LED2.set(true);
            Task.remove(this);
        }
    }
}
