/**
 * @Author Phil
 * @version 2019.03.26
 */
package team06.aurelia;

import java.io.PrintStream;

import ch.ntb.inf.deep.runtime.mpc555.driver.SCI;
import ch.ntb.inf.deep.runtime.ppc32.Task;

import team06.Instanzen;
import team06.Variablen;
import static team06.aurelia.Zustand.*;

public class Main extends Task {

	private static Main task;

	public Instanzen instanz;
	private Zustand zustand = STARTZUSTAND;
	
	private int passKurz; 		//Zaehler für kurze Paesse geworfen
	private int passLang;		//Zaehler für lange Paesse geworfen
	private int gefangen;		//Zaehler für gefangene Baelle

	/**
	 * Konstruktor der Klasse Main
	 * 
	 * Instanz erzeugen
	 */
	public Main() {

		instanz = new Instanzen();
		passKurz = 0;
		passLang = 0;
		gefangen = 0;
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

			case WURF_KURZ:							//kurzen Pass an Partner vorbereiten
			{
				wurf_kurz();
				break;
			}

			case WURF_LANG:							//langen Pass an Partner vorbereiten
			{
				wurf_lang();
				break;
			}

			case POSITION_WECHSELN:					//Positionswechsel einleiten
			{
				position_wechseln();
				break;
			}

			case VORRUECKEN:						//in naechsten Spielfelddabschnitt vorruecken
			{
				vorruecken();
				break;
			}

			case RUECKWAERTS_AN_WAND:				//rueckwaerts an Wand fahren, um neu auszurichten und Fangen vorzubereiten
			{
				rueckwaerts_an_wand();
				break;
			}

			case FANGPOSITION:						//Bereit zum Fangen
			{
				fangposition();
				break;
			}

			case ENDE:								//Spiel beendet
			{
				ende();
				break;
			}

			case FEHLER:							//Spielabbruch durch Fehler
			{
				fehler();
				break;
			}
		}
	}


	/**
	 * Methode Startzustand
	 */
	public void startzustand()
	{
		
		// Methode formulieren
	}

	/**
	 * Methode, für kurzen Pass an Partner
	 */
	public void wurf_kurz()
	{
		// Methode formulieren
	}

	/**
	 * Methode, für langen Pass an Partner
	 */
	public void wurf_lang()
	{
		// Methode formulieren
	}

	/**
	 * Methode, um Positionswechsel einzuleiten
	 */
	public void position_wechseln()
	{
		// Methode formulieren
	}

	/**
	 * Methode, um in den naechsten Feldabschnitt vorzuruecken
	 */
	public void vorruecken()
	{
		// Methode formulieren
	}

	/**
	 * Methode, um rueckwaerts an Wand zu fahren und neue Position einzunehmen
	 */
	public void rueckwaerts_an_wand()
	{
		// Methode formulieren
	}

	/**
	 * Methode, um Ball zu fangen
	 */
	public void fangposition()
	{
		// Methode formulieren
	}

	/**
	 * Methode, Spiel zu beenden
	 */
	public void ende()
	{
		// Methode formulieren
	}

	/**
	 * Methode, um Fehler zu melden/Spiel abzubrechen
	 */
	public void fehler()
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
