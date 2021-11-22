package ConcurrencyHomework;

import java.util.ArrayList;

public class Gate {


    public Gate() {

    }
    boolean isOpen = true;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    ArrayList<TicketType> ticketsList = new ArrayList<>();

    public ArrayList<TicketType> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(ArrayList<TicketType> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public void addTicket(TicketType type){
        ticketsList.add(type);
    }
}
