/*
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.HalteBedingung;
import team02.IO;
import team02.Konstanten;
import team02.ZustandBewegung;
import team02.beispiele.Orientierung;

public class BewegungsSystem
{
	private static BewegungsSystem bewegungsSystem;
	
	
	private ZustandBewegung zustandBewegung 		= ZustandBewegung.STOP;
	private HalteBedingung 	halteBedingung			= HalteBedingung .BIS_NICHTS;		
	private boolean 		bewegungsRichtung		= false; //vorwaerts 	= true
	private boolean 		drehRichtung			= false; //GUZ		= true
	private boolean 		inBewegung 				= false;

	private BewegungsSystem()
	{

	}

	public static BewegungsSystem getInstance()
	{
		if(bewegungsSystem==null)
		{
			bewegungsSystem = new BewegungsSystem();
		}
		return bewegungsSystem;
	}

	
	/**
     * diagrammDummy erzeugt k�nstlich Abhaengigkeiten fuer Klassendiagramm
     */
	protected void diagrammDummy()
	{
		Orientierung 	orientierung 	;
		Fahren 			fahren 			;
	}

    /**
     * kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
     * @param richtung true = vorwaerts
     * @param kreuzungsPos ?
     */
	public void fahreFreiBisKreuzung(boolean richtung,int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FAHRE_FREI;
		
		bewegungsRichtung = richtung;
		
		switch(kreuzungsPos)
		{
		case Konstanten.LINIE_VORNE : 
			halteBedingung	= HalteBedingung.BIS_KREUZUNG_VORNE;
			break;
		case Konstanten.LINIE_MITTE: 
			halteBedingung	= HalteBedingung.BIS_KREUZUNG_MITTE;
			break;
		case Konstanten.LINIE_HINTEN: 
			halteBedingung	= HalteBedingung.BIS_KREUZUNG_HINTEN;
			break;
		default:
			IO.debug.print("BewegungsSystem: ungueltige kreuzungsPos ");IO.debug.println(kreuzungsPos);
		}
	}

    /**
     * kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
     * @param kreuzungsPos ?
     */
	public void folgeLinieBisKreuzung(boolean richtung,int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE;
		
		bewegungsRichtung = richtung;
		
		switch(kreuzungsPos)
		{
		case Konstanten.LINIE_VORNE : 
			halteBedingung	= HalteBedingung.BIS_KREUZUNG_VORNE;
			break;
		case Konstanten.LINIE_MITTE: 
			halteBedingung	= HalteBedingung.BIS_KREUZUNG_MITTE;
			break;
		case Konstanten.LINIE_HINTEN: 
			halteBedingung	= HalteBedingung.BIS_KREUZUNG_HINTEN;
			break;
		default:
			IO.debug.print("BewegungsSystem: ungueltige kreuzungsPos ");IO.debug.println(kreuzungsPos);
		}
	}

    /**
     * ?
     */
	public void folgeLinieBisWandRueckwaerts()
	{
		bewegungsRichtung	= false;
		zustandBewegung 	= ZustandBewegung.FOLGE_LINIE;
		halteBedingung		= HalteBedingung.BIS_WAND;
	}

    /**
     * ?
     */
	public void richteAnWandAus()
	{
		zustandBewegung = ZustandBewegung.RICHTE_AN_WAND_AUS;
		halteBedingung	= HalteBedingung.BIS_WAND_VORNE;
	}

    /**
     * ?
     */
	public void dreheZuKorb()
	{
		drehRichtung	= false;
		zustandBewegung = ZustandBewegung.DREHE;
		halteBedingung	= HalteBedingung.BIS_DREHWINKEL_ERREICHT;
	}
	
	
	
	
	/**
     * Distanz
     * @param richtung vorwaerts = true
     * @param distanz
     */
	public void folgeLinie(boolean richtung, int distanz)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE;
		halteBedingung	= HalteBedingung.BIS_DISTANZ_ERREICHT;
	}
	
	/**
     * Drehe exakt 90 Grad nach rechts auf Linie
     */
	public void drehe90GradUZ()
	{
		drehRichtung 	= false; 
		zustandBewegung = ZustandBewegung.DREHE;
		halteBedingung	= HalteBedingung.BIS_LINIE_SENKRECHT;
	}
	
	/**
     * Drehe exakt 90 Grad nach links auf Linie
     */
	public void drehe90GradGUZ()
	{
		drehRichtung 	= true;
		zustandBewegung = ZustandBewegung.DREHE;
		halteBedingung	= HalteBedingung.BIS_LINIE_SENKRECHT;
	}
	
	/**
     * drehe Ungenau in Grad (Gegenuhrzeigersinn positiv!)
     */
	public void dreheUngenau(int winkel)
	{
		zustandBewegung = ZustandBewegung.DREHE;
		halteBedingung	= HalteBedingung.BIS_DREHWINKEL_ERREICHT;
	}
	
	/**folge Linie in Fahrtrichtung
	 * 
	 * @param fahrtRichtung
	 */
	public void folgeLinie(boolean fahrtRichtung)
	{
		if (fahrtRichtung)
		{
			//Vorwaertsfahrt
			if (IO.LINE_Sensor_Vorne.istLinieLinks())
			{
				//Korrigiere im GegenUhrzeigersinn
				Fahren.KorrekturKurve(fahrtRichtung, true);
			}
			else if (IO.LINE_Sensor_Vorne.istLinieRechts())
			{
				//Korrigiere im Uhrzeigersinn
				Fahren.KorrekturKurve(fahrtRichtung, false);
			}
			else
			{
				Fahren.geradeaus(Konstanten.DRIVING_SPEED);
			}
		}
		else
		{
			//Rueckwaertsfahrt
			if (IO.LINE_Sensor_Hinten.istLinieLinks())
			{
				//Korrigiere im Uhrzeigersinn
				Fahren.KorrekturKurve(fahrtRichtung, false);
			}
			else if (IO.LINE_Sensor_Hinten.istLinieRechts())
			{
				//Korrigiere im GegenUhrzeigersinn
				Fahren.KorrekturKurve(fahrtRichtung, true);
			}
			else
			{
				Fahren.geradeaus(-Konstanten.DRIVING_SPEED);
			}
		}
	}
	
	private boolean istHalteBedingungErfuellt()
	{
		switch(halteBedingung)
		{
		case BIS_KREUZUNG_VORNE:
			return IO.LINE_Sensor_Vorne.istLinieVorne(); 
		case BIS_KREUZUNG_MITTE:
			return IO.LINE_Sensor_Rechts.istLinieLinks() || IO.LINE_Sensor_Links.istLinieRechts();
		case BIS_KREUZUNG_HINTEN:
			return IO.LINE_Sensor_Hinten.istLinieVorne();
		default:
			IO.debug.print("BewegungsSystem: nicht implementierte Haltebedingung!");
		}
		
		return false;
	}


    /**
     * Pr�fe ob der Roboter noch eine Bewegung durchfuehrt
     * @return
     */
	public boolean istInBewegung()
	{
		return inBewegung;
	}
	
	/**
	 * update wird zyklisch aufgerufen
	 * Stoppt die Bewegung des Roboters sobald Zielposition erreicht wurde  
	 */
	public void update()
	{
		Fahren.update();
		
		if (istInBewegung())
		{
			if (istHalteBedingungErfuellt())
			{
				inBewegung 		= false;
				zustandBewegung = ZustandBewegung.STOP;
				halteBedingung 	= HalteBedingung.BIS_NICHTS;
				Fahren.stop();
			}
		}
		
	}
	
	

}
