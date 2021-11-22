package ConcurrencyHomework;

public class FestivalStatistics implements Runnable{

    int participants;
    int noFull;
    int noFreePass;
    int noFullVIPPass;
    int noOneDayVIPPass;
    int noOneDay;
    Gate gate;

    public FestivalStatistics(Gate gate) {
        this.gate = gate;
    }

    @Override
    public void run() {
        boolean runOneMoreTime = true;
      while(gate.isOpen || runOneMoreTime) {
          initializeCalculation();
          computeNoOfTicketsByType();
          computeNoOfPeople();
          if (!gate.isOpen) runOneMoreTime = false;
          printStats();
          try {
              Thread.sleep(5000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }

    private void printStats() {
        System.out.println(
                participants + " people entered" + "\n" +
                noFull + " people have full tickets" + "\n" +
                noFreePass + " people have free passes" + "\n" +
                noFullVIPPass + " people have full VIP passes" + "\n" +
                noOneDay + " people have one-day passes" + "\n" +
                noOneDayVIPPass + " people have one-day VIP passes." + "\n" +
                " ========== LIVE STATS ================= >>>" + "\n");
    }

    private synchronized void computeNoOfTicketsByType() {
        for(TicketType ticketType : this.gate.ticketsList) {
            if (ticketType.equals(TicketType.FULL)) {
                noFull++;
            }
            else if (ticketType.equals(TicketType.FREEPASS)) {
                noFreePass++;
            }
            else if (ticketType.equals(TicketType.FULLVIP)) {
                noFullVIPPass++;
            }
            else  if (ticketType.equals(TicketType.ONEDAY)) {
                noOneDay++;
            }
            else  if (ticketType.equals(TicketType.ONEDAYVIP)) {
                noOneDayVIPPass++;
            }
        }
    }

    private synchronized void computeNoOfPeople() {
        participants = noFreePass + noOneDayVIPPass + noFullVIPPass + noFull + noOneDay;
    }

    private void initializeCalculation() {
        participants=0;
        noFull=0;
        noFreePass=0;
        noFullVIPPass=0;
        noOneDayVIPPass=0;
        noOneDay=0;
    }
}
