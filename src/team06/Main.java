/**
 * @Author Phil
 * @version 2019.03.26
 */
package team06;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;
import team06.system.WurfSystem;
import team06.aurelia.*;
import static team06.aurelia.Zustand.*;

public class Main extends Task {

	private static Main task;

	public Instanzen instanz;
	private Zustand zustand = Zustand.STARTZUSTAND;

	/**
	 * Konstruktor Instanz erzugen
	 */
	public Main() {

		instanz = new Instanzen();
		System.out.println("Main Konstruktor gestartet");

	}

	/**
	 * Methode, die zyklisch aufgerufen wird
	 */
	public void action() {
		Instanzen.wurfSystem.gibweg();
	}

	/**
	 * Spielablauf mit Switch/Case
	 */
	public void spielablauf() {

		switch (zustand) {

			case STARTZUSTAND:						//Startzustand
			{	
				startzustand();
				break;

			}

			case WURF_KURZ_VORBEREITEN:				//kurzen Pass an Partner vorbereiten
			{
				wurf_kurz_vorbereiten();
				break;

			}

			case WURF_LANG_VORBEREITEN:				//langen Pass an Partner vorbereiten
			{
				wurf_lang_vorbereiten();
				break;

			}

			case KORBWURF_VORBEREITEN:				//Wurf auf Korb vorbereiten
			{
				korbwurf_vorbereiten();
				break;

			}

			case WURFPOSITION:				//Wurf auf Korb vorbereiten
			{
				korbwurf_vorbereiten();
				break;

			}


		}

	}


	/**
	 * Methode Startzustand
	 */
	public void startzustand()
	{
		if(Instanzen.sMSC_WurfMotor2.gibInkrement() == 5) {
			zustand = WURF_KURZ_VORBEREITEN;
		}
		
		// Methode formulieren
	}

	/**
	 * Methode, um kurzen Pass an Partner vorzubereiten
	 */
	public void wurf_kurz_vorbereiten()
	{
		// Methode formulieren
	}

	/**
	 * Methode, um langen Pass an Partner vorzubereiten
	 */
	public void wurf_lang_vorbereiten()
	{
		// Methode formulieren
	}

	/**
	 * Methode, Korbwurf vorzubereiten
	 */
	public void korbwurf_vorbereiten()
	{
		// Methode formulieren
	}


	/**
	 * Task initialisieren/ SCI_OUT
	 */
	static {
		
		task = new Main();
		task.period = Variablen.TASK_PERIOD;
		Task.install(task);

		SCI sci1 = SCI.getInstance(SCI.pSCI1);
		sci1.start(9600, SCI.NO_PARITY, (short) 8);
		// Hook SCI1.out on System.out
		System.out = new PrintStream(sci1.out);

		System.out.println("Main Static gestartet");

	}

}
