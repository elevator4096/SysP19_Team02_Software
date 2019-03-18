/**
 * @author loris
 * @version 2019.03.17
 */
package team02.vorlagen;

import team02.ZustandBewegung;

public class BewegungsSystem {
	private boolean inBewegung = false;
	private ZustandBewegung zustandBewegung = ZustandBewegung.STOP;
	
	/**
     * diagrammDummy erzeugt künstlich Abhaengigkeiten fuer Klassendiagramm
     * */
	private void diagrammDummy()
	{
		LinienSensoren 	linienSensoren 	;
		Orientierung 	orientierung 	;
		Fahren 			fahren 			;
	}
	
	/**
     * kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
     * @param kreuzungsPos ?
     * */
	public void fahreFreiBisKreuzungVorwaerts(int kreuzungsPos)
	{
		zustandBewegung = ZustandBewegung.FAHRE_FREI_BIS_KREUZUNG_VORWAERTS;
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
     * 	kreuzungsPos = LINIE_VORNE,LINIE_MITTE,LINIE_HINTEN
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
		zustandBewegung = ZustandBewegung.FOLGE_LINIE_BIS_WAND_VORWAERTS;
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
     * ?
     * @param speed
     */
	public void fahreFreiRueckwaerts(int speed)
	{
		
	}


    /**
     * Prï¿½fe ob der Roboter noch eine Bewegung durchfuehrt
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
		
	}
	
	

}
