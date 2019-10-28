
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


// ************ THIS IS WISE CRITTER ************


/* 
* Wise Critter is smart.
* In fact it is the most smart critter in the world.
* 
* MOVEMENT
* Wise critter know that running is exhausting
* So it never runs. In the world where everything is random
* there is not point of running.
* Wise critter knows it so it always walks.
* 
* Moreover it is a curious critter too. 
* It enjoys exploring the World.
* It has as its maxim:
* "to always be exploring new grid-cells"
* So unless it has no other options it will always
* choose to move onto an unexplored cell rather than 
* go onto already visited cell.
* 
* FIGHTING
* Wise critter never attacks critters of its own kind,
* as this is clearly unwise. It chooses to just walk aside.
* Moreover it can communicate with other
* Wise critters through a global variable where it has moved. 
* Thus two Wise critters who occupied the same field will 
* never run away into a same field.
* 
* Wise critters will fight any other critter as an act 
* of defense of its own kind. 
* 
* REPRODUCE
* Wise critter wants to maximise likelyhood of survival of its offsprings.
* Therefore it will only produce offsprings when it knows
* that they will be born strong. 
* 
* */

public class Critter4 extends Critter{
	
	private int x;
	private int y;
	private int coordinate;
	private int[][] visited_fields;
	private int dir;
	public static int friendly_direction = -1;
    
	@Override
    public String toString() {
        return "4";
    }
    
    public Critter4() {
    	visited_fields = new int[Params.WORLD_WIDTH][Params.WORLD_HEIGHT];
    }
 

    private void doMovement() {
    	
		int x = this.getX_coord(this);
		int y = this.getY_coord(this);
		
		int number_of_explorations = 2;  // Wise is surrounded by some fields that it has already visited. If in 2 trials it doesn't get a field it hasn't visited it doesn't move in this turn.
    	
		for(int i = 0; i < number_of_explorations; i++) {
			
    		dir = Critter.getRandomInt(8);
			walk(dir);
			if(visited_fields[this.getX_coord(this)][this.getY_coord(this)] != 0) {
				CritterWorld.world[this.getX_coord(this)][this.getY_coord(this)].remove(this);
				this.setX_coord_(x);
				this.setY_coord_(y);
				CritterWorld.world[x][y].add(this);
				int new_energy = this.getEnergy() - Params.RUN_ENERGY_COST;
				this.setEnergy(new_energy);
			}
			else {  // you haven't visited - you change location
				visited_fields[this.getX_coord(this)][this.getY_coord(this)] = 1;
				break;
			}
    	}
    }
    
    
	@Override
	public void doTimeStep() {
		dir = Critter.getRandomInt(8);
    	doMovement();
    	
    	if (getEnergy() > 180) {
            Critter4 child = new Critter4();
            reproduce(child, Critter.getRandomInt(8));
        }
	}

	@Override
	public boolean fight(String oponent) {
		if(oponent.contentEquals("W")) {
			if(friendly_direction == -1) {
				friendly_direction = Critter.getRandomInt(8); 
				walkInFight(friendly_direction);
			}
			else { // the other Wise critter moved first 
				dir = Critter.getRandomInt(8);
				while(dir == friendly_direction) {
					dir = Critter.getRandomInt(8);
				}
				walkInFight(dir);
			}
			return false;
		}
		else{
			return true;
		}
	}
}