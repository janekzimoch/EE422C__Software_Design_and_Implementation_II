package assignment5;
/*
 * CRITTERS Main.java
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
import java.util.List;

//************ THIS IS AGGRESIVE CRITTER ************


/*

 *
 */

public class Critter1 extends Critter {

	private int rageLevel;
	private int fightProb;

    @Override
    public String toString() {
        return "1";
    }

    public Critter1() {
    	rageLevel = Critter.getRandomInt(5) + 1;// generate number between 1 and 5
    }

    @Override
    public String displayCharacter(){
    	String str = "************ THIS IS AGGRESIVE CRITTER ************ \n"
    			+ "\n"
    			+ "MOVEMENT \n"
    			+ "Aggressive critter wants to be the top predator in the \n"
    			+ "world. It runs everywhere - which quickly depletes its \n"
    			+ "energy. \n"
    			+ "\n"
    			+ "FIGHTING \n"
    			+ "Fights depending on its rage level. If it has rage level of \n"
    			+ "5 (max), it fights \n"
    			+ "all the time. If rage level 4, it fights 80% of the time. \n"
    			+ "3: 60% and so on. \n"
    			+ "Even if it chooses not to fight, it never runs away. \n"
    			+ "\n"
    			+ "REPRODUCE \n"
    			+ "Passes rage level onto child. \n"
    			+ "The aggresive critter gets most excited when it fights. \n"
    			+ "Therefore, it reproduced every time it fights. \n";
    	return str;
    }

	@Override
	public void doTimeStep() {
		run(Critter.getRandomInt(8));

	}

	/**
	 * Fights depending on its rage level.
	 * param: oponent
	 */
	@Override
	public boolean fight(String oponent) {
		Critter1 child = new Critter1();
		child.rageLevel = this.rageLevel;

		if(rageLevel == 5) {//fights 100 % of time
			reproduce(child,Critter.getRandomInt(8));
			return true;
		}
		else if(rageLevel == 4) {//fights 80% of time
			fightProb = Critter.getRandomInt(10);
			if(fightProb < 8) {
				reproduce(child,Critter.getRandomInt(8));
				return true;
			}
			else {
				return false;
			}
		}
		else if(rageLevel == 3) {//fights 80% of time
			fightProb = Critter.getRandomInt(10);
			if(fightProb < 6) {
				reproduce(child,Critter.getRandomInt(8));
				return true;
			}
			else {
				return false;
			}
		}
		else if(rageLevel == 2) {//fights 80% of time
			fightProb = Critter.getRandomInt(10);
			if(fightProb < 4) {
				reproduce(child,Critter.getRandomInt(8));
				return true;
			}
			else {
				return false;
			}
		}
		else{//fights 80% of time
			fightProb = Critter.getRandomInt(10);
			if(fightProb < 2) {
				reproduce(child,Critter.getRandomInt(8));
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.SQUARE;
	}
	 @Override
	 public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.RED;
     }



    public static String runStats(List<Critter> critter) {
        String str = "";
        str += ("there are " + critter.size() + " Critter1 on the map " + "represented by: " + "RED SQUARE");
        return str;
    }
}
