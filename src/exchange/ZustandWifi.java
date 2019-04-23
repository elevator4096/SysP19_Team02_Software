package exchange;

public interface ZustandWifi 
{
    int WURF_BEREIT =0;
    int FANG_BEREIT=1;
    int FAHREN=2;
    int ERROR=3;
    int SETUP = 99;
    int NO_ROUTER_CONNECTION = -1;
    int HEARTBEAT = -2;
    int TEST = 42;
    int TEST_PASSED = 43;
}
