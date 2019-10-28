package assignment4;

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


//************ THIS IS SCARED CRITTER ************


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
	public void doTimeStep() {
		shouldIMove = Critter.getRandomInt(5);//generates between 0 and 5
		
		if(shouldIMove != 0) { //too scared to do anything
			turnsSinceFight++;
			this.setHaveWalked(false);
			return;
		}
		else {
			dir = Critter.getRandomInt(8);
			isMoveValid(dir,this.getX_coord(this),this.getY_coord(this)); 
			walk(dir);
			this.setHaveWalked(true);  // variable that ensures that critter doesn't walk twice in a timeStep
			
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
			isMoveValid(this.dir,x,y);
		}
		if(x == 0 && (dir == 3 || dir == 4 || dir == 5)) {//left border and wants to move left
			this.dir = Critter.getRandomInt(8);
			isMoveValid(this.dir,x,y);
		}
		if(x == (Params.WORLD_WIDTH - 1) && (dir == 1 || dir == 0 || dir == 7)) {//right border and wants to move right
			this.dir = Critter.getRandomInt(8);
			isMoveValid(this.dir,x,y);
		}
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
}

