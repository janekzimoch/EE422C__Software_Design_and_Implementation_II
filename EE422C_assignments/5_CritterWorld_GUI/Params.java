/*
 * Do not submit this file.
 *
 * Change these parameter values for testing either with new
 * hard-coded values or by changing them from within your testing
 * code.  For 'normal' operation, these should not be changed by your
 * own Critters, Main, or Critter class.
 *
 * Do not add or remove any parameters in this file.
 */

package assignment5;

public class Params {

    public static int WORLD_WIDTH;
    public static int WORLD_HEIGHT;
    public static int WALK_ENERGY_COST;
    public static int RUN_ENERGY_COST;
    public static int REST_ENERGY_COST;
    public static int MIN_REPRODUCE_ENERGY;
    public static int REFRESH_CLOVER_COUNT;
    public static int PHOTOSYNTHESIS_ENERGY_AMOUNT;
    public static int START_ENERGY;
    public static int LOOK_ENERGY_COST;
    
    public static void initialiseParams(){
        WORLD_WIDTH = 80;
        WORLD_HEIGHT = 40;
        WALK_ENERGY_COST = 2;
        RUN_ENERGY_COST = 5;
        REST_ENERGY_COST = 1;
        MIN_REPRODUCE_ENERGY = 20;
        REFRESH_CLOVER_COUNT = 10;
        PHOTOSYNTHESIS_ENERGY_AMOUNT = 1;
        START_ENERGY = 100;
    }
}
