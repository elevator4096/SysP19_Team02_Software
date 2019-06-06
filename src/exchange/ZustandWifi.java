package exchange;

public interface ZustandWifi 
{
    int WURF_BEREIT 			=	4;
    int FANG_BEREIT				=	1;
    int FAHREN					=	2;
    int ERROR					=	3;
    int SETUP 					=  99;
    int NO_ROUTER_CONNECTION 	=  -1;
    int HEARTBEAT 				=  -2;
    int TEST 					=  42;
    int TEST_PASSED 			=  43;
    int START 					= 100;
    
    //Demo Wifi-Zustaende
    int DEMO_WARTEN		 		= 100;
    int DEMO_WAND				= 101;
    int DEMO_WAND_AUS			= 102;
    int DEMO_DREHEN_90_UZ 		= 103;
    int DEMO_FAHREN_WEIT 		= 104;
    int DEMO_DREHEN_90_GUZ 		= 105;
    int DEMO_ZYLINDER_HOCH		= 106;
    int DEMO_ZYLINDER_RUNTER 	= 107;
    int DEMO_HALT				= 110;
    
    int DEMO_POS1				= 111;
    int DEMO_POS2				= 112;
    int DEMO_NORM_DIST			= 113;
    
    //Bereich 1000-1100 fuer Zylinderspannen(0-100%)
    int DEMO_WERFEN				= 1101;
    
    
    //Fuer Fernsteuerung
    int VORWAERTS				= 4200;
    int RUECKWAERTS				= 4201;
    int DREHE_UZ				= 4202;
    int DREHE_GUZ				= 4203;
    
    int KURVEV_UZ				= 4204;
    int KURVEV_GUZ				= 4205;
    
    int KURVER_UZ				= 4206;
    int KURVER_GUZ				= 4207;
    
    int SPANNE90				= 4208;
    int SPANNE40				= 4209;
    int SPANNE08				= 4210;
    int WERFE					= 4211;
    
    int LOESE_WAND				= 4212;
}
