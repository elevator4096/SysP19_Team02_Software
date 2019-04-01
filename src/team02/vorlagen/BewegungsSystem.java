/*
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.IO;
import team02.Konstanten;
import team02.ZustandBewegung;
import team02.beispiele.Orientierung;

public class BewegungsSystem
{
	private static BewegungsSystem bewegungsSystem;
	
	
	private ZustandBewegung zustandBewegung 		= ZustandBewegung.STOP;
	private int 			halteBedingung			=	0;		
	private boolean 		bewegungsRichtung		=	false; //vorwaerts 	= true
	private boolean 		drehRichtung			=	false; //GUZ		= true
	private boolean 		inBewegung 				=	false;

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
     * @param kreuzungsPos ?
     */
	public void fahreFreiBisKreuzungVorwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FAHRE_FREI_BIS_KREUZUNG_VORWAERTS;
		//halteBedingung	= 
	}

    /**
     * kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
     * @param kreuzungsPos ?
     */
	public void fahreFreiBisKreuzungRueckwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FAHRE_FREI_BIS_KREUZUNG_RUECKWAERTS;
	}

    /**
     * kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
     * @param kreuzungsPos ?
     */
	public void folgeLinieBisKreuzungVorwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_BIS_KREUZUNG_VORWAERTS;
	}

    /**
     * kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
     * @param kreuzungsPos ?
     */
	public void folgeLinieBisKreuzungRueckwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_BIS_KREUZUNG_RUECKWAERTS;
	}

    /**
     * ?
     */
	public void folgeLinieBisWandVorwaerts()
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_BIS_WAND_RUECKWAERTS;
	}

    /**
     * ?
     */
	public void richteAnWandAus()
	{
		zustandBewegung = ZustandBewegung.RICHTE_AN_WAND_AUS;
	}

    /**
     * ?
     */
	public void dreheZuKorb()
	{
		zustandBewegung = ZustandBewegung.DREHE_ZU_KORB;
	}
	
	
	
	
	/**
     * Distanz
     * @param distanz 0=fahre unendlich
     */
	public void folgeLinieVorwaerts(int distanz)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_VORWAERTS;
	}
	
	/**
     * Muss nicht zwingend wirklich Linie folgen!
     * @param distanz ?
     */
	public void folgeLinieRueckwaerts(int distanz)
	{
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_RUECKWAERTS;
	}
	
	/**
     * Drehe exakt 90 Grad nach rechts auf Linie
     */
	public void drehe90GradRechts()
	{
		zustandBewegung = ZustandBewegung.DREHE_90GRAD_RECHTS;
	}
	
	/**
     * Drehe exakt 90 Grad nach links auf Linie
     */
	public void drehe90GradLinks()
	{
		zustandBewegung = ZustandBewegung.DREHE_90GRAD_LINKS;
	}
	
	/**
     * drehe Ungenau in Grad (Gegenuhrzeigersinn positiv!)
     */
	public void dreheUngenau(int winkel)
	{
		zustandBewegung = ZustandBewegung.DREHE_UNGENAU;
	}

    /**
     * ?
     * @param speed
     */
	public void fahreFreiVorwaerts(int speed)
	{
		
	}

    /**
     */
	public void fahreFreiRueckwaerts()
	{
		
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
		
	}
	
	

}
