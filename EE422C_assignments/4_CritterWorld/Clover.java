/*
 * Do not change or submit this file.
 */
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
package assignment4;

import assignment4.Critter.TestCritter;

public class Clover extends TestCritter {

    public String toString() {
        return "@";
    }

    public boolean fight(String not_used) {
        return false;
    }

    public void doTimeStep() {
        setEnergy(getEnergy() + Params.PHOTOSYNTHESIS_ENERGY_AMOUNT);
    }
}
