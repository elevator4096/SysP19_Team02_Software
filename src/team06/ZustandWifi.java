package team06;

public enum ZustandWifi
{
    WURF_BEREIT(0),
    FANG_BEREIT(1),
    FAHREN(2),
    ERROR(3);

    public int number;
    ZustandWifi(int number)
    {
        this.number = number;
    }
}
