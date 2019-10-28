/*
 * Do not change or submit this file.
 */

package assignment5;

import java.util.List;

import assignment5.Critter.TestCritter;

public class Clover extends TestCritter {

    public String toString() {
        return "@";
    }

    public boolean fight(String opponent) {
        // same species as me!
        if (toString().equals(opponent)) {
            /* try to move away */
            walk(Critter.getRandomInt(8));
        }
        return false;
    }

    public void doTimeStep() {
        setEnergy(getEnergy() + Params.PHOTOSYNTHESIS_ENERGY_AMOUNT);
    }

    @Override
    public CritterShape viewShape() {
        return CritterShape.CIRCLE;
    }
    @Override
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.GREEN;
    }

    public static String runStats(List<Critter> critter) {
        String str = "";
        for (Object obj : critter) {
        	// TODO:
        }
        str += ("there are " + critter.size() + " Clover on the map " + "represented by: " + "@");
        return str;
    }

    @Override
    public String displayCharacter(){
    	String str = "************ THIS IS CLOVER ************ \n"
    			+ "clover is food for other critters"
    			+ "it doesn't do anything by itself";
    	return str;
    }

}
