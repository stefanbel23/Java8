package ConcurrencyHomework;

public class FestivalAttendee implements Runnable {

    TicketType ticketType;
    Gate gate;

    public FestivalAttendee(TicketType ticketType, Gate gate) {
        this.ticketType = ticketType;
        this.gate = gate;
    }

    @Override
    public void run() {
        this.gate.addTicket(ticketType);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
