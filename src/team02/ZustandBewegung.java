/*
 * @Author Loris
 * @version 2019.03.13
 */
package team02;

public enum ZustandBewegung {
	FAHRE_FREI_BIS_KREUZUNG_VORWAERTS,
	FAHRE_FREI_BIS_KREUZUNG_RUECKWAERTS,
	FOLGE_LINIE_BIS_KREUZUNG_VORWAERTS,
	FOLGE_LINIE_BIS_KREUZUNG_RUECKWAERTS,
	FOLGE_LINIE_BIS_WAND_RUECKWAERTS,
	DREHE_ZU_KORB,
	FOLGE_LINIE_VORWAERTS,
	FOLGE_LINIE_RUECKWAERTS,
	DREHE_90GRAD_RECHTS,
	DREHE_90GRAD_LINKS,
	DREHE_UNGENAU,
	
	FAHRE_FREI,
	FOLGE_LINIE,
	RICHTE_AN_WAND_AUS,
	RICHTE_AN_KORB_AUS,
	DREHE,
	STOP,

}
