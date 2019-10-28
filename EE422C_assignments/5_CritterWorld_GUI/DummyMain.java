package assignment5;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;

import assignment5.Critter.TestCritter;

public class DummyMain {
	
	public static void main(String[] args) {
		
//		// create critter + set their variables
//		try {
//			Critter.createCritter("MyCritter1");
//		} catch (InvalidCritterException e) {
//			e.printStackTrace();
//		}
//		// Clover c1 = new Clover();
//		MyCritter1 m1 = (MyCritter1) TestCritter.getPopulation().get(0);
//		// add to population
//		//m1.setX_coord_(10);
//		//m1.setY_coord_(2);
//
//		
//		CritterWorld myWorld = new CritterWorld();
//		//CritterWorld.add(m1);
//		System.out.println(m1.getEnergy());
//		m1.setEnergy(Params.START_ENERGY);
//		
//		Critter.worldTimeStep();
//		System.out.println(m1.getEnergy());
//		
//		Critter.worldTimeStep();
//		System.out.println(m1.getEnergy());
//		
//		m1.walk(0);
//		System.out.println(m1.getEnergy());
//
//		
		CritterWorld myWorld = new CritterWorld();
			try {
				Critter.createCritter("MyCritter1");
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
	        MyCritter1 c = (MyCritter1) TestCritter.getPopulation().get(0);
	        int step = 0;
	        int energyUsePerStep = Params.REST_ENERGY_COST
	                + Params.WALK_ENERGY_COST;
	        while (c.getEnergy() > 0) {
	        	System.out.println(Params.START_ENERGY
	                    - step * energyUsePerStep);
	        	System.out.println("getting: " + c.getEnergy());
	            Critter.worldTimeStep();
	        	//c.doTimeStep();
	            //c.subtractRestEnergy();
	            step++;
	        }
	       System.out.println("getting: " + c.getEnergy());
	        
	}
	
}
