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

/* abstract class which allows to see which critters are in directory,
 * and it stores information about them.
 */

public abstract class LookCritters {

    /* return all the critter types available for this game - i.e. search through directory for available critter classes */
//    public static List<String> getCrittersList(){
//    	List<String> l = new ArrayList<String>();
//    	l.add("sss");
//    	//System.out.println(Main.critters.toString());
//    	//TODO: implement functionality that goes through the directory to check for available critter names
//    	return l;
//    }

    public static ArrayList<String> getCrittersList(){
    	ArrayList<String> list_of_critters = new ArrayList<String>();

    	for(String critter : Main.critters){
    		list_of_critters.add(critter);
    	}

    	return list_of_critters;
    }
}
