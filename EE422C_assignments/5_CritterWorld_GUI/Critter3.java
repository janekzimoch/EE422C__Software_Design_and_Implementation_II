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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

import assignment5.Critter.CritterShape;


//************ THIS IS SCARED CRITTER ************
 //Implements look() in doTimeStep

/*
 * MOVEMENT
 * Scared critter often gets so scared that it panics
 * and doesn't not do anything.
 * In fact it is this scared 20% of the times.
 *
 * It also is scared of going through the boarders of a map.
 * i.e. going through right boarder to apear on the left boarder.
 * Thus it never does it! it will chose to move in any other direction.
 *
 * It is terrified of the Aggressive Critter so it will always look
 * before it moves to see if there is an aggressive critter.
 *
 * FIGHTING
 * Scared critter never fights.
 * It always runs away, as it finds this
 * to be the best way of avoiding getting hurt
 *
 * REPRODUCE
 * It will only reproduce if
 * it didn't fight for last two worldSteps.
 *
 */
public class Critter3 extends Critter {

	private int turnsSinceFight;
	private int shouldIMove;
	private int dir;

    @Override
    public String toString() {
        return "3";
    }

	public Critter3() {
		turnsSinceFight = 0;
		this.setHaveWalked(false);
	}

    @Override
    public String displayCharacter(){
    	String str = "************ THIS IS SCARED CRITTER ************ \n"
    			+ "\n"
    			+ "MOVEMENT \n"
    			+ "Scared critter often gets so scared that it panics \n"
    			+ "and doesn't not do anything. \n"
    			+ "In fact it is this scared 20% of the times. \n"
    			+ "\n"
    			+ "It also is scared of going through the boarders of a map. \n"
    			+ "i.e. going through right boarder to apear on the left \n"
    			+ "boarder. Thus it never does it! it will chose to move \n"
    			+ "in any other direction. \n"
    			+ "\n"
    			+ "It is terrified of the Aggressive Critter so it \n"
    			+ "will always look before it moves to see if there \n"
    			+ "is an aggressive critter.\n"
    			+ "\n"
    			+ "\n"
    			+ "FIGHTING \n"
    			+ "Scared critter never fights. \n"
    			+ "It always runs away, as it finds this \n"
    			+ "to be the best way of avoiding getting hurt \n"
    			+ " \n"
    			+ "REPRODUCE \n"
    			+ "It will only reproduce if \n"
    			+ "it didn't fight for last two worldSteps. \n";
    	return str;
    }

	@Override
	public void doTimeStep() {
		shouldIMove = Critter.getRandomInt(5);//generates between 0 and 5

		if(shouldIMove == 0) { //too scared to do anything
			turnsSinceFight++;
			this.setHaveWalked(false);
			return;
		}
		else {
			dir = Critter.getRandomInt(8);
			isMoveValid(dir,this.getX_coord(this),this.getY_coord(this));

			if(look(dir,false) != "1") {// Check to see if there is an aggressive critter at the location it wants to move to
				walk(dir);
				this.setHaveWalked(true);  // variable that ensures that critter doesn't walk twice in a timeStep
			}

			if(turnsSinceFight == 3) {//condition to reproduce
				Critter3 child = new Critter3();
				reproduce(child,getRandomInt(8));
				turnsSinceFight = 0;
			}
			turnsSinceFight++;
		}
	}

	private void isMoveValid(int dir, int x, int y) {
		if(y == 0 && (dir == 1 || dir == 2 || dir ==3)) { //if on top border and wants to move in up direction
			this.dir = Critter.getRandomInt(8); //choose a new direction and check validity
			isMoveValid(this.dir,x,y);
		}
		if(y == (Params.WORLD_HEIGHT -1) && (dir == 5 || dir == 6 || dir == 7)) {//if on bottom border and wants to move down
			this.dir = Critter.getRandomInt(8);
			isMoveValid(this.dir,x,y);		}
		if(x == 0 && (dir == 3 || dir == 4 || dir == 5)) {//left border and wants to move left
			this.dir = Critter.getRandomInt(8);
			isMoveValid(this.dir,x,y);		}
		if(x == (Params.WORLD_WIDTH - 1) && (dir == 1 || dir == 0 || dir == 7)) {//right border and wants to move right
			this.dir = Critter.getRandomInt(8);
			isMoveValid(this.dir,x,y);		}
		return;
	}

	@Override
	public boolean fight(String oponent) {
		turnsSinceFight = 0;
		dir = Critter.getRandomInt(8);
		isMoveValid(dir,this.getX_coord(this), this.getY_coord(this));
		runInFight(dir);
		return false;
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.DIAMOND;
	}
	 @Override
	 public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.ORANGE;
     }



    public static String runStats(List<Critter> critter) {
        String str = "";
        str += ("there are " + critter.size() + " Critter3 on the map " + "represented by: " + "ORANGE DIAMOND");
        return str;
    }
}

