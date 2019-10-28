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

import assignment5.Critter.CritterShape;
import javafx.event.*;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Grid extends Parent {
	private VBox rows;
	public static int cellSize;

	public static void setSize() {
		//int a = (int) WorldDisplay.worldWindow.getWidth() / Params.WORLD_WIDTH;
		//int b = (int) WorldDisplay.worldWindow.getHeight() / Params.WORLD_HEIGHT;
		int a  = 1200 / Params.WORLD_WIDTH;
		int b = 700 / Params.WORLD_HEIGHT;
		if(a < b) { cellSize = a;}
		else { cellSize = b;}
	}
	
    public Grid() {
    	int num_rows = Params.WORLD_HEIGHT;
    	int num_columns = Params.WORLD_WIDTH;
    	rows = new VBox();
    	setSize();
        for (int y = 0; y < num_rows; y++) {
            HBox row = new HBox();
            for (int x = 0; x < num_columns; x++) {
	            if(CritterWorld.world[x][y].size() == 0) {
	            	Cell c = new Cell(x, y, this);
		            //c.setOnMouseClicked(handler);
		            row.getChildren().add(c);
	            }
	            else{
	            	Critter.CritterShape shape = CritterWorld.world[x][y].get(0).viewShape();
	            	javafx.scene.paint.Color col = CritterWorld.world[x][y].get(0).viewColor();
            		Cell c = new Cell(x,y,this);

	            	if(shape == CritterShape.CIRCLE) {
	            		Circle cr = new Circle(x,(y+1),((cellSize)/2));
	            		cr.setFill(col);
	            		cr.setStroke(Color.BLACK);
	            		StackPane pane = new StackPane(c,cr);
	            		row.getChildren().add(pane);
	            	}
	            	
	            	else if(shape == CritterShape.SQUARE) {
	            		c.setFill(col);
	            		c.setStroke(Color.BLACK);
	            		row.getChildren().add(c);	            	}
	            	else if(shape == CritterShape.TRIANGLE) {
	            		Polygon tr = createTriangle(x,y,(cellSize-2));
	            		tr.setFill(col);
	            		tr.setStroke(Color.BLACK);
	            		StackPane pane = new StackPane(c,tr);
	            		row.getChildren().add(pane);	            	}
	            	else if(shape == CritterShape.DIAMOND) {
	            		Polygon dm = createDiamod(x,y,(cellSize - 2));
	            		dm.setFill(col);
	            		dm.setStroke(col);
	            		StackPane pane = new StackPane(c,dm);
	            		row.getChildren().add(pane);	            	}
	            	else if (shape == CritterShape.STAR) {
	            		Polygon sr = createStar(x,y,(cellSize-5));
	            		sr.setFill(col);
	            		sr.setStroke(col);
	            		StackPane pane = new StackPane(c,sr);
	            		row.getChildren().add(pane);	            	}
	            		
	            	}
	            
            	}
	            
            rows.getChildren().add(row);
            }
            
        this.getChildren().add(rows);
    }

    public class Cell extends Rectangle {
        public int x, y;

        private Grid grid;

        public Cell(int x, int y, Grid grid) {
            super(cellSize, cellSize);
            this.x = x;
            this.y = y;
            this.grid = grid;
            setFill(Color.LIGHTGRAY);
            setStroke(Color.BLACK);
        }
    }
    
    public static Polygon createTriangle(double x, double y, double side) {
        Polygon triangle = new Polygon();
        triangle.setLayoutX((x));
        triangle.setLayoutY((y));
        triangle.getPoints().addAll( 0.0, side, (side / 2), 0.0, side, side);
        return triangle;
    }
    
    public static Polygon createDiamod(double x, double y, double side) {
    	Polygon diamond = new Polygon();
    	diamond.setLayoutX(x);
    	diamond.setLayoutY(y);
    	diamond.getPoints().addAll(
    			0.0 , (side / 2), 
    			(side/2) , 0.0, 
    			side, (side / 2), 
    			(side /2 ), side);
    	return diamond;
    }
    
    public static Polygon createStar(double x, double y, double s) {
    	Polygon star = new Polygon();
    	star.setLayoutX(x);
    	star.setLayoutY(y);
    	star.getPoints().setAll(
    			(s/2),0.0, (2*s/3),(s/3), s,(s/3),
    			(3*s/4),(s/2), s, s,
    			(s/2), (2*s/3), 0.0, s,
    			(s/4),(s/2), 0.0, (s/3), (s/3), (s/3));
    			
    	return star;
    }
}
