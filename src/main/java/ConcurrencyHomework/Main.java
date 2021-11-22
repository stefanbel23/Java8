package ConcurrencyHomework;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Gate gate = new Gate();
        FestivalStatistics statistics = new FestivalStatistics(gate);
        Thread statisticsThread = new Thread(statistics);
        FestivalAttendee festivalAttendee;

        TicketType ticketType;

        gate.setOpen(true);
        statisticsThread.start();

        for (int i=1; i<=100; i++) {
            ticketType = TicketType.getRandom();
            festivalAttendee = new FestivalAttendee(ticketType, gate);
            Thread festivalAttendeeThread = new Thread(festivalAttendee);
            festivalAttendeeThread.start();
            festivalAttendeeThread.join();
        }
        gate.setOpen(false);


    }
}
