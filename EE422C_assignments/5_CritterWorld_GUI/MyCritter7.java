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
 * Fighter, always fight when encounter other critters.
 */
public class MyCritter7 extends TestCritter {

	@Override
	public void doTimeStep() {
	}

	@Override
	public boolean fight(String opponent) {

		return true;
	}

	@Override
	public String toString () {
		return "7";
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.SQUARE;
	}

    @Override
    public String displayCharacter(){
    	String str = "************ THIS IS MyCritter7 ************ \n"
    			+ "We apologize... \n"
    			+ "but ther is no characteristic provided for MyCritter7 \n"
    			+ "in this version of Critter Game. Sorry";
    	return str;
    }

    public static String runStats(List<Critter> critter) {
        String str = "";
        str += ("there are " + critter.size() + " Critter7 on the map " + "represented by: " + "7");
        return str;
    }
}