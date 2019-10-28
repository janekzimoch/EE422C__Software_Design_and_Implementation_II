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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

import assignment6.Theater.Seat;
import assignment6.Theater.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Thread;

public class BookingClient {

	private Map<String, Integer> office;
	private Theater theater;
	private ExecutorService executor;
	private boolean outOfTickets;

    /**
     * @param office  maps box office id to number of customers in line
     * @param theater the theater where the show is playing
     */
    public BookingClient(Map<String, Integer> office, Theater theater) {
    	this.office = office;
    	this.theater = theater;
    }

    /**
     * Starts the box office simulation by creating (and starting) threads
     * for each box office to sell tickets for the given theater
     *
     * @return list of threads used in the simulation,
     * should have as many threads as there are box offices
     */
    public List<Thread> simulate() {
    	executor = Executors.newFixedThreadPool(office.size());
    	List<Thread> listOfThreads = new ArrayList<Thread>();
    	int client = 0;
    	int total = 0;
    	boolean started = false;
    	while(theater.seatsStillAvailable() && (total != 0 || !started)){
    		started = true;
    		total = 0;
	    	for(Map.Entry<String, Integer> entry : office.entrySet()){
	    		if(entry.getValue() != 0){
	    			total = total + entry.getValue();
	    			client++;
	    			office.put(entry.getKey(), entry.getValue()-1);
	    			executor.execute(new BoxOffice(entry.getKey(), client));
	    		}
	    	}
    	}

	    Set<Thread> threads = Thread.getAllStackTraces().keySet();
    	for (Thread t : threads) {
    		listOfThreads.add(t);
    	}
    	executor.shutdown();
        return listOfThreads;
    }

    public static void main(String[] args) {
	    Map<String, Integer> office = new HashMap<String, Integer>() {{
	        put("BX1", 4);
	        put("BX2", 4);
	        put("BX3", 3);
	        put("BX4", 4);
	        put("BX5", 4);
	    }};
    	Theater theater = new Theater(3, 5, "Ouija");
    	BookingClient bc = new BookingClient(office, theater);
    	bc.simulate();
    	//System.out.println(theater.getTransactionLog());
    }

    private class BoxOffice implements Runnable {

    	private String boxOfficeId;
    	private int client;

    	public BoxOffice(String boxOfficeId, int client){
    		this.boxOfficeId = boxOfficeId;
    		this.client = client;
    	}

    	@Override
    	public void run() {
//    		synchronized (theater){
//        		boolean succesfulTransaction = theater.reserveSeat(boxOfficeId, client);
//        		if(!succesfulTransaction){
////        			System.out.println("Sorry, we are sold out!");
//        			executor.shutdownNow();
//        		}
//    		}
    		synchronized (theater){
	        	if(theater.seatsStillAvailable()){
	        		Seat seat = theater.bestAvailableSeat();
	        		Ticket ticket = theater.printTicket(boxOfficeId, seat, client);
	        		theater.addToTransactionLog(ticket);
	        	}
	        	
	        	else{
	        		if(!outOfTickets){
		        		System.out.println("Sorry, we are sold out!");
		        		outOfTickets = true;
	        		}
//	    			executor.shutdownNow();
	    		}
    		}
    	}
    }
}
