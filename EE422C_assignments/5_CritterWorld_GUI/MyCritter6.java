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

import java.util.List;

import assignment5.Critter.TestCritter;
/**
 * Runner, always run when encounter other critters.
 */
public class MyCritter6 extends TestCritter {

	@Override
	public void doTimeStep() {
	}

	@Override
	public boolean fight(String opponent) {
		run(getRandomInt(8));
		return false;
	}

	@Override
	public String toString () {
		return "6";
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}

    @Override
    public String displayCharacter(){
    	String str = "************ THIS IS MyCritter6 ************ \n"
    			+ "We apologize... \n"
    			+ "but ther is no characteristic provided for MyCritter6 \n"
    			+ "in this version of Critter Game. Sorry";
    	return str;
    }

    public static String runStats(List<Critter> critter) {
        String str = "";
        str += ("there are " + critter.size() + " Critter6 on the map " + "represented by: " + "6");
        return str;
    }
}