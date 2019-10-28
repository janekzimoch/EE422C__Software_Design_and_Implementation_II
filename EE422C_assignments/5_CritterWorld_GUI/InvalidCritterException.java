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

public class InvalidCritterException extends Exception {

    String offending_class;

    public InvalidCritterException(String critter_class_name) {
        offending_class = critter_class_name;
    }

    public String toString() {
        return "Invalid Critter Class: " + offending_class;
    }
}
