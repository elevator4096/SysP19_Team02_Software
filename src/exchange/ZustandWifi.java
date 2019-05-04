package exchange;

public interface ZustandWifi 
{
    int WURF_BEREIT 			=	0;
    int FANG_BEREIT				=	1;
    int FAHREN					=	2;
    int ERROR					=	3;
    int SETUP 					=  99;
    int NO_ROUTER_CONNECTION 	=  -1;
    int HEARTBEAT 				=  -2;
    int TEST 					=  42;
    int TEST_PASSED 			=  43;
    
    //Demo Wifi-Zustaende
    int DEMO_WARTEN		 		= 100;
    int DEMO_WAND				= 101;
    int DEMO_ZYLINDER_SPANNEN	= 102;
    int DEMO_FAHREN_WEIT 		= 103;
    int DEMO_DREHEN_90_GUZ 		= 104;
    int DEMO_FAHREN_KURZ 		= 105;
    int DEMO_DREHEN_90_UZ 		= 106;
    int DEMO_FAHREN_WAND		= 107;
    int DEMO_WERFEN				= 108;
    int DEMO_HALT				= 109;
    int DEMO_LEDS_AN			= 110;

    
}
