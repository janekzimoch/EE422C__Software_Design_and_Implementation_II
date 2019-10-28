/*
 * CRITTERS Critter.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Spring 2019
 */

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

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.javafx.tk.Toolkit.Task;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/*
 * See the PDF for descriptions of the methods and fields in this
 * class.
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter  {

    /* START --- NEW FOR PROJECT 5 */
    public enum CritterShape {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        DIAMOND,
        STAR
    }

    public static boolean startGrid = false;
    public static boolean completed;
    public static Parent newDisp;

    /* the default color is white, which I hope makes critters invisible by default
     * If you change the background color of your View component, then update the default
     * color to be the same as you background
     *
     * critters must override at least one of the following three methods, it is not
     * proper for critters to remain invisible in the view
     *
     * If a critter only overrides the outline color, then it will look like a non-filled
     * shape, at least, that's the intent. You can edit these default methods however you
     * need to, but please preserve that intent as you implement them.
     */
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.LIGHTGREY;
    }

    public javafx.scene.paint.Color viewOutlineColor() {
        return viewColor();
    }

    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }

    public abstract CritterShape viewShape();

    /**
     * If steps is false, looks one location.
     * If steps is true, looks two locations.
     * Returns null if no critter at location.
     * Returns toString method of critter if location is occupied.
     * @param direction
     * @param steps
     * @return
     */
    protected final String look(int direction, boolean steps) {
    	this.energy = this.energy - Params.LOOK_ENERGY_COST;

    	int lookX = this.getX_coord(this);
    	int lookY = this.getY_coord(this);
    	int numSteps;

    	if(steps == false) {numSteps = 1;} // How many steps to look
    	else { numSteps = 2;}

    	for(int i = 0; i < numSteps; i++) {
    	//calculate coordinates of location to look at
	    	if(direction == 0) { // look directly to right
	    		if(lookX == (Params.WORLD_WIDTH - 1)) {//if on right most edge
	    			lookX = 0;
	    		}
	    		else {
	    			lookX = lookX + 1;
	    		}
	    	}
	    	else if(direction == 1) { // move up and right
	    		if(lookX == (Params.WORLD_WIDTH - 1) && lookY == 0) {//top right corner
	    			lookX = 0;
	    			lookY = Params.WORLD_HEIGHT - 1;
	    		}
	    		else if(lookX == (Params.WORLD_WIDTH - 1)) {// if on right most edge
	    			lookX = 0;
	    			lookY = lookY - 1;
	    		}
	    		else if(lookY == 0) {
	    			lookX = lookX + 1;
	    			lookY = Params.WORLD_HEIGHT - 1;
	    		}
	    		else {
	    			lookX = lookX + 1;
	    			lookY = lookY - 1;
	    		}
	    	}
	    	else if (direction == 2) { // move up
	    		if(lookY == 0) {
	    			lookY = Params.WORLD_HEIGHT - 1;
	    		}
	    		else {
	    			lookY = lookY - 1;
	    		}
	    	}
	    	else if(direction == 3) { // move up and left
	    		if(lookX == 0 && lookY ==0 ) {//top left corner
	    			lookX = Params.WORLD_WIDTH - 1;
	    			lookY = Params.WORLD_HEIGHT - 1;
	    		}
	    		else if(lookX == 0) {// if on left most edge
	    			lookX = Params.WORLD_WIDTH - 1;
	    			lookY = lookY - 1;
	    		}
	    		else if(lookY == 0) {
	    			lookX = lookX - 1;
	    			lookY = Params.WORLD_HEIGHT - 1;
	    		}
	    		else {
	    			lookX = lookX - 1;
	    			lookY = lookY - 1;
	    		}
	    	}
	    	else if(direction == 4) { // move directly to left
	    		if(lookX == 0) {//if on left most edge
	    			lookX = Params.WORLD_WIDTH - 1;
	    		}
	    		else {
	    			lookX = lookX - 1;
	    		}
	    	}
	    	else if(direction == 5) { // move down and left
	    		if(lookX == 0 && lookY == (Params.WORLD_HEIGHT - 1)) {//bottom left corner
	    			lookX = Params.WORLD_WIDTH - 1;
	    			lookY = 0;
	    		}
	    		else if(lookX == 0) {// if on left most edge
	    			lookX = Params.WORLD_WIDTH - 1;
	    			lookY = lookY + 1;
	    		}
	    		else if (lookY == (Params.WORLD_HEIGHT - 1)) {
	    			lookX = lookX - 1;
	    			lookY = 0;
	    		}
	    		else {
	    			lookX = lookX - 1;
	    			lookY = lookY + 1;
	    		}
	    	}
	    	else if (direction == 6) { // move down
	    		if(lookY == (Params.WORLD_HEIGHT - 1)) {
	    			lookY = 0;
	    		}
	    		else {
	    			lookY = lookY + 1;
	    		}
	    	}
	    	else if(direction == 7) { // move down and right
	    		if(lookX == (Params.WORLD_WIDTH - 1) && lookY == (Params.WORLD_HEIGHT - 1)) {//top left corner
	    			lookX = 0;
	    			lookY = 0;
	    		}
	    		else if(lookX == (Params.WORLD_WIDTH - 1)) {// if on right most edge
	    			lookX = 0;
	    			lookY = lookY + 1;
	    		}
	    		else if (lookY == (Params.WORLD_HEIGHT - 1)) {// if on bottom edge
	    			lookX = lookX + 1;
	    			lookY = 0;
	    		}
	    		else {
	    			lookX = lookX + 1;
	    			lookY = lookY + 1;
	    		}
	    	}
    	}

    	if(CritterWorld.world[lookX][lookY].size() == 0) { // no critters at that coord
    		return null;
    	}
    	else {//occupied coord
    		if(this instanceof Critter3) {//look called from doTimeStep
	    		for(int i = 0; i < CritterWorld.world[lookX][lookY].size(); i++) {
	    			if(CritterWorld.world[lookX][lookY].get(i).timeStepCompleted == false) {
	    				return CritterWorld.world[lookX][lookY].get(i).toString();
	    			}

	    		}
    		}
    		else {//look called from fight method
    			return CritterWorld.world[lookX][lookY].get(0).toString();
    		}
    	}
    	return null;
    }



    public static void displayWorld(Object pane) {

    	WorldDisplay.container.getChildren().clear();
    	newDisp = WorldDisplay.createGrid();
    	WorldDisplay.scene.setRoot(newDisp);
		WorldDisplay.worldWindow.setTitle(String.format("world display - step %d", Main.number_of_steps));
    	((Stage) pane).setScene(WorldDisplay.scene);
    	((Stage) pane).show();

    }


    private int energy = 0;
    private boolean timeStepCompleted = false;

    private int x_coord;
    private int y_coord;
    private boolean haveWalked;

    private static List<Critter> population = new ArrayList<Critter>();
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static Random rand = new Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static String getMyPackage() {
    	return myPackage;
    }

    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
    	System.out.println("set_seed called" + Long.toString(new_seed));   // TO BE DELETED
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the qualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)  //new instance() java docs
            throws InvalidCritterException {
    	Critter critter = getCritterFromString(myPackage + "." + critter_class_name);

    	// initialise critter
    	critter.x_coord = Critter.getRandomInt(Params.WORLD_WIDTH);
		critter.y_coord = Critter.getRandomInt(Params.WORLD_HEIGHT);
		critter.energy = Params.START_ENERGY;

    	CritterWorld.add(critter); // add to CritterWorld arrayList;
    }

    /* way to access private x & y coordinates of a critter */
    public int getX_coord(Critter critter) {
    	return critter.x_coord;
    }

    public int getY_coord(Critter critter) {
    	return critter.y_coord;
    }

    public boolean getHaveWalked() {
    	return this.haveWalked;
    }

    public void setHaveWalked(boolean input) {
    	this.haveWalked = input;
    }

    public void setX_coord_(int x) {
    	this.x_coord = x;
    }

    public void setY_coord_(int y) {
    	this.y_coord = y;
    }


    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *        Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
    	List<Critter> list_of_critters = new ArrayList<Critter>();
		for(int i = 0; i < Params.WORLD_WIDTH; i++) {
			for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
				for(Critter critter : CritterWorld.world[i][j]) {
					if(critter.getClass().getName().equals(myPackage + '.' + critter_class_name)) {
						list_of_critters.add(critter);
					}
				}
			}
		}
        return list_of_critters;
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
    	CritterWorld critter_world = new CritterWorld();
    	Main.number_of_steps = 0;
    }

    /**
     * Perform a worldTimeStep.
     * Sequence:
     * 		doTimeStep for each critter in world
     * 		doEncounters for all cells occupied with more than one critter
     * 		Subtract parameter rest energy from all critters
     * 		Clear all dead critters from world.
     * 		Add all babies to world
     */
    public static void worldTimeStep() {
    	for(int i = 0; i < Params.WORLD_WIDTH; i++) {
    		for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
    			if(CritterWorld.world[i][j].size() != 0) {
    				for(int x = 0; x < CritterWorld.world[i][j].size(); x++) {
    					if(!(CritterWorld.world[i][j].get(x).timeStepCompleted) ) {
    						Critter temp = CritterWorld.world[i][j].get(x);
    						//so multiple time steps aren't completed on same critter
    						CritterWorld.world[i][j].get(x).timeStepCompleted = true;
    						CritterWorld.world[i][j].get(x).doTimeStep();
    						if(temp.getEnergy() <= 0) { // If critter dies during timeStep
    							CritterWorld.world[temp.getX_coord(temp)][temp.getY_coord(temp)].remove(temp);
    						}
    					}
    				}
    			}
			}
    	}

    	for(int i = 0; i < Params.WORLD_WIDTH; i++) { // reset all boolean variables to false
    		for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
    			for(int x = 0; x < CritterWorld.world[i][j].size(); x++) {
    				(CritterWorld.world[i][j].get(x)).timeStepCompleted = false;
				}
			}
    	}
    	Main.number_of_steps++;
      	CritterWorld.doEncounters();
    	CritterWorld.clearDead();
    	subtractRestEnergy();
    	CritterWorld.addClovers();
    	CritterWorld.addBabies();
    }

    /**
     * Go through all populated critters and remove
     * the restEnergy from total energy.
     */
    public static void subtractRestEnergy() {
    	for(int i = 0; i < Params.WORLD_WIDTH; i++) {
    		for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
    			for(int x = 0; x < CritterWorld.world[i][j].size(); x++) {
    				CritterWorld.world[i][j].get(x).energy = CritterWorld.world[i][j].get(x).energy - Params.REST_ENERGY_COST;
				}
			}
    	}
    }

    /**
     * Displays the world grid.
     * Grid border consists of '+' in each corner, '-' along top borders,
     * and '|' along vertical borders.
     * Shows only the critter at the top of the arrayList.
     * Invokes each critters toString() method to display them.
     */
    public static void displayWorld() {
        String[][] worldDisplay = new String[(Params.WORLD_WIDTH + 2)][(Params.WORLD_HEIGHT + 2)];

        worldDisplay[0][0] = "+";
        worldDisplay[(Params.WORLD_WIDTH + 1)][0] = "+";
        worldDisplay[0][(Params.WORLD_HEIGHT + 1)] = "+";
        worldDisplay[(Params.WORLD_WIDTH+1)][(Params.WORLD_HEIGHT+1)] = "+";

        for(int i = 1; i < (Params.WORLD_WIDTH + 1); i++){//top and bottom border
        	worldDisplay[i][0] = "-";
        	worldDisplay[i][(Params.WORLD_HEIGHT+1)] = "-";
        }

        for(int i = 1; i < (Params.WORLD_HEIGHT+1); i++) {
        	worldDisplay[0][i] = "|";
        	worldDisplay[(Params.WORLD_WIDTH+1)][i] = "|";
        }

        for(int i = 0; i < Params.WORLD_WIDTH; i++) {
			for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
				if(CritterWorld.world[i][j].size() > 0) { // array list contains more than one critter
					worldDisplay[i+1][j+1] = CritterWorld.world[i][j].get(0).toString();
				}
			}
		}

        for(int i = 0; i < (Params.WORLD_HEIGHT+2); i++) {
        	for(int j = 0; j < (Params.WORLD_WIDTH + 2); j++) {
        		if(worldDisplay[j][i] == null) {
        			System.out.print(" ");
        		}
        		else {
        		      	System.out.print(worldDisplay[j][i]);
        		}
        	}
        	System.out.println();
        }
    }

    /**
     * Prints out how many Critters of each type there are on the
     * board.
     *
     * @param critters List of Critters.
     */
    public static String runStats(List<Critter> critters) {
    	// TODO: implement
    	return "";
    }

	/**
	 * determine whether critter is allowed to run in it's fight method or not.
	 * If it had already ran in its timeStep method, it can not run during a fight.
	 * @param dir
	 */
	public void runInFight(int dir) {
		int x_old = x_coord;
		int y_old = y_coord;

		if(haveWalked) {
			int new_energy = energy - Params.RUN_ENERGY_COST;
			this.setEnergy(new_energy);
		}
		else {
			run(dir);
			if(CritterWorld.world[this.x_coord][this.y_coord].size() != 0) {
				CritterWorld.world[x_coord][y_coord].remove(this);
				x_coord = x_old;
				y_coord = y_old;
				CritterWorld.world[x_old][y_old].add(this);
				int new_energy = energy - Params.RUN_ENERGY_COST;
				this.setEnergy(new_energy);
			}
		}
	}


    /**
     * Same as runInFight but walks instead.
     * @param dir
     */
	public void walkInFight(int dir) {
		int x_old = x_coord;
		int y_old = y_coord;

		if(haveWalked) {
			int new_energy = energy - Params.RUN_ENERGY_COST;
			this.setEnergy(new_energy);
		}
		else {
			walk(dir);
			if(CritterWorld.world[x_coord][y_coord].size() != 0) {
				CritterWorld.world[x_coord][y_coord].remove(this);
				x_coord = x_old;
				y_coord = y_old;
				CritterWorld.world[x_old][y_old].add(this);
				int new_energy = energy - Params.RUN_ENERGY_COST;
				this.setEnergy(new_energy);
			}
		}
	}

	public abstract String displayCharacter();

    public abstract void doTimeStep();

    public abstract boolean fight(String oponent);

    /* a one-character long string that visually depicts your critter
     * in the ASCII interface */
    public String toString() {
        return "";
    }

    protected int getEnergy() {
        return energy;
    }

    protected void setEnergy(int new_energy) {
    	this.energy = new_energy;
    }

    /**
     * Method for a critter to move. Subtracts walk energy from total energy.
     * If a critter moves in an upward direction across the top border,
     * it reappears on the bottom of the grid.
     * If a critter moves towards the right across the right border,
     * it reappears on the left of the grid.
     * Same for down and left.
     * @param direction
     */
    protected final void walk(int direction) {
    	this.energy = this.energy - Params.WALK_ENERGY_COST;
    	CritterWorld.world[this.x_coord][this.y_coord].remove(this);

    	if(direction == 0) { // move directly to right
    		if(this.x_coord == (Params.WORLD_WIDTH - 1)) {//if on right most edge
    			this.x_coord = 0;
    		}
    		else {
    			this.x_coord = this.x_coord + 1;
    		}
    	}
    	else if(direction == 1) { // move up and right
    		if(this.x_coord == (Params.WORLD_WIDTH - 1) && this.y_coord == 0) {//top right corner
    			this.x_coord = 0;
    			this.y_coord = Params.WORLD_HEIGHT - 1;
    		}
    		else if(this.x_coord == (Params.WORLD_WIDTH - 1)) {// if on right most edge
    			this.x_coord = 0;
    			this.y_coord--;
    		}
    		else if(this.y_coord == 0) {
    			this.x_coord ++;
    			this.y_coord = Params.WORLD_HEIGHT - 1;
    		}
    		else {
    			this.x_coord++;
    			this.y_coord--;
    		}
    	}
    	else if (direction == 2) { // move up
    		if(this.y_coord == 0) {
    			this.y_coord = Params.WORLD_HEIGHT - 1;
    		}
    		else {
    			this.y_coord--;
    		}
    	}
    	else if(direction == 3) { // move up and left
    		if(this.x_coord == 0 && this.y_coord ==0 ) {//top left corner
    			this.x_coord = Params.WORLD_WIDTH - 1;
    			this.y_coord = Params.WORLD_HEIGHT - 1;
    		}
    		else if(this.x_coord == 0) {// if on left most edge
    			this.x_coord = Params.WORLD_WIDTH - 1;
    			this.y_coord--;
    		}
    		else if(this.y_coord == 0) {
    			this.x_coord--;
    			this.y_coord = Params.WORLD_HEIGHT - 1;
    		}
    		else {
    			this.x_coord--;
    			this.y_coord--;
    		}
    	}
    	else if(direction == 4) { // move directly to left
    		if(this.x_coord == 0) {//if on left most edge
    			this.x_coord = Params.WORLD_WIDTH - 1;
    		}
    		else {
    			this.x_coord--;
    		}
    	}
    	else if(direction == 5) { // move down and left
    		if(this.x_coord == 0 && this.y_coord == (Params.WORLD_HEIGHT - 1)) {//top left corner
    			this.x_coord = Params.WORLD_WIDTH - 1;
    			this.y_coord = 0;
    		}
    		else if(this.x_coord == 0) {// if on left most edge
    			this.x_coord = Params.WORLD_WIDTH - 1;
    			this.y_coord++;
    		}
    		else if (this.y_coord == (Params.WORLD_HEIGHT - 1)) {
    			this.x_coord--;
    			this.y_coord = 0;
    		}
    		else {
    			this.x_coord--;
    			this.y_coord++;
    		}
    	}
    	else if (direction == 6) { // move down
    		if(this.y_coord == (Params.WORLD_HEIGHT - 1)) {
    			this.y_coord = 0;
    		}
    		else {
    			this.y_coord++;
    		}
    	}
    	else if(direction == 7) { // move down and right
    		if(this.x_coord == (Params.WORLD_WIDTH - 1) && this.y_coord == (Params.WORLD_HEIGHT - 1)) {//top left corner
    			this.x_coord = 0;
    			this.y_coord = 0;
    		}
    		else if(this.x_coord == (Params.WORLD_WIDTH - 1)) {// if on right most edge
    			this.x_coord = 0;
    			this.y_coord++;
    		}
    		else if (this.y_coord == (Params.WORLD_HEIGHT - 1)) {// if on bottom edge
    			this.x_coord++;
    			this.y_coord = 0;
    		}
    		else {
    			this.x_coord++;
    			this.y_coord++;
    		}
    	}

    	CritterWorld.world[this.x_coord][this.y_coord].add(this);
    }

    /**
     * Invokes walk method twice.
     * Adds 2*walkEnergy and then subtracts runEnergy.
     * @param direction
     */
    protected final void run(int direction) {
    	this.walk(direction);
    	this.walk(direction);
    	this.energy = this.energy + (2 * Params.WALK_ENERGY_COST);
    	this.energy = this.energy - Params.RUN_ENERGY_COST;

    }

    protected final void reproduce(Critter offspring, int direction) {
    	offspring.x_coord = this.x_coord;
    	offspring.y_coord = this.y_coord;
        if(this.energy < Params.MIN_REPRODUCE_ENERGY) {
        	return;
        }
        else {
        	//calculate energy of offspring and parent
        	if(this.energy % 2 == 1) {// odd level on energy
        		offspring.energy = (this.energy - 1) / 2;
        		this.energy = (this.energy + 1) / 2;

        	}
        	else {
        		offspring.energy = this.energy/2;
        		this.energy = this.energy/2;
        	}

        	//calculate coordinates of offspring
        	if(direction == 0) { // move directly to right
        		if(this.x_coord == (Params.WORLD_WIDTH - 1)) {//if on right most edge
        			offspring.x_coord = 0;
        		}
        		else {
        			offspring.x_coord = this.x_coord + 1;
        		}
        	}
        	else if(direction == 1) { // move up and right
        		if(this.x_coord == (Params.WORLD_WIDTH - 1) && this.y_coord == 0) {//top right corner
        			offspring.x_coord = 0;
        			offspring.y_coord = Params.WORLD_HEIGHT - 1;
        		}
        		else if(this.x_coord == (Params.WORLD_WIDTH - 1)) {// if on right most edge
        			offspring.x_coord = 0;
        			offspring.y_coord = this.y_coord-1;
        		}
        		else if(this.y_coord == 0) {
        			offspring.x_coord = this.x_coord+1;
        			offspring.y_coord = Params.WORLD_HEIGHT - 1;
        		}
        		else {
        			offspring.x_coord = this.x_coord+1;
        			offspring.y_coord = this.y_coord-1;
        		}
        	}
        	else if (direction == 2) { // move up
        		if(this.y_coord == 0) {
        			offspring.y_coord = Params.WORLD_HEIGHT - 1;
        		}
        		else {
        			offspring.y_coord = this.y_coord-1;
        		}
        	}
        	else if(direction == 3) { // move up and left
        		if(this.x_coord == 0 && this.y_coord ==0 ) {//top left corner
        			offspring.x_coord = Params.WORLD_WIDTH - 1;
        			offspring.y_coord = Params.WORLD_HEIGHT - 1;
        		}
        		else if(this.x_coord == 0) {// if on left most edge
        			offspring.x_coord = Params.WORLD_WIDTH - 1;
        			offspring.y_coord = this.y_coord-1;
        		}
        		else if(this.y_coord == 0) {
        			offspring.x_coord = this.x_coord-1;
        			offspring.y_coord = Params.WORLD_HEIGHT - 1;
        		}
        		else {
        			offspring.x_coord = this.x_coord-1;
        			offspring.y_coord = this.y_coord-1;
        		}
        	}
        	else if(direction == 4) { // move directly to left
        		if(this.x_coord == 0) {//if on left most edge
        			offspring.x_coord = Params.WORLD_WIDTH - 1;
        		}
        		else {
        			offspring.x_coord = this.x_coord-1;
        		}
        	}
        	else if(direction == 5) { // move down and left
        		if(this.x_coord == 0 && this.y_coord == (Params.WORLD_HEIGHT - 1)) {//top left corner
        			offspring.x_coord = Params.WORLD_WIDTH - 1;
        			offspring.y_coord = 0;
        		}
        		else if(this.x_coord == 0) {// if on left most edge
        			offspring.x_coord = Params.WORLD_WIDTH - 1;
        			offspring.y_coord = this.y_coord+1;
        		}
        		else if (this.y_coord == (Params.WORLD_HEIGHT - 1)) {
        			offspring.x_coord = this.x_coord-1;
        			offspring.y_coord = 0;
        		}
        		else {
        			offspring.x_coord = this.x_coord-1;
        			offspring.y_coord = this.y_coord+1;
        		}
        	}
        	else if (direction == 6) { // move down
        		if(this.y_coord == (Params.WORLD_HEIGHT - 1)) {
        			offspring.y_coord = 0;
        		}
        		else {
        			offspring.y_coord = this.y_coord+1;
        		}
        	}
        	else if(direction == 7) { // move down and right
        		if(this.x_coord == (Params.WORLD_WIDTH - 1) && this.y_coord == (Params.WORLD_HEIGHT - 1)) {//top left corner
        			offspring.x_coord = 0;
        			offspring.y_coord = 0;
        		}
        		else if(this.x_coord == (Params.WORLD_WIDTH - 1)) {// if on right most edge
        			offspring.x_coord = 0;
        			offspring.y_coord = this.y_coord+1;
        		}
        		else if (this.y_coord == (Params.WORLD_HEIGHT - 1)) {// if on bottom edge
        			offspring.x_coord = this.x_coord+1;
        			offspring.y_coord = 0;
        		}
        		else {
        			offspring.x_coord = this.x_coord+1;
        			offspring.y_coord = this.y_coord+1;
        		}
        	}
        }

        getBabiess().add(offspring);
    }

    /**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
        	CritterWorld.world[getX_coord()][getY_coord()].remove(this);
            super.x_coord = new_x_coord;
            CritterWorld.world[getX_coord()][getY_coord()].add(this);
        }

        protected void setY_coord(int new_y_coord) {
        	CritterWorld.world[getX_coord()][getY_coord()].remove(this);
            super.y_coord = new_y_coord;
            CritterWorld.world[getX_coord()][getY_coord()].add(this);
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
        	List<Critter> list_of_critters = new ArrayList<Critter>();
    		for(int i = 0; i < Params.WORLD_WIDTH; i++) {
    			for(int j = 0; j < Params.WORLD_HEIGHT; j++) {
    				for(Critter critter : CritterWorld.world[i][j]) {
   						list_of_critters.add(critter);
    				}
    			}
    		}
        	return list_of_critters;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }


    /**
     * Converts a String class name into an actual class object
     * @param critter_class_name
     * @return
     * @throws InvalidCritterException
     */
    public static Critter getCritterFromString (String critter_class_name) throws InvalidCritterException {
		Class<?> myCritter = null;
		Constructor<?> constructor = null;
		Object instanceOfMyCritter = null;

		try {
			myCritter = Class.forName(critter_class_name); 	// Class object of specified name
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		try {
			constructor = myCritter.getConstructor();		// No-parameter constructor object
			instanceOfMyCritter = constructor.newInstance();	// Create new object using constructor
		} catch (Exception e) {
			e.printStackTrace();
		}
		Critter me = (Critter)instanceOfMyCritter;		// Cast to Critter
		return me;
	}


    public static List<Critter> getBabiess() {
    	return babies;
    }


	public static void setBabiess(List<Critter> babies) {
		Critter.babies = babies;
	}
}
