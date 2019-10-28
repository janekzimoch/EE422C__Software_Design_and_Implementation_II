package assignment4;
/*
 * CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Ryed Ahmed>
 * <ra35335>
 * <16190>
 * <Janek Z>
 * <jsz323>
 * <16190>
 * Slip days used: <0>
 * Spring 2019
 */


//************ THIS IS HUMAN CRITTER ************


/*
 * FIGHTING
 * Human critter is a very random one. 
 * It doesn't seam to have much logic,
 * most of its actions are illogical
 * and lead to its eventual self destruction.
 * 
 * MOVEMENT
 * Human critter likes to run a lot. 
 * In fact running is the only way of moving this critter knows
 * This is due to its greed for money
 * It is so focused on materialistic world 
 * that it is capable of running itself to death.
 * 
 * REPRODUCTION
 * As I said Human's are a weird one.
 * They see too much value in monetary possesion,
 * and miss what is the most important in life.
 * This is love. 
 * As a result they either never find a life partner,
 * or wait to find one until when they are very week.
 */
public class Critter2 extends Critter {
	
	private int dir;
	private boolean dont_want_to_reproduce;
	private int turns_left_until_walking;
	
    @Override
    public String toString() {
        return "2";
    }
    
    public Critter2() {
    	dont_want_to_reproduce = false;
		this.setHaveWalked(false);
    	turns_left_until_walking = 3;
    }

	@Override
	public void doTimeStep() {
		if(turns_left_until_walking == 0) {
			dir = Critter.getRandomInt(8);
			run(dir);
			this.setHaveWalked(true);
			turns_left_until_walking = 3;
		}
		else {
			turns_left_until_walking--;
			this.setHaveWalked(false);
		}
		
		int out_of_10_how_weird_am_I = Critter.getRandomInt(11);
		if (out_of_10_how_weird_am_I == 10) {
			dont_want_to_reproduce = true;
		}
		
		if(!dont_want_to_reproduce) {
			if (20 < getEnergy() && getEnergy() < 60) {
	            Critter2 child = new Critter2();
	            reproduce(child, Critter.getRandomInt(8));
	        }
		}
	}
	

	@Override
	public boolean fight(String oponent) {
		if(oponent.equals("2") || oponent.equals("1")){ // fight only with other Humans and Aggresive critters
			return true;
		}
		else {
			dir = Critter.getRandomInt(8);
			runInFight(dir);
			return false;
		}
	}
}
