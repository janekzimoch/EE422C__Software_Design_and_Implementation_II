package assignment4;
/*
 * CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Ryed Ahmed>
 * <ra35335>
 * <16190>
 * <Janek Zimoch>
 * <jsz323>
 * <16190>
 * Slip days used: <0>
 * Spring 2019
 */
import java.util.ArrayList;

public class CritterWorld{
	
	public static ArrayList<Critter>[][] world;
	private static ArrayList<Cell> fightCoords;
	
	/**
	 * Constructor for a World Object.
	 * 2d Array of size world width and world height.
	 * Each element of array is an ArrayList that holds critters
	 * at that position.
	 */
	public CritterWorld() {
		world = (ArrayList<Critter>[][]) new ArrayList[Params.WORLD_WIDTH][Params.WORLD_HEIGHT];
		
		for(int i = 0; i < Params.WORLD_WIDTH; i++) { 
			for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
				world[i][j] = new ArrayList<Critter>(); //initialize an ArrayList of type critter at each grid coordinate
			}
		}	
	 }
	

	/**
	 * Traverses through CritterWorld and returns a
	 * list of all coordinates that are occupied by more than one critter.
	 */
	public static void getFights(){ // get coordinates of all cells occupied by more than one critter
		fightCoords = new ArrayList<Cell>();
		
		for(int i = 0; i < Params.WORLD_WIDTH; i++) {
			for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
				if(world[i][j].size() > 1) { // array list contains more than one critter
					fightCoords.add(new Cell(i,j));
				}
			}
		}

	}
	
	public static ArrayList<Critter>[][] getWorld(){
		return world;
	}
	
	/**
	 * Adds critter to world at location of its coordinates.
	 * @param critter
	 */
	public static void add(Critter critter) {
		world[critter.getX_coord(critter)][critter.getY_coord(critter)].add(critter);
	}
	

	/**
	 * called from doWorldStep() so after each critter has performed
	 * it's own time step.
	 * Supposed to cause action between all critters occupying same coordinates.
	 */
	public static void doEncounters() {
		for(int i = 0; i < Params.WORLD_WIDTH; i++) {
			for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
				int critters_in_cell = world[i][j].size();
				while(critters_in_cell > 1) { // array list contains more than one critter
					
					// take two critters in a field at random to fight
					int rand_fighter_a = Critter.getRandomInt(critters_in_cell);
					int rand_fighter_b = Critter.getRandomInt(critters_in_cell);
					while(rand_fighter_b == rand_fighter_a) {
						rand_fighter_b = Critter.getRandomInt(critters_in_cell);
					}
					Critter a = world[i][j].get(rand_fighter_a);
					Critter b = world[i][j].get(rand_fighter_b);
					int roll_A = 0;
					int roll_B = 0;
					boolean aFight = a.fight(b.toString());
					boolean bFight = b.fight(a.toString());
					
					// if a wants to fight, is alive and in same location as b
					if(aFight && a.getEnergy()> 0 && a.getX_coord(a) == b.getX_coord(b) && a.getY_coord(a) == b.getY_coord(b)) {
						roll_A = Critter.getRandomInt(a.getEnergy());
					}
					// if b wants to fight, is alive, and in same location as b
					if(bFight && b.getEnergy()> 0 && b.getX_coord(b) == b.getX_coord(b) && a.getY_coord(a) == b.getY_coord(b)) {
						roll_B = Critter.getRandomInt(b.getEnergy());
					}				

					
					// distribute energy post fight
					if(a.getX_coord(a) == b.getX_coord(b) && a.getY_coord(a) == b.getY_coord(b)) {
						
						int rand = Critter.getRandomInt(2);  // randomise which critter dies when rolls are equal
						if(roll_A > roll_B || (roll_A == roll_B && rand == 0)) {
							int a_new_energy = a.getEnergy() + (b.getEnergy() / 2);
							b.setEnergy(0);
							world[i][j].remove(b);
							a.setEnergy(a_new_energy);
						}
						else if(roll_A < roll_B || (roll_A == roll_B && rand ==1)) {
							int b_new_energy = b.getEnergy() + (a.getEnergy() / 2);
							a.setEnergy(0);
							world[i][j].remove(a);
							b.setEnergy(b_new_energy);
						}
					}
					critters_in_cell = world[i][j].size();
				}
			}
		}
	}
	
	/**
	 * Traverses through all critters in world and removes
	 * any critters with energy less than or equal to 0.
	 */
	public static void clearDead() {
		for(int i = 0; i < Params.WORLD_WIDTH; i++) {//check each cell
			for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
				if(world[i][j].size() > 0) {//if cell has critter objects in it
					for(int x = 0; x < world[i][j].size(); x++) {
						if(world[i][j].get(x).getEnergy() <= 0) {//if energy is 0, remove from list
							world[i][j].remove(x);
							x--;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Called at the end of each worldTimeStep.
	 * Adds CloverCount amount of clovers randomly accross world.
	 */
	public static void addClovers() {
		for(int i=0; i < Params.REFRESH_CLOVER_COUNT; i++) {
			try {
				Critter.createCritter("Clover");
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Called at the end of worldTimeStep.
	 * Adds all newly born babies into the world.
	 */
	public static void addBabies() {
		for(Critter temp: Critter.getBabiess()) {//add each baby into world
			CritterWorld.add(temp);
		}
		Critter.getBabiess().clear();//clear list
	}

}
