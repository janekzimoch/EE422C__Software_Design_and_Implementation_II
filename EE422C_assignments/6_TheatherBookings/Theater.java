/* MULTITHREADING <MyClass.java>
 * EE422C Project 6 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * Slip days used: <0>
 * Spring 2019
 */
package assignment6;

import java.util.ArrayList;
import java.util.List;

public class Theater{

    /**
     * the delay time you will use when print tickets
     */
    private int printDelay = 50; // 50 ms.  Use it in your Thread.sleep()

    public void setPrintDelay(int printDelay) {
        this.printDelay = printDelay;
    }

    public int getPrintDelay() {
        return printDelay;
    }

    /**
     * Represents a seat in the theater
     * A1, A2, A3, ... B1, B2, B3 ...
     */
    static class Seat {
        private int rowNum;
        private int seatNum;

        public Seat(int rowNum, int seatNum) {
            this.rowNum = rowNum;
            this.seatNum = seatNum;
        }

        public int getSeatNum() {
            return seatNum;
        }

        public int getRowNum() {
            return rowNum;
        }

        @Override
        public String toString() {
            String result = "";
            int tempRowNumber = rowNum + 1;
            do {
                tempRowNumber--;
                result = ((char) ('A' + tempRowNumber % 26)) + result;
                tempRowNumber = tempRowNumber / 26;
            } while (tempRowNumber > 0);
            result += seatNum;
            return result;
        }
    }

    /**
     * Represents a ticket purchased by a client
     */
    static class Ticket {
        private String show;
        private String boxOfficeId;
        private Seat seat;
        private int client;
        public static final int ticketStringRowLength = 31;


        public Ticket(String show, String boxOfficeId, Seat seat, int client) {
            this.show = show;
            this.boxOfficeId = boxOfficeId;
            this.seat = seat;
            this.client = client;
        }

        public Seat getSeat() {
            return seat;
        }

        public String getShow() {
            return show;
        }

        public String getBoxOfficeId() {
            return boxOfficeId;
        }

        public int getClient() {
            return client;
        }

        @Override
        public String toString() {
            String result, dashLine, showLine, boxLine, seatLine, clientLine, eol;

            eol = System.getProperty("line.separator");

            dashLine = new String(new char[ticketStringRowLength]).replace('\0', '-');

            showLine = "| Show: " + show;
            for (int i = showLine.length(); i < ticketStringRowLength - 1; ++i) {
                showLine += " ";
            }
            showLine += "|";

            boxLine = "| Box Office ID: " + boxOfficeId;
            for (int i = boxLine.length(); i < ticketStringRowLength - 1; ++i) {
                boxLine += " ";
            }
            boxLine += "|";

            seatLine = "| Seat: " + seat.toString();
            for (int i = seatLine.length(); i < ticketStringRowLength - 1; ++i) {
                seatLine += " ";
            }
            seatLine += "|";

            clientLine = "| Client: " + client;
            for (int i = clientLine.length(); i < ticketStringRowLength - 1; ++i) {
                clientLine += " ";
            }
            clientLine += "|";

            result = dashLine + eol +
                    showLine + eol +
                    boxLine + eol +
                    seatLine + eol +
                    clientLine + eol +
                    dashLine;

            return result;
        }
    }

    private String show;
    private int seatsPerRow;
    private int numRows;
    private boolean reservedSeats[][];
    private int availableSeats;
    private List<Ticket> ticketsSoldLog;


    public Theater(int numRows, int seatsPerRow, String show) {
    	this.show = show;
    	this.seatsPerRow = seatsPerRow;
    	this.numRows = numRows;

    	reservedSeats = new boolean[this.numRows][this.seatsPerRow];
    	ticketsSoldLog = new ArrayList<Ticket>();

    	availableSeats = this.numRows*this.seatsPerRow;
    }

    public boolean seatsStillAvailable(){
    	if(availableSeats == 0){
    		return false;
    	}
    	else return true;
    }

    /**
     * Calculates the best seat not yet reserved
     *
     * @return the best seat or null if theater is full
     */
    public Seat bestAvailableSeat() {
    	for(int r = 0; r < numRows; r++){
        	for(int s = 0; s < seatsPerRow; s++){
        		if(reservedSeats[r][s] == false){
        			availableSeats--;
        			Seat seat = new Seat(r, s);
        			reservedSeats[r][s] = true;
        			return seat;
        		}
        	}
    	}
        return null;
    }

    /**
     * Prints a ticket for the client after they reserve a seat
     * Also prints the ticket to the console
     *
     * @param seat a particular seat in the theater
     * @return a ticket or null if a box office failed to reserve the seat
     */
    public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
    	Ticket ticket = new Ticket(show, boxOfficeId, seat, client);
    	System.out.println(ticket.toString());
    	System.out.println("");
    	try {
			Thread.sleep(getPrintDelay());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return ticket;
    }

    public boolean reserveSeat(String boxOfficeId, int client){
    	if(seatsStillAvailable()){
			Seat seat = bestAvailableSeat();
			Ticket ticket = printTicket(boxOfficeId, seat, client);
			addToTransactionLog(ticket);
			return true;
		}
		else{
       		System.out.println("Sorry,  we are sold out!");
			return false;
		}
    }

    /**
     * Lists all tickets sold for this theater in order of purchase
     *
     * @return list of tickets sold
     */
    public List<Ticket> getTransactionLog() {
        return ticketsSoldLog;
    }

    public void addToTransactionLog(Ticket ticket){
		ticketsSoldLog.add(ticket);
    }
}
