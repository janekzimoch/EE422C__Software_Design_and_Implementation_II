package assignment5;

/*
* CRITTERS Main.java
* EE422C Project 5 submission by
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

import java.util.*;
/**
 * Wise critter, fight only when it has energy
 */
public class MyCritter1 extends Critter.TestCritter {

	@Override
	public void doTimeStep() {
		walk(0);
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 10) return true;
		return false;
	}

	public String toString() {
		return "5";
	}

	public void test (List<Critter> l) {

	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.DIAMOND;
	}

    @Override
    public String displayCharacter(){
    	String str = "************ THIS IS MyCritter1 ************ \n"
    			+ "We apologize... \n"
    			+ "but ther is no characteristic provided for Goblin \n"
    			+ "in this version of Critter Game. Sorry";
    	return str;
    }

    public static String runStats(List<Critter> critter) {
        String str = "";
        str += ("there are " + critter.size() + " MyCritter1 on the map " + "represented by: " + "5");
        return str;
    }
}