package ConcurrencyHomework;

public enum TicketType {
    FULL,
    FULLVIP,
    FREEPASS,
    ONEDAY,
    ONEDAYVIP;

    public static TicketType getRandom() {
        return  values()[(int) (Math.random()* values().length)];

    }
}


