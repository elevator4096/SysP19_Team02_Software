package team06;

import ch.ntb.inf.deep.runtime.ppc32.Task;
import team06.system.WlanSystem;
import team06.system.WurfSystem;


public class Main extends Task implements IO{
	
	private WurfSystem wurfSystem;
	private WlanSystem wlanSystem;
	
	
	/**
	 * Konstruktor
	 */	
	public Main() {
		wurfSystem = WurfSystem.getInstance();
		wlanSystem = WlanSystem.getInstance();
	}
	
	
	/**
     * Methode die Zyklisch aufgerufen wird
     */
	public void action() {
	
	}
	
	
	
	
	/**
	 * Task initialisieren
	 */
	static
    {
        try
        {
            Task task = new Main();
            task.period = Variablen.TASK_PERIOD;
            Task.install(task);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }	
		

}
